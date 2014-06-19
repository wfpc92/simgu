/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asae.utilidades;

import com.asae.entities.Usuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Developer
 */
@FacesConverter(forClass=Usuario.class, value = "usuarioConverter")
public class UsuarioConverter implements Converter {    

    private EntityManagerFactory emf;
    private EntityManager em;
    public static List<Usuario> lstUsuarioRegistrados;    
    
    public UsuarioConverter() {                   
            emf = Persistence.createEntityManagerFactory("simgu_transportesPU");
            em = emf.createEntityManager();
            
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            lstUsuarioRegistrados = em.createQuery(cq).setMaxResults(10).getResultList();
    }

    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);

                for (Usuario usu : lstUsuarioRegistrados) {
                    if (usu.getIdusuario() == number) {
                        return usu;
                    }
                }

            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Usuario) value).getIdusuario());
        }
    }    
}
