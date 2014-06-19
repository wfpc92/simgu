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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "MEDIDAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medidas.findAll", query = "SELECT m FROM Medidas m"),
    @NamedQuery(name = "Medidas.findByIdMedidas", query = "SELECT m FROM Medidas m WHERE m.idMedidas = :idMedidas"),
    @NamedQuery(name = "Medidas.findByTricepsmedida", query = "SELECT m FROM Medidas m WHERE m.tricepsmedida = :tricepsmedida"),
    @NamedQuery(name = "Medidas.findBySubescapularmedida", query = "SELECT m FROM Medidas m WHERE m.subescapularmedida = :subescapularmedida"),
    @NamedQuery(name = "Medidas.findBySuprailiacomedida", query = "SELECT m FROM Medidas m WHERE m.suprailiacomedida = :suprailiacomedida"),
    @NamedQuery(name = "Medidas.findByAbdominalmedida", query = "SELECT m FROM Medidas m WHERE m.abdominalmedida = :abdominalmedida"),
    @NamedQuery(name = "Medidas.findByMuslomedida", query = "SELECT m FROM Medidas m WHERE m.muslomedida = :muslomedida"),
    @NamedQuery(name = "Medidas.findByPantorrillamedida", query = "SELECT m FROM Medidas m WHERE m.pantorrillamedida = :pantorrillamedida"),
    @NamedQuery(name = "Medidas.findByPerimetromunecamedida", query = "SELECT m FROM Medidas m WHERE m.perimetromunecamedida = :perimetromunecamedida"),
    @NamedQuery(name = "Medidas.findByPerimetrobrazomedida", query = "SELECT m FROM Medidas m WHERE m.perimetrobrazomedida = :perimetrobrazomedida"),
    @NamedQuery(name = "Medidas.findByPerimetroantebrazomedida", query = "SELECT m FROM Medidas m WHERE m.perimetroantebrazomedida = :perimetroantebrazomedida"),
    @NamedQuery(name = "Medidas.findByPerimetropantorrillamedida", query = "SELECT m FROM Medidas m WHERE m.perimetropantorrillamedida = :perimetropantorrillamedida"),
    @NamedQuery(name = "Medidas.findByPerimetrocajatoraxicamedida", query = "SELECT m FROM Medidas m WHERE m.perimetrocajatoraxicamedida = :perimetrocajatoraxicamedida"),
    @NamedQuery(name = "Medidas.findByPerimetromuslomedida", query = "SELECT m FROM Medidas m WHERE m.perimetromuslomedida = :perimetromuslomedida"),
    @NamedQuery(name = "Medidas.findByFechamedidas", query = "SELECT m FROM Medidas m WHERE m.fechamedidas = :fechamedidas"),
    @NamedQuery(name = "Medidas.findByPeso", query = "SELECT m FROM Medidas m WHERE m.peso = :peso"),
    @NamedQuery(name = "Medidas.findByAltura", query = "SELECT m FROM Medidas m WHERE m.altura = :altura")})
public class Medidas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MEDIDAS")
    private Integer idMedidas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRICEPSMEDIDA")
    private float tricepsmedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBESCAPULARMEDIDA")
    private float subescapularmedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUPRAILIACOMEDIDA")
    private float suprailiacomedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABDOMINALMEDIDA")
    private float abdominalmedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MUSLOMEDIDA")
    private float muslomedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PANTORRILLAMEDIDA")
    private float pantorrillamedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIMETROMUNECAMEDIDA")
    private float perimetromunecamedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIMETROBRAZOMEDIDA")
    private float perimetrobrazomedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIMETROANTEBRAZOMEDIDA")
    private float perimetroantebrazomedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIMETROPANTORRILLAMEDIDA")
    private float perimetropantorrillamedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIMETROCAJATORAXICAMEDIDA")
    private float perimetrocajatoraxicamedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERIMETROMUSLOMEDIDA")
    private float perimetromuslomedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAMEDIDAS")
    @Temporal(TemporalType.DATE)
    private Date fechamedidas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESO")
    private float peso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ALTURA")
    private Float altura;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;

    public Medidas() {
    }

    public Medidas(Integer idMedidas) {
        this.idMedidas = idMedidas;
    }

    public Medidas(Integer idMedidas, float tricepsmedida, float subescapularmedida, float suprailiacomedida, float abdominalmedida, float muslomedida, float pantorrillamedida, float perimetromunecamedida, float perimetrobrazomedida, float perimetroantebrazomedida, float perimetropantorrillamedida, float perimetrocajatoraxicamedida, float perimetromuslomedida, Date fechamedidas, float peso) {
        this.idMedidas = idMedidas;
        this.tricepsmedida = tricepsmedida;
        this.subescapularmedida = subescapularmedida;
        this.suprailiacomedida = suprailiacomedida;
        this.abdominalmedida = abdominalmedida;
        this.muslomedida = muslomedida;
        this.pantorrillamedida = pantorrillamedida;
        this.perimetromunecamedida = perimetromunecamedida;
        this.perimetrobrazomedida = perimetrobrazomedida;
        this.perimetroantebrazomedida = perimetroantebrazomedida;
        this.perimetropantorrillamedida = perimetropantorrillamedida;
        this.perimetrocajatoraxicamedida = perimetrocajatoraxicamedida;
        this.perimetromuslomedida = perimetromuslomedida;
        this.fechamedidas = fechamedidas;
        this.peso = peso;
    }

    public Integer getIdMedidas() {
        return idMedidas;
    }

    public void setIdMedidas(Integer idMedidas) {
        this.idMedidas = idMedidas;
    }

    public float getTricepsmedida() {
        return tricepsmedida;
    }

    public void setTricepsmedida(float tricepsmedida) {
        this.tricepsmedida = tricepsmedida;
    }

    public float getSubescapularmedida() {
        return subescapularmedida;
    }

    public void setSubescapularmedida(float subescapularmedida) {
        this.subescapularmedida = subescapularmedida;
    }

    public float getSuprailiacomedida() {
        return suprailiacomedida;
    }

    public void setSuprailiacomedida(float suprailiacomedida) {
        this.suprailiacomedida = suprailiacomedida;
    }

    public float getAbdominalmedida() {
        return abdominalmedida;
    }

    public void setAbdominalmedida(float abdominalmedida) {
        this.abdominalmedida = abdominalmedida;
    }

    public float getMuslomedida() {
        return muslomedida;
    }

    public void setMuslomedida(float muslomedida) {
        this.muslomedida = muslomedida;
    }

    public float getPantorrillamedida() {
        return pantorrillamedida;
    }

    public void setPantorrillamedida(float pantorrillamedida) {
        this.pantorrillamedida = pantorrillamedida;
    }

    public float getPerimetromunecamedida() {
        return perimetromunecamedida;
    }

    public void setPerimetromunecamedida(float perimetromunecamedida) {
        this.perimetromunecamedida = perimetromunecamedida;
    }

    public float getPerimetrobrazomedida() {
        return perimetrobrazomedida;
    }

    public void setPerimetrobrazomedida(float perimetrobrazomedida) {
        this.perimetrobrazomedida = perimetrobrazomedida;
    }

    public float getPerimetroantebrazomedida() {
        return perimetroantebrazomedida;
    }

    public void setPerimetroantebrazomedida(float perimetroantebrazomedida) {
        this.perimetroantebrazomedida = perimetroantebrazomedida;
    }

    public float getPerimetropantorrillamedida() {
        return perimetropantorrillamedida;
    }

    public void setPerimetropantorrillamedida(float perimetropantorrillamedida) {
        this.perimetropantorrillamedida = perimetropantorrillamedida;
    }

    public float getPerimetrocajatoraxicamedida() {
        return perimetrocajatoraxicamedida;
    }

    public void setPerimetrocajatoraxicamedida(float perimetrocajatoraxicamedida) {
        this.perimetrocajatoraxicamedida = perimetrocajatoraxicamedida;
    }

    public float getPerimetromuslomedida() {
        return perimetromuslomedida;
    }

    public void setPerimetromuslomedida(float perimetromuslomedida) {
        this.perimetromuslomedida = perimetromuslomedida;
    }

    public Date getFechamedidas() {
        return fechamedidas;
    }

    public void setFechamedidas(Date fechamedidas) {
        this.fechamedidas = fechamedidas;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
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
        hash += (idMedidas != null ? idMedidas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medidas)) {
            return false;
        }
        Medidas other = (Medidas) object;
        if ((this.idMedidas == null && other.idMedidas != null) || (this.idMedidas != null && !this.idMedidas.equals(other.idMedidas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Medidas[ idMedidas=" + idMedidas + " ]";
    }
    
}
