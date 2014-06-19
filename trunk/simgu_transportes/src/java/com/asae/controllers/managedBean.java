/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asae.controllers;

import java.util.ArrayList;
import java.util.List;
 
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
 
 
@ManagedBean(name="managedBean", eager = true)
@ApplicationScoped
public class managedBean {

    private String id_usuario;

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public managedBean() {
    }
}
