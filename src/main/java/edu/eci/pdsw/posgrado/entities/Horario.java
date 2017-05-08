/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.entities;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Laura RB
 */
public class Horario {
    private int id;
    private java.sql.Time hora_inicio;
    private java.sql.Time hora_fin;
    private Date fecha;
    
    
     public Horario(){
        
    }
    
    public Horario(int id,Time inicio,Time fin,Date fecha){
        this.id=id;
        hora_inicio=inicio;
        hora_fin=fin;
        this.fecha=fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(java.sql.Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public java.sql.Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(java.sql.Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
