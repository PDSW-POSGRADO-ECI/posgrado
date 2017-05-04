/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Laura RB
 */
public class Clase implements Serializable{

    private int id;
    private Time hora_inicio;
    private Time hora_fin;
    private Cohorte cohorte_id;
    private Materia materia_sigla;
    private Profesor profesor_documento;
    private Date fecha;
    
    public Clase(){
        //this.recursos = new ArrayList<>();
    }
    
    public Clase(int id,Time inicio,Time fin,Cohorte corte,Materia mat,Date fecha,Profesor prof){
        this.id=id;
        hora_inicio=inicio;
        hora_fin=fin;
        materia_sigla=mat;
        cohorte_id=corte;
        this.fecha=fecha;
        profesor_documento=prof;

    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Cohorte getCohorte_id() {
        return cohorte_id;
    }

    public void setCohorte_id(Cohorte cohorte_id) {
        this.cohorte_id = cohorte_id;
    }

    public Materia getMateria_sigla() {
        return materia_sigla;
    }

    public void setMateria_sigla(Materia materia_sigla) {
        this.materia_sigla = materia_sigla;
    }

    public Profesor getProfesor_documento() {
        return profesor_documento;
    }

    public void setProfesor_documento(Profesor profesor_documento) {
        this.profesor_documento = profesor_documento;
    }

    

   


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
     @Override
    public String toString() {
        return "Clase{" + "id=" + id + ", fecha=" + fecha + ", Hora_inicio=" + hora_inicio +'}';
    }
}
