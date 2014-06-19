/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "grupo_cross")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoCross.findAll", query = "SELECT g FROM GrupoCross g"),
    @NamedQuery(name = "GrupoCross.findByIdCross", query = "SELECT g FROM GrupoCross g WHERE g.idCross = :idCross")})
public class GrupoCross implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CROSS")
    private Integer idCross;
    @JoinTable(name = "dia_cross", joinColumns = {
        @JoinColumn(name = "ID_CROSS", referencedColumnName = "ID_CROSS")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_DIA", referencedColumnName = "ID_DIA")})
    @ManyToMany
    private List<Dia> diaList;
    @ManyToMany(mappedBy = "grupoCrossList", cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<EjercicioCross> ejercicioCrossList;
    @JoinColumn(name = "IDGRUPO_CROSS_GENERAL", referencedColumnName = "IDGRUPO_CROSS_GENERAL")
    @ManyToOne
    private GrupoCrossGeneral idgrupoCrossGeneral;

    public GrupoCross() {
    }

    public GrupoCross(Integer idCross) {
        this.idCross = idCross;
    }

    public Integer getIdCross() {
        return idCross;
    }

    public void setIdCross(Integer idCross) {
        this.idCross = idCross;
    }

    @XmlTransient
    public List<Dia> getDiaList() {
        return diaList;
    }

    public void setDiaList(List<Dia> diaList) {
        this.diaList = diaList;
    }

    @XmlTransient
    public List<EjercicioCross> getEjercicioCrossList() {
        return ejercicioCrossList;
    }

    public void setEjercicioCrossList(List<EjercicioCross> ejercicioCrossList) {
        this.ejercicioCrossList = ejercicioCrossList;
    }

    public GrupoCrossGeneral getIdgrupoCrossGeneral() {
        return idgrupoCrossGeneral;
    }

    public void setIdgrupoCrossGeneral(GrupoCrossGeneral idgrupoCrossGeneral) {
        this.idgrupoCrossGeneral = idgrupoCrossGeneral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCross != null ? idCross.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoCross)) {
            return false;
        }
        GrupoCross other = (GrupoCross) object;
        if ((this.idCross == null && other.idCross != null) || (this.idCross != null && !this.idCross.equals(other.idCross))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.GrupoCross[ idCross=" + idCross + " ]";
    }
    
}
