/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByIdAsistencia", query = "SELECT a FROM Asistencia a WHERE a.idAsistencia = :idAsistencia"),
    @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Asistencia.findBetweenFechasNoGroup", query = "SELECT a.idAsistencia, a.fecha, a.idusuario FROM Asistencia a WHERE a.fecha BETWEEN :finic AND :ffin"),
    @NamedQuery(name = "Asistencia.findBetweenFechasTodos", query = "SELECT a.idAsistencia, a.fecha, a.idusuario, COUNT(a) FROM Asistencia a GROUP BY a.idusuario"),
    @NamedQuery(name = "Asistencia.findBetweenFechasDerecha", query = "SELECT a.idAsistencia, a.fecha, a.idusuario, COUNT(a) FROM Asistencia a WHERE a.fecha > :finic GROUP BY a.idusuario"),
    @NamedQuery(name = "Asistencia.findBetweenFechasIzquierda", query = "SELECT a.idAsistencia, a.fecha, a.idusuario, COUNT(a) FROM Asistencia a WHERE a.fecha < :ffin GROUP BY a.idusuario"),
    @NamedQuery(name = "Asistencia.findBetweenFechas", query = "SELECT a.idAsistencia, a.fecha, a.idusuario, COUNT(a) FROM Asistencia a WHERE a.fecha BETWEEN :finic AND :ffin GROUP BY a.idusuario"),
    @NamedQuery(name = "Asistencia.findBetweenFechasTodosNoEst", query = "SELECT a.idAsistencia, a.fecha, a.idusuario, COUNT(a) FROM Asistencia a GROUP BY a.idusuario"),
    @NamedQuery(name = "Asistencia.findBetweenFechasDerechaNoEst", query = "SELECT a.idAsistencia, a.fecha, a.idusuario, COUNT(a) FROM Asistencia a WHERE a.fecha > :finic GROUP BY a.idusuario"),
    @NamedQuery(name = "Asistencia.findBetweenFechasIzquierdaNoEst", query = "SELECT a.idAsistencia, a.fecha, a.idusuario, COUNT(a) FROM Asistencia a WHERE a.fecha < :ffin GROUP BY a.idusuario"),
    @NamedQuery(name = "Asistencia.findBetweenFechasNoEst", query = "SELECT a.idAsistencia, a.fecha, a.idusuario, COUNT(a) FROM Asistencia a WHERE a.fecha BETWEEN :finic AND :ffin GROUP BY a.idusuario"),
    @NamedQuery(name = "Asistencia.findByAsistenciaUsuario", query = "SELECT COUNT(a) FROM Asistencia a WHERE (a.fecha BETWEEN :finic AND :ffin) AND a.idusuario = :idusuario"),
    @NamedQuery(name = "Asistencia.findByAsistenciaUsuarioFechas", query = "SELECT a FROM Asistencia a WHERE (a.fecha BETWEEN :finic AND :ffin) AND a.idusuario = :idusuario"),
    @NamedQuery(name = "Asistencia.findBetweenFechasAsistencia", query = "SELECT a FROM Asistencia a WHERE a.fecha BETWEEN :finic AND :ffin")})
public class Asistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ASISTENCIA")
    private Integer idAsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;

    public Asistencia() {
    }

    public Asistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Asistencia(Integer idAsistencia, Date fecha) {
        this.idAsistencia = idAsistencia;
        this.fecha = fecha;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Asistencia[ idAsistencia=" + idAsistencia + " ]";
    }
    
}
