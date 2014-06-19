/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessionbeans;

import com.asae.entities.GrupoMuscularGeneral;
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
public class GrupoMuscularGeneralFacade extends AbstractFacade<GrupoMuscularGeneral> {
    @PersistenceContext(unitName = "simgu_transportesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoMuscularGeneralFacade() {
        super(GrupoMuscularGeneral.class);
    }
    public GrupoMuscularGeneral findGMuscularGeneralByName(String name) {
        try {
            Query query = em.createNamedQuery("GrupoMuscularGeneral.findByNombre");
            query.setParameter("nombre", name);
            query.setMaxResults(1);
            return (GrupoMuscularGeneral) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
