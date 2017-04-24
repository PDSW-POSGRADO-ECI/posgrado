/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.entities;

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
    private Cohorte materia_cohorte_sigla;
    private Materia materia_cohorte_id;
    private Date fecha;
    
    public Clase(){
        //this.recursos = new ArrayList<>();
        
    }
    
    public Clase(int id,Time inicio,Time fin,Cohorte corte,Materia mat,Date fecha){
        this.id=id;
        hora_inicio=inicio;
        hora_fin=fin;
        materia_cohorte_sigla=corte;
        materia_cohorte_id=mat;
        this.fecha=fecha;

    
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

    public Cohorte getMateria_cohorte_sigla() {
        return materia_cohorte_sigla;
    }

    public void setMateria_cohorte_sigla(Cohorte materia_cohorte_sigla) {
        this.materia_cohorte_sigla = materia_cohorte_sigla;
    }

    public Materia getMateria_cohorte_id() {
        return materia_cohorte_id;
    }

    public void setMateria_cohorte_id(Materia materia_cohorte_id) {
        this.materia_cohorte_id = materia_cohorte_id;
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
