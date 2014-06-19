/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessionbeans;

import com.asae.entities.EjercicioGm;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class EjercicioGmFacade extends AbstractFacade<EjercicioGm> {
    @PersistenceContext(unitName = "simgu_transportesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EjercicioGmFacade() {
        super(EjercicioGm.class);
    }
     public List<EjercicioGm> findEjerciciosGmSinGrupoMuscular(){
        return getEntityManager().createNativeQuery("select e.EJERCICIO,e.NUMERO_SERIES,e.REPETICIONES,e.RECESO,e.PESO,e.idejerciciogm from ejercicio_gm e left join grupo_muscular_ejercicio_gm g on e.idejerciciogm = g.ID_EJERCICIO where g.ID_EJERCICIO is null",EjercicioGm.class).getResultList();
    }  
}
