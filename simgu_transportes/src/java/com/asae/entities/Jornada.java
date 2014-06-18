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
@Table(name = "jornada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jornada.findAll", query = "SELECT j FROM Jornada j"),
    @NamedQuery(name = "Jornada.findByIdjornada", query = "SELECT j FROM Jornada j WHERE j.idjornada = :idjornada"),
    @NamedQuery(name = "Jornada.findByNombrejornada", query = "SELECT j FROM Jornada j WHERE j.nombrejornada = :nombrejornada"),
    @NamedQuery(name = "Jornada.findByValorjornada", query = "SELECT j FROM Jornada j WHERE j.valorjornada = :valorjornada"),
    @NamedQuery(name = "Jornada.findByHorasjornada", query = "SELECT j FROM Jornada j WHERE j.horasjornada = :horasjornada")})
public class Jornada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDJORNADA")
    private Integer idjornada;
    @Size(max = 50)
    @Column(name = "NOMBREJORNADA")
    private String nombrejornada;
    @Column(name = "VALORJORNADA")
    private Integer valorjornada;
    @Column(name = "HORASJORNADA")
    private Integer horasjornada;
    @OneToMany(mappedBy = "idjornada")
    private List<Recibo> reciboList;

    public Jornada() {
    }

    public Jornada(Integer idjornada) {
        this.idjornada = idjornada;
    }

    public Integer getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(Integer idjornada) {
        this.idjornada = idjornada;
    }

    public String getNombrejornada() {
        return nombrejornada;
    }

    public void setNombrejornada(String nombrejornada) {
        this.nombrejornada = nombrejornada;
    }

    public Integer getValorjornada() {
        return valorjornada;
    }

    public void setValorjornada(Integer valorjornada) {
        this.valorjornada = valorjornada;
    }

    public Integer getHorasjornada() {
        return horasjornada;
    }

    public void setHorasjornada(Integer horasjornada) {
        this.horasjornada = horasjornada;
    }

    @XmlTransient
    public List<Recibo> getReciboList() {
        return reciboList;
    }

    public void setReciboList(List<Recibo> reciboList) {
        this.reciboList = reciboList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjornada != null ? idjornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jornada)) {
            return false;
        }
        Jornada other = (Jornada) object;
        if ((this.idjornada == null && other.idjornada != null) || (this.idjornada != null && !this.idjornada.equals(other.idjornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Jornada[ idjornada=" + idjornada + " ]";
    }
    
}
