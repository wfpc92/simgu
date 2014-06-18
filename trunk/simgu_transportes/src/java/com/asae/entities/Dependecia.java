/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "dependecia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dependecia.findAll", query = "SELECT d FROM Dependecia d"),
    @NamedQuery(name = "Dependecia.findByIddependencia", query = "SELECT d FROM Dependecia d WHERE d.iddependencia = :iddependencia"),
    @NamedQuery(name = "Dependecia.findByNombredependecia", query = "SELECT d FROM Dependecia d WHERE d.nombredependecia = :nombredependecia")})
public class Dependecia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDEPENDENCIA")
    private Integer iddependencia;
    @Size(max = 100)
    @Column(name = "NOMBREDEPENDECIA")
    private String nombredependecia;
    @JoinColumn(name = "IDRECIBO", referencedColumnName = "IDRECIBO")
    @ManyToOne(optional = false)
    private Recibo idrecibo;

    public Dependecia() {
    }

    public Dependecia(Integer iddependencia) {
        this.iddependencia = iddependencia;
    }

    public Integer getIddependencia() {
        return iddependencia;
    }

    public void setIddependencia(Integer iddependencia) {
        this.iddependencia = iddependencia;
    }

    public String getNombredependecia() {
        return nombredependecia;
    }

    public void setNombredependecia(String nombredependecia) {
        this.nombredependecia = nombredependecia;
    }

    public Recibo getIdrecibo() {
        return idrecibo;
    }

    public void setIdrecibo(Recibo idrecibo) {
        this.idrecibo = idrecibo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddependencia != null ? iddependencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dependecia)) {
            return false;
        }
        Dependecia other = (Dependecia) object;
        if ((this.iddependencia == null && other.iddependencia != null) || (this.iddependencia != null && !this.iddependencia.equals(other.iddependencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Dependecia[ iddependencia=" + iddependencia + " ]";
    }
    
}
