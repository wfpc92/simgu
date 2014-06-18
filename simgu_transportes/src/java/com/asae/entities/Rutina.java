/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "rutina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rutina.findAll", query = "SELECT r FROM Rutina r"),
    @NamedQuery(name = "Rutina.findByIdRutina", query = "SELECT r FROM Rutina r WHERE r.idRutina = :idRutina"),
    @NamedQuery(name = "Rutina.findByFechaInicio", query = "SELECT r FROM Rutina r WHERE r.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Rutina.findByFechaFin", query = "SELECT r FROM Rutina r WHERE r.fechaFin = :fechaFin"),
    @NamedQuery(name = "Rutina.findByCodigoRutina", query = "SELECT r FROM Rutina r WHERE r.codigoRutina = :codigoRutina"),
    @NamedQuery(name = "Rutina.findByCalentamiento", query = "SELECT r FROM Rutina r WHERE r.calentamiento = :calentamiento")})
public class Rutina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RUTINA")
    private Integer idRutina;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 100)
    @Column(name = "CODIGO_RUTINA")
    private String codigoRutina;
    @Size(max = 500)
    @Column(name = "CALENTAMIENTO")
    private String calentamiento;
    @ManyToMany(mappedBy = "rutinaList")
    private List<Dia> diaList;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;

    public Rutina() {
    }

    public Rutina(Integer idRutina) {
        this.idRutina = idRutina;
    }

    public Integer getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(Integer idRutina) {
        this.idRutina = idRutina;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCodigoRutina() {
        return codigoRutina;
    }

    public void setCodigoRutina(String codigoRutina) {
        this.codigoRutina = codigoRutina;
    }

    public String getCalentamiento() {
        return calentamiento;
    }

    public void setCalentamiento(String calentamiento) {
        this.calentamiento = calentamiento;
    }

    @XmlTransient
    public List<Dia> getDiaList() {
        return diaList;
    }

    public void setDiaList(List<Dia> diaList) {
        this.diaList = diaList;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRutina != null ? idRutina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rutina)) {
            return false;
        }
        Rutina other = (Rutina) object;
        if ((this.idRutina == null && other.idRutina != null) || (this.idRutina != null && !this.idRutina.equals(other.idRutina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Rutina[ idRutina=" + idRutina + " ]";
    }
    
}
