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
            
    public Recurso(){
        nomRecurso="";
        disponible=true;
    }
    public Recurso(int id,String nombreRecurso,boolean disp,Clase idc){
        this.id=id;
        nomRecurso=nombreRecurso;
        disponible=disp;
        clase_id=idc;
    }
    
    public void setClase_id(Clase clase) {
        this.clase_id = clase;
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

    @Override
    public String toString() {
        return "Recurso{" + "id=" + id + ", nombre=" + nomRecurso + '}';
    }
}
