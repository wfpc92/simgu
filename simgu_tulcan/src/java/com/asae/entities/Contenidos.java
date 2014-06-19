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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "CONTENIDOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contenidos.findAll", query = "SELECT c FROM Contenidos c"),
    @NamedQuery(name = "Contenidos.findByIdcontenido", query = "SELECT c FROM Contenidos c WHERE c.idcontenido = :idcontenido"),
    @NamedQuery(name = "Contenidos.findByTitulocontenido", query = "SELECT c FROM Contenidos c WHERE c.titulocontenido = :titulocontenido"),
    @NamedQuery(name = "Contenidos.findByDescripcioncontenido", query = "SELECT c FROM Contenidos c WHERE c.descripcioncontenido = :descripcioncontenido"),
    @NamedQuery(name = "Contenidos.findByTextocontenido", query = "SELECT c FROM Contenidos c WHERE c.textocontenido = :textocontenido"),
    @NamedQuery(name = "Contenidos.findByTipocontenido", query = "SELECT c FROM Contenidos c WHERE c.tipocontenido = :tipocontenido"),
    @NamedQuery(name = "Contenidos.findByEstadocontenido", query = "SELECT c FROM Contenidos c WHERE c.estadocontenido = :estadocontenido"),
    @NamedQuery(name = "Contenidos.findByFechapublicacion", query = "SELECT c FROM Contenidos c WHERE c.fechapublicacion = :fechapublicacion")})
public class Contenidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCONTENIDO")
    private Integer idcontenido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITULOCONTENIDO")
    private String titulocontenido;
    @Size(max = 100)
    @Column(name = "DESCRIPCIONCONTENIDO")
    private String descripcioncontenido;
    @Size(max = 800)
    @Column(name = "TEXTOCONTENIDO")
    private String textocontenido;
    @Size(max = 20)
    @Column(name = "TIPOCONTENIDO")
    private String tipocontenido;
    @Size(max = 20)
    @Column(name = "ESTADOCONTENIDO")
    private String estadocontenido;
    @Column(name = "FECHAPUBLICACION")
    @Temporal(TemporalType.DATE)
    private Date fechapublicacion;

    public Contenidos() {
    }

    public Contenidos(Integer idcontenido) {
        this.idcontenido = idcontenido;
    }

    public Contenidos(Integer idcontenido, String titulocontenido) {
        this.idcontenido = idcontenido;
        this.titulocontenido = titulocontenido;
    }

    public Integer getIdcontenido() {
        return idcontenido;
    }

    public void setIdcontenido(Integer idcontenido) {
        this.idcontenido = idcontenido;
    }

    public String getTitulocontenido() {
        return titulocontenido;
    }

    public void setTitulocontenido(String titulocontenido) {
        this.titulocontenido = titulocontenido;
    }

    public String getDescripcioncontenido() {
        return descripcioncontenido;
    }

    public void setDescripcioncontenido(String descripcioncontenido) {
        this.descripcioncontenido = descripcioncontenido;
    }

    public String getTextocontenido() {
        return textocontenido;
    }

    public void setTextocontenido(String textocontenido) {
        this.textocontenido = textocontenido;
    }

    public String getTipocontenido() {
        return tipocontenido;
    }

    public void setTipocontenido(String tipocontenido) {
        this.tipocontenido = tipocontenido;
    }

    public String getEstadocontenido() {
        return estadocontenido;
    }

    public void setEstadocontenido(String estadocontenido) {
        this.estadocontenido = estadocontenido;
    }

    public Date getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Date fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontenido != null ? idcontenido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenidos)) {
            return false;
        }
        Contenidos other = (Contenidos) object;
        if ((this.idcontenido == null && other.idcontenido != null) || (this.idcontenido != null && !this.idcontenido.equals(other.idcontenido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Contenidos[ idcontenido=" + idcontenido + " ]";
    }
    
}
