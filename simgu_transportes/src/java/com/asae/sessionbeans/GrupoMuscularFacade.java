/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asae.sessionbeans;

import com.asae.entities.GrupoMuscular;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class GrupoMuscularFacade extends AbstractFacade<GrupoMuscular> {
    @PersistenceContext(unitName = "simgu_transportesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoMuscularFacade() {
        super(GrupoMuscular.class);
    }
    
    public List<GrupoMuscular> findGMuscularesDia(int idDia){
        return getEntityManager().createNamedQuery("GrupoMuscular.findGMuscularesDia").setParameter("idDia", idDia).getResultList();
    }
    
    public List<GrupoMuscular> findGMuscularesSinDia(){
        return getEntityManager().createNativeQuery("select gm.ID_GRUPO_MUSCULAR,gm.IDGRUPO_MUSCULAR_GENERAL from grupo_muscular gm left join dia_grupo_muscular dgm on gm.ID_GRUPO_MUSCULAR = dgm.ID_GRUPO_MUSCULAR where dgm.ID_GRUPO_MUSCULAR is null",GrupoMuscular.class).getResultList();
    }
    
}
