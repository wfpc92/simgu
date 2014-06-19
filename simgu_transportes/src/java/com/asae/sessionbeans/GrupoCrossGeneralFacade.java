/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessionbeans;

import com.asae.entities.GrupoCrossGeneral;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
public class GrupoCrossGeneralFacade extends AbstractFacade<GrupoCrossGeneral> {
    @PersistenceContext(unitName = "simgu_transportesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoCrossGeneralFacade() {
        super(GrupoCrossGeneral.class);
    }
 public GrupoCrossGeneral findGCrossGeneralByName(String name) {
        try {
            Query query = em.createNamedQuery("GrupoCrossGeneral.findByNombre");
            query.setParameter("nombre", name);
            query.setMaxResults(1);
            return (GrupoCrossGeneral) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }   
}
