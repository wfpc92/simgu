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
@Table(name = "TESTRUFFIER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Testruffier.findAll", query = "SELECT t FROM Testruffier t"),
    @NamedQuery(name = "Testruffier.findByIdtestruffier", query = "SELECT t FROM Testruffier t WHERE t.idtestruffier = :idtestruffier"),
    @NamedQuery(name = "Testruffier.findByPulso0ruffier", query = "SELECT t FROM Testruffier t WHERE t.pulso0ruffier = :pulso0ruffier"),
    @NamedQuery(name = "Testruffier.findByPulso1ruffier", query = "SELECT t FROM Testruffier t WHERE t.pulso1ruffier = :pulso1ruffier"),
    @NamedQuery(name = "Testruffier.findByPulso2ruffier", query = "SELECT t FROM Testruffier t WHERE t.pulso2ruffier = :pulso2ruffier"),
    @NamedQuery(name = "Testruffier.findByResultadoruffier", query = "SELECT t FROM Testruffier t WHERE t.resultadoruffier = :resultadoruffier"),
    @NamedQuery(name = "Testruffier.findByFecharuffier", query = "SELECT t FROM Testruffier t WHERE t.fecharuffier = :fecharuffier"),
    @NamedQuery(name = "Testruffier.findByCalificacion", query = "SELECT t FROM Testruffier t WHERE t.calificacion = :calificacion")})
public class Testruffier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTESTRUFFIER")
    private Integer idtestruffier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PULSO0RUFFIER")
    private int pulso0ruffier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PULSO1RUFFIER")
    private int pulso1ruffier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PULSO2RUFFIER")
    private int pulso2ruffier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESULTADORUFFIER")
    private float resultadoruffier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHARUFFIER")
    @Temporal(TemporalType.DATE)
    private Date fecharuffier;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "CALIFICACION")
    private String calificacion;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;

    public Testruffier() {
    }

    public Testruffier(Integer idtestruffier) {
        this.idtestruffier = idtestruffier;
    }

    public Testruffier(Integer idtestruffier, int pulso0ruffier, int pulso1ruffier, int pulso2ruffier, float resultadoruffier, Date fecharuffier, String calificacion) {
        this.idtestruffier = idtestruffier;
        this.pulso0ruffier = pulso0ruffier;
        this.pulso1ruffier = pulso1ruffier;
        this.pulso2ruffier = pulso2ruffier;
        this.resultadoruffier = resultadoruffier;
        this.fecharuffier = fecharuffier;
        this.calificacion = calificacion;
    }

    public Integer getIdtestruffier() {
        return idtestruffier;
    }

    public void setIdtestruffier(Integer idtestruffier) {
        this.idtestruffier = idtestruffier;
    }

    public int getPulso0ruffier() {
        return pulso0ruffier;
    }

    public void setPulso0ruffier(int pulso0ruffier) {
        this.pulso0ruffier = pulso0ruffier;
    }

    public int getPulso1ruffier() {
        return pulso1ruffier;
    }

    public void setPulso1ruffier(int pulso1ruffier) {
        this.pulso1ruffier = pulso1ruffier;
    }

    public int getPulso2ruffier() {
        return pulso2ruffier;
    }

    public void setPulso2ruffier(int pulso2ruffier) {
        this.pulso2ruffier = pulso2ruffier;
    }

    public float getResultadoruffier() {
        return resultadoruffier;
    }

    public void setResultadoruffier(float resultadoruffier) {
        this.resultadoruffier = resultadoruffier;
    }

    public Date getFecharuffier() {
        return fecharuffier;
    }

    public void setFecharuffier(Date fecharuffier) {
        this.fecharuffier = fecharuffier;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
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
        hash += (idtestruffier != null ? idtestruffier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Testruffier)) {
            return false;
        }
        Testruffier other = (Testruffier) object;
        if ((this.idtestruffier == null && other.idtestruffier != null) || (this.idtestruffier != null && !this.idtestruffier.equals(other.idtestruffier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Testruffier[ idtestruffier=" + idtestruffier + " ]";
    }
    
}
