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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "ejercicio_cross")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjercicioCross.findAll", query = "SELECT e FROM EjercicioCross e"),
    @NamedQuery(name = "EjercicioCross.findByNumVecesMedida", query = "SELECT e FROM EjercicioCross e WHERE e.numVecesMedida = :numVecesMedida"),
    @NamedQuery(name = "EjercicioCross.findByIdejercicioCross", query = "SELECT e FROM EjercicioCross e WHERE e.idejercicioCross = :idejercicioCross")})
public class EjercicioCross implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_VECES_MEDIDA")
    private int numVecesMedida;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEJERCICIO_CROSS")
    private Integer idejercicioCross;
    @JoinTable(name = "cross_ejercicio_cross", joinColumns = {
        @JoinColumn(name = "IDEJERCICIO_CROSS", referencedColumnName = "IDEJERCICIO_CROSS")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_CROSS", referencedColumnName = "ID_CROSS")})
    @ManyToMany
    private List<GrupoCross> grupoCrossList;
    @JoinColumn(name = "EJERCICIO", referencedColumnName = "ID_EJERCICIO")
    @ManyToOne(optional = false)
    private Ejercicio ejercicio;
    @JoinColumn(name = "IDMEDIDA_EJERCICIO_CROSS", referencedColumnName = "IDMEDIDA_EJERCICIO_CROSS")
    @ManyToOne
    private MedidaEjercicioCross idmedidaEjercicioCross;

    public EjercicioCross() {
    }

    public EjercicioCross(Integer idejercicioCross) {
        this.idejercicioCross = idejercicioCross;
    }

    public EjercicioCross(Integer idejercicioCross, int numVecesMedida) {
        this.idejercicioCross = idejercicioCross;
        this.numVecesMedida = numVecesMedida;
    }

    public int getNumVecesMedida() {
        return numVecesMedida;
    }

    public void setNumVecesMedida(int numVecesMedida) {
        this.numVecesMedida = numVecesMedida;
    }

    public Integer getIdejercicioCross() {
        return idejercicioCross;
    }

    public void setIdejercicioCross(Integer idejercicioCross) {
        this.idejercicioCross = idejercicioCross;
    }

    @XmlTransient
    public List<GrupoCross> getGrupoCrossList() {
        return grupoCrossList;
    }

    public void setGrupoCrossList(List<GrupoCross> grupoCrossList) {
        this.grupoCrossList = grupoCrossList;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public MedidaEjercicioCross getIdmedidaEjercicioCross() {
        return idmedidaEjercicioCross;
    }

    public void setIdmedidaEjercicioCross(MedidaEjercicioCross idmedidaEjercicioCross) {
        this.idmedidaEjercicioCross = idmedidaEjercicioCross;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idejercicioCross != null ? idejercicioCross.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjercicioCross)) {
            return false;
        }
        EjercicioCross other = (EjercicioCross) object;
        if ((this.idejercicioCross == null && other.idejercicioCross != null) || (this.idejercicioCross != null && !this.idejercicioCross.equals(other.idejercicioCross))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.EjercicioCross[ idejercicioCross=" + idejercicioCross + " ]";
    }
    
}
