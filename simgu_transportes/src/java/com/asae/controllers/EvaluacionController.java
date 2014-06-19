package com.asae.controllers;

import com.asae.entities.Evaluacion;
import com.asae.controllers.util.JsfUtil;
import com.asae.controllers.util.PaginationHelper;
import com.asae.sessionbeans.EvaluacionFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.component.tabview.TabView;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@Named("evaluacionController")
@SessionScoped
public class EvaluacionController implements Serializable {

    private Evaluacion current;
    private DataModel items = null;
    @EJB
    private com.asae.sessionbeans.EvaluacionFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    TabView tabViewMedidas;
    
    public TabView getTabViewMedidas() {
        return tabViewMedidas;
    }
    public void setTabViewMedidas(TabView tabViewMedidas) {
        this.tabViewMedidas = tabViewMedidas;
    }
    
    public EvaluacionController() {
    }

    public Evaluacion getSelected() {
        if (current == null) {
            current = new Evaluacion();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EvaluacionFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Evaluacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "ViewHistorial";
    }

    public String prepareCreate() {
        current = new Evaluacion();
        selectedItemIndex = -1;
        return "resumenEvaluacion";
    }

    public String create() {
        if (this.current.getFechaSigEvaluacion() == null) {
            Date var = new Date();
            var.setTime(var.getTime() + 30L * 24 * 60 * 60 * 1000);
            this.current.setFechaSigEvaluacion(var);
        }
        this.current.setFechaSigEvaluacion(new Date());
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EvaluacionCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Evaluacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EvaluacionUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Evaluacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EvaluacionDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Evaluacion getEvaluacion(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    /*Cargar Resumen*/
    public String resumen() {
        current = new Evaluacion();
        selectedItemIndex = -1;
        //return "CreateEvaluacion";
        return "resumenEvaluacion";
    }
    /**
     * Creacion de graficos de barras
     */
    private BarChartModel barModel;
    private PieChartModel pieModel1;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        ChartSeries boys1 = new ChartSeries();
        ChartSeries boys2 = new ChartSeries();
        ChartSeries boys3 = new ChartSeries();
        ChartSeries boys4 = new ChartSeries();
        ChartSeries boys5 = new ChartSeries();

        boys.setLabel("Peso Total");
        boys1.setLabel("Masa Muscular Total");
        boys2.setLabel("Porcentaje Grasa Total");
        boys3.setLabel("Peso Ideal");
        boys4.setLabel("Masa Muscular Ideal");
        boys5.setLabel("Porcentaje de Grasa Ideal");

        boys.set("2014", 87);
        boys1.set("2014", 110);
        boys2.set("2014", 50);
        boys3.set("2014", 80);
        boys4.set("2014", 90);
        boys5.set("2014", 89);

        model.addSeries(boys);
        model.addSeries(boys1);
        model.addSeries(boys2);
        model.addSeries(boys3);
        model.addSeries(boys4);
        model.addSeries(boys5);
        return model;
    }

    private void createBarModels() {
        createBarModel();
        createPieModel1();
    }

    private void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("Objetivos");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("fecha");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Objetivos");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);

        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }

    /*aquitermina la grafica de barras.*/
    /*Actualizacion de datos desde las entidades*/
    private void actualizar_datos() {
        getSelected().calc_imc();

        getSelected().calc_pesoTotal();
        getSelected().calc_porcGrasaTot();
        getSelected().calc_pesoResidual();
        getSelected().calc_porcPesoResidual();
        getSelected().calc_pesoOseo();
        getSelected().calc_porcPesoOseo();

        getSelected().calc_masaCorpMagra();
        getSelected().calc_masaCorpMagraIdeal();
        getSelected().calc_porcGrasaIdeal();
        getSelected().calc_pesoIdeal();

        getSelected().calc_pesoMusc();
        getSelected().calc_pesoMuscIdeal();
        getSelected().calc_porcPesoMusc();
        getSelected().calc_porcPesoOseoIdeal();
        getSelected().calc_porcPesoMuscIdeal();

        getSelected().calc_relacionCinturaCadera();
        getSelected().calc_sumPliegue();

        getSelected().calc_caloriasDieta();
        getSelected().calc_quemaCalorias();
        getSelected().calc_quemaCaloriasSesion();
        getSelected().calc_rangosFrecuencia();
        getSelected().calc_pesoAnterior();
        getSelected().calc_porcGrasaAnterior();
        getSelected().calc_pesoMuscAnterior();
    }
    /*FIn Actualizacion*/

    @FacesConverter(forClass = Evaluacion.class)
    public static class EvaluacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EvaluacionController controller = (EvaluacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "evaluacionController");
            return controller.getEvaluacion(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Evaluacion) {
                Evaluacion o = (Evaluacion) object;
                return getStringKey(o.getIdEvaluacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Evaluacion.class.getName());
            }
        }

    }

}
