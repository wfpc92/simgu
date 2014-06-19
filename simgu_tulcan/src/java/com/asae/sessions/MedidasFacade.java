/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessions;

import com.asae.entities.Medidas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andres
 */
@Stateless
public class MedidasFacade extends AbstractFacade<Medidas> {
    @PersistenceContext(unitName = "simgu_tulcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedidasFacade() {
        super(Medidas.class);
    }
    
}
