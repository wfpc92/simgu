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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andres
 */
@Entity
@Table(name = "RESULTADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultados.findAll", query = "SELECT r FROM Resultados r"),
    @NamedQuery(name = "Resultados.findByIdresultados", query = "SELECT r FROM Resultados r WHERE r.idresultados = :idresultados"),
    @NamedQuery(name = "Resultados.findBySumatoriapliegues", query = "SELECT r FROM Resultados r WHERE r.sumatoriapliegues = :sumatoriapliegues"),
    @NamedQuery(name = "Resultados.findByPorcentajegrasa", query = "SELECT r FROM Resultados r WHERE r.porcentajegrasa = :porcentajegrasa"),
    @NamedQuery(name = "Resultados.findByPesograso", query = "SELECT r FROM Resultados r WHERE r.pesograso = :pesograso"),
    @NamedQuery(name = "Resultados.findByPesolibregrasa", query = "SELECT r FROM Resultados r WHERE r.pesolibregrasa = :pesolibregrasa"),
    @NamedQuery(name = "Resultados.findByMasamuscular", query = "SELECT r FROM Resultados r WHERE r.masamuscular = :masamuscular"),
    @NamedQuery(name = "Resultados.findByPesoideal", query = "SELECT r FROM Resultados r WHERE r.pesoideal = :pesoideal"),
    @NamedQuery(name = "Resultados.findByIndmasamuscular", query = "SELECT r FROM Resultados r WHERE r.indmasamuscular = :indmasamuscular"),
    @NamedQuery(name = "Resultados.findByComplexion", query = "SELECT r FROM Resultados r WHERE r.complexion = :complexion"),
    @NamedQuery(name = "Resultados.findByTasametabbasal", query = "SELECT r FROM Resultados r WHERE r.tasametabbasal = :tasametabbasal"),
    @NamedQuery(name = "Resultados.findByExcesopeso", query = "SELECT r FROM Resultados r WHERE r.excesopeso = :excesopeso"),
    @NamedQuery(name = "Resultados.findByPesooptimo", query = "SELECT r FROM Resultados r WHERE r.pesooptimo = :pesooptimo"),
    @NamedQuery(name = "Resultados.findByFecharesultados", query = "SELECT r FROM Resultados r WHERE r.fecharesultados = :fecharesultados"),
    @NamedQuery(name = "Resultados.findByResgrasa", query = "SELECT r FROM Resultados r WHERE r.resgrasa = :resgrasa"),
    @NamedQuery(name = "Resultados.findByRescomplexion", query = "SELECT r FROM Resultados r WHERE r.rescomplexion = :rescomplexion"),
    @NamedQuery(name = "Resultados.findByResimc", query = "SELECT r FROM Resultados r WHERE r.resimc = :resimc")})
public class Resultados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDRESULTADOS")
    private Integer idresultados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUMATORIAPLIEGUES")
    private float sumatoriapliegues;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJEGRASA")
    private float porcentajegrasa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESOGRASO")
    private float pesograso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESOLIBREGRASA")
    private float pesolibregrasa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MASAMUSCULAR")
    private float masamuscular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESOIDEAL")
    private float pesoideal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INDMASAMUSCULAR")
    private float indmasamuscular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPLEXION")
    private float complexion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TASAMETABBASAL")
    private float tasametabbasal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXCESOPESO")
    private float excesopeso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESOOPTIMO")
    private float pesooptimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHARESULTADOS")
    private float fecharesultados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESGRASA")
    private float resgrasa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESCOMPLEXION")
    private float rescomplexion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESIMC")
    private float resimc;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario idusuario;

    public Resultados() {
    }

    public Resultados(Integer idresultados) {
        this.idresultados = idresultados;
    }

    public Resultados(Integer idresultados, float sumatoriapliegues, float porcentajegrasa, float pesograso, float pesolibregrasa, float masamuscular, float pesoideal, float indmasamuscular, float complexion, float tasametabbasal, float excesopeso, float pesooptimo, float fecharesultados, float resgrasa, float rescomplexion, float resimc) {
        this.idresultados = idresultados;
        this.sumatoriapliegues = sumatoriapliegues;
        this.porcentajegrasa = porcentajegrasa;
        this.pesograso = pesograso;
        this.pesolibregrasa = pesolibregrasa;
        this.masamuscular = masamuscular;
        this.pesoideal = pesoideal;
        this.indmasamuscular = indmasamuscular;
        this.complexion = complexion;
        this.tasametabbasal = tasametabbasal;
        this.excesopeso = excesopeso;
        this.pesooptimo = pesooptimo;
        this.fecharesultados = fecharesultados;
        this.resgrasa = resgrasa;
        this.rescomplexion = rescomplexion;
        this.resimc = resimc;
    }

    public Integer getIdresultados() {
        return idresultados;
    }

    public void setIdresultados(Integer idresultados) {
        this.idresultados = idresultados;
    }

    public float getSumatoriapliegues() {
        return sumatoriapliegues;
    }

    public void setSumatoriapliegues(float sumatoriapliegues) {
        this.sumatoriapliegues = sumatoriapliegues;
    }

    public float getPorcentajegrasa() {
        return porcentajegrasa;
    }

    public void setPorcentajegrasa(float porcentajegrasa) {
        this.porcentajegrasa = porcentajegrasa;
    }

    public float getPesograso() {
        return pesograso;
    }

    public void setPesograso(float pesograso) {
        this.pesograso = pesograso;
    }

    public float getPesolibregrasa() {
        return pesolibregrasa;
    }

    public void setPesolibregrasa(float pesolibregrasa) {
        this.pesolibregrasa = pesolibregrasa;
    }

    public float getMasamuscular() {
        return masamuscular;
    }

    public void setMasamuscular(float masamuscular) {
        this.masamuscular = masamuscular;
    }

    public float getPesoideal() {
        return pesoideal;
    }

    public void setPesoideal(float pesoideal) {
        this.pesoideal = pesoideal;
    }

    public float getIndmasamuscular() {
        return indmasamuscular;
    }

    public void setIndmasamuscular(float indmasamuscular) {
        this.indmasamuscular = indmasamuscular;
    }

    public float getComplexion() {
        return complexion;
    }

    public void setComplexion(float complexion) {
        this.complexion = complexion;
    }

    public float getTasametabbasal() {
        return tasametabbasal;
    }

    public void setTasametabbasal(float tasametabbasal) {
        this.tasametabbasal = tasametabbasal;
    }

    public float getExcesopeso() {
        return excesopeso;
    }

    public void setExcesopeso(float excesopeso) {
        this.excesopeso = excesopeso;
    }

    public float getPesooptimo() {
        return pesooptimo;
    }

    public void setPesooptimo(float pesooptimo) {
        this.pesooptimo = pesooptimo;
    }

    public float getFecharesultados() {
        return fecharesultados;
    }

    public void setFecharesultados(float fecharesultados) {
        this.fecharesultados = fecharesultados;
    }

    public float getResgrasa() {
        return resgrasa;
    }

    public void setResgrasa(float resgrasa) {
        this.resgrasa = resgrasa;
    }

    public float getRescomplexion() {
        return rescomplexion;
    }

    public void setRescomplexion(float rescomplexion) {
        this.rescomplexion = rescomplexion;
    }

    public float getResimc() {
        return resimc;
    }

    public void setResimc(float resimc) {
        this.resimc = resimc;
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
        hash += (idresultados != null ? idresultados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultados)) {
            return false;
        }
        Resultados other = (Resultados) object;
        if ((this.idresultados == null && other.idresultados != null) || (this.idresultados != null && !this.idresultados.equals(other.idresultados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Resultados[ idresultados=" + idresultados + " ]";
    }
    
}
