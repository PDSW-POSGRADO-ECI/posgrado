/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Laura RB
 */
public class Cohorte implements Serializable{

    private int id;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String periodo;
    
    public Cohorte(){
        periodo="";
    }
    
    public Cohorte(int id, Date inicio,Date fin, String per) {
        this.id=id;
        fecha_inicio=inicio;
        fecha_fin=fin;
        periodo=per;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Clase{" + "id=" + id + ", periodo=" + periodo + '}';
    }
}
