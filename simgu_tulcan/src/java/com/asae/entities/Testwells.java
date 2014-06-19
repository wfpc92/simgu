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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "TESTWELLS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Testwells.findAll", query = "SELECT t FROM Testwells t"),
    @NamedQuery(name = "Testwells.findByIdtestwells", query = "SELECT t FROM Testwells t WHERE t.idtestwells = :idtestwells"),
    @NamedQuery(name = "Testwells.findByFlexibilidadwells", query = "SELECT t FROM Testwells t WHERE t.flexibilidadwells = :flexibilidadwells"),
    @NamedQuery(name = "Testwells.findByFechawells", query = "SELECT t FROM Testwells t WHERE t.fechawells = :fechawells"),
    @NamedQuery(name = "Testwells.findByCalificacionwells", query = "SELECT t FROM Testwells t WHERE t.calificacionwells = :calificacionwells")})
public class Testwells implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTESTWELLS")
    private Integer idtestwells;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FLEXIBILIDADWELLS")
    private float flexibilidadwells;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAWELLS")
    @Temporal(TemporalType.DATE)
    private Date fechawells;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CALIFICACIONWELLS")
    private String calificacionwells;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;

    public Testwells() {
    }

    public Testwells(Integer idtestwells) {
        this.idtestwells = idtestwells;
    }

    public Testwells(Integer idtestwells, float flexibilidadwells, Date fechawells, String calificacionwells) {
        this.idtestwells = idtestwells;
        this.flexibilidadwells = flexibilidadwells;
        this.fechawells = fechawells;
        this.calificacionwells = calificacionwells;
    }

    public Integer getIdtestwells() {
        return idtestwells;
    }

    public void setIdtestwells(Integer idtestwells) {
        this.idtestwells = idtestwells;
    }

    public float getFlexibilidadwells() {
        return flexibilidadwells;
    }

    public void setFlexibilidadwells(float flexibilidadwells) {
        this.flexibilidadwells = flexibilidadwells;
    }

    public Date getFechawells() {
        return fechawells;
    }

    public void setFechawells(Date fechawells) {
        this.fechawells = fechawells;
    }

    public String getCalificacionwells() {
        return calificacionwells;
    }

    public void setCalificacionwells(String calificacionwells) {
        this.calificacionwells = calificacionwells;
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
        hash += (idtestwells != null ? idtestwells.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Testwells)) {
            return false;
        }
        Testwells other = (Testwells) object;
        if ((this.idtestwells == null && other.idtestwells != null) || (this.idtestwells != null && !this.idtestwells.equals(other.idtestwells))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Testwells[ idtestwells=" + idtestwells + " ]";
    }
    
}
