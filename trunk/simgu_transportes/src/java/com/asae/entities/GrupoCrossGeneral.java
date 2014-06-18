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
@Table(name = "grupo_cross_general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoCrossGeneral.findAll", query = "SELECT g FROM GrupoCrossGeneral g"),
    @NamedQuery(name = "GrupoCrossGeneral.findByIdgrupoCrossGeneral", query = "SELECT g FROM GrupoCrossGeneral g WHERE g.idgrupoCrossGeneral = :idgrupoCrossGeneral"),
    @NamedQuery(name = "GrupoCrossGeneral.findByNombre", query = "SELECT g FROM GrupoCrossGeneral g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GrupoCrossGeneral.findByDescripcion", query = "SELECT g FROM GrupoCrossGeneral g WHERE g.descripcion = :descripcion")})
public class GrupoCrossGeneral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDGRUPO_CROSS_GENERAL")
    private Integer idgrupoCrossGeneral;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idgrupoCrossGeneral")
    private List<GrupoCross> grupoCrossList;

    public GrupoCrossGeneral() {
    }

    public GrupoCrossGeneral(Integer idgrupoCrossGeneral) {
        this.idgrupoCrossGeneral = idgrupoCrossGeneral;
    }

    public GrupoCrossGeneral(Integer idgrupoCrossGeneral, String nombre, String descripcion) {
        this.idgrupoCrossGeneral = idgrupoCrossGeneral;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdgrupoCrossGeneral() {
        return idgrupoCrossGeneral;
    }

    public void setIdgrupoCrossGeneral(Integer idgrupoCrossGeneral) {
        this.idgrupoCrossGeneral = idgrupoCrossGeneral;
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
    public List<GrupoCross> getGrupoCrossList() {
        return grupoCrossList;
    }

    public void setGrupoCrossList(List<GrupoCross> grupoCrossList) {
        this.grupoCrossList = grupoCrossList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupoCrossGeneral != null ? idgrupoCrossGeneral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoCrossGeneral)) {
            return false;
        }
        GrupoCrossGeneral other = (GrupoCrossGeneral) object;
        if ((this.idgrupoCrossGeneral == null && other.idgrupoCrossGeneral != null) || (this.idgrupoCrossGeneral != null && !this.idgrupoCrossGeneral.equals(other.idgrupoCrossGeneral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.GrupoCrossGeneral[ idgrupoCrossGeneral=" + idgrupoCrossGeneral + " ]";
    }
    
}
