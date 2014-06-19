/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessionbeans;

import com.asae.entities.Dia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class DiaFacade extends AbstractFacade<Dia> {
    @PersistenceContext(unitName = "simgu_transportesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiaFacade() {
        super(Dia.class);
    }
    public List<Dia> findDiaByIdRutina(int idRutina){
        return getEntityManager().createNamedQuery("Dia.findByIdRutina").setParameter("idRutina", idRutina).getResultList();
    }
    
    public List<Dia> findDiasSinRutina(){                          
        return getEntityManager().createNativeQuery("select d.ID_DIA,d.NOMBRE,d.NUM_DIA from dia d left join rutina_dia rd on d.ID_DIA = rd.ID_DIA where rd.ID_DIA is null",Dia.class).getResultList();
    }
}
