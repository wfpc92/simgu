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
@Table(name = "grupo_muscular_general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoMuscularGeneral.findAll", query = "SELECT g FROM GrupoMuscularGeneral g"),
    @NamedQuery(name = "GrupoMuscularGeneral.findByIdgrupoMuscularGeneral", query = "SELECT g FROM GrupoMuscularGeneral g WHERE g.idgrupoMuscularGeneral = :idgrupoMuscularGeneral"),
    @NamedQuery(name = "GrupoMuscularGeneral.findByNombre", query = "SELECT g FROM GrupoMuscularGeneral g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GrupoMuscularGeneral.findByDescripcion", query = "SELECT g FROM GrupoMuscularGeneral g WHERE g.descripcion = :descripcion")})
public class GrupoMuscularGeneral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDGRUPO_MUSCULAR_GENERAL")
    private Integer idgrupoMuscularGeneral;
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
    @OneToMany(mappedBy = "idgrupoMuscularGeneral")
    private List<GrupoMuscular> grupoMuscularList;

    public GrupoMuscularGeneral() {
    }

    public GrupoMuscularGeneral(Integer idgrupoMuscularGeneral) {
        this.idgrupoMuscularGeneral = idgrupoMuscularGeneral;
    }

    public GrupoMuscularGeneral(Integer idgrupoMuscularGeneral, String nombre, String descripcion) {
        this.idgrupoMuscularGeneral = idgrupoMuscularGeneral;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdgrupoMuscularGeneral() {
        return idgrupoMuscularGeneral;
    }

    public void setIdgrupoMuscularGeneral(Integer idgrupoMuscularGeneral) {
        this.idgrupoMuscularGeneral = idgrupoMuscularGeneral;
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
    public List<GrupoMuscular> getGrupoMuscularList() {
        return grupoMuscularList;
    }

    public void setGrupoMuscularList(List<GrupoMuscular> grupoMuscularList) {
        this.grupoMuscularList = grupoMuscularList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupoMuscularGeneral != null ? idgrupoMuscularGeneral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoMuscularGeneral)) {
            return false;
        }
        GrupoMuscularGeneral other = (GrupoMuscularGeneral) object;
        if ((this.idgrupoMuscularGeneral == null && other.idgrupoMuscularGeneral != null) || (this.idgrupoMuscularGeneral != null && !this.idgrupoMuscularGeneral.equals(other.idgrupoMuscularGeneral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.GrupoMuscularGeneral[ idgrupoMuscularGeneral=" + idgrupoMuscularGeneral + " ]";
    }
    
}
