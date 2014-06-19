/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asae.controllers;

import com.asae.sessionbeans.ReciboFacade;
import com.asae.sessionbeans.UsuarioFacade;
import com.asae.controllers.util.JsfUtil;
import com.asae.entities.Recibo;
import java.math.BigInteger;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Hektor
 */
@ManagedBean
@SessionScoped
public class MBRecaudos {

    private String consulta;
    private String fechainicio;
    private String fechafin;
    private String tipopersona;
    private String mensaje;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ReciboFacade reciboFacade;

    /**
     * Creates a new instance of MBRecaudos
     */
    public MBRecaudos() {

    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(String tipopersona) {
        this.tipopersona = tipopersona;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Recibo> getLstRecibos() {
        return reciboFacade.consul(1);
    }

    public int getLstRecibos2() {
        List<Recibo> list = reciboFacade.findAll();
        int suma = 0;

        if (this.consulta.equals("TOTALRECAUDO")) {
            this.setMensaje("Es el total recaudado por personal que ingreso al gimnasio entre " + this.getFechainicio() + " y " + this.getFechafin());
            for (int i = 0; i < list.size(); i++) {
                suma = suma + Integer.parseInt(String.valueOf(list.get(i).getValorrecibo()));
            }
        }

        if (this.consulta.equals("TOTALJORNADA")) {
            this.setMensaje("Es el total recaudado por personal que pago por jornada entre " + this.getFechainicio() + " y " + this.getFechafin());

            for (int i = 0; i < list.size(); i++) {
                if (String.valueOf(list.get(i).getIdjornada()).equalsIgnoreCase("null")) {
                    suma = suma + 0;
                } else {
                    suma = suma + Integer.parseInt(String.valueOf(list.get(i).getValorrecibo()));
                }
            }
        }
        if (this.consulta.equals("TOTALMENSUAL")) {
            this.setMensaje("Es el total recaudado por personal que pago por mensualidad entre " + this.getFechainicio() + " y " + this.getFechafin());
            for (int i = 0; i < list.size(); i++) {
                if (String.valueOf(list.get(i).getIdjornada()).equalsIgnoreCase("null")) {
                    suma = suma + Integer.parseInt(String.valueOf(list.get(i).getValorrecibo()));
                } else {
                    suma = suma + 0;
                }
            }
        }
        return suma;
    }

    public List<Recibo> getLstPersonas() {
        List<Recibo> list = reciboFacade.findAll();

        if (this.consulta.equals("PERSONAL")) {
            this.setMensaje("Personal que ingreso al gimnacio entre " + this.getFechainicio() + " y " + this.getFechafin());
        }
        if (this.consulta.equals("TIPOSI")) {
            this.setMensaje("Las personas que ingresaron al gimnacio del tipo" + this.tipopersona + " entre " + this.getFechainicio() + " y " + this.getFechafin());
        }
        if (this.consulta.equals("TIPOSJ")) {
            this.setMensaje("Personal que cancelo por jornada entre" + this.getFechainicio() + " y " + this.getFechafin());
        }
        if (this.consulta.equals("TIPOSM")) {
            this.setMensaje("Personal que cancelo por mensualidad entre" + this.getFechainicio() + " y " + this.getFechafin());
        }
        return list;
    }
}
