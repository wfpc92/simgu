/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "recibo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibo.findAll", query = "SELECT r FROM Recibo r"),
    @NamedQuery(name = "Recibo.findByIdrecibo", query = "SELECT r FROM Recibo r WHERE r.idrecibo = :idrecibo"),
    @NamedQuery(name = "Recibo.findByValorrecibo", query = "SELECT r FROM Recibo r WHERE r.valorrecibo = :valorrecibo"),
    @NamedQuery(name = "Recibo.findByFechaingreso", query = "SELECT r FROM Recibo r WHERE r.fechaingreso = :fechaingreso"),
    @NamedQuery(name = "Recibo.findByDeuda", query = "SELECT r FROM Recibo r WHERE r.deuda = :deuda"),
    @NamedQuery(name = "Recibo.findByEstadorecibo", query = "SELECT r FROM Recibo r WHERE r.estadorecibo = :estadorecibo")})
public class Recibo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDRECIBO")
    private Integer idrecibo;
    @Column(name = "VALORRECIBO")
    private BigInteger valorrecibo;
    @Column(name = "FECHAINGRESO")
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;
    @Size(max = 10)
    @Column(name = "DEUDA")
    private String deuda;
    @Size(max = 50)
    @Column(name = "ESTADORECIBO")
    private String estadorecibo;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;
    @JoinColumn(name = "IDJORNADA", referencedColumnName = "IDJORNADA")
    @ManyToOne
    private Jornada idjornada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idrecibo")
    private List<Dependecia> dependeciaList;

    public Recibo() {
    }

    public Recibo(Integer idrecibo) {
        this.idrecibo = idrecibo;
    }

    public Integer getIdrecibo() {
        return idrecibo;
    }

    public void setIdrecibo(Integer idrecibo) {
        this.idrecibo = idrecibo;
    }

    public BigInteger getValorrecibo() {
        return valorrecibo;
    }

    public void setValorrecibo(BigInteger valorrecibo) {
        this.valorrecibo = valorrecibo;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getDeuda() {
        return deuda;
    }

    public void setDeuda(String deuda) {
        this.deuda = deuda;
    }

    public String getEstadorecibo() {
        return estadorecibo;
    }

    public void setEstadorecibo(String estadorecibo) {
        this.estadorecibo = estadorecibo;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Jornada getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(Jornada idjornada) {
        this.idjornada = idjornada;
    }

    @XmlTransient
    public List<Dependecia> getDependeciaList() {
        return dependeciaList;
    }

    public void setDependeciaList(List<Dependecia> dependeciaList) {
        this.dependeciaList = dependeciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecibo != null ? idrecibo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recibo)) {
            return false;
        }
        Recibo other = (Recibo) object;
        if ((this.idrecibo == null && other.idrecibo != null) || (this.idrecibo != null && !this.idrecibo.equals(other.idrecibo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Recibo[ idrecibo=" + idrecibo + " ]";
    }
    
}
