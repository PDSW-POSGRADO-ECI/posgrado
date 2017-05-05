/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.entities;

import java.io.Serializable;

/**
 *
 * @author Laura RB
 */
public class Cohorte implements Serializable{

    private int id;
    private Periodo periodo;
    
    public Cohorte(){
        periodo=null;
    }
    
    public Cohorte(int id, Periodo per) {
        this.id=id;
        periodo=per;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

   

    
    @Override
    public String toString() {
        return "Clase{" + "id=" + id + ", periodo=" + periodo + '}';
    }
}
