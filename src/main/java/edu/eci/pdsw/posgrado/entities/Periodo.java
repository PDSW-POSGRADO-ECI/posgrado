/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Laura RB
 */
public class Periodo implements Serializable{

    private Date fecha_inicio;
    private Date fecha_fin;
    private String periodo;
    
    public Periodo(){
        periodo="";
    }
    
    public Periodo(Date inicio,Date fin, String per) {
        fecha_inicio=inicio;
        fecha_fin=fin;
        periodo=per;
        
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    @Override
    public String toString() {
        return "Periodo{periodo=" + periodo + '}';
    }
}
