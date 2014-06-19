/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author andres
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByIdentification", query = "SELECT u FROM Usuario u WHERE u.identification = :identification"),
    @NamedQuery(name = "Usuario.findByFisrtname", query = "SELECT u FROM Usuario u WHERE u.fisrtname = :fisrtname"),
    @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findBySecondname", query = "SELECT u FROM Usuario u WHERE u.secondname = :secondname"),
    @NamedQuery(name = "Usuario.findByFirstlastname", query = "SELECT u FROM Usuario u WHERE u.firstlastname = :firstlastname"),
    @NamedQuery(name = "Usuario.findBySecondlastname", query = "SELECT u FROM Usuario u WHERE u.secondlastname = :secondlastname"),
    @NamedQuery(name = "Usuario.findBySex", query = "SELECT u FROM Usuario u WHERE u.sex = :sex"),
    @NamedQuery(name = "Usuario.findByDatebirth", query = "SELECT u FROM Usuario u WHERE u.datebirth = :datebirth"),
    @NamedQuery(name = "Usuario.findByCitybirth", query = "SELECT u FROM Usuario u WHERE u.citybirth = :citybirth"),
    @NamedQuery(name = "Usuario.findByDepartmentbirth", query = "SELECT u FROM Usuario u WHERE u.departmentbirth = :departmentbirth"),
    @NamedQuery(name = "Usuario.findByTypeidentification", query = "SELECT u FROM Usuario u WHERE u.typeidentification = :typeidentification"),
    @NamedQuery(name = "Usuario.findByRh", query = "SELECT u FROM Usuario u WHERE u.rh = :rh"),
    @NamedQuery(name = "Usuario.findByGs", query = "SELECT u FROM Usuario u WHERE u.gs = :gs"),
    @NamedQuery(name = "Usuario.findByEps", query = "SELECT u FROM Usuario u WHERE u.eps = :eps"),
    @NamedQuery(name = "Usuario.findByArp", query = "SELECT u FROM Usuario u WHERE u.arp = :arp"),
    @NamedQuery(name = "Usuario.findByAddress", query = "SELECT u FROM Usuario u WHERE u.address = :address"),
    @NamedQuery(name = "Usuario.findByTelephon", query = "SELECT u FROM Usuario u WHERE u.telephon = :telephon"),
    @NamedQuery(name = "Usuario.findByMovil", query = "SELECT u FROM Usuario u WHERE u.movil = :movil"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByDependencia", query = "SELECT u FROM Usuario u WHERE u.dependencia = :dependencia"),
    @NamedQuery(name = "Usuario.findByCargo", query = "SELECT u FROM Usuario u WHERE u.cargo = :cargo"),
    @NamedQuery(name = "Usuario.findByJefedependencia", query = "SELECT u FROM Usuario u WHERE u.jefedependencia = :jefedependencia"),
    @NamedQuery(name = "Usuario.findByTelefonodependencia", query = "SELECT u FROM Usuario u WHERE u.telefonodependencia = :telefonodependencia"),
    @NamedQuery(name = "Usuario.findByExtensiondependencia", query = "SELECT u FROM Usuario u WHERE u.extensiondependencia = :extensiondependencia"),
    @NamedQuery(name = "Usuario.findByEmaildependencia", query = "SELECT u FROM Usuario u WHERE u.emaildependencia = :emaildependencia"),
    @NamedQuery(name = "Usuario.findByParentezco", query = "SELECT u FROM Usuario u WHERE u.parentezco = :parentezco"),
    @NamedQuery(name = "Usuario.findByIdfamiliar", query = "SELECT u FROM Usuario u WHERE u.idfamiliar = :idfamiliar")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSUARIO")
    private Integer idusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDENTIFICATION")
    private int identification;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FISRTNAME")
    private String fisrtname;
    @Size(max = 128)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 128)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 100)
    @Column(name = "SECONDNAME")
    private String secondname;
    @Size(max = 100)
    @Column(name = "FIRSTLASTNAME")
    private String firstlastname;
    @Size(max = 100)
    @Column(name = "SECONDLASTNAME")
    private String secondlastname;
    @Size(max = 10)
    @Column(name = "SEX")
    private String sex;
    @Column(name = "DATEBIRTH")
    @Temporal(TemporalType.DATE)
    private Date datebirth;
    @Size(max = 60)
    @Column(name = "CITYBIRTH")
    private String citybirth;
    @Size(max = 60)
    @Column(name = "DEPARTMENTBIRTH")
    private String departmentbirth;
    @Size(max = 45)
    @Column(name = "TYPEIDENTIFICATION")
    private String typeidentification;
    @Column(name = "RH")
    private Character rh;
    @Size(max = 2)
    @Column(name = "GS")
    private String gs;
    @Size(max = 100)
    @Column(name = "EPS")
    private String eps;
    @Size(max = 100)
    @Column(name = "ARP")
    private String arp;
    @Size(max = 150)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "TELEPHON")
    private Integer telephon;
    @Column(name = "MOVIL")
    private Integer movil;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 60)
    @Column(name = "DEPENDENCIA")
    private String dependencia;
    @Size(max = 60)
    @Column(name = "CARGO")
    private String cargo;
    @Size(max = 100)
    @Column(name = "JEFEDEPENDENCIA")
    private String jefedependencia;
    @Column(name = "TELEFONODEPENDENCIA")
    private Integer telefonodependencia;
    @Column(name = "EXTENSIONDEPENDENCIA")
    private Integer extensiondependencia;
    @Size(max = 100)
    @Column(name = "EMAILDEPENDENCIA")
    private String emaildependencia;
    @Size(max = 60)
    @Column(name = "PARENTEZCO")
    private String parentezco;
    @Column(name = "IDFAMILIAR")
    private Integer idfamiliar;
    @OneToMany(mappedBy = "idusuario")
    private List<Asistencia> asistenciaList;
    @OneToMany(mappedBy = "idusuario")
    private List<Medidas> medidasList;
    @OneToMany(mappedBy = "idusuario")
    private List<Testsargent> testsargentList;
    @OneToMany(mappedBy = "idusuario")
    private List<Resultados> resultadosList;
    @OneToMany(mappedBy = "idusuario")
    private List<Testruffier> testruffierList;
    @JoinColumn(name = "ROLE_IDROLE", referencedColumnName = "IDROLE")
    @ManyToOne
    private Role roleIdrole;
    @OneToMany(mappedBy = "idusuario")
    private List<Testwells> testwellsList;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, int identification, String fisrtname) {
        this.idusuario = idusuario;
        this.identification = identification;
        this.fisrtname = fisrtname;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public String getFisrtname() {
        return fisrtname;
    }

    public void setFisrtname(String fisrtname) {
        this.fisrtname = fisrtname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getFirstlastname() {
        return firstlastname;
    }

    public void setFirstlastname(String firstlastname) {
        this.firstlastname = firstlastname;
    }

    public String getSecondlastname() {
        return secondlastname;
    }

    public void setSecondlastname(String secondlastname) {
        this.secondlastname = secondlastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(Date datebirth) {
        this.datebirth = datebirth;
    }

    public String getCitybirth() {
        return citybirth;
    }

    public void setCitybirth(String citybirth) {
        this.citybirth = citybirth;
    }

    public String getDepartmentbirth() {
        return departmentbirth;
    }

    public void setDepartmentbirth(String departmentbirth) {
        this.departmentbirth = departmentbirth;
    }

    public String getTypeidentification() {
        return typeidentification;
    }

    public void setTypeidentification(String typeidentification) {
        this.typeidentification = typeidentification;
    }

    public Character getRh() {
        return rh;
    }

    public void setRh(Character rh) {
        this.rh = rh;
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getArp() {
        return arp;
    }

    public void setArp(String arp) {
        this.arp = arp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelephon() {
        return telephon;
    }

    public void setTelephon(Integer telephon) {
        this.telephon = telephon;
    }

    public Integer getMovil() {
        return movil;
    }

    public void setMovil(Integer movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getJefedependencia() {
        return jefedependencia;
    }

    public void setJefedependencia(String jefedependencia) {
        this.jefedependencia = jefedependencia;
    }

    public Integer getTelefonodependencia() {
        return telefonodependencia;
    }

    public void setTelefonodependencia(Integer telefonodependencia) {
        this.telefonodependencia = telefonodependencia;
    }

    public Integer getExtensiondependencia() {
        return extensiondependencia;
    }

    public void setExtensiondependencia(Integer extensiondependencia) {
        this.extensiondependencia = extensiondependencia;
    }

    public String getEmaildependencia() {
        return emaildependencia;
    }

    public void setEmaildependencia(String emaildependencia) {
        this.emaildependencia = emaildependencia;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }

    public Integer getIdfamiliar() {
        return idfamiliar;
    }

    public void setIdfamiliar(Integer idfamiliar) {
        this.idfamiliar = idfamiliar;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @XmlTransient
    public List<Medidas> getMedidasList() {
        return medidasList;
    }

    public void setMedidasList(List<Medidas> medidasList) {
        this.medidasList = medidasList;
    }

    @XmlTransient
    public List<Testsargent> getTestsargentList() {
        return testsargentList;
    }

    public void setTestsargentList(List<Testsargent> testsargentList) {
        this.testsargentList = testsargentList;
    }

    @XmlTransient
    public List<Resultados> getResultadosList() {
        return resultadosList;
    }

    public void setResultadosList(List<Resultados> resultadosList) {
        this.resultadosList = resultadosList;
    }

    @XmlTransient
    public List<Testruffier> getTestruffierList() {
        return testruffierList;
    }

    public void setTestruffierList(List<Testruffier> testruffierList) {
        this.testruffierList = testruffierList;
    }

    public Role getRoleIdrole() {
        return roleIdrole;
    }

    public void setRoleIdrole(Role roleIdrole) {
        this.roleIdrole = roleIdrole;
    }

    @XmlTransient
    public List<Testwells> getTestwellsList() {
        return testwellsList;
    }

    public void setTestwellsList(List<Testwells> testwellsList) {
        this.testwellsList = testwellsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.asae.entities.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
