/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asae.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "evaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e"),
    @NamedQuery(name = "Evaluacion.findByIdEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.idEvaluacion = :idEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByPeso", query = "SELECT e FROM Evaluacion e WHERE e.peso = :peso"),
    @NamedQuery(name = "Evaluacion.findByFechaIngreso", query = "SELECT e FROM Evaluacion e WHERE e.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Evaluacion.findByFechaSigEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.fechaSigEvaluacion = :fechaSigEvaluacion"),
    @NamedQuery(name = "Evaluacion.findByAbdomenInferior", query = "SELECT e FROM Evaluacion e WHERE e.abdomenInferior = :abdomenInferior"),
    @NamedQuery(name = "Evaluacion.findByCadera", query = "SELECT e FROM Evaluacion e WHERE e.cadera = :cadera"),
    @NamedQuery(name = "Evaluacion.findByBicepsRelajadoDer", query = "SELECT e FROM Evaluacion e WHERE e.bicepsRelajadoDer = :bicepsRelajadoDer"),
    @NamedQuery(name = "Evaluacion.findByBicepsRelajadoIzq", query = "SELECT e FROM Evaluacion e WHERE e.bicepsRelajadoIzq = :bicepsRelajadoIzq"),
    @NamedQuery(name = "Evaluacion.findBySesionesSemana", query = "SELECT e FROM Evaluacion e WHERE e.sesionesSemana = :sesionesSemana"),
    @NamedQuery(name = "Evaluacion.findBySemanaPrograma", query = "SELECT e FROM Evaluacion e WHERE e.semanaPrograma = :semanaPrograma"),
    @NamedQuery(name = "Evaluacion.findByBicepsContraidoDer", query = "SELECT e FROM Evaluacion e WHERE e.bicepsContraidoDer = :bicepsContraidoDer"),
    @NamedQuery(name = "Evaluacion.findByTorax", query = "SELECT e FROM Evaluacion e WHERE e.torax = :torax"),
    @NamedQuery(name = "Evaluacion.findByBicepsContraidoIzq", query = "SELECT e FROM Evaluacion e WHERE e.bicepsContraidoIzq = :bicepsContraidoIzq"),
    @NamedQuery(name = "Evaluacion.findByMusloSuperiorDer", query = "SELECT e FROM Evaluacion e WHERE e.musloSuperiorDer = :musloSuperiorDer"),
    @NamedQuery(name = "Evaluacion.findByMusloSuperiorIzq", query = "SELECT e FROM Evaluacion e WHERE e.musloSuperiorIzq = :musloSuperiorIzq"),
    @NamedQuery(name = "Evaluacion.findByPantorrillaDer", query = "SELECT e FROM Evaluacion e WHERE e.pantorrillaDer = :pantorrillaDer"),
    @NamedQuery(name = "Evaluacion.findByPantorrillaIzq", query = "SELECT e FROM Evaluacion e WHERE e.pantorrillaIzq = :pantorrillaIzq"),
    @NamedQuery(name = "Evaluacion.findByAbdominal", query = "SELECT e FROM Evaluacion e WHERE e.abdominal = :abdominal"),
    @NamedQuery(name = "Evaluacion.findByGrasaImpedanciometria", query = "SELECT e FROM Evaluacion e WHERE e.grasaImpedanciometria = :grasaImpedanciometria"),
    @NamedQuery(name = "Evaluacion.findByMedialPierna", query = "SELECT e FROM Evaluacion e WHERE e.medialPierna = :medialPierna"),
    @NamedQuery(name = "Evaluacion.findByMuslo", query = "SELECT e FROM Evaluacion e WHERE e.muslo = :muslo"),
    @NamedQuery(name = "Evaluacion.findByPectoral", query = "SELECT e FROM Evaluacion e WHERE e.pectoral = :pectoral"),
    @NamedQuery(name = "Evaluacion.findBySubescapular", query = "SELECT e FROM Evaluacion e WHERE e.subescapular = :subescapular"),
    @NamedQuery(name = "Evaluacion.findBySuprailiaco", query = "SELECT e FROM Evaluacion e WHERE e.suprailiaco = :suprailiaco"),
    @NamedQuery(name = "Evaluacion.findByTriceps", query = "SELECT e FROM Evaluacion e WHERE e.triceps = :triceps"),
    @NamedQuery(name = "Evaluacion.findByFrecuenciaReposo", query = "SELECT e FROM Evaluacion e WHERE e.frecuenciaReposo = :frecuenciaReposo")})
public class Evaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EVALUACION")
    private Integer idEvaluacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO")
    private BigDecimal peso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SIG_EVALUACION")
    @Temporal(TemporalType.DATE)
    private Date fechaSigEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABDOMEN_INFERIOR")
    private BigDecimal abdomenInferior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CADERA")
    private BigDecimal cadera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BICEPS_RELAJADO_DER")
    private BigDecimal bicepsRelajadoDer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BICEPS_RELAJADO_IZQ")
    private BigDecimal bicepsRelajadoIzq;
    @Column(name = "SESIONES_SEMANA")
    private Integer sesionesSemana;
    @Column(name = "SEMANA_PROGRAMA")
    private Integer semanaPrograma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BICEPS_CONTRAIDO_DER")
    private BigDecimal bicepsContraidoDer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TORAX")
    private BigDecimal torax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BICEPS_CONTRAIDO_IZQ")
    private BigDecimal bicepsContraidoIzq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MUSLO_SUPERIOR_DER")
    private BigDecimal musloSuperiorDer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MUSLO_SUPERIOR_IZQ")
    private BigDecimal musloSuperiorIzq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PANTORRILLA_DER")
    private BigDecimal pantorrillaDer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PANTORRILLA_IZQ")
    private BigDecimal pantorrillaIzq;
    @Column(name = "ABDOMINAL")
    private BigDecimal abdominal;
    @Column(name = "GRASA_IMPEDANCIOMETRIA")
    private BigDecimal grasaImpedanciometria;
    @Column(name = "MEDIAL_PIERNA")
    private BigDecimal medialPierna;
    @Column(name = "MUSLO")
    private BigDecimal muslo;
    @Column(name = "PECTORAL")
    private BigDecimal pectoral;
    @Column(name = "SUBESCAPULAR")
    private BigDecimal subescapular;
    @Column(name = "SUPRAILIACO")
    private BigDecimal suprailiaco;
    @Column(name = "TRICEPS")
    private BigDecimal triceps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FRECUENCIA_REPOSO")
    private BigDecimal frecuenciaReposo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "ID_TECNICO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idTecnico;

    public Evaluacion() {
    }

    public Evaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Evaluacion(Integer idEvaluacion, Date fechaIngreso, Date fechaSigEvaluacion, BigDecimal abdomenInferior, BigDecimal cadera, BigDecimal bicepsRelajadoDer, BigDecimal bicepsRelajadoIzq, BigDecimal bicepsContraidoDer, BigDecimal torax, BigDecimal bicepsContraidoIzq, BigDecimal musloSuperiorDer, BigDecimal musloSuperiorIzq, BigDecimal pantorrillaDer, BigDecimal pantorrillaIzq, BigDecimal frecuenciaReposo) {
        this.idEvaluacion = idEvaluacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSigEvaluacion = fechaSigEvaluacion;
        this.abdomenInferior = abdomenInferior;
        this.cadera = cadera;
        this.bicepsRelajadoDer = bicepsRelajadoDer;
        this.bicepsRelajadoIzq = bicepsRelajadoIzq;
        this.bicepsContraidoDer = bicepsContraidoDer;
        this.torax = torax;
        this.bicepsContraidoIzq = bicepsContraidoIzq;
        this.musloSuperiorDer = musloSuperiorDer;
        this.musloSuperiorIzq = musloSuperiorIzq;
        this.pantorrillaDer = pantorrillaDer;
        this.pantorrillaIzq = pantorrillaIzq;
        this.frecuenciaReposo = frecuenciaReposo;
    }

    public Integer getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(Integer idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSigEvaluacion() {
        return fechaSigEvaluacion;
    }

    public void setFechaSigEvaluacion(Date fechaSigEvaluacion) {
        this.fechaSigEvaluacion = fechaSigEvaluacion;
    }

    public BigDecimal getAbdomenInferior() {
        return abdomenInferior;
    }

    public void setAbdomenInferior(BigDecimal abdomenInferior) {
        this.abdomenInferior = abdomenInferior;
    }

    public BigDecimal getCadera() {
        return cadera;
    }

    public void setCadera(BigDecimal cadera) {
        this.cadera = cadera;
    }

    public BigDecimal getBicepsRelajadoDer() {
        return bicepsRelajadoDer;
    }

    public void setBicepsRelajadoDer(BigDecimal bicepsRelajadoDer) {
        this.bicepsRelajadoDer = bicepsRelajadoDer;
    }

    public BigDecimal getBicepsRelajadoIzq() {
        return bicepsRelajadoIzq;
    }

    public void setBicepsRelajadoIzq(BigDecimal bicepsRelajadoIzq) {
        this.bicepsRelajadoIzq = bicepsRelajadoIzq;
    }

    public Integer getSesionesSemana() {
        return sesionesSemana;
    }

    public void setSesionesSemana(Integer sesionesSemana) {
        this.sesionesSemana = sesionesSemana;
    }

    public Integer getSemanaPrograma() {
        return semanaPrograma;
    }

    public void setSemanaPrograma(Integer semanaPrograma) {
        this.semanaPrograma = semanaPrograma;
    }

    public BigDecimal getBicepsContraidoDer() {
        return bicepsContraidoDer;
    }

    public void setBicepsContraidoDer(BigDecimal bicepsContraidoDer) {
        this.bicepsContraidoDer = bicepsContraidoDer;
    }

    public BigDecimal getTorax() {
        return torax;
    }

    public void setTorax(BigDecimal torax) {
        this.torax = torax;
    }

    public BigDecimal getBicepsContraidoIzq() {
        return bicepsContraidoIzq;
    }

    public void setBicepsContraidoIzq(BigDecimal bicepsContraidoIzq) {
        this.bicepsContraidoIzq = bicepsContraidoIzq;
    }

    public BigDecimal getMusloSuperiorDer() {
        return musloSuperiorDer;
    }

    public void setMusloSuperiorDer(BigDecimal musloSuperiorDer) {
        this.musloSuperiorDer = musloSuperiorDer;
    }

    public BigDecimal getMusloSuperiorIzq() {
        return musloSuperiorIzq;
    }

    public void setMusloSuperiorIzq(BigDecimal musloSuperiorIzq) {
        this.musloSuperiorIzq = musloSuperiorIzq;
    }

    public BigDecimal getPantorrillaDer() {
        return pantorrillaDer;
    }

    public void setPantorrillaDer(BigDecimal pantorrillaDer) {
        this.pantorrillaDer = pantorrillaDer;
    }

    public BigDecimal getPantorrillaIzq() {
        return pantorrillaIzq;
    }

    public void setPantorrillaIzq(BigDecimal pantorrillaIzq) {
        this.pantorrillaIzq = pantorrillaIzq;
    }

    public BigDecimal getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(BigDecimal abdominal) {
        this.abdominal = abdominal;
    }

    public BigDecimal getGrasaImpedanciometria() {
        return grasaImpedanciometria;
    }

    public void setGrasaImpedanciometria(BigDecimal grasaImpedanciometria) {
        this.grasaImpedanciometria = grasaImpedanciometria;
    }

    public BigDecimal getMedialPierna() {
        return medialPierna;
    }

    public void setMedialPierna(BigDecimal medialPierna) {
        this.medialPierna = medialPierna;
    }

    public BigDecimal getMuslo() {
        return muslo;
    }

    public void setMuslo(BigDecimal muslo) {
        this.muslo = muslo;
    }

    public BigDecimal getPectoral() {
        return pectoral;
    }

    public void setPectoral(BigDecimal pectoral) {
        this.pectoral = pectoral;
    }

    public BigDecimal getSubescapular() {
        return subescapular;
    }

    public void setSubescapular(BigDecimal subescapular) {
        this.subescapular = subescapular;
    }

    public BigDecimal getSuprailiaco() {
        return suprailiaco;
    }

    public void setSuprailiaco(BigDecimal suprailiaco) {
        this.suprailiaco = suprailiaco;
    }

    public BigDecimal getTriceps() {
        return triceps;
    }

    public void setTriceps(BigDecimal triceps) {
        this.triceps = triceps;
    }

    public BigDecimal getFrecuenciaReposo() {
        return frecuenciaReposo;
    }

    public void setFrecuenciaReposo(BigDecimal frecuenciaReposo) {
        this.frecuenciaReposo = frecuenciaReposo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Usuario idTecnico) {
        this.idTecnico = idTecnico;
    }
    /*Datos Calculados*/
    @Transient
    private String imc;
    @Transient
    private String sumPliegue;
    @Transient
    private String pesoIdeal;
    @Transient
    private String pesoIdealMin;
    @Transient
    private String pesoIdealMax;
    @Transient
    private String pesoTotal;
    @Transient
    private String porcGrasaTot;
    @Transient
    private String porcGrasaIdeal;
    @Transient
    private String pesoGraso;
    @Transient
    private String pesoOseo;
    @Transient
    private String porcPesoOseo;
    @Transient
    private String pesoMusc;
    @Transient
    private String pesoMuscIdeal;
    @Transient
    private String porcPesoMusc;
    @Transient
    private String porcPesoMuscIdeal;
    @Transient
    private String pesoResidual;
    @Transient
    private String porcPesoResidual;
    @Transient
    private String masaCorpMagra;
    @Transient
    private String masaCorpMagraIdeal;
    @Transient
    private String porcPesoOseoIdeal;
    @Transient
    private String relacionCinturaCadera;
    //datos calculados de nutricion
    @Transient
    private String caloriasDieta;
    @Transient
    private String quemaCalorias;
    @Transient
    private String quemaCaloriasSesion;
    @Transient
    private String rangosFrecuencia;
    @Transient
    private String pesoAnterior;
    @Transient
    private String porcGrasaAnterior;
    @Transient
    private String pesoMuscAnterior;

    private String numAleatorio() {
        return String.valueOf(Math.random() * 4);
    }
    /*metodos para datos calculaddos*/

    public void calc_imc() {
        double formula = -1e9;
        try {
            double Pe = peso.doubleValue();
            double Es = getIdUsuario().getMedidasGenerales().getEstatura().doubleValue();
            formula = Pe / (Math.pow(Es, 2));
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_sumPliegue");
            e.printStackTrace();
        }
        this.sumPliegue = String.valueOf(formula);
    }

    public void calc_sumPliegue() {
        double formula = -1e9;
        try {
            double Tr = this.triceps.doubleValue();
            double Sub = this.subescapular.doubleValue();
            double Sup = this.suprailiaco.doubleValue();
            double Ab = this.abdominal.doubleValue();
            double Mus = this.muslo.doubleValue();
            double Med = this.medialPierna.doubleValue();
            formula = Tr + Sub + Sup + Ab + Mus + Med;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_sumPliegue");
            e.printStackTrace();
        }
        this.sumPliegue = String.valueOf(formula);
    }

    public void calc_pesoIdeal() {
        double formula = -1e9;
        try {
            MedidasGenerales m = this.getIdUsuario().getMedidasGenerales();
            double PeT = Double.valueOf(this.pesoTotal);
            double pgrCorT = Double.valueOf(this.porcGrasaTot);
            double pgrId = Double.valueOf(this.porcGrasaIdeal);
            double MasCMId = Double.valueOf(this.masaCorpMagraIdeal);
            double MasCM = Double.valueOf(this.masaCorpMagra);
            formula = (PeT * (100 - pgrCorT) / (100 - pgrId)) + MasCMId - MasCM;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_pesoIdeal");
            e.printStackTrace();
        }
        this.pesoIdeal = String.valueOf(formula);
    }

    public void calc_pesoTotal() {
        double formula = -1e9;
        try {
            double Pe = this.peso.doubleValue();
            formula = Pe;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_pesoTotal");
            e.printStackTrace();
        }
        this.pesoTotal = String.valueOf(formula);
    }

    public void calc_porcGrasaTot() {
        double formula = -1e9;
        try {
            double Pe = this.peso.doubleValue();
            double pgrCT = Double.valueOf(this.pesoTotal);
            formula = Pe * pgrCT / 100.0;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_porcGrasaTot");
            e.printStackTrace();
        }
        this.porcGrasaTot = String.valueOf(formula);
    }

    public void calc_porcGrasaIdeal() {
        double formula = -1e9;
        try {
            double PeI = Double.valueOf(this.pesoTotal);
            double pgrId = Double.valueOf(this.porcGrasaTot);
            formula = PeI * (pgrId / 100.0);
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_porcGrasaIdeal");
            e.printStackTrace();
        }
        this.porcGrasaIdeal = String.valueOf(formula);
    }

    public void calc_pesoOseo() {
        double formula = -1e9;
        try {
            MedidasGenerales m = this.getIdUsuario().getMedidasGenerales();
            double est = m.getEstatura().doubleValue();
            double radC = m.getRadioCubital().doubleValue();
            double bieF = m.getBiepicondilarFemoral().doubleValue();
            formula = Math.pow(3.02 * (Math.pow(est, 2) * (radC / 100.0) * (bieF / 100.0) * 400), 0.712);
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_pesoOseo");
            e.printStackTrace();
        }
        this.pesoOseo = String.valueOf(formula);
    }

    public void calc_porcPesoOseo() {
        double formula = -1e9;
        try {
            double Pe = peso.doubleValue();
            double PeO = Double.valueOf(this.pesoOseo);
            formula = PeO * 100 / Pe;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_porcPesoOseo");
            e.printStackTrace();
        }
        this.porcPesoOseo = String.valueOf(formula);
    }

    public void calc_pesoMusc() {
        double formula = -1e9;
        try {
            double Pe = peso.doubleValue();
            double PeGr = Double.valueOf(this.pesoIdeal);
            double PeRes = Double.valueOf(this.pesoResidual);
            double PeO = Double.valueOf(this.pesoOseo);
            formula = Pe - (PeGr + PeRes + PeO);
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_pesoMusc");
            e.printStackTrace();
        }
        this.pesoMusc = String.valueOf(formula);
    }

    public void calc_pesoMuscIdeal() {
        double formula = -1e9;
        try {
            double PeId = Double.valueOf(this.pesoIdeal);
            double PeGrId = Double.valueOf(this.pesoIdeal);
            double PeRes = Double.valueOf(this.pesoResidual);
            double PeO = Double.valueOf(this.pesoOseo);
            formula = PeId - (PeGrId + PeRes + PeO);
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_pesoMuscIdeal");
            e.printStackTrace();
        }
        this.pesoMuscIdeal = String.valueOf(formula);
    }

    public void calc_porcPesoMusc() {
        double formula = -1e9;
        try {
            double Pe = peso.doubleValue();
            double PeM = Double.valueOf(this.pesoMusc);
            formula = PeM * 100 / Pe;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_porcPesoMusc");
            e.printStackTrace();
        }
        this.porcPesoMusc = String.valueOf(formula);
    }

    public void calc_porcPesoMuscIdeal() {
        double formula = -1e9;
        try {
            double pPeRes = Double.valueOf(this.porcPesoResidual);
            double pGrId = Double.valueOf(this.porcGrasaIdeal);
            double pOsId = Double.valueOf(this.porcPesoOseoIdeal);
            formula = 100.0 - pPeRes - pGrId - pOsId;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_porcPesoMuscIdeal");
            e.printStackTrace();
        }
        this.porcPesoMuscIdeal = String.valueOf(formula);
    }

    public void calc_pesoResidual() {
        double formula = -1e9;
        try {
            MedidasGenerales m = this.getIdUsuario().getMedidasGenerales();
            double PeT = Double.valueOf(this.pesoTotal);
            String sexo = m.getSexo();
            if (sexo.toLowerCase().contains("f")) {
                formula = PeT * 20.9 / 100.0;
            } else {
                formula = PeT * 24.1 / 100;
            }
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_pesoResidual");
            e.printStackTrace();
        }
        this.pesoResidual = String.valueOf(formula);
    }

    public void calc_porcPesoResidual() {
        double formula = -1e9;
        try {
            MedidasGenerales m = this.getIdUsuario().getMedidasGenerales();
            String sexo = m.getSexo();
            if (sexo.toLowerCase().contains("f")) {
                formula = 20.9;
            } else {
                formula = 24.1;
            }
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_porcPesoResidual");
            e.printStackTrace();
        }
        this.porcPesoResidual = String.valueOf(formula);
    }

    public void calc_masaCorpMagra() {
        double formula = -1e9;
        try {
            double PeT = peso.doubleValue();
            double PeGr = Double.valueOf(this.pesoTotal);
            formula = PeT - PeGr;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_masaCorpMagra");
            e.printStackTrace();
        }
        this.masaCorpMagra = String.valueOf(formula);
    }

    public void calc_masaCorpMagraIdeal() {
        double formula = -1e9;
        try {
            formula = -1000000000;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_masaCorpMagraIdeal");
            e.printStackTrace();
        }
        this.masaCorpMagraIdeal = String.valueOf(formula);
    }

    public void calc_porcPesoOseoIdeal() {
        double formula = -1e9;
        try {
            double PeO = Double.valueOf(this.pesoOseo);
            double PeId = Double.valueOf(this.pesoIdeal);
            formula = PeO / PeId * 100;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_porcPesoOseoIdeal");
            e.printStackTrace();
        }
        this.porcPesoOseoIdeal = String.valueOf(formula);
    }

    public void calc_relacionCinturaCadera() {
        double formula = -1e9;
        try {
            System.out.print("este es el valor de abdomenInferior: ");
            System.out.print(getAbdomenInferior());
            System.out.print("termina...");
            double Abi = getAbdomenInferior().doubleValue();
            double Ca = this.cadera.doubleValue();
            formula = Abi / Ca;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_relacionCinturaCadera");
            e.printStackTrace();
        }
        this.relacionCinturaCadera = String.valueOf(formula);
    }
    /*terminan metodos para datos calculaddos*/

    /*inician metodos para datos calculados de nutricion*/
    public void calc_caloriasDieta() {
        double formula = -1e9;
        try {
            MedidasGenerales m = this.getIdUsuario().getMedidasGenerales();
            String actividad = m.getEstado();
            double tmb24 = -100000000;
            double PeId = Double.valueOf(this.pesoIdeal);

            if (actividad.toLowerCase().contains("lig")) {
                formula = ((tmb24 / 40.0) * PeId * 0.9);
            } else if (actividad.toLowerCase().contains("mod")) {
                formula = ((tmb24 / 40.0) * PeId * 1);

            } else if (actividad.toLowerCase().contains("alt")) {
                formula = ((tmb24 / 40.0) * PeId * 1.17);
            }
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_caloriasDieta");
            e.printStackTrace();
        }
        this.caloriasDieta = String.valueOf(formula);
    }

    public void calc_quemaCalorias() {
        double formula = -1e9;
        try {
            double PeGr = Double.valueOf(this.pesoIdeal);
            double PeGrId = Double.valueOf(this.pesoIdeal);
            double SemProg = this.semanaPrograma.doubleValue();
            formula = ((PeGr - PeGrId) * 7000) * SemProg;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_quemaCalorias");
            e.printStackTrace();
        }
        this.quemaCalorias = String.valueOf(formula);
    }

    public void calc_quemaCaloriasSesion() {
        double formula = -1e9;
        try {
            double QuCaSem = Double.valueOf(this.quemaCalorias);
            double SeSem = Double.valueOf(sesionesSemana);
            formula = QuCaSem / SeSem;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_quemaCaloriasSesion");
            e.printStackTrace();
        }
        this.quemaCaloriasSesion = String.valueOf(formula);
    }

    public void calc_rangosFrecuencia() {
        double formula1 = -1e9;
        double formula2 = -1e9;
        try {
            MedidasGenerales m = this.getIdUsuario().getMedidasGenerales();
            String sexo = m.getSexo();
            double Ed = Double.valueOf(0);
            double FrCaRep = frecuenciaReposo.doubleValue();
            if (sexo.toLowerCase().contains("fem")) {
                formula1 = (225 - Ed) * 0.65;
                formula2 = (((225 - Ed) - FrCaRep) * 0.8) + FrCaRep;
            } else if (sexo.toLowerCase().contains("mas")) {
                formula1 = (220 - Ed) * 0.65;
                formula2 = (((220 - Ed) - FrCaRep) * 0.8) + FrCaRep;
            } else {
                formula1 = -1000000000;
                formula2 = -1000000000;
            }
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_rangosFrecuencia");
            e.printStackTrace();
        }
        this.rangosFrecuencia = String.valueOf("[" + formula1 + "," + formula2 + "]");
    }

    public void calc_pesoAnterior() {
        double formula = -1e9;
        try {
            formula = -1000000000;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_pesoAnterior");
            e.printStackTrace();
        }
        this.pesoAnterior = String.valueOf(formula);
    }

    public void calc_porcGrasaAnterior() {
        double formula = -1e9;
        try {
            formula = -1000000000;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_porcGrasaAnterior");
            e.printStackTrace();
        }
        this.porcGrasaAnterior = String.valueOf(formula);
    }

    public void calc_pesoMuscAnterior() {
        double formula = -1e9;
        try {
            formula = -1000000000;
        } catch (Exception e) {
            System.out.print("se ha producido un error en calc_pesoMuscAnterior");
            e.printStackTrace();
        }
        this.pesoMuscAnterior = String.valueOf(formula);
    }

    /*Fin Datos Calculados*/
    /*Get y set de Datos Calculados*/
    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getSumPliegue() {
        return sumPliegue;
    }

    public void setSumPliegue(String sumPliegue) {
        this.sumPliegue = sumPliegue;
    }

    public String getPesoIdeal() {
        return pesoIdeal;
    }

    public void setPesoIdeal(String pesoIdeal) {
        this.pesoIdeal = pesoIdeal;
    }

    public String getPesoIdealMin() {
        return pesoIdealMin;
    }

    public void setPesoIdealMin(String pesoIdealMin) {
        this.pesoIdealMin = pesoIdealMin;
    }

    public String getPesoIdealMax() {
        return pesoIdealMax;
    }

    public void setPesoIdealMax(String pesoIdealMax) {
        this.pesoIdealMax = pesoIdealMax;
    }

    public String getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(String pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public String getPorcGrasaTot() {
        return porcGrasaTot;
    }

    public void setPorcGrasaTot(String porcGrasaTot) {
        this.porcGrasaTot = porcGrasaTot;
    }

    public String getPorcGrasaIdeal() {
        return porcGrasaIdeal;
    }

    public void setPorcGrasaIdeal(String porcGrasaIdeal) {
        this.porcGrasaIdeal = porcGrasaIdeal;
    }

    public String getPesoGraso() {
        return pesoGraso;
    }

    public void setPesoGraso(String pesoGraso) {
        this.pesoGraso = pesoGraso;
    }

    public String getPesoOseo() {
        return pesoOseo;
    }

    public void setPesoOseo(String pesoOseo) {
        this.pesoOseo = pesoOseo;
    }

    public String getPorcPesoOseo() {
        return porcPesoOseo;
    }

    public void setPorcPesoOseo(String porcPesoOseo) {
        this.porcPesoOseo = porcPesoOseo;
    }

    public String getPesoMusc() {
        return pesoMusc;
    }

    public void setPesoMusc(String pesoMusc) {
        this.pesoMusc = pesoMusc;
    }

    public String getPesoMuscIdeal() {
        return pesoMuscIdeal;
    }

    public void setPesoMuscIdeal(String pesoMuscIdeal) {
        this.pesoMuscIdeal = pesoMuscIdeal;
    }

    public String getPorcPesoMusc() {
        return porcPesoMusc;
    }

    public void setPorcPesoMusc(String porcPesoMusc) {
        this.porcPesoMusc = porcPesoMusc;
    }

    public String getPorcPesoMuscIdeal() {
        return porcPesoMuscIdeal;
    }

    public void setPorcPesoMuscIdeal(String porcPesoMuscIdeal) {
        this.porcPesoMuscIdeal = porcPesoMuscIdeal;
    }

    public String getPesoResidual() {
        return pesoResidual;
    }

    public void setPesoResidual(String pesoResidual) {
        this.pesoResidual = pesoResidual;
    }

    public String getPorcPesoResidual() {
        return porcPesoResidual;
    }

    public void setPorcPesoResidual(String porcPesoResidual) {
        this.porcPesoResidual = porcPesoResidual;
    }

    public String getMasaCorpMagra() {
        return masaCorpMagra;
    }

    public void setMasaCorpMagra(String masaCorpMagra) {
        this.masaCorpMagra = masaCorpMagra;
    }

    public String getMasaCorpMagraIdeal() {
        return masaCorpMagraIdeal;
    }

    public void setMasaCorpMagraIdeal(String masaCorpMagraIdeal) {
        this.masaCorpMagraIdeal = masaCorpMagraIdeal;
    }

    public String getPorcPesoOseoIdeal() {
        return porcPesoOseoIdeal;
    }

    public void setPorcPesoOseoIdeal(String porcPesoOseoIdeal) {
        this.porcPesoOseoIdeal = porcPesoOseoIdeal;
    }

    public String getRelacionCinturaCadera() {
        return relacionCinturaCadera;
    }

    public void setRelacionCinturaCadera(String relacionCinturaCadera) {
        this.relacionCinturaCadera = relacionCinturaCadera;
    }

    public String getCaloriasDieta() {
        return caloriasDieta;
    }

    public void setCaloriasDieta(String caloriasDieta) {
        this.caloriasDieta = caloriasDieta;
    }

    public String getQuemaCalorias() {
        return quemaCalorias;
    }

    public void setQuemaCalorias(String quemaCalorias) {
        this.quemaCalorias = quemaCalorias;
    }

    public String getQuemaCaloriasSesion() {
        return quemaCaloriasSesion;
    }

    public void setQuemaCaloriasSesion(String quemaCaloriasSesion) {
        this.quemaCaloriasSesion = quemaCaloriasSesion;
    }

    public String getRangosFrecuencia() {
        return rangosFrecuencia;
    }

    public void setRangosFrecuencia(String rangosFrecuencia) {
        this.rangosFrecuencia = rangosFrecuencia;
    }

    public String getPesoAnterior() {
        return pesoAnterior;
    }

    public void setPesoAnterior(String pesoAnterior) {
        this.pesoAnterior = pesoAnterior;
    }

    public String getPorcGrasaAnterior() {
        return porcGrasaAnterior;
    }

    public void setPorcGrasaAnterior(String porcGrasaAnterior) {
        this.porcGrasaAnterior = porcGrasaAnterior;
    }

    public String getPesoMuscAnterior() {
        return pesoMuscAnterior;
    }

    public void setPesoMuscAnterior(String pesoMuscAnterior) {
        this.pesoMuscAnterior = pesoMuscAnterior;
    }
    
    /*Fin Gets y Sets*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvaluacion != null ? idEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.idEvaluacion == null && other.idEvaluacion != null) || (this.idEvaluacion != null && !this.idEvaluacion.equals(other.idEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Evaluacion[ idEvaluacion=" + idEvaluacion + " ]";
    }
}
