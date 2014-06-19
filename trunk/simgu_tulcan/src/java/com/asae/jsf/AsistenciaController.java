package com.asae.jsf;

import com.asae.entities.Asistencia;
import com.asae.entities.Usuario;
import com.asae.jsf.util.JsfUtil;
import com.asae.jsf.util.PaginationHelper;
import com.asae.sessions.AsistenciaFacade;
import com.asae.sessions.UsuarioFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.primefaces.event.SelectEvent;
import sun.misc.IOUtils;

@Named("asistenciaController")
@SessionScoped
public class AsistenciaController implements Serializable {

    private Asistencia current;
    private DataModel items = null;
    @EJB
    private com.asae.sessions.AsistenciaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private AsistenciaFacade asistenciaFacade;

    // Gestión de Ususarios
    private List<Usuario> listaUsuarios;
    private Usuario[] usuariosSeleccionados;
    private List<Usuario> filteredUsuarios;
    private final static String[] estamentos;
    private Date fechaSeleccionada;

    static {
        estamentos = new String[4];
        estamentos[0] = "Docente";
        estamentos[1] = "Estudiante";
        estamentos[2] = "Funcionario";
        estamentos[3] = "Familiar";
    }

    // Gestión de Reportes
    private String estamento;
    private Date finic;
    private Date ffin;
    private List<Object[]> reporte;
    private List<Object[]> reporteExport;

    // Gestión de Asistencias
    private List<Asistencia> listaAsistencias;
    private Asistencia[] asistenciasSeleccionadas;
    private List<Asistencia> filteredAsistencias;
    private Date fechaFiltro;

    /**
     * Creates a new instance of ControlAsistencia
     */
    public AsistenciaController() {
        this.listaUsuarios = new ArrayList<>();
        this.listaAsistencias = new ArrayList<>();
        this.reporte = new ArrayList<>();
        this.reporteExport = new ArrayList<>();
        this.fechaSeleccionada = new Date();
    }

    public Asistencia getSelected() {
        if (current == null) {
            current = new Asistencia();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AsistenciaFacade getFacade() {
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
        current = (Asistencia) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Asistencia();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AsistenciaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Asistencia) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AsistenciaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Asistencia) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AsistenciaDeleted"));
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

    public Asistencia getAsistencia(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Asistencia.class)
    public static class AsistenciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsistenciaController controller = (AsistenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asistenciaController");
            return controller.getAsistencia(getKey(value));
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
            if (object instanceof Asistencia) {
                Asistencia o = (Asistencia) object;
                return getStringKey(o.getIdAsistencia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Asistencia.class.getName());
            }
        }

    }
    
    public List<Usuario> getListaUsuarios() {
        listaUsuarios = usuarioFacade.findAll();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Asistencia> getListaAsistencias() {
        listaAsistencias = asistenciaFacade.findAllOrderDate();
        return listaAsistencias;
    }

    public void setListaAsistencias(List<Asistencia> listaAsistencias) {
        this.listaAsistencias = listaAsistencias;
    }

    public Usuario[] getUsuariosSeleccionados() {
        return usuariosSeleccionados;
    }

    public void setUsuariosSeleccionados(Usuario[] usuariosSeleccionados) {
        this.usuariosSeleccionados = usuariosSeleccionados;
    }

    public Asistencia[] getAsistenciasSeleccionadas() {
        return asistenciasSeleccionadas;
    }

    public void setAsistenciasSeleccionadas(Asistencia[] asistenciasSeleccionadas) {
        this.asistenciasSeleccionadas = asistenciasSeleccionadas;
    }

    public String getEstamento() {
        return estamento;
    }

    public void setEstamento(String estamento) {
        this.estamento = estamento;
    }

    public Date getFinic() {
        return finic;
    }

    public void setFinic(Date finic) {
        this.finic = finic;
    }

    public Date getFfin() {
        return ffin;
    }

    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }

    public List<Object[]> getReporte() {
        return reporte;
    }

    public void setReporte(List<Object[]> reporte) {
        this.reporte = reporte;
    }

    public List<Object[]> getReporteExport() {
        return reporteExport;
    }

    public void setReporteExport(List<Object[]> reporteExport) {
        this.reporteExport = reporteExport;
    }

    public List<String> getEstamentos() {
        return Arrays.asList(estamentos);
    }

    public List<Usuario> getFilteredUsuarios() {
        return filteredUsuarios;
    }

    public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
        this.filteredUsuarios = filteredUsuarios;
    }

    public List<Asistencia> getFilteredAsistencias() {
        return filteredAsistencias;
    }

    public void setFilteredAsistencias(List<Asistencia> filteredAsistencias) {
        this.filteredAsistencias = filteredAsistencias;
    }

    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public Date getFechaFiltro() {
        return fechaFiltro;
    }

    public void setFechaFiltro(Date fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }

    public void onDateSelect(SelectEvent event) {
        this.fechaSeleccionada = (Date) event.getObject();
    }

    public void onDateFilterSelect(SelectEvent event) {
        this.fechaFiltro = (Date) event.getObject();
    }

    public void registrarAsistencia() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.usuariosSeleccionados.length == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se han seleccionado registros"));
            } else {
                ArrayList<Usuario> atendidosFecha = new ArrayList();
                int numAsistencias = 0;
                for (Usuario usuarioSeleccionado : this.usuariosSeleccionados) {
                    Asistencia objAuxiliar = new Asistencia();
                    objAuxiliar.setIdusuario(usuarioSeleccionado);
                    objAuxiliar.setFecha(this.fechaSeleccionada);
                    if (!this.asistenciaFacade.asistenciaFecha(objAuxiliar, this.fechaSeleccionada)) {
                        this.asistenciaFacade.create(objAuxiliar);
                        numAsistencias++;
                    } else {
                        atendidosFecha.add(usuarioSeleccionado);
                    }
                }
                if (atendidosFecha.isEmpty()) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro de asistencia realizado satisfactoriamente, " + numAsistencias + " asistencias registradas"));
                } else {
                    String noRegistrados = "";
                    for (Usuario usuarioSeleccionado : atendidosFecha) {
                        noRegistrados += "- " + usuarioSeleccionado.getFisrtname() + " " + usuarioSeleccionado.getFirstlastname() + " " + usuarioSeleccionado.getSecondname() + " " + usuarioSeleccionado.getSecondlastname() + "<br/>";
                    }
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Registro de asistencia realizado satisfactoriamente, " + numAsistencias + " asistencias registradas.<br/> Los siguentes usuarios ya han sido registrados para la fecha seleccionada:<br/>" + noRegistrados));
                }
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Fallo interno de la aplicación: " + e.getMessage()));
        }
    }

    public void generarReporte() {
        this.reporte = new ArrayList<>();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.finic != null && this.ffin != null) {
                finic.setHours(0);
                finic.setMinutes(0);
                finic.setSeconds(0);
                ffin.setHours(23);
                ffin.setMinutes(59);
                ffin.setSeconds(59);
                if (this.ffin.before(finic)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La fecha final debe ser mayor a la fecha inicial"));
                } else {
                    this.reporte = this.asistenciaFacade.getAsistenciaEntreFechas(this.estamento, this.finic, this.ffin);
                    this.reporteExport = this.asistenciaFacade.getAsistenciaEntreFechasNoGroup(this.estamento, this.finic, this.ffin);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Generación exitosa, " + this.reporte.size() + " registros obtenidos"));
                }
            } else if (this.finic != null) {
                finic.setHours(0);
                finic.setMinutes(0);
                finic.setSeconds(0);
                this.reporte = this.asistenciaFacade.getAsistenciaEntreFechas(this.estamento, this.finic, this.ffin);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Generación exitosa, " + this.reporte.size() + " registros obtenidos"));
            } else if (this.ffin != null) {
                ffin.setHours(23);
                ffin.setMinutes(59);
                ffin.setSeconds(59);
                this.reporte = this.asistenciaFacade.getAsistenciaEntreFechas(this.estamento, this.finic, this.ffin);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Generación exitosa, " + this.reporte.size() + " registros obtenidos"));
            } else {
                this.reporte = this.asistenciaFacade.getAsistenciaEntreFechas(this.estamento, this.finic, this.ffin);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Generación exitosa, " + this.reporte.size() + " registros obtenidos"));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Fallo interno de la aplicación: " + e.getMessage()));
        }
    }

    public void eliminarAsistencia() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.asistenciasSeleccionadas.length == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se han seleccionado registros"));
            } else {
                int numEliminados = 0;
                for (Asistencia asistenciaSeleccionada : this.asistenciasSeleccionadas) {
                    this.asistenciaFacade.remove(asistenciaSeleccionada);
                    numEliminados++;
                }

                if (fechaFiltro == null) {
                    this.filteredAsistencias = this.asistenciaFacade.findAll();
                } else {
                    this.filteredAsistencias = this.asistenciaFacade.fetchByDate(fechaFiltro);
                }

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Borrado de asistencias realizado satisfactoriamente, " + numEliminados + " asistencias eliminadas"));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Fallo interno de la aplicación: " + e.getMessage()));
        }
    }

    public void filtrarFecha(SelectEvent event) {
        Date fecha = (Date) event.getObject();
        if (fecha != null) {
            this.filteredAsistencias = this.asistenciaFacade.fetchByDate(fecha);
        } else {
            this.filteredAsistencias = this.asistenciaFacade.findAll();
        }
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        wb.removeSheetAt(0);
    }

    public void preProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.createSheet("Reporte");

        // Header 
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                4, //last row  (0-based)
                0, //first column (0-based)
                2 //last column  (0-based)
        ));

        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                4, //last row  (0-based)
                3, //first column (0-based)
                10 //last column  (0-based)
        ));

        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                4, //last row  (0-based)
                11, //first column (0-based)
                30 //last column  (0-based)
        ));

        sheet.addMergedRegion(new CellRangeAddress(
                6, //first row (0-based)
                6, //last row  (0-based)
                0, //first column (0-based)
                30 //last column  (0-based)
        ));

        HSSFRow header = sheet.createRow(0);

        HSSFCell fcell = header.createCell(3);
        fcell.setCellValue("VICERECTORÍA DE CULTURA Y BIENESTAR\nDIVISIÓN DE DEPORTE Y RECREACIÓN");
        HSSFCell fcell2 = header.createCell(11);
        fcell2.setCellValue("FORMATO REGÍSTRO ASISTENCIA A PROGRAMAS");

        HSSFRow header1 = sheet.createRow(6);

        HSSFCell fcell3 = header1.createCell(0);
        Date now = new Date();
        DateFormat df = DateFormat.getDateInstance();
        String s = df.format(now);
        fcell3.setCellValue("INSTRUCTOR:                                                                                                                      PROGRAMA:                                                                                                                            FECHA: " + s);

        // Imagen
        int pictureIdx = 0;
        File image = new File("logo.jpg");
        InputStream is = null;
        try {
            is = new FileInputStream(image);
            //byte[] bytes = IOUtils.toByteArray(is);
            byte[] bytes = IOUtils.readFully(is, pictureIdx, true);
            pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            CreationHelper helper = wb.getCreationHelper();

            // Create the drawing patriarch.  This is the top level container for all shapes. 
            Drawing drawing = sheet.createDrawingPatriarch();

            //add a picture shape
            ClientAnchor anchor = helper.createClientAnchor();
            //set top-left corner of the picture,
            //subsequent call of Picture#resize() will operate relative to it
            anchor.setCol1(1);
            anchor.setRow1(1);
            Picture pict = drawing.createPicture(anchor, pictureIdx);

            //auto-size picture relative to its top-left corner
            pict.resize(0.35);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        } finally {
            // close the streams using close method
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }

//        CellStyle style = wb.createCellStyle();
//        Font font = wb.createFont();
//        font.setColor(HSSFColor.BLACK.index);
//        style.setFont(font);
        //Cabeceras
        String[] columnNames = {"No", "NOMBRES Y APELLIDOS", "SEXO", "CÓDIGO", "PROGRAMA ACADÉMICO", "PROGRAMA", "ESTAMENTO"};
        HSSFRow header2 = sheet.createRow(9);
        HSSFRow header3 = sheet.createRow(8);
        int i = 0;
        for (i = 0; i < columnNames.length; i++) {
            HSSFCell fcellIter = header2.createCell(i);
            fcellIter.setCellValue(columnNames[i]);
            sheet.autoSizeColumn(i);
        }

        long diff;
        long diffSeconds;
        long diffMinutes;
        long diffHours;
        int diffInDays = 0;

        if (this.finic != null && this.ffin != null) {
            finic.setHours(0);
            finic.setMinutes(0);
            finic.setSeconds(0);
            ffin.setHours(23);
            ffin.setMinutes(59);
            ffin.setSeconds(59);
            if (!this.ffin.before(finic)) {
                diff = ffin.getTime() - finic.getTime();
                diffSeconds = diff / 1000 % 60;
                diffMinutes = diff / (60 * 1000) % 60;
                diffHours = diff / (60 * 60 * 1000);
                diffInDays = (int) ((ffin.getTime() - finic.getTime()) / (1000 * 60 * 60 * 24));
            }
        } else if (this.finic != null) {
            finic.setHours(0);
            finic.setMinutes(0);
            finic.setSeconds(0);
            diff = now.getTime() - finic.getTime();
            diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            diffHours = diff / (60 * 60 * 1000);

        } else if (this.ffin != null) {
            ffin.setHours(23);
            ffin.setMinutes(59);
            ffin.setSeconds(59);
            diffInDays = 0;
        } else {
            diffInDays = 0;
        }

        int offset = i + diffInDays + 1;

        Date fechaIter = (Date) finic.clone();
        int j;
        for (j = i; j < offset; j++) {
            HSSFCell fcellIter = header2.createCell(j);
            fcellIter.setCellValue(fechaIter.getDate());
            sheet.autoSizeColumn(j);

            Calendar c = Calendar.getInstance();
            c.setTime(fechaIter);
            c.add(Calendar.DATE, 1);
            fechaIter = c.getTime();
        }

        sheet.addMergedRegion(new CellRangeAddress(
                8, //first row (0-based)
                8, //last row  (0-based)
                i, //first column (0-based)
                j //last column  (0-based)
        ));

        if (finic != null && ffin != null) {
            String s1 = df.format(finic);
            String s2 = df.format(ffin);
            HSSFCell fcell4 = header3.createCell(i);
            fcell4.setCellValue("Rango: " + s1 + " - " + s2);
        }

        // Datos
        int countIter = 1;
        int rowIter = 10;
        int rowIterClone = 10;
        int cellIter = 0;
        int cellIterClone = 0;
        HSSFRow headerIter;
        HSSFCell fcellIter;

        int m = 0;
        int f = 0;
        int sl = 0;
        int sm = 0;
        int c = 0;
        int hs = 0;
        int efd = 0;
        int e = 0;
        int d = 0;
        int fun = 0;
        int fam = 0;
        diffInDays = (int) ((ffin.getTime() - finic.getTime()) / (1000 * 60 * 60 * 24));
        int[] posCounts = new int[diffInDays+1];
        //int[] posCounts = null;
        for (Object[] reporteItem : this.reporte) {
            headerIter = sheet.createRow(rowIter);
            Usuario userIter = (Usuario) reporteItem[2];
            for (int l = 0; l < 7; l++) {
                fcellIter = headerIter.createCell(cellIter);
                if (l == 0) {
                    fcellIter.setCellValue(countIter);
                }
                if (l == 1) {
                    fcellIter.setCellValue(userIter.getFisrtname() + " " + userIter.getFirstlastname() + " " + userIter.getSecondname() + " " + userIter.getSecondlastname());
                }
                if (l == 2) {
                    fcellIter.setCellValue(userIter.getSex());
                    if ("M".equals(userIter.getSex())) {
                        m++;
                    } else {
                        f++;
                    }
                }
                if (l == 3) {
                    fcellIter.setCellValue(userIter.getIdentification());
                }
                if (l == 4) {
                    fcellIter.setCellValue(userIter.getDependencia());
                }
                if (l == 5) {
//                    fcellIter.setCellValue(userIter.getTipoPrograma());
//                    if (null != userIter.getTipoPrograma()) {
//                        switch (userIter.getTipoPrograma()) {
//                            case "Sl":
//                                sl++;
//                                break;
//                            case "Sm":
//                                sm++;
//                                break;
//                            case "C":
//                                c++;
//                                break;
//                            case "HS":
//                                hs++;
//                                break;
//                            case "EFD":
//                                efd++;
//                                break;
//                        }
//                    }
                }
                if (l == 6) {
//                    fcellIter.setCellValue(userIter.getTipoEstamento());
//                    if (null != userIter.getTipoEstamento()) {
//                        switch (userIter.getTipoEstamento()) {
//                            case "estudiante":
//                                e++;
//                                break;
//                            case "docente":
//                                d++;
//                                break;
//                            case "funcionario":
//                                fun++;
//                                break;
//                            case "familiar":
//                                fam++;
//                                break;
//                        }
//                    }
                }
                sheet.autoSizeColumn(l);
                cellIter++;
            }
            
            //int cellIterClone = cellIter;
            //fechaIter = (Date) finic.clone();
            List<Asistencia> asisUser = this.asistenciaFacade.getAsistenciasUsuarioEntreFechas(userIter, finic, ffin);
            //System.out.println(asisUser);
            //for (int k = 0; k < diffInDays; k++) {

            
            for (Asistencia asisUserItem : asisUser) {

                Date fechaTemp = asisUserItem.getFecha();
                diffInDays = (int) ((fechaTemp.getTime() - finic.getTime()) / (1000 * 60 * 60 * 24));

                int pos = cellIter + diffInDays;
                //System.out.println("pos: " + pos);
                posCounts[pos-cellIter]++;
                fcellIter = headerIter.createCell(pos);
                fcellIter.setCellValue("X");

            }

//                Calendar c = Calendar.getInstance(); 
//                c.setTime(fechaIter); 
//                c.add(Calendar.DATE, 1);
//                fechaIter = c.getTime();
//                sheet.autoSizeColumn(cellIter);
//                cellIter++;
//            }
            cellIterClone = cellIter;
            cellIter = 0;
            rowIter++;
            rowIterClone = rowIter;
            countIter++;
        }

        // Totales
        HSSFRow rowReport = sheet.createRow(rowIter);
        rowIter++;
        HSSFCell cellReport = rowReport.createCell(2);
        cellReport.setCellValue("M:" + m + " F:" + f);
        sheet.autoSizeColumn(2);
        
        cellReport = rowReport.createCell(5);
        cellReport.setCellValue("Sl:" + sl + " Sm:" + sm + " C:"+ c + " HS:" +hs + " EFD:"+efd);
        sheet.autoSizeColumn(5);
        
        cellReport = rowReport.createCell(6);
        cellReport.setCellValue("E:" + e + " D:" + d + " F:"+ fun + " Fliar:" +fam);
        sheet.autoSizeColumn(6);
        
        for (int k = 0; k < posCounts.length; k++) {
            //if(posCounts[k] != 0) {
                cellReport = rowReport.createCell(cellIterClone + k);
                cellReport.setCellValue(posCounts[k]);
                sheet.autoSizeColumn(cellIterClone + k);
            //}
        }
        //System.out.println(Arrays.toString(posCounts));
        
        // Footer
        sheet.addMergedRegion(new CellRangeAddress(
                rowIter + 2, //first row (0-based)
                rowIter + 6, //last row  (0-based)
                0, //first column (0-based)
                30 //last column  (0-based)
        ));
        
        HSSFRow footer = sheet.createRow(rowIter + 2);
        HSSFCell fcellf = footer.createCell(0);
        fcellf.setCellValue("OBSERVACIONES:");
    }

}
