/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessions;

import com.asae.entities.Contenidos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andres
 */
@Stateless
public class ContenidosFacade extends AbstractFacade<Contenidos> {
    @PersistenceContext(unitName = "simgu_tulcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContenidosFacade() {
        super(Contenidos.class);
    }
    
}
