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
public class Asignatura implements Serializable {
    
    private int id;
    private String nombre;
    private Posgrado posgrado_id;
    
    public Asignatura(){
        //this.materias = new ArrayList<>();
        posgrado_id=null;
    }
    
    public Asignatura(int id, String nombre, Posgrado Posgrado_id) {
        this.id = id;
        this.nombre = nombre;
        this.posgrado_id = Posgrado_id;
        //materias=mat;
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

    public Posgrado getPosgrado_id() {
        return posgrado_id;
    }

    public void setPosgrado_id(Posgrado Posgrado_id) {
        this.posgrado_id = Posgrado_id;
    }
     @Override
    public String toString() {
        return "Asignatura{" + "id=" + id + ", nombre=" + nombre +'}';
    }
}
