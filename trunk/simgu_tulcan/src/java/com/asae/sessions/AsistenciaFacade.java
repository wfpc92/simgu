/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessions;

import com.asae.entities.Asistencia;
import com.asae.entities.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

/**
 *
 * @author andres
 */
@Stateless
public class AsistenciaFacade extends AbstractFacade<Asistencia> {
    @PersistenceContext(unitName = "simgu_tulcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaFacade() {
        super(Asistencia.class);
    }
    
    public List<Object[]> getAsistenciaEntreFechas(String estamento, Date finic, Date ffin) {
        if(finic == null && ffin == null) {
            if(estamento.equals("todos")) {
                return em.createNamedQuery("Asistencia.findBetweenFechasTodosNoEst").getResultList();        
            } else {
                //return em.createNamedQuery("Asistencia.findBetweenFechasTodos").setParameter("estamento", estamento).getResultList();        
                return em.createNamedQuery("Asistencia.findBetweenFechasTodos").getResultList();        
            }            
        } else if(ffin == null) {
            if(estamento.equals("todos")) {
                return em.createNamedQuery("Asistencia.findBetweenFechasDerechaNoEst").setParameter("finic", finic).getResultList();        
            } else {
                //return em.createNamedQuery("Asistencia.findBetweenFechasDerecha").setParameter("estamento", estamento).setParameter("finic", finic).getResultList();        
                return em.createNamedQuery("Asistencia.findBetweenFechasDerecha").setParameter("finic", finic).getResultList();        
            }
        } else if(finic == null) {
            if(estamento.equals("todos")) {
                return em.createNamedQuery("Asistencia.findBetweenFechasIzquierdaNoEst").setParameter("ffin", ffin).getResultList();        
            } else {
                return em.createNamedQuery("Asistencia.findBetweenFechasIzquierda").setParameter("ffin", ffin).getResultList();        
            }
        } else {
            if(estamento.equals("todos")) {
                return em.createNamedQuery("Asistencia.findBetweenFechasNoEst").setParameter("finic", finic).setParameter("ffin", ffin).getResultList();        
            } else {
                return em.createNamedQuery("Asistencia.findBetweenFechas").setParameter("finic", finic).setParameter("ffin", ffin).getResultList();        
            }
        }
    }
    
    public List<Object[]> getAsistenciaEntreFechasNoGroup(String estamento, Date finic, Date ffin) {
        if(finic != null && ffin != null) {
            if(estamento.equals("todos")) {
                return em.createNamedQuery("Asistencia.findBetweenFechasNoGroup").setParameter("finic", finic).setParameter("ffin", ffin).getResultList();        
            }           
        } 
        return null;
    }
    
    public boolean asistenciaFecha(Asistencia atendido, Date fecha) {
        Date finic = (Date)fecha.clone();
        Date ffin = (Date)fecha.clone();
        finic.setHours(0);
        finic.setMinutes(0);
        finic.setSeconds(0);
        ffin.setHours(23);
        ffin.setMinutes(59);
        ffin.setSeconds(59);
        long cResults = (long)em.createNamedQuery("Asistencia.findByAsistenciaUsuario").setParameter("idUsuario", atendido.getIdusuario()).setParameter("finic", finic).setParameter("ffin", ffin).getSingleResult();
        return cResults > 0;
    }
    
    public List<Asistencia> findAllOrderDate() {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Asistencia> asis = cq.from(Asistencia.class);
        cq.select(asis);
        //cq.orderBy(cb.desc(asis.get(Asistencia_.fecha)));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public List<Asistencia> fetchByDate(Date fecha) {
        Date finic = (Date)fecha.clone();
        Date ffin = (Date)fecha.clone();
        finic.setHours(0);
        finic.setMinutes(0);
        finic.setSeconds(0);
        ffin.setHours(23);
        ffin.setMinutes(59);
        ffin.setSeconds(59);
        return em.createNamedQuery("Asistencia.findBetweenFechasAsistencia").setParameter("finic", finic).setParameter("ffin", ffin).getResultList();        
    }
    
    public List<Asistencia> getAsistenciasUsuarioEntreFechas(Usuario usuario, Date finic, Date ffin) {
        finic.setHours(0);
        finic.setMinutes(0);
        finic.setSeconds(0);
        ffin.setHours(23);
        ffin.setMinutes(59);
        ffin.setSeconds(59);
        return em.createNamedQuery("Asistencia.findByAsistenciaUsuarioFechas").setParameter("finic", finic).setParameter("ffin", ffin).setParameter("idUsuario", usuario).getResultList();        
    }
    
}
