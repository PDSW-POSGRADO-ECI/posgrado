/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.entities;

import java.io.Serializable;

/**
 *
 * @author Laura RB
 */
public class Recurso implements Serializable{
    
    private int id;
    private String nomRecurso;
    private boolean disponible;
    private Clase clase_id;
    private int cantidad ;
            
    public Recurso(){
        nomRecurso="";
        disponible=true;
    }
    public Recurso(int id,String nombreRecurso,boolean disp,Clase idc,int cant){
        this.id=id;
        nomRecurso=nombreRecurso;
        disponible=disp;
        clase_id=idc;
        cantidad=cant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomRecurso() {
        return nomRecurso;
    }

    public void setNomRecurso(String nomRecurso) {
        this.nomRecurso = nomRecurso;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Clase getClase_id() {
        return clase_id;
    }

    public void setClase_id(Clase clase_id) {
        this.clase_id = clase_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
   
    
    @Override
    public String toString() {
        return "Recurso{" + "id=" + id + ", nombre=" + nomRecurso + '}';
    }
}
