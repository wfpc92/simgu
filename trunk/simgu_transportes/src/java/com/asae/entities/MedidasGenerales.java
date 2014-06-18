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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "medidas_generales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedidasGenerales.findAll", query = "SELECT m FROM MedidasGenerales m"),
    @NamedQuery(name = "MedidasGenerales.findByIdUsuario", query = "SELECT m FROM MedidasGenerales m WHERE m.idUsuario = :idUsuario"),
    @NamedQuery(name = "MedidasGenerales.findByDeporte", query = "SELECT m FROM MedidasGenerales m WHERE m.deporte = :deporte"),
    @NamedQuery(name = "MedidasGenerales.findByEstado", query = "SELECT m FROM MedidasGenerales m WHERE m.estado = :estado"),
    @NamedQuery(name = "MedidasGenerales.findByEstatura", query = "SELECT m FROM MedidasGenerales m WHERE m.estatura = :estatura"),
    @NamedQuery(name = "MedidasGenerales.findByFechaIngreso", query = "SELECT m FROM MedidasGenerales m WHERE m.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "MedidasGenerales.findByActividad", query = "SELECT m FROM MedidasGenerales m WHERE m.actividad = :actividad"),
    @NamedQuery(name = "MedidasGenerales.findBySedentario", query = "SELECT m FROM MedidasGenerales m WHERE m.sedentario = :sedentario"),
    @NamedQuery(name = "MedidasGenerales.findBySexo", query = "SELECT m FROM MedidasGenerales m WHERE m.sexo = :sexo"),
    @NamedQuery(name = "MedidasGenerales.findByBiepicondilarFemoral", query = "SELECT m FROM MedidasGenerales m WHERE m.biepicondilarFemoral = :biepicondilarFemoral"),
    @NamedQuery(name = "MedidasGenerales.findByBiepicondilarHumeral", query = "SELECT m FROM MedidasGenerales m WHERE m.biepicondilarHumeral = :biepicondilarHumeral"),
    @NamedQuery(name = "MedidasGenerales.findByRadioCubital", query = "SELECT m FROM MedidasGenerales m WHERE m.radioCubital = :radioCubital")})
public class MedidasGenerales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Size(max = 255)
    @Column(name = "DEPORTE")
    private String deporte;
    @Size(max = 255)
    @Column(name = "ESTADO")
    private String estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ESTATURA")
    private BigDecimal estatura;
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Size(max = 255)
    @Column(name = "ACTIVIDAD")
    private String actividad;
    @Size(max = 255)
    @Column(name = "SEDENTARIO")
    private String sedentario;
    @Size(max = 1)
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "BIEPICONDILAR_FEMORAL")
    private BigDecimal biepicondilarFemoral;
    @Column(name = "BIEPICONDILAR_HUMERAL")
    private BigDecimal biepicondilarHumeral;
    @Column(name = "RADIO_CUBITAL")
    private BigDecimal radioCubital;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "IDUSUARIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public MedidasGenerales() {
    }

    public MedidasGenerales(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getEstatura() {
        return estatura;
    }

    public void setEstatura(BigDecimal estatura) {
        this.estatura = estatura;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getSedentario() {
        return sedentario;
    }

    public void setSedentario(String sedentario) {
        this.sedentario = sedentario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public BigDecimal getBiepicondilarFemoral() {
        return biepicondilarFemoral;
    }

    public void setBiepicondilarFemoral(BigDecimal biepicondilarFemoral) {
        this.biepicondilarFemoral = biepicondilarFemoral;
    }

    public BigDecimal getBiepicondilarHumeral() {
        return biepicondilarHumeral;
    }

    public void setBiepicondilarHumeral(BigDecimal biepicondilarHumeral) {
        this.biepicondilarHumeral = biepicondilarHumeral;
    }

    public BigDecimal getRadioCubital() {
        return radioCubital;
    }

    public void setRadioCubital(BigDecimal radioCubital) {
        this.radioCubital = radioCubital;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedidasGenerales)) {
            return false;
        }
        MedidasGenerales other = (MedidasGenerales) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.MedidasGenerales[ idUsuario=" + idUsuario + " ]";
    }
    
}
