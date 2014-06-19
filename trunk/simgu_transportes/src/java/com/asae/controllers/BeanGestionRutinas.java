/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asae.controllers;

import com.asae.entities.Dia;
import com.asae.entities.Ejercicio;
import com.asae.entities.EjercicioCross;
import com.asae.entities.EjercicioGm;
import com.asae.entities.Evaluacion;
import com.asae.entities.GrupoCross;
import com.asae.entities.GrupoCrossGeneral;
import com.asae.entities.GrupoMuscular;
import com.asae.entities.GrupoMuscularGeneral;
import com.asae.entities.MedidaEjercicioCross;
import com.asae.entities.Rutina;
import com.asae.entities.Usuario;
import com.asae.sessionbeans.DiaFacade;
import com.asae.sessionbeans.EjercicioCrossFacade;
import com.asae.sessionbeans.EjercicioFacade;
import com.asae.sessionbeans.EjercicioGmFacade;
import com.asae.sessionbeans.GrupoCrossFacade;
import com.asae.sessionbeans.GrupoCrossGeneralFacade;
import com.asae.sessionbeans.GrupoMuscularFacade;
import com.asae.sessionbeans.GrupoMuscularGeneralFacade;
import com.asae.sessionbeans.MedidaEjercicioCrossFacade;
import com.asae.sessionbeans.RutinaFacade;
import com.asae.utilidades.UsuarioConverter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Administrador
 */
@ManagedBean
@SessionScoped
public class BeanGestionRutinas {

    @EJB
    private MedidaEjercicioCrossFacade medidaEjercicioCrossFacade;
    @EJB
    private EjercicioCrossFacade ejercicioCrossFacade;
    @EJB
    private EjercicioGmFacade ejercicioGmFacade;
    @EJB
    private GrupoCrossFacade grupoCrossFacade;
    @EJB
    private GrupoCrossGeneralFacade grupoCrossGeneralFacade;
    @EJB
    private GrupoMuscularGeneralFacade grupoMuscularGeneralFacade;
    @EJB
    private EjercicioFacade ejercicioFacade;
    @EJB
    private GrupoMuscularFacade grupoMuscularFacade;
    @EJB
    private DiaFacade diaFacade;
    @EJB
    private RutinaFacade rutinaFacade;

    private List<Rutina> lstRutinasDisponibles;
    private Usuario usuRutinaRegistrar;
    private List<Usuario> lstUsuarioRegistrados;
    private List<Dia> lstDiasRutina;
    private Rutina rutinaRegistrar;
    private Date fechaInicioRutina;
    private Date fechaFinRutina;
    private GrupoMuscularGeneral grupoMuscRegistrar;
    private GrupoCrossGeneral grupoCrossRegistrar;
    private List<GrupoMuscularGeneral> lstGruposMusculares;
    private Dia diaSeleccionado;
    private List<GrupoMuscular> lstGruposMuscularesDia;
    private List<GrupoCross> lstGruposCrossDia;
    private List<Ejercicio> lstEjercicios;
    private List<EjercicioGm> lstEjerciciosGMuscular;
    private List<EjercicioCross> lstEjerciciosGCross;
    private GrupoMuscular grupoMuscSeleccionado;
    private GrupoCross grupoCrossSeleccionado;
    private EjercicioGm ejercicioGmRegistrar;
    private Ejercicio ejercicioRegistrar;
    private GrupoMuscularGeneral grupoMuscularRegistrarDlg;
    private GrupoMuscularGeneral grupoMuscularEditarDlg;
    private GrupoMuscular gMuscularTemp;
    private GrupoCross gCrossTemp;
    private Rutina rutinaVisualizar;
    private List<GrupoCrossGeneral> lstGrupoCross;
    private GrupoCrossGeneral gruposCrossGeneralEditar;
    private GrupoCrossGeneral gruposCrossGeneralRegistrar;
    private EjercicioCross ejercicioCrossRegistrar;
    private Ejercicio ejercicioRegistrarCross;
    private Ejercicio ejercicioRegistrarDlg;
    private Ejercicio ejercicioEditarDlg;
    private String accionRutina;
    private List<Dia> lstDiasTemp;
    private List<GrupoMuscular> lstGruposMuscularesTemp;
    private List<GrupoCross> lstGrupoCrossTemp;
    private List<EjercicioGm> lstEjerciciosGmTemp;
    private List<EjercicioCross> lstEjerciciosCrossTemp;
    private int idGrupoMuscRegistrar;
    private int idGrupoCrossRegistrar;
    private int idEjercicioRegistrarCross;
    private int idEjercicioRegistrarGm;
    private int idMedidaEjercicioCross;
    private SelectOneMenu selEjerCross;
    private SelectOneMenu selEjerGm;
    private SelectOneMenu selGrupoCross;
    private SelectOneMenu selGrupoGm;
    private SelectOneMenu selMedidaEjerCross;
    private List<MedidaEjercicioCross> lstMedidasEjercicioCross;
    private MedidaEjercicioCross medidaEjerCrossRegistrar;
    private MedidaEjercicioCross medidaEditarDlg;
    private MedidaEjercicioCross medidaRegistrarDlg;
    private String pdfFileName;

    public BeanGestionRutinas() {
        lstUsuarioRegistrados = UsuarioConverter.lstUsuarioRegistrados;
        usuRutinaRegistrar = new Usuario();

        rutinaRegistrar = new Rutina();

        diaSeleccionado = new Dia();

        lstGruposMusculares = new ArrayList<>();
//        lstGruposMuscularesDia = new ArrayList<>();
        grupoMuscRegistrar = new GrupoMuscularGeneral();
        grupoMuscSeleccionado = new GrupoMuscular();
        grupoMuscularRegistrarDlg = new GrupoMuscularGeneral();
        grupoMuscularEditarDlg = new GrupoMuscularGeneral();
//        gMuscularTemp = new GrupoMuscular();

        lstEjercicios = new ArrayList<>();
        ejercicioRegistrar = new Ejercicio();
        ejercicioRegistrarCross = new Ejercicio();
        ejercicioRegistrarDlg = new Ejercicio();
        ejercicioEditarDlg = new Ejercicio();

        rutinaVisualizar = new Rutina();

        lstGrupoCross = new ArrayList<>();
        gruposCrossGeneralEditar = new GrupoCrossGeneral();
        gruposCrossGeneralRegistrar = new GrupoCrossGeneral();
        grupoCrossRegistrar = new GrupoCrossGeneral();
        grupoCrossSeleccionado = new GrupoCross();

        ejercicioCrossRegistrar = new EjercicioCross();
        idEjercicioRegistrarCross = -1;
        idEjercicioRegistrarGm = -1;
        idMedidaEjercicioCross = -1;

        medidaEditarDlg = new MedidaEjercicioCross();
        medidaRegistrarDlg = new MedidaEjercicioCross();
        pdfFileName = "sin-cargar";
    }

    public void generarPdf() {
        Document document = new Document();
        try {
            File file = File.createTempFile("rutina-", ".pdf", new File("/var/webapp/pdf"));
            pdfFileName = file.getName();

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Font bold = new Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD);
            URL url = FacesContext.getCurrentInstance().getExternalContext().getResource("/img/logo-unicauca-negro.png");
            Image imgLogoUnicauca = Image.getInstance(url);
            imgLogoUnicauca.scaleAbsolute(118f, 131f);

            PdfPTable tableEncabezado = new PdfPTable(2);
            tableEncabezado.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableEncabezado.setWidthPercentage(100);
            tableEncabezado.setSpacingAfter(5);
            PdfPCell cell1 = new PdfPCell(imgLogoUnicauca);
            cell1.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell2 = new PdfPCell(new Paragraph("Vicerrectoria Administrativa"));
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell cell3 = new PdfPCell(new Paragraph("Universidad del Cauca", bold));
            cell3.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell4 = new PdfPCell(new Paragraph("Gimnasio y Actividad Física", bold));
            cell4.setBorder(Rectangle.NO_BORDER);
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableEncabezado.addCell(cell1);
            tableEncabezado.addCell(cell2);
            tableEncabezado.addCell(cell3);
            tableEncabezado.addCell(cell4);

            PdfPTable tableDatosUsuario = new PdfPTable(3);
            tableDatosUsuario.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableDatosUsuario.setWidthPercentage(100);
            tableDatosUsuario.setSpacingBefore(20);
            PdfPCell cell5 = new PdfPCell(new Paragraph("Nombre: " + rutinaVisualizar.getIdusuario().getFisrtname() + " " + rutinaVisualizar.getIdusuario().getSecondname() + " " + rutinaVisualizar.getIdusuario().getFirstlastname() + " " + rutinaVisualizar.getIdusuario().getSecondlastname()));
            cell5.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell6 = new PdfPCell();
            cell6.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell7 = new PdfPCell();
            cell7.setBorder(Rectangle.NO_BORDER);

            tableDatosUsuario.addCell(cell5);
            tableDatosUsuario.addCell(cell6);
            tableDatosUsuario.addCell(cell7);

            PdfPTable tableFechas = new PdfPTable(3);
            tableFechas.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableFechas.setWidthPercentage(100);
            tableFechas.setSpacingBefore(10);
            PdfPCell cell8 = new PdfPCell(new Paragraph("Fecha de inicio: " + getMyFormattedDate(rutinaVisualizar.getFechaInicio())));
            cell8.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell9 = new PdfPCell(new Paragraph("Fecha de fin: " + getMyFormattedDate(rutinaVisualizar.getFechaFin())));
            cell9.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell10 = new PdfPCell();
            cell10.setBorder(Rectangle.NO_BORDER);

            tableFechas.addCell(cell8);
            tableFechas.addCell(cell9);
            tableFechas.addCell(cell10);

            PdfPTable tableMedidas = new PdfPTable(4);
            tableMedidas.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableMedidas.setWidthPercentage(100);
            tableMedidas.setSpacingBefore(10);

            Usuario usuAux = rutinaVisualizar.getIdusuario();
            List<Evaluacion> lstEvalAux = usuAux.getEvaluacionList();
            Evaluacion evalAux = lstEvalAux.get(lstEvalAux.size() - 1);

            PdfPCell cell11 = new PdfPCell(new Paragraph("Peso: " + evalAux.getPeso()));
            cell11.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell12 = new PdfPCell(new Paragraph("Talla: " + usuAux.getMedidasGenerales().getEstatura()));
            cell12.setBorder(Rectangle.NO_BORDER);

            double estatura = usuAux.getMedidasGenerales().getEstatura().doubleValue();
            double peso = evalAux.getPeso().doubleValue();

            double imc = peso / Math.pow(estatura, 2);

            PdfPCell cell13 = new PdfPCell(new Paragraph("I.M.C: " + imc));
            cell13.setBorder(Rectangle.NO_BORDER);
            PdfPCell cell14 = new PdfPCell(new Paragraph("Rutina: " + rutinaVisualizar.getCodigoRutina()));
            cell14.setBorder(Rectangle.NO_BORDER);

            tableMedidas.addCell(cell11);
            tableMedidas.addCell(cell12);
            tableMedidas.addCell(cell13);
            tableMedidas.addCell(cell14);

            PdfPTable tableDias = new PdfPTable(2);
            tableDias.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableDias.setWidthPercentage(100);
            tableDias.setSpacingBefore(20);
            tableDias.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableDias.setWidths(new int[]{1, 9});

            List<Dia> lstDiasPDF = rutinaVisualizar.getDiaList();

            PdfPCell cellCabezeraDia = new PdfPCell(new Paragraph("Día", bold));
            PdfPCell cellCabezeraDia2 = new PdfPCell();
            cellCabezeraDia2.setBorder(Rectangle.NO_BORDER);

            tableDias.addCell(cellCabezeraDia);
            tableDias.addCell(cellCabezeraDia2);

            for (Dia dia : lstDiasPDF) {
                PdfPCell cellDia = new PdfPCell(new Paragraph(dia.getNumDia().toString()));
                PdfPCell cellDia2 = new PdfPCell();
                cellDia2.setBorder(Rectangle.NO_BORDER);

                List<GrupoMuscular> lstGMuscularPDF = dia.getGrupoMuscularList();

                if (lstGMuscularPDF.size() > 0) {
                    PdfPTable tableGMuscular = new PdfPTable(2);
                    tableGMuscular.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    tableGMuscular.setWidthPercentage(100);
                    tableGMuscular.setHorizontalAlignment(Element.ALIGN_LEFT);
                    tableGMuscular.setWidths(new int[]{1, 6});

                    PdfPCell cellCabezeraGMuscular = new PdfPCell(new Paragraph("Grupo Muscular", bold));
                    cellCabezeraGMuscular.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
                    PdfPCell cellCabezeraGMuscular2 = new PdfPCell();
                    cellCabezeraGMuscular2.setBorder(Rectangle.NO_BORDER);
                    tableGMuscular.addCell(cellCabezeraGMuscular);
                    tableGMuscular.addCell(cellCabezeraGMuscular2);

                    int aux = 1;
                    for (GrupoMuscular gMuscularFor : lstGMuscularPDF) {
                        PdfPCell cellGMuscular = new PdfPCell(new Paragraph(gMuscularFor.getIdgrupoMuscularGeneral().getNombre()));
                        cellGMuscular.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                        PdfPCell cellGMuscular2 = new PdfPCell();
                        cellGMuscular2.setBorder(Rectangle.NO_BORDER);

                        List<EjercicioGm> lstEjerciciosGMuscularPDF = gMuscularFor.getEjercicioGmList();
                        BaseColor myColor = WebColors.getRGBColor("#CCEEFF");
                        if (lstEjerciciosGMuscularPDF.size() > 0) {
                            PdfPTable tableEjercicioGMsucular = new PdfPTable(5);
                            tableEjercicioGMsucular.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                            tableEjercicioGMsucular.setWidthPercentage(100);
                            tableEjercicioGMsucular.setHorizontalAlignment(Element.ALIGN_LEFT);

                            PdfPCell cellCabezeraEjercicioGM = new PdfPCell(new Paragraph("Ejercicio", bold));
                            cellCabezeraEjercicioGM.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
                            cellCabezeraEjercicioGM.setLeading(20f, 0f);
                            cellCabezeraEjercicioGM.setBackgroundColor(myColor);
                            tableEjercicioGMsucular.addCell(cellCabezeraEjercicioGM);

                            PdfPCell cellCabezeraEjercicioGM2 = new PdfPCell(new Paragraph("Series", bold));
                            cellCabezeraEjercicioGM2.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
                            cellCabezeraEjercicioGM2.setLeading(20f, 0f);
                            cellCabezeraEjercicioGM2.setBackgroundColor(myColor);
                            tableEjercicioGMsucular.addCell(cellCabezeraEjercicioGM2);

                            PdfPCell cellCabezeraEjercicioGM3 = new PdfPCell(new Paragraph("Repeticiones", bold));
                            cellCabezeraEjercicioGM3.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
                            cellCabezeraEjercicioGM3.setLeading(20f, 0f);
                            cellCabezeraEjercicioGM3.setBackgroundColor(myColor);
                            tableEjercicioGMsucular.addCell(cellCabezeraEjercicioGM3);

                            PdfPCell cellCabezeraEjercicioGM4 = new PdfPCell(new Paragraph("Receso", bold));
                            cellCabezeraEjercicioGM4.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
                            cellCabezeraEjercicioGM4.setLeading(20f, 0f);
                            cellCabezeraEjercicioGM4.setBackgroundColor(myColor);
                            tableEjercicioGMsucular.addCell(cellCabezeraEjercicioGM4);

                            PdfPCell cellCabezeraEjercicioGM5 = new PdfPCell(new Paragraph("Peso", bold));
                            cellCabezeraEjercicioGM5.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
                            cellCabezeraEjercicioGM5.setLeading(20f, 0f);
                            cellCabezeraEjercicioGM5.setBackgroundColor(myColor);
                            tableEjercicioGMsucular.addCell(cellCabezeraEjercicioGM5);

                            int aux2 = 1;
                            for (EjercicioGm ejercicioGm : lstEjerciciosGMuscularPDF) {
                                PdfPCell cellEjercicioGM = new PdfPCell(new Paragraph(ejercicioGm.getEjercicio().getNombre()));
                                cellEjercicioGM.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                                cellEjercicioGM.setLeading(20f, 0f);

                                PdfPCell cellEjercicioGM2 = new PdfPCell(new Paragraph(ejercicioGm.getNumeroSeries().toString()));
                                cellEjercicioGM2.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                                cellEjercicioGM2.setLeading(20f, 0f);

                                PdfPCell cellEjercicioGM3 = new PdfPCell(new Paragraph(ejercicioGm.getRepeticiones().toString()));
                                cellEjercicioGM3.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                                cellEjercicioGM3.setLeading(20f, 0f);

                                PdfPCell cellEjercicioGM4 = new PdfPCell(new Paragraph(ejercicioGm.getReceso().toString()));
                                cellEjercicioGM4.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                                cellEjercicioGM4.setLeading(20f, 0f);

                                PdfPCell cellEjercicioGM5 = new PdfPCell(new Paragraph(ejercicioGm.getPeso().toString()));
                                cellEjercicioGM5.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                                cellEjercicioGM5.setLeading(20f, 0f);

                                if (aux2 % 2 == 0) {
                                    cellEjercicioGM.setBackgroundColor(myColor);
                                    cellEjercicioGM2.setBackgroundColor(myColor);
                                    cellEjercicioGM3.setBackgroundColor(myColor);
                                    cellEjercicioGM4.setBackgroundColor(myColor);
                                    cellEjercicioGM5.setBackgroundColor(myColor);
                                }

                                tableEjercicioGMsucular.addCell(cellEjercicioGM);
                                tableEjercicioGMsucular.addCell(cellEjercicioGM2);
                                tableEjercicioGMsucular.addCell(cellEjercicioGM3);
                                tableEjercicioGMsucular.addCell(cellEjercicioGM4);
                                tableEjercicioGMsucular.addCell(cellEjercicioGM5);
                                aux2++;
                            }
                            cellGMuscular2.addElement(tableEjercicioGMsucular);
                        }
                        tableGMuscular.addCell(cellGMuscular);
                        tableGMuscular.addCell(cellGMuscular2);
                        aux++;
                    }
                    cellDia2.addElement(tableGMuscular);
                }
                tableDias.addCell(cellDia);
                tableDias.addCell(cellDia2);
            }

            LineSeparator ls = new LineSeparator();
            document.add(tableEncabezado);
            document.add(ls);
            document.add(tableDatosUsuario);
            document.add(tableFechas);
            document.add(tableMedidas);
            document.add(tableDias);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(BeanGestionRutinas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> completeUsuarioRegistrados(String query) {
        List<Usuario> suggestions = new ArrayList<>();
        for (Usuario u : lstUsuarioRegistrados) {
            if (u.getFisrtname().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(u);
            }
        }

        return suggestions;
    }

    public void agregarDiaARutina() {
        Dia diaRutina = new Dia();
        if (lstDiasRutina.size() < 7) {
            if (lstDiasRutina.isEmpty()) {
                diaRutina.setNumDia(1);
                diaRutina.setNombre("Día 1");
            } else {
                Collections.sort(lstDiasRutina, Dia.NUMDIA);
                int numComprobante = 0;
                int numSalida = lstDiasRutina.size() + 1;
                for (int i = 0; i < lstDiasRutina.size(); i++) {
                    if (lstDiasRutina.get(i).getNumDia() == i + 1) {
                        numComprobante += 1;
                    }
                }
                if (lstDiasRutina.size() != numComprobante) {
                    for (int i = 0; i < lstDiasRutina.size(); i++) {
                        if (lstDiasRutina.get(i).getNumDia() != i + 1) {
                            numSalida = i + 1;
                            break;
                        }
                    }
                }
                diaRutina.setNumDia(numSalida);
                diaRutina.setNombre("Día " + numSalida);
            }
            lstDiasRutina.add(diaRutina);
            Collections.sort(lstDiasRutina, Dia.NUMDIA);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Solo pueden agregarse siete días"));
        }
        RequestContext.getCurrentInstance().update("frmDatosRutina:tblDias");
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public void agregarGrupoMuscular() {
        if (idGrupoMuscRegistrar != -1) {
            gMuscularTemp = new GrupoMuscular();
            grupoMuscRegistrar = grupoMuscularGeneralFacade.find(idGrupoMuscRegistrar);
            gMuscularTemp.setIdgrupoMuscularGeneral(grupoMuscRegistrar);
            if (lstGruposMuscularesDia != null) {
                boolean isInList = false;
                for (GrupoMuscular gMuscular : lstGruposMuscularesDia) {
                    if (gMuscular.getIdgrupoMuscularGeneral().equals(gMuscularTemp.getIdgrupoMuscularGeneral())) {
                        isInList = true;
                        break;
                    }
                }
                if (!isInList) {
                    lstGruposMuscularesDia.add(gMuscularTemp);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se ha agregado el grupo muscular " + grupoMuscRegistrar.getNombre()));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El grupo muscular " + grupoMuscRegistrar.getNombre() + " ya está registrado"));
                }
            } else {
                lstGruposMuscularesDia = new ArrayList<>();
                lstGruposMuscularesDia.add(gMuscularTemp);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se ha agregado el grupo muscular " + grupoMuscRegistrar.getNombre()));
            }
            diaSeleccionado.setGrupoMuscularList(lstGruposMuscularesDia);
            selGrupoGm.setValue("-1");
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("frmGruposMusculares");
            RequestContext.getCurrentInstance().update("frmDatosDia");
        }
    }

    public void seleccionarGrupoMuscular(GrupoMuscular gMuscularIn) {
        ejercicioGmRegistrar = new EjercicioGm();
        ejercicioGmRegistrar.setNumeroSeries(1);
        ejercicioGmRegistrar.setPeso(1);
        ejercicioGmRegistrar.setReceso(1);
        ejercicioGmRegistrar.setRepeticiones(1);
        grupoMuscSeleccionado = gMuscularIn;
        lstEjerciciosGMuscular = grupoMuscSeleccionado.getEjercicioGmList();
        lstEjerciciosGmTemp = grupoMuscSeleccionado.getEjercicioGmList();
        selEjerGm.setValue("-1");
        RequestContext.getCurrentInstance().update("frmEjercicios");
        RequestContext.getCurrentInstance().update("frmDatosDiaGMuscular");
        RequestContext.getCurrentInstance().update("frmDatosEjercicio");
    }

    public void agregarGrupoCross() {
        if (idGrupoCrossRegistrar != -1) {
            gCrossTemp = new GrupoCross();
            grupoCrossRegistrar = grupoCrossGeneralFacade.find(idGrupoCrossRegistrar);
            gCrossTemp.setIdgrupoCrossGeneral(grupoCrossRegistrar);
            if (lstGruposCrossDia != null) {
                boolean isInList = false;
                for (GrupoCross gCross : lstGruposCrossDia) {
                    if (gCross.getIdgrupoCrossGeneral().equals(gCrossTemp.getIdgrupoCrossGeneral())) {
                        isInList = true;
                        break;
                    }
                }
                if (!isInList) {
                    lstGruposCrossDia.add(gCrossTemp);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se ha agregado el grupo cross " + grupoCrossRegistrar.getNombre()));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El grupo cross " + grupoCrossRegistrar.getNombre() + " ya está registrado"));
                }
            } else {
                lstGruposCrossDia = new ArrayList<>();
                lstGruposCrossDia.add(gCrossTemp);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se ha agregado el grupo cross " + grupoCrossRegistrar.getNombre()));
            }
            diaSeleccionado.setGrupoCrossList(lstGruposCrossDia);
            selGrupoCross.setValue("-1");
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("frmGruposCross");
            RequestContext.getCurrentInstance().update("frmDatosDiaCross");
        }
    }

    public void seleccionarGrupoCross(GrupoCross grupoCrossIn) {
        ejercicioCrossRegistrar = new EjercicioCross();
        grupoCrossSeleccionado = grupoCrossIn;
        lstEjerciciosGCross = grupoCrossSeleccionado.getEjercicioCrossList();
        lstEjerciciosCrossTemp = grupoCrossSeleccionado.getEjercicioCrossList();
        selEjerCross.setValue("-1");
        selMedidaEjerCross.setValue("-1");
        RequestContext.getCurrentInstance().update("frmEjerciciosCross");
        RequestContext.getCurrentInstance().update("frmDatosDiaGCross");
        RequestContext.getCurrentInstance().update("frmDatosEjercicioCross");
    }

    public void agregarEjercicio() {
        if (ejercicioGmRegistrar.getEjercicio() != null) {
            if (lstEjerciciosGMuscular != null) {
                boolean isInList = false;
                for (EjercicioGm ejercicioGmFor : lstEjerciciosGMuscular) {
                    if (ejercicioGmFor.getEjercicio().equals(ejercicioGmRegistrar.getEjercicio()) && ejercicioGmFor.getNumeroSeries() == ejercicioGmRegistrar.getNumeroSeries() && ejercicioGmFor.getPeso() == ejercicioGmRegistrar.getPeso() && ejercicioGmFor.getReceso() == ejercicioGmRegistrar.getReceso() && ejercicioGmFor.getRepeticiones() == ejercicioGmRegistrar.getRepeticiones()) {
                        isInList = true;
                        break;
                    }
                }
                if (!isInList) {
                    lstEjerciciosGMuscular.add(ejercicioGmRegistrar);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se ha agregado el ejercicio " + ejercicioRegistrar.getNombre()));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El ejercicio " + ejercicioRegistrar.getNombre() + " ya está registrado"));
                }
            } else {
                lstEjerciciosGMuscular = new ArrayList<>();
                lstEjerciciosGMuscular.add(ejercicioGmRegistrar);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se ha agregado el ejercicio " + ejercicioRegistrar.getNombre()));
            }
            grupoMuscSeleccionado.setEjercicioGmList(lstEjerciciosGMuscular);
            selEjerGm.setValue("-1");
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("frmEjercicios");
            RequestContext.getCurrentInstance().update("frmDatosEjercicio");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Atención", "No ha seleccionado ningún ejercicio"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void agregarEjercicioCross() {
        if (ejercicioCrossRegistrar.getEjercicio() != null) {
            if (lstEjerciciosGCross != null) {
                boolean isInList = false;
                for (EjercicioCross ejercicioCrossFor : lstEjerciciosGCross) {
                    if (ejercicioCrossFor.getEjercicio().equals(ejercicioCrossRegistrar.getEjercicio()) && ejercicioCrossFor.getIdmedidaEjercicioCross().equals(ejercicioCrossRegistrar.getIdmedidaEjercicioCross()) && ejercicioCrossFor.getNumVecesMedida() == ejercicioCrossRegistrar.getNumVecesMedida()) {
                        isInList = true;
                        break;
                    }
                }
                if (!isInList) {
                    lstEjerciciosGCross.add(ejercicioCrossRegistrar);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se ha agregado el ejercicio " + ejercicioRegistrarCross.getNombre()));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El ejercicio " + ejercicioRegistrarCross.getNombre() + " ya está registrado"));
                }
            } else {
                lstEjerciciosGCross = new ArrayList<>();
                lstEjerciciosGCross.add(ejercicioCrossRegistrar);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se ha agregado el ejercicio " + ejercicioRegistrarCross.getNombre()));
            }
            grupoCrossSeleccionado.setEjercicioCrossList(lstEjerciciosGCross);
            selEjerCross.setValue("-1");
            selMedidaEjerCross.setValue("-1");
            ejercicioCrossRegistrar = new EjercicioCross();
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("frmEjerciciosCross");
            RequestContext.getCurrentInstance().update("frmDatosEjercicioCross");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Atención", "No ha seleccionado ningún ejercicio"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void guardarEjercicios() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Los ejercicios han sido registrados con éxito para el grupo muscular"));
        RequestContext.getCurrentInstance().update("frmGrowl");
        RequestContext.getCurrentInstance().update("frmEjercicios");
    }

    public void agregarGruposMusculares() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Los grupos musculares han sido registrados con éxito para el día"));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public void agregarRutina() {
        switch (accionRutina) {
            case "crear":
                rutinaRegistrar.setFechaInicio(new java.sql.Date(fechaInicioRutina.getTime()));
                rutinaRegistrar.setFechaFin(new java.sql.Date(fechaFinRutina.getTime()));
                rutinaRegistrar.setIdusuario(usuRutinaRegistrar);
                rutinaRegistrar.setDiaList(lstDiasRutina);
                rutinaFacade.create(rutinaRegistrar);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Los rutina ha sido registrada con éxito para el usuario"));
                RequestContext.getCurrentInstance().update("frmGrowl");
                RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaRutina");
                break;
            case "editar":
                rutinaRegistrar.setDiaList(lstDiasRutina);
                rutinaFacade.edit(rutinaRegistrar);
                List<Dia> lstDiasSinRutina = diaFacade.findDiasSinRutina();
                for (Dia diaFor : lstDiasSinRutina) {
                    diaFacade.remove(diaFor);
                }
                List<GrupoMuscular> lstGMuscularesSinDia = grupoMuscularFacade.findGMuscularesSinDia();
                for (GrupoMuscular grupoMuscularFor : lstGMuscularesSinDia) {
                    grupoMuscularFacade.remove(grupoMuscularFor);
                }
                List<GrupoCross> lstGCrossSinDia = grupoCrossFacade.findGCrossSinDia();
                for (GrupoCross grupoCrossFor : lstGCrossSinDia) {
                    grupoCrossFacade.remove(grupoCrossFor);
                }
                List<EjercicioGm> lstEjerciciosGmSinGrupoMuscular = ejercicioGmFacade.findEjerciciosGmSinGrupoMuscular();
                for (EjercicioGm ejercicioGmFor : lstEjerciciosGmSinGrupoMuscular) {
                    ejercicioGmFacade.remove(ejercicioGmFor);
                }
                List<EjercicioCross> lstEjerciciosCrossSinGCross = ejercicioCrossFacade.findEjerciciosCrossSinGCross();
                for (EjercicioCross ejercicioCrossFor : lstEjerciciosCrossSinGCross) {
                    ejercicioCrossFacade.remove(ejercicioCrossFor);
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Los rutina ha sido editada con éxito para el usuario"));
                RequestContext.getCurrentInstance().update("frmGrowl");
                RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaRutina");
        }
    }

    public void seleccionarDia(Dia diaIn) {
        diaSeleccionado = diaIn;
        lstGruposMuscularesTemp = diaSeleccionado.getGrupoMuscularList();
        lstGruposMuscularesDia = diaSeleccionado.getGrupoMuscularList();
        RequestContext.getCurrentInstance().update("frmGruposMusculares");
        RequestContext.getCurrentInstance().update("frmDatosDia");
    }

    public void eliminarDiaRutina(Dia diaIn) {
        List<Dia> lstDiasAux = lstDiasRutina;
        if (diaIn.getIdDia() == null) {
            for (int i = 0; i < lstDiasAux.size(); i++) {
                if (lstDiasAux.get(i).getNumDia() == diaIn.getNumDia()) {
                    lstDiasRutina.remove(i);
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El día ha sido eliminado"));
        } else {
            boolean isInListTemp;
            lstDiasTemp.remove(diaIn);
            lstDiasRutina.remove(diaIn);
            for (Dia diaFor : lstDiasRutina) {
                isInListTemp = false;
                for (Dia diaForTemp : lstDiasTemp) {
                    if (diaForTemp.getNumDia() == diaFor.getNumDia()) {
                        isInListTemp = true;
                        break;
                    }
                }
                if (diaFor.getIdDia() == null && !isInListTemp) {
                    lstDiasTemp.add(diaFor);
                } else if (diaFor.getIdDia() != null) {
                    lstDiasTemp.get(lstDiasTemp.indexOf(diaFor)).setGrupoMuscularList(diaFor.getGrupoMuscularList());
                    lstDiasTemp.get(lstDiasTemp.indexOf(diaFor)).setGrupoCrossList(diaFor.getGrupoCrossList());
                }
            }
            lstDiasRutina = lstDiasTemp;
            Collections.sort(lstDiasRutina, Dia.NUMDIA);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El día ha sido eliminado"));
        }
        RequestContext.getCurrentInstance().update("frmDatosRutina");
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public void eliminarGMuscular(GrupoMuscular grupoMuscularIn) {
        List<GrupoMuscular> lstGMuscularesAux = lstGruposMuscularesDia;
        if (grupoMuscularIn.getIdGrupoMuscular() == null) {
            for (int i = 0; i < lstGMuscularesAux.size(); i++) {
                if (lstGMuscularesAux.get(i).getIdgrupoMuscularGeneral().getNombre().equals(grupoMuscularIn.getIdgrupoMuscularGeneral().getNombre())) {
                    lstGMuscularesAux.remove(i);
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El grupo muscular ha sido eliminado"));
        } else {
            boolean isInListTemp;
            lstGruposMuscularesTemp.remove(grupoMuscularIn);
            lstGruposMuscularesDia.remove(grupoMuscularIn);
            for (GrupoMuscular gMuscularFor : lstGruposMuscularesDia) {
                isInListTemp = false;
                for (GrupoMuscular gMuscularForTemp : lstGruposMuscularesTemp) {
                    if (gMuscularForTemp.getIdgrupoMuscularGeneral().getNombre().equals(gMuscularFor.getIdgrupoMuscularGeneral().getNombre())) {
                        isInListTemp = true;
                        break;
                    }
                }
                if (gMuscularFor.getIdGrupoMuscular() == null && !isInListTemp) {
                    lstGruposMuscularesTemp.add(gMuscularFor);
                } else if (gMuscularFor.getIdGrupoMuscular() != null) {
                    lstGruposMuscularesTemp.get(lstGruposMuscularesTemp.indexOf(gMuscularFor)).setEjercicioGmList(gMuscularFor.getEjercicioGmList());
                }
            }
            lstGruposMuscularesDia = lstGruposMuscularesTemp;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El grupo muscular ha sido eliminado"));
        }
        RequestContext.getCurrentInstance().update("frmGruposMusculares");
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public void eliminarGCross(GrupoCross grupoCrossIn) {
        List<GrupoCross> lstGCrossAux = lstGruposCrossDia;
        if (grupoCrossIn.getIdCross() == null) {
            for (int i = 0; i < lstGCrossAux.size(); i++) {
                if (lstGCrossAux.get(i).getIdgrupoCrossGeneral().getNombre().equals(grupoCrossIn.getIdgrupoCrossGeneral().getNombre())) {
                    lstGCrossAux.remove(i);
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El grupo cross ha sido eliminado"));
        } else {
            boolean isInListTemp;
            lstGrupoCrossTemp.remove(grupoCrossIn);
            lstGruposCrossDia.remove(grupoCrossIn);
            for (GrupoCross gCrossFor : lstGruposCrossDia) {
                isInListTemp = false;
                for (GrupoCross gCrossForTemp : lstGrupoCrossTemp) {
                    if (gCrossForTemp.getIdgrupoCrossGeneral().getNombre().equals(gCrossFor.getIdgrupoCrossGeneral().getNombre())) {
                        isInListTemp = true;
                        break;
                    }
                }
                if (gCrossFor.getIdCross() == null && !isInListTemp) {
                    lstGrupoCrossTemp.add(gCrossFor);
                } else if (gCrossFor.getIdCross() != null) {
                    lstGrupoCrossTemp.get(lstGrupoCrossTemp.indexOf(gCrossFor)).setEjercicioCrossList(gCrossFor.getEjercicioCrossList());
                }
            }
            lstGruposCrossDia = lstGrupoCrossTemp;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El grupo cross ha sido eliminado"));
        }
        RequestContext.getCurrentInstance().update("frmGruposCross");
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public void eliminarEjercicioGMuscular(EjercicioGm ejercicioGmIn) {
        List<EjercicioGm> lstEjerciciosGmAux = lstEjerciciosGMuscular;
        if (ejercicioGmIn.getIdejerciciogm() == null) {
            for (int i = 0; i < lstEjerciciosGmAux.size(); i++) {
                if (lstEjerciciosGmAux.get(i).getEjercicio().getNombre().equals(ejercicioGmIn.getEjercicio().getNombre()) && lstEjerciciosGmAux.get(i).getNumeroSeries() == ejercicioGmIn.getNumeroSeries() && lstEjerciciosGmAux.get(i).getPeso() == ejercicioGmIn.getPeso() && lstEjerciciosGmAux.get(i).getReceso() == ejercicioGmIn.getReceso() && lstEjerciciosGmAux.get(i).getRepeticiones() == ejercicioGmIn.getRepeticiones()) {
                    lstEjerciciosGmAux.remove(i);
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El ejercicio ha sido eliminado"));
        } else {
            boolean isInListTemp;
            lstEjerciciosGmTemp.remove(ejercicioGmIn);
            lstEjerciciosGMuscular.remove(ejercicioGmIn);
            for (EjercicioGm ejercicioGmFor : lstEjerciciosGMuscular) {
                isInListTemp = false;
                for (EjercicioGm ejercicioGmForTemp : lstEjerciciosGmTemp) {
                    if (ejercicioGmForTemp.getEjercicio().getNombre().equals(ejercicioGmFor.getEjercicio().getNombre()) && ejercicioGmForTemp.getNumeroSeries() == ejercicioGmFor.getNumeroSeries() && ejercicioGmForTemp.getPeso() == ejercicioGmFor.getPeso() && ejercicioGmForTemp.getReceso() == ejercicioGmFor.getReceso() && ejercicioGmForTemp.getRepeticiones() == ejercicioGmFor.getRepeticiones()) {
                        isInListTemp = true;
                        break;
                    }
                }
                if (ejercicioGmFor.getIdejerciciogm() == null && !isInListTemp) {
                    lstEjerciciosGmTemp.add(ejercicioGmFor);
                }
            }
            lstEjerciciosGMuscular = lstEjerciciosGmTemp;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El ejercicio ha sido eliminado"));
        }
        RequestContext.getCurrentInstance().update("frmEjercicios");
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public void eliminarEjercicioGCross(EjercicioCross ejercicioCrossIn) {
        List<EjercicioCross> lstEjerciciosCrossAux = lstEjerciciosGCross;
        if (ejercicioCrossIn.getIdejercicioCross() == null) {
            for (int i = 0; i < lstEjerciciosCrossAux.size(); i++) {
                if (lstEjerciciosCrossAux.get(i).getEjercicio().getNombre().equals(ejercicioCrossIn.getEjercicio().getNombre()) && lstEjerciciosCrossAux.get(i).getIdmedidaEjercicioCross().equals(ejercicioCrossIn.getIdmedidaEjercicioCross()) && lstEjerciciosCrossAux.get(i).getNumVecesMedida() == ejercicioCrossIn.getNumVecesMedida()) {
                    lstEjerciciosCrossAux.remove(i);
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El ejercicio ha sido eliminado"));
        } else {
            boolean isInListTemp;
            lstEjerciciosCrossTemp.remove(ejercicioCrossIn);
            lstEjerciciosGCross.remove(ejercicioCrossIn);
            for (EjercicioCross ejercicioCrossFor : lstEjerciciosGCross) {
                isInListTemp = false;
                for (EjercicioCross ejercicioCrossForTemp : lstEjerciciosCrossTemp) {
                    if (ejercicioCrossForTemp.getEjercicio().getNombre().equals(ejercicioCrossFor.getEjercicio().getNombre()) && ejercicioCrossForTemp.getIdmedidaEjercicioCross().equals(ejercicioCrossFor.getIdmedidaEjercicioCross()) && ejercicioCrossForTemp.getNumVecesMedida() == ejercicioCrossFor.getNumVecesMedida()) {
                        isInListTemp = true;
                        break;
                    }
                }
                if (ejercicioCrossFor.getIdejercicioCross() == null && !isInListTemp) {
                    lstEjerciciosCrossTemp.add(ejercicioCrossFor);
                }
            }
            lstEjerciciosGCross = lstEjerciciosCrossTemp;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El ejercicio ha sido eliminado"));
        }
        RequestContext.getCurrentInstance().update("frmEjerciciosCross");
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public void seleccionarDiaCross(Dia diaIn) {
        diaSeleccionado = diaIn;
        lstGruposCrossDia = diaSeleccionado.getGrupoCrossList();
        lstGrupoCrossTemp = diaSeleccionado.getGrupoCrossList();
        RequestContext.getCurrentInstance().update("frmDatosDiaCross");
        RequestContext.getCurrentInstance().update("frmGruposCross");
    }

    public void seleccionarEjercicio() {
        if (idEjercicioRegistrarGm != -1) {
            ejercicioGmRegistrar = new EjercicioGm();
            ejercicioRegistrar = ejercicioFacade.find(idEjercicioRegistrarGm);
            ejercicioGmRegistrar.setEjercicio(ejercicioRegistrar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Ejercicio: " + ejercicioRegistrar.getNombre() + " | descripcion: " + ejercicioRegistrar.getDescripcion()));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("frmDatosEjercicio:selectEjercicioGm");
            RequestContext.getCurrentInstance().update("frmDatosEjercicio:msgSelectEjercicioGm");
        }
    }

    public void seleccionarEjercicioCross() {
        if (idEjercicioRegistrarCross != -1) {
            ejercicioRegistrarCross = ejercicioFacade.find(idEjercicioRegistrarCross);
            ejercicioCrossRegistrar.setEjercicio(ejercicioRegistrarCross);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Ejercicio: " + ejercicioRegistrarCross.getNombre() + " | descripcion: " + ejercicioRegistrarCross.getDescripcion()));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("frmDatosEjercicioCross:selectEjercicioCross");
            RequestContext.getCurrentInstance().update("frmDatosEjercicioCross:msgSelectEjercicioCross");
        }
    }

    public void seleccionarMedidaEjerCross() {
        if (idMedidaEjercicioCross != -1) {
            medidaEjerCrossRegistrar = medidaEjercicioCrossFacade.find(idMedidaEjercicioCross);
            ejercicioCrossRegistrar.setIdmedidaEjercicioCross(medidaEjerCrossRegistrar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Medida: " + medidaEjerCrossRegistrar.getNombre() + " | descripcion: " + medidaEjerCrossRegistrar.getDescripcion()));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("frmDatosEjercicioCross:selectMedidasEjerCross");
            RequestContext.getCurrentInstance().update("frmDatosEjercicioCross:msgSelectMedidasEjerCross");
        }
    }

    public void registrarGMuscular() {
        GrupoMuscularGeneral gMuscularExistente = grupoMuscularGeneralFacade.findGMuscularGeneralByName(grupoMuscularRegistrarDlg.getNombre());
        if (gMuscularExistente == null) {
            grupoMuscularGeneralFacade.create(grupoMuscularRegistrarDlg);
            RequestContext.getCurrentInstance().addCallbackParam("saved", true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Grupo muscular registrado exitosamente"));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("tabModuloRutina:frmTblGMuscular");
        } else {
            RequestContext.getCurrentInstance().addCallbackParam("saved", false);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El grupo muscular ya existe"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void registrarEjercicioDlg() {
        Ejercicio ejercicioTemp = ejercicioFacade.findEjercicioByName(ejercicioRegistrarDlg.getNombre());
        if (ejercicioTemp == null) {
            ejercicioRegistrarDlg.setTipo("gm");
            ejercicioFacade.create(ejercicioRegistrarDlg);
            RequestContext.getCurrentInstance().addCallbackParam("saved", true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Ejercicio registrado exitosamente"));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaEjercicios");
        } else {
            RequestContext.getCurrentInstance().addCallbackParam("saved", false);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El ejercicio ya existe"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void registrarMedidaDlg() {
        MedidaEjercicioCross medidaTemp = medidaEjercicioCrossFacade.findMedidaEjercicioCrossByName(medidaRegistrarDlg.getNombre());
        if (medidaTemp == null) {
            medidaEjercicioCrossFacade.create(medidaRegistrarDlg);
            RequestContext.getCurrentInstance().addCallbackParam("saved", true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Medida registrada exitosamente"));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaMedidas");
        } else {
            RequestContext.getCurrentInstance().addCallbackParam("saved", false);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "La medida ya existe"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void eliminarGrupoMuscular(GrupoMuscularGeneral gMuscularIn) {
        List<GrupoMuscular> lstGrupoMuscularTemp = gMuscularIn.getGrupoMuscularList();
        if (lstGrupoMuscularTemp.isEmpty()) {
            grupoMuscularGeneralFacade.remove(gMuscularIn);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Grupo muscular eliminado exitosamente"));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("tabModuloRutina:frmTblGMuscular");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El grupo muscular se encuentra registrado en algunas rutinas, primero debe borrar dichas rutinas"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void registrarGCross() {
        GrupoCrossGeneral gCrossExistente = grupoCrossGeneralFacade.findGCrossGeneralByName(gruposCrossGeneralRegistrar.getNombre());
        if (gCrossExistente == null) {
            grupoCrossGeneralFacade.create(gruposCrossGeneralRegistrar);
            RequestContext.getCurrentInstance().addCallbackParam("saved", true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Grupo cross registrado exitosamente"));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaGrupoCross");
        } else {
            RequestContext.getCurrentInstance().addCallbackParam("saved", false);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El grupo cross ya existe"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void seleccionarEditarGrupoCross(GrupoCrossGeneral grupoCrossIn) {
        gruposCrossGeneralEditar = grupoCrossIn;
        RequestContext.getCurrentInstance().update("frmEditarGCross");
    }

    public void eliminarGrupoCross(GrupoCrossGeneral grupoCrossIn) {
        List<GrupoCross> lstGrupoCrossCrud = grupoCrossIn.getGrupoCrossList();
        if (lstGrupoCrossCrud.isEmpty()) {
            grupoCrossGeneralFacade.remove(grupoCrossIn);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Grupo cross eliminado exitosamente"));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaGrupoCross");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El grupo cross se encuentra registrado en algunas rutinas, primero debe borrar dichas rutinas"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void seleccionarEditarEjercicio(Ejercicio ejercicioTbl) {
        ejercicioEditarDlg = ejercicioTbl;
        RequestContext.getCurrentInstance().update("frmEditarEjercicio");
    }

    public void seleccionarEditarMedida(MedidaEjercicioCross medidaIn) {
        medidaEditarDlg = medidaIn;
        RequestContext.getCurrentInstance().update("frmEditarMedida");
    }

    public void eliminarEjercicio(Ejercicio ejercicioTbl) {
        List<EjercicioGm> lstEjercicioGmTemp = ejercicioTbl.getEjercicioGmList();
        List<EjercicioCross> lstEjercicioCrossTemp = ejercicioTbl.getEjercicioCrossList();
        if (lstEjercicioGmTemp.isEmpty() && lstEjercicioCrossTemp.isEmpty()) {
            ejercicioFacade.remove(ejercicioTbl);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "El ejercicio se ha eliminado exitosamente"));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaEjercicios");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "El ejercicio se encuentra registrado en algunas rutinas, primero debe borrar dichas rutinas"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void eliminarMedida(MedidaEjercicioCross medidaIn) {
        List<EjercicioCross> lstEjercicioCrossTemp = medidaIn.getEjercicioCrossList();
        if (lstEjercicioCrossTemp.isEmpty()) {
            medidaEjercicioCrossFacade.remove(medidaIn);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "La medida se ha eliminado exitosamente"));
            RequestContext.getCurrentInstance().update("frmGrowl");
            RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaMedidas");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "La medida se encuentra registrada en algunas rutinas, primero debe borrar dichas rutinas"));
            RequestContext.getCurrentInstance().update("frmGrowl");
        }
    }

    public void seleccionarEditarRutina(Rutina rutinaIn) {
        accionRutina = "editar";
        usuRutinaRegistrar = rutinaIn.getIdusuario();
        fechaInicioRutina = rutinaIn.getFechaInicio();
        fechaFinRutina = rutinaIn.getFechaFin();
        rutinaRegistrar = rutinaIn;
        lstDiasRutina = diaFacade.findDiaByIdRutina(rutinaIn.getIdRutina());
        lstDiasTemp = diaFacade.findDiaByIdRutina(rutinaIn.getIdRutina());
        RequestContext.getCurrentInstance().update("frmDatosRutina");
    }

    public void seleccionarCopiarRutina(Rutina rutinaIn) {
        accionRutina = "crear";
        usuRutinaRegistrar = rutinaIn.getIdusuario();
        fechaInicioRutina = rutinaIn.getFechaInicio();
        fechaFinRutina = rutinaIn.getFechaFin();
        rutinaRegistrar = rutinaIn;
        lstDiasRutina = rutinaRegistrar.getDiaList();
        RequestContext.getCurrentInstance().update("frmDatosRutina");
    }

    public String getMyFormattedDate(Date myDate) {
        if (myDate != null) {
            return new SimpleDateFormat("dd/MM/yyyy").format(myDate);
        } else {
            return "";
        }
    }

    public void seleccionarEliminarRutina(Rutina rutinaIn) {
        rutinaFacade.remove(rutinaIn);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "La rutina se ha eliminado exitosamente"));
        RequestContext.getCurrentInstance().update("frmGrowl");
        RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaRutina");
    }

    public void seleccionarEditarGrupoMuscular(GrupoMuscularGeneral gMuscularIn) {
        grupoMuscularEditarDlg = gMuscularIn;
        RequestContext.getCurrentInstance().update("frmEditarGMuscular");
    }

    public void editarGMuscular() {
        grupoMuscularGeneralFacade.edit(grupoMuscularEditarDlg);
        RequestContext.getCurrentInstance().addCallbackParam("edited", true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Grupo muscular editado exitosamente"));
        RequestContext.getCurrentInstance().update("frmGrowl");
        RequestContext.getCurrentInstance().update("tabModuloRutina:frmTblGMuscular");
    }

    public void editarEjercicio() {
        ejercicioFacade.edit(ejercicioEditarDlg);
        RequestContext.getCurrentInstance().addCallbackParam("edited", true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Grupo muscular editado exitosamente"));
        RequestContext.getCurrentInstance().update("frmGrowl");
        RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaEjercicios");
    }

    public void editarMedidaDlg() {
        medidaEjercicioCrossFacade.edit(medidaEditarDlg);
        RequestContext.getCurrentInstance().addCallbackParam("edited", true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Medida editada exitosamente"));
        RequestContext.getCurrentInstance().update("frmGrowl");
        RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaMedidas");
    }

    public void editarGCross() {
        grupoCrossGeneralFacade.edit(gruposCrossGeneralEditar);
        RequestContext.getCurrentInstance().addCallbackParam("edited", true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Grupo cross editado exitosamente"));
        RequestContext.getCurrentInstance().update("frmGrowl");
        RequestContext.getCurrentInstance().update("tabModuloRutina:frmTablaGrupoCross");
    }

    public void crearRutina() {
        accionRutina = "crear";
        lstDiasRutina = new ArrayList<>();
        RequestContext.getCurrentInstance().update("frmDatosRutina");
    }

    public void verRutina(Rutina rutinaIn) {
        rutinaVisualizar = rutinaIn;
        generarPdf();
        RequestContext.getCurrentInstance().update("frmVerRutina");
    }

    public double obtenerPeso(List<Evaluacion> lstEvalIn) {
        if (lstEvalIn != null) {
            int tamanio = lstEvalIn.size();
            return lstEvalIn.get(tamanio).getPeso().doubleValue();
        } else {
            return -1;
        }
    }

    public double obtenerImc(Usuario usuIn) {
        double estatura = -1;
        double peso = -1;
        if(usuIn != null && usuIn.getMedidasGenerales() != null){
            estatura = usuIn.getMedidasGenerales().getEstatura().doubleValue();
            peso = obtenerPeso(usuIn.getEvaluacionList());
        }                
        return peso / Math.pow(estatura, 2);
    }

    public void imprimirRutina() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "La impresión de la rutina comenzará en unos momentos"));
        RequestContext.getCurrentInstance().update("frmGrowl");
    }

    public List<Rutina> getLstRutinasDisponibles() {
        return lstRutinasDisponibles = rutinaFacade.findAll();
    }

    public void setLstRutinasDisponibles(List<Rutina> lstRutinasDisponibles) {
        this.lstRutinasDisponibles = lstRutinasDisponibles;
    }

    public Usuario getUsuRutinaRegistrar() {
        return usuRutinaRegistrar;
    }

    public void setUsuRutinaRegistrar(Usuario usuRutinaRegistrar) {
        this.usuRutinaRegistrar = usuRutinaRegistrar;
    }

    public List<Dia> getLstDiasRutina() {
        return lstDiasRutina;
    }

    public void setLstDiasRutina(List<Dia> lstDiasRutina) {
        this.lstDiasRutina = lstDiasRutina;
    }

    public Rutina getRutinaRegistrar() {
        return rutinaRegistrar;
    }

    public void setRutinaRegistrar(Rutina rutinaRegistrar) {
        this.rutinaRegistrar = rutinaRegistrar;
    }

    public Date getFechaInicioRutina() {
        return fechaInicioRutina;
    }

    public void setFechaInicioRutina(Date fechaInicioRutina) {
        this.fechaInicioRutina = fechaInicioRutina;
    }

    public Date getFechaFinRutina() {
        return fechaFinRutina;
    }

    public void setFechaFinRutina(Date fechaFinRutina) {
        this.fechaFinRutina = fechaFinRutina;
    }

    public GrupoMuscularGeneral getGrupoMuscRegistrar() {
        return grupoMuscRegistrar;
    }

    public void setGrupoMuscRegistrar(GrupoMuscularGeneral grupoMuscRegistrar) {
        this.grupoMuscRegistrar = grupoMuscRegistrar;
    }

    public List<GrupoMuscularGeneral> getLstGruposMusculares() {
        return lstGruposMusculares = grupoMuscularGeneralFacade.findAll();
    }

    public void setLstGruposMusculares(List<GrupoMuscularGeneral> lstGruposMusculares) {
        this.lstGruposMusculares = lstGruposMusculares;
    }

    public Dia getDiaSeleccionado() {
        return diaSeleccionado;
    }

    public void setDiaSeleccionado(Dia diaSeleccionado) {
        this.diaSeleccionado = diaSeleccionado;
    }

    public List<GrupoMuscular> getLstGruposMuscularesDia() {
        return lstGruposMuscularesDia;
    }

    public void setLstGruposMuscularesDia(List<GrupoMuscular> lstGruposMuscularesDia) {
        this.lstGruposMuscularesDia = lstGruposMuscularesDia;
    }

    public List<Ejercicio> getLstEjercicios() {
        return lstEjercicios = ejercicioFacade.findAll();
    }

    public void setLstEjercicios(List<Ejercicio> lstEjercicios) {
        this.lstEjercicios = lstEjercicios;
    }

    public List<EjercicioGm> getLstEjerciciosGMuscular() {
        return lstEjerciciosGMuscular;
    }

    public void setLstEjerciciosGMuscular(List<EjercicioGm> lstEjerciciosGMuscular) {
        this.lstEjerciciosGMuscular = lstEjerciciosGMuscular;
    }

    public GrupoMuscular getGrupoMuscSeleccionado() {
        return grupoMuscSeleccionado;
    }

    public void setGrupoMuscSeleccionado(GrupoMuscular grupoMuscSeleccionado) {
        this.grupoMuscSeleccionado = grupoMuscSeleccionado;
    }

    public EjercicioGm getEjercicioGmRegistrar() {
        return ejercicioGmRegistrar;
    }

    public void setEjercicioGmRegistrar(EjercicioGm ejercicioGmRegistrar) {
        this.ejercicioGmRegistrar = ejercicioGmRegistrar;
    }

    public Ejercicio getEjercicioRegistrar() {
        return ejercicioRegistrar;
    }

    public void setEjercicioRegistrar(Ejercicio ejercicioRegistrar) {
        this.ejercicioRegistrar = ejercicioRegistrar;
    }

    public GrupoMuscularGeneral getGrupoMuscularRegistrarDlg() {
        return grupoMuscularRegistrarDlg;
    }

    public void setGrupoMuscularRegistrarDlg(GrupoMuscularGeneral grupoMuscularRegistrarDlg) {
        this.grupoMuscularRegistrarDlg = grupoMuscularRegistrarDlg;
    }

    public GrupoMuscularGeneral getGrupoMuscularEditarDlg() {
        return grupoMuscularEditarDlg;
    }

    public void setGrupoMuscularEditarDlg(GrupoMuscularGeneral grupoMuscularEditarDlg) {
        this.grupoMuscularEditarDlg = grupoMuscularEditarDlg;
    }

    public Rutina getRutinaVisualizar() {
        return rutinaVisualizar;
    }

    public void setRutinaVisualizar(Rutina rutinaVisualizar) {
        this.rutinaVisualizar = rutinaVisualizar;
    }

    public List<GrupoCrossGeneral> getLstGrupoCross() {
        return lstGrupoCross = grupoCrossGeneralFacade.findAll();
    }

    public void setLstGrupoCross(List<GrupoCrossGeneral> lstGrupoCross) {
        this.lstGrupoCross = lstGrupoCross;
    }

    public GrupoCrossGeneral getGruposCrossGeneralEditar() {
        return gruposCrossGeneralEditar;
    }

    public void setGruposCrossGeneralEditar(GrupoCrossGeneral gruposCrossGeneralEditar) {
        this.gruposCrossGeneralEditar = gruposCrossGeneralEditar;
    }

    public GrupoCrossGeneral getGruposCrossGeneralRegistrar() {
        return gruposCrossGeneralRegistrar;
    }

    public void setGruposCrossGeneralRegistrar(GrupoCrossGeneral gruposCrossGeneralRegistrar) {
        this.gruposCrossGeneralRegistrar = gruposCrossGeneralRegistrar;
    }

    public GrupoCrossGeneral getGrupoCrossRegistrar() {
        return grupoCrossRegistrar;
    }

    public void setGrupoCrossRegistrar(GrupoCrossGeneral grupoCrossRegistrar) {
        this.grupoCrossRegistrar = grupoCrossRegistrar;
    }

    public List<GrupoCross> getLstGruposCrossDia() {
        return lstGruposCrossDia;
    }

    public void setLstGruposCrossDia(List<GrupoCross> lstGruposCrossDia) {
        this.lstGruposCrossDia = lstGruposCrossDia;
    }

    public EjercicioCross getEjercicioCrossRegistrar() {
        return ejercicioCrossRegistrar;
    }

    public void setEjercicioCrossRegistrar(EjercicioCross ejercicioCrossRegistrar) {
        this.ejercicioCrossRegistrar = ejercicioCrossRegistrar;
    }

    public GrupoCross getGrupoCrossSeleccionado() {
        return grupoCrossSeleccionado;
    }

    public void setGrupoCrossSeleccionado(GrupoCross grupoCrossSeleccionado) {
        this.grupoCrossSeleccionado = grupoCrossSeleccionado;
    }

    public List<EjercicioCross> getLstEjerciciosGCross() {
        return lstEjerciciosGCross;
    }

    public void setLstEjerciciosGCross(List<EjercicioCross> lstEjerciciosGCross) {
        this.lstEjerciciosGCross = lstEjerciciosGCross;
    }

    public Ejercicio getEjercicioRegistrarCross() {
        return ejercicioRegistrarCross;
    }

    public void setEjercicioRegistrarCross(Ejercicio ejercicioRegistrarCross) {
        this.ejercicioRegistrarCross = ejercicioRegistrarCross;
    }

    public Ejercicio getEjercicioRegistrarDlg() {
        return ejercicioRegistrarDlg;
    }

    public void setEjercicioRegistrarDlg(Ejercicio ejercicioRegistrarDlg) {
        this.ejercicioRegistrarDlg = ejercicioRegistrarDlg;
    }

    public Ejercicio getEjercicioEditarDlg() {
        return ejercicioEditarDlg;
    }

    public void setEjercicioEditarDlg(Ejercicio ejercicioEditarDlg) {
        this.ejercicioEditarDlg = ejercicioEditarDlg;
    }

    public int getIdGrupoMuscRegistrar() {
        return idGrupoMuscRegistrar;
    }

    public void setIdGrupoMuscRegistrar(int idGrupoMuscRegistrar) {
        this.idGrupoMuscRegistrar = idGrupoMuscRegistrar;
    }

    public int getIdGrupoCrossRegistrar() {
        return idGrupoCrossRegistrar;
    }

    public void setIdGrupoCrossRegistrar(int idGrupoCrossRegistrar) {
        this.idGrupoCrossRegistrar = idGrupoCrossRegistrar;
    }

    public int getIdEjercicioRegistrarCross() {
        return idEjercicioRegistrarCross;
    }

    public void setIdEjercicioRegistrarCross(int idEjercicioRegistrarCross) {
        this.idEjercicioRegistrarCross = idEjercicioRegistrarCross;
    }

    public SelectOneMenu getSelEjerCross() {
        return selEjerCross;
    }

    public void setSelEjerCross(SelectOneMenu selEjerCross) {
        this.selEjerCross = selEjerCross;
    }

    public int getIdEjercicioRegistrarGm() {
        return idEjercicioRegistrarGm;
    }

    public void setIdEjercicioRegistrarGm(int idEjercicioRegistrarGm) {
        this.idEjercicioRegistrarGm = idEjercicioRegistrarGm;
    }

    public SelectOneMenu getSelEjerGm() {
        return selEjerGm;
    }

    public void setSelEjerGm(SelectOneMenu selEjerGm) {
        this.selEjerGm = selEjerGm;
    }

    public SelectOneMenu getSelGrupoCross() {
        return selGrupoCross;
    }

    public void setSelGrupoCross(SelectOneMenu selGrupoCross) {
        this.selGrupoCross = selGrupoCross;
    }

    public SelectOneMenu getSelGrupoGm() {
        return selGrupoGm;
    }

    public void setSelGrupoGm(SelectOneMenu selGrupoGm) {
        this.selGrupoGm = selGrupoGm;
    }

    public int getIdMedidaEjercicioCross() {
        return idMedidaEjercicioCross;
    }

    public void setIdMedidaEjercicioCross(int idMedidaEjercicioCross) {
        this.idMedidaEjercicioCross = idMedidaEjercicioCross;
    }

    public List<MedidaEjercicioCross> getLstMedidasEjercicioCross() {
        return lstMedidasEjercicioCross = medidaEjercicioCrossFacade.findAll();
    }

    public void setLstMedidasEjercicioCross(List<MedidaEjercicioCross> lstMedidasEjercicioCross) {
        this.lstMedidasEjercicioCross = lstMedidasEjercicioCross;
    }

    public SelectOneMenu getSelMedidaEjerCross() {
        return selMedidaEjerCross;
    }

    public void setSelMedidaEjerCross(SelectOneMenu selMedidaEjerCross) {
        this.selMedidaEjerCross = selMedidaEjerCross;
    }

    public MedidaEjercicioCross getMedidaEditarDlg() {
        return medidaEditarDlg;
    }

    public void setMedidaEditarDlg(MedidaEjercicioCross medidaEditarDlg) {
        this.medidaEditarDlg = medidaEditarDlg;
    }

    public MedidaEjercicioCross getMedidaRegistrarDlg() {
        return medidaRegistrarDlg;
    }

    public void setMedidaRegistrarDlg(MedidaEjercicioCross medidaRegistrarDlg) {
        this.medidaRegistrarDlg = medidaRegistrarDlg;
    }

    public String getPdfFileName() {
        return pdfFileName;
    }

    public void setPdfFileName(String pdfFileName) {
        this.pdfFileName = pdfFileName;
    }
}
