/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.entities;

import java.io.Serializable;
import java.util.Comparator;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "dia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dia.findAll", query = "SELECT d FROM Dia d"),
    @NamedQuery(name = "Dia.findByIdDia", query = "SELECT d FROM Dia d WHERE d.idDia = :idDia"),
    @NamedQuery(name = "Dia.findByIdRutina", query = "SELECT d FROM Dia d JOIN d.rutinaList r WHERE r.idRutina = :idRutina ORDER BY d.numDia"),
    @NamedQuery(name = "Dia.findByNombre", query = "SELECT d FROM Dia d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Dia.findByNumDia", query = "SELECT d FROM Dia d WHERE d.numDia = :numDia")})
public class Dia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIA")
    private Integer idDia;
    @Size(max = 15)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NUM_DIA")
    private Integer numDia;
    @JoinTable(name = "dia_cross", joinColumns = {
        @JoinColumn(name = "ID_DIA", referencedColumnName = "ID_DIA")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_CROSS", referencedColumnName = "ID_CROSS")})
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<GrupoCross> grupoCrossList;
    @JoinTable(name = "dia_grupo_muscular", joinColumns = {
        @JoinColumn(name = "ID_DIA", referencedColumnName = "ID_DIA")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_GRUPO_MUSCULAR", referencedColumnName = "ID_GRUPO_MUSCULAR")})
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<GrupoMuscular> grupoMuscularList;
    @JoinTable(name = "rutina_dia", joinColumns = {
        @JoinColumn(name = "ID_DIA", referencedColumnName = "ID_DIA")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_RUTINA", referencedColumnName = "ID_RUTINA")})
    @ManyToMany
    private List<Rutina> rutinaList;

    public Dia() {
    }

    public Dia(Integer idDia) {
        this.idDia = idDia;
    }

    public Integer getIdDia() {
        return idDia;
    }

    public void setIdDia(Integer idDia) {
        this.idDia = idDia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumDia() {
        return numDia;
    }

    public void setNumDia(Integer numDia) {
        this.numDia = numDia;
    }

    @XmlTransient
    public List<GrupoCross> getGrupoCrossList() {
        return grupoCrossList;
    }

    public void setGrupoCrossList(List<GrupoCross> grupoCrossList) {
        this.grupoCrossList = grupoCrossList;
    }

    @XmlTransient
    public List<GrupoMuscular> getGrupoMuscularList() {
        return grupoMuscularList;
    }

    public void setGrupoMuscularList(List<GrupoMuscular> grupoMuscularList) {
        this.grupoMuscularList = grupoMuscularList;
    }

    @XmlTransient
    public List<Rutina> getRutinaList() {
        return rutinaList;
    }

    public void setRutinaList(List<Rutina> rutinaList) {
        this.rutinaList = rutinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDia != null ? idDia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dia)) {
            return false;
        }
        Dia other = (Dia) object;
        if ((this.idDia == null && other.idDia != null) || (this.idDia != null && !this.idDia.equals(other.idDia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Dia[ idDia=" + idDia + " ]";
    }
     public int compareTo(Dia d) {
        return NUMDIA.compare(this, d);
    }
    
    public static Comparator<Dia> NUMDIA = new Comparator<Dia>() {
            @Override
            public int compare(Dia d1, Dia d2) {
                return d1.numDia - d2.numDia;
            }
        };
}
