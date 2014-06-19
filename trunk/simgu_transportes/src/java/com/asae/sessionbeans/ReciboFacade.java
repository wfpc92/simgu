/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessionbeans;

import com.asae.entities.Recibo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class ReciboFacade extends AbstractFacade<Recibo> {
    @PersistenceContext(unitName = "simgu_transportesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReciboFacade() {
        super(Recibo.class);
    }
        public List<Recibo> consul(int id) {
        return getEntityManager().createNamedQuery("Recibo.findByIdusuario").setParameter("idusuario", id).getResultList();
    }
    
    public List<Recibo> consul2(String finicio, String ffin) {
        return getEntityManager().createNamedQuery("Recibo.totalRecaudo").setParameter("fechainicio", finicio).setParameter("fechafin",ffin).getResultList();
    }
}
