/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessionbeans;

import com.asae.entities.GrupoCross;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class GrupoCrossFacade extends AbstractFacade<GrupoCross> {
    @PersistenceContext(unitName = "simgu_transportesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoCrossFacade() {
        super(GrupoCross.class);
    }
  public List<GrupoCross> findGCrossSinDia(){
        return getEntityManager().createNativeQuery("select gc.ID_CROSS,gc.IDGRUPO_CROSS_GENERAL from grupo_cross gc left join dia_cross dc on gc.ID_CROSS = dc.ID_CROSS where dc.ID_CROSS is null",GrupoCross.class).getResultList();
    }   
}
