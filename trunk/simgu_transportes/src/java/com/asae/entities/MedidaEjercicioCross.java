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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "medida_ejercicio_cross")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedidaEjercicioCross.findAll", query = "SELECT m FROM MedidaEjercicioCross m"),
    @NamedQuery(name = "MedidaEjercicioCross.findByIdmedidaEjercicioCross", query = "SELECT m FROM MedidaEjercicioCross m WHERE m.idmedidaEjercicioCross = :idmedidaEjercicioCross"),
    @NamedQuery(name = "MedidaEjercicioCross.findByNombre", query = "SELECT m FROM MedidaEjercicioCross m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MedidaEjercicioCross.findByDescripcion", query = "SELECT m FROM MedidaEjercicioCross m WHERE m.descripcion = :descripcion")})
public class MedidaEjercicioCross implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMEDIDA_EJERCICIO_CROSS")
    private Integer idmedidaEjercicioCross;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idmedidaEjercicioCross")
    private List<EjercicioCross> ejercicioCrossList;

    public MedidaEjercicioCross() {
    }

    public MedidaEjercicioCross(Integer idmedidaEjercicioCross) {
        this.idmedidaEjercicioCross = idmedidaEjercicioCross;
    }

    public MedidaEjercicioCross(Integer idmedidaEjercicioCross, String nombre, String descripcion) {
        this.idmedidaEjercicioCross = idmedidaEjercicioCross;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdmedidaEjercicioCross() {
        return idmedidaEjercicioCross;
    }

    public void setIdmedidaEjercicioCross(Integer idmedidaEjercicioCross) {
        this.idmedidaEjercicioCross = idmedidaEjercicioCross;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<EjercicioCross> getEjercicioCrossList() {
        return ejercicioCrossList;
    }

    public void setEjercicioCrossList(List<EjercicioCross> ejercicioCrossList) {
        this.ejercicioCrossList = ejercicioCrossList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmedidaEjercicioCross != null ? idmedidaEjercicioCross.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedidaEjercicioCross)) {
            return false;
        }
        MedidaEjercicioCross other = (MedidaEjercicioCross) object;
        if ((this.idmedidaEjercicioCross == null && other.idmedidaEjercicioCross != null) || (this.idmedidaEjercicioCross != null && !this.idmedidaEjercicioCross.equals(other.idmedidaEjercicioCross))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.MedidaEjercicioCross[ idmedidaEjercicioCross=" + idmedidaEjercicioCross + " ]";
    }
    
}
