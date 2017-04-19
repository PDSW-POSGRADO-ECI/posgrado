/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.entities;

import java.util.Date;

/**
 *
 * @author Laura RB
 */
public class Clase extends Exception {

    private int id;
    private Date hora_inicio;
    private Date hora_fin;
    private Cohorte cohorte;
    private Materia materia;
    private Date fecha;
    
            
    public Clase(){
    }
    
    public Clase(int id,Date inicio,Date fin,Cohorte corte,Materia mat,Date fecha){
        this.id=id;
        hora_inicio=inicio;
        hora_fin=fin;
        cohorte=corte;
        materia=mat;
        this.fecha=fecha;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Date getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Date hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Cohorte getCohorte() {
        return cohorte;
    }

    public void setCohorte(Cohorte cohorte) {
        this.cohorte = cohorte;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    

}
