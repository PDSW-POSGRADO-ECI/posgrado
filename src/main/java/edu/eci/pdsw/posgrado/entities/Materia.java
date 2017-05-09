/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.entities;

import java.io.Serializable;

/**
 *
 * @author Daniel Rodriguez
 */
public class Materia implements Serializable{
    
    private String sigla;
    private String nombre;
    private int creditos;
    private Asignatura asignatura_id;
    private String descripcion;
    
    
    public Materia(){
        
    }
    
    public Materia(String sigla ,String nombre, int creditos, Asignatura Asignatura_id, String descripcion) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.creditos = creditos;
        this.asignatura_id = Asignatura_id;
        this.descripcion = descripcion;
        //clases=cla;
        //cortes=cor;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Asignatura getAsignatura_id() {
        return asignatura_id;
    }

    public void setAsignatura_id(Asignatura asignatura_id) {
        this.asignatura_id = asignatura_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     @Override
    public String toString() {
        return "Materia{" + "id=" + sigla+ ", nombre=" + nombre+'}';
    }
}