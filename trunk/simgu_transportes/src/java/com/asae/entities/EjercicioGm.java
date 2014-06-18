/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "ejercicio_gm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjercicioGm.findAll", query = "SELECT e FROM EjercicioGm e"),
    @NamedQuery(name = "EjercicioGm.findByNumeroSeries", query = "SELECT e FROM EjercicioGm e WHERE e.numeroSeries = :numeroSeries"),
    @NamedQuery(name = "EjercicioGm.findByRepeticiones", query = "SELECT e FROM EjercicioGm e WHERE e.repeticiones = :repeticiones"),
    @NamedQuery(name = "EjercicioGm.findByReceso", query = "SELECT e FROM EjercicioGm e WHERE e.receso = :receso"),
    @NamedQuery(name = "EjercicioGm.findByPeso", query = "SELECT e FROM EjercicioGm e WHERE e.peso = :peso"),
    @NamedQuery(name = "EjercicioGm.findByIdejerciciogm", query = "SELECT e FROM EjercicioGm e WHERE e.idejerciciogm = :idejerciciogm")})
public class EjercicioGm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "NUMERO_SERIES")
    private Integer numeroSeries;
    @Column(name = "REPETICIONES")
    private Integer repeticiones;
    @Column(name = "RECESO")
    private Integer receso;
    @Column(name = "PESO")
    private Integer peso;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEJERCICIOGM")
    private Integer idejerciciogm;
    @JoinTable(name = "grupo_muscular_ejercicio_gm", joinColumns = {
        @JoinColumn(name = "ID_EJERCICIO", referencedColumnName = "IDEJERCICIOGM")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_GRUPO_MUSCULAR", referencedColumnName = "ID_GRUPO_MUSCULAR")})
    @ManyToMany
    private List<GrupoMuscular> grupoMuscularList;
    @JoinColumn(name = "EJERCICIO", referencedColumnName = "ID_EJERCICIO")
    @ManyToOne(optional = false)
    private Ejercicio ejercicio;

    public EjercicioGm() {
    }

    public EjercicioGm(Integer idejerciciogm) {
        this.idejerciciogm = idejerciciogm;
    }

    public Integer getNumeroSeries() {
        return numeroSeries;
    }

    public void setNumeroSeries(Integer numeroSeries) {
        this.numeroSeries = numeroSeries;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Integer getReceso() {
        return receso;
    }

    public void setReceso(Integer receso) {
        this.receso = receso;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getIdejerciciogm() {
        return idejerciciogm;
    }

    public void setIdejerciciogm(Integer idejerciciogm) {
        this.idejerciciogm = idejerciciogm;
    }

    @XmlTransient
    public List<GrupoMuscular> getGrupoMuscularList() {
        return grupoMuscularList;
    }

    public void setGrupoMuscularList(List<GrupoMuscular> grupoMuscularList) {
        this.grupoMuscularList = grupoMuscularList;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idejerciciogm != null ? idejerciciogm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjercicioGm)) {
            return false;
        }
        EjercicioGm other = (EjercicioGm) object;
        if ((this.idejerciciogm == null && other.idejerciciogm != null) || (this.idejerciciogm != null && !this.idejerciciogm.equals(other.idejerciciogm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.EjercicioGm[ idejerciciogm=" + idejerciciogm + " ]";
    }
    
}
