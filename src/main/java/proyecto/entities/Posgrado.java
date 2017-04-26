/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Daniel Rodriguez
 */
public class Posgrado implements Serializable{
    
    private int id; 
    private String nombre;
    private int creditos;
    
    
    public Posgrado(){
        //this.asig = new ArrayList<>();
    }
    public Posgrado(int id, String nombre, int creditos) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        //asig=as;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

     @Override
    public String toString() {
        return "Posgrado{" + "id=" + id+ ", nombre=" + nombre+'}';
    }
}
    

