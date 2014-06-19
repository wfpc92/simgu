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
@Table(name = "TESTSARGENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Testsargent.findAll", query = "SELECT t FROM Testsargent t"),
    @NamedQuery(name = "Testsargent.findByIdtestsargent", query = "SELECT t FROM Testsargent t WHERE t.idtestsargent = :idtestsargent"),
    @NamedQuery(name = "Testsargent.findByEmbergadurasargent", query = "SELECT t FROM Testsargent t WHERE t.embergadurasargent = :embergadurasargent"),
    @NamedQuery(name = "Testsargent.findBySaltomaximosargent", query = "SELECT t FROM Testsargent t WHERE t.saltomaximosargent = :saltomaximosargent"),
    @NamedQuery(name = "Testsargent.findBySaltorealsargent", query = "SELECT t FROM Testsargent t WHERE t.saltorealsargent = :saltorealsargent"),
    @NamedQuery(name = "Testsargent.findByFechasargent", query = "SELECT t FROM Testsargent t WHERE t.fechasargent = :fechasargent"),
    @NamedQuery(name = "Testsargent.findByCalificacionsargent", query = "SELECT t FROM Testsargent t WHERE t.calificacionsargent = :calificacionsargent")})
public class Testsargent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTESTSARGENT")
    private Integer idtestsargent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMBERGADURASARGENT")
    private float embergadurasargent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALTOMAXIMOSARGENT")
    private float saltomaximosargent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALTOREALSARGENT")
    private float saltorealsargent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHASARGENT")
    @Temporal(TemporalType.DATE)
    private Date fechasargent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CALIFICACIONSARGENT")
    private String calificacionsargent;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;

    public Testsargent() {
    }

    public Testsargent(Integer idtestsargent) {
        this.idtestsargent = idtestsargent;
    }

    public Testsargent(Integer idtestsargent, float embergadurasargent, float saltomaximosargent, float saltorealsargent, Date fechasargent, String calificacionsargent) {
        this.idtestsargent = idtestsargent;
        this.embergadurasargent = embergadurasargent;
        this.saltomaximosargent = saltomaximosargent;
        this.saltorealsargent = saltorealsargent;
        this.fechasargent = fechasargent;
        this.calificacionsargent = calificacionsargent;
    }

    public Integer getIdtestsargent() {
        return idtestsargent;
    }

    public void setIdtestsargent(Integer idtestsargent) {
        this.idtestsargent = idtestsargent;
    }

    public float getEmbergadurasargent() {
        return embergadurasargent;
    }

    public void setEmbergadurasargent(float embergadurasargent) {
        this.embergadurasargent = embergadurasargent;
    }

    public float getSaltomaximosargent() {
        return saltomaximosargent;
    }

    public void setSaltomaximosargent(float saltomaximosargent) {
        this.saltomaximosargent = saltomaximosargent;
    }

    public float getSaltorealsargent() {
        return saltorealsargent;
    }

    public void setSaltorealsargent(float saltorealsargent) {
        this.saltorealsargent = saltorealsargent;
    }

    public Date getFechasargent() {
        return fechasargent;
    }

    public void setFechasargent(Date fechasargent) {
        this.fechasargent = fechasargent;
    }

    public String getCalificacionsargent() {
        return calificacionsargent;
    }

    public void setCalificacionsargent(String calificacionsargent) {
        this.calificacionsargent = calificacionsargent;
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
        hash += (idtestsargent != null ? idtestsargent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Testsargent)) {
            return false;
        }
        Testsargent other = (Testsargent) object;
        if ((this.idtestsargent == null && other.idtestsargent != null) || (this.idtestsargent != null && !this.idtestsargent.equals(other.idtestsargent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Testsargent[ idtestsargent=" + idtestsargent + " ]";
    }
    
}
