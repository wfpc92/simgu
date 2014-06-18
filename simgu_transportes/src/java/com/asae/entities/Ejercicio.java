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
@Table(name = "ejercicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ejercicio.findAll", query = "SELECT e FROM Ejercicio e"),
    @NamedQuery(name = "Ejercicio.findByIdEjercicio", query = "SELECT e FROM Ejercicio e WHERE e.idEjercicio = :idEjercicio"),
    @NamedQuery(name = "Ejercicio.findByNombre", query = "SELECT e FROM Ejercicio e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Ejercicio.findByDescripcion", query = "SELECT e FROM Ejercicio e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Ejercicio.findByTipo", query = "SELECT e FROM Ejercicio e WHERE e.tipo = :tipo")})
public class Ejercicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EJERCICIO")
    private Integer idEjercicio;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TIPO")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicio")
    private List<EjercicioCross> ejercicioCrossList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejercicio")
    private List<EjercicioGm> ejercicioGmList;

    public Ejercicio() {
    }

    public Ejercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Ejercicio(Integer idEjercicio, String nombre, String descripcion, String tipo) {
        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public Integer getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<EjercicioCross> getEjercicioCrossList() {
        return ejercicioCrossList;
    }

    public void setEjercicioCrossList(List<EjercicioCross> ejercicioCrossList) {
        this.ejercicioCrossList = ejercicioCrossList;
    }

    @XmlTransient
    public List<EjercicioGm> getEjercicioGmList() {
        return ejercicioGmList;
    }

    public void setEjercicioGmList(List<EjercicioGm> ejercicioGmList) {
        this.ejercicioGmList = ejercicioGmList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEjercicio != null ? idEjercicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.idEjercicio == null && other.idEjercicio != null) || (this.idEjercicio != null && !this.idEjercicio.equals(other.idEjercicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Ejercicio[ idEjercicio=" + idEjercicio + " ]";
    }
    
}
