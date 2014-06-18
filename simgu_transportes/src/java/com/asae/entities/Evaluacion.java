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
