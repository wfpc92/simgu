/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessionbeans;

import com.asae.entities.EjercicioCross;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class EjercicioCrossFacade extends AbstractFacade<EjercicioCross> {
    @PersistenceContext(unitName = "simgu_transportesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EjercicioCrossFacade() {
        super(EjercicioCross.class);
    }
    public List<EjercicioCross> findEjerciciosCrossSinGCross(){
        return getEntityManager().createNativeQuery("select ec.EJERCICIO,ec.IDMEDIDA_EJERCICIO_CROSS,ec.NUM_VECES_MEDIDA,ec.IDEJERCICIO_CROSS from ejercicio_cross ec left join cross_ejercicio_cross cec on ec.idejercicio_cross = cec.IDEJERCICIO_CROSS where cec.IDEJERCICIO_CROSS is null;",EjercicioCross.class).getResultList();
    } 
}
