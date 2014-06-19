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
@Table(name = "grupo_muscular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoMuscular.findAll", query = "SELECT g FROM GrupoMuscular g"),
    @NamedQuery(name = "GrupoMuscular.findGMuscularesDia", query = "SELECT g FROM GrupoMuscular g JOIN g.diaList d WHERE d.idDia = :idDia"),
    @NamedQuery(name = "GrupoMuscular.findByIdGrupoMuscular", query = "SELECT g FROM GrupoMuscular g WHERE g.idGrupoMuscular = :idGrupoMuscular")})
public class GrupoMuscular implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRUPO_MUSCULAR")
    private Integer idGrupoMuscular;
    @JoinTable(name = "grupo_muscular_ejercicio_gm", joinColumns = {
        @JoinColumn(name = "ID_GRUPO_MUSCULAR", referencedColumnName = "ID_GRUPO_MUSCULAR")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_EJERCICIO", referencedColumnName = "idejerciciogm")})
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<EjercicioGm> ejercicioGmList;
    @JoinTable(name = "dia_grupo_muscular", joinColumns = {
        @JoinColumn(name = "ID_GRUPO_MUSCULAR", referencedColumnName = "ID_GRUPO_MUSCULAR")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_DIA", referencedColumnName = "ID_DIA")})
    @ManyToMany
    private List<Dia> diaList;
    @JoinColumn(name = "IDGRUPO_MUSCULAR_GENERAL", referencedColumnName = "IDGRUPO_MUSCULAR_GENERAL")
    @ManyToOne
    private GrupoMuscularGeneral idgrupoMuscularGeneral;

    public GrupoMuscular() {
    }

    public GrupoMuscular(Integer idGrupoMuscular) {
        this.idGrupoMuscular = idGrupoMuscular;
    }

    public Integer getIdGrupoMuscular() {
        return idGrupoMuscular;
    }

    public void setIdGrupoMuscular(Integer idGrupoMuscular) {
        this.idGrupoMuscular = idGrupoMuscular;
    }

    @XmlTransient
    public List<EjercicioGm> getEjercicioGmList() {
        return ejercicioGmList;
    }

    public void setEjercicioGmList(List<EjercicioGm> ejercicioGmList) {
        this.ejercicioGmList = ejercicioGmList;
    }

    @XmlTransient
    public List<Dia> getDiaList() {
        return diaList;
    }

    public void setDiaList(List<Dia> diaList) {
        this.diaList = diaList;
    }

    public GrupoMuscularGeneral getIdgrupoMuscularGeneral() {
        return idgrupoMuscularGeneral;
    }

    public void setIdgrupoMuscularGeneral(GrupoMuscularGeneral idgrupoMuscularGeneral) {
        this.idgrupoMuscularGeneral = idgrupoMuscularGeneral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoMuscular != null ? idGrupoMuscular.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoMuscular)) {
            return false;
        }
        GrupoMuscular other = (GrupoMuscular) object;
        if ((this.idGrupoMuscular == null && other.idGrupoMuscular != null) || (this.idGrupoMuscular != null && !this.idGrupoMuscular.equals(other.idGrupoMuscular))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.GrupoMuscular[ idGrupoMuscular=" + idGrupoMuscular + " ]";
    }
    
}
