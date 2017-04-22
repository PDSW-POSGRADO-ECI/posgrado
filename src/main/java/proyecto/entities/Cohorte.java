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
    private char periodo;
    private ArrayList<Materia> materias;
    private ArrayList<Clase> clases;
    private ArrayList<Profesor> profes;
    
    public Cohorte(){
        this.profes = new ArrayList<>();
        this.clases = new ArrayList<>();
        this.materias = new ArrayList<>();
    }
    
    public Cohorte(int id, Date inicio,Date fin, char per,ArrayList<Materia> mat,ArrayList<Profesor> pro,ArrayList<Clase> cla) {
        this.id=id;
        fecha_inicio=inicio;
        fecha_fin=fin;
        periodo=per;
        materias=mat;
        clases=cla;
        profes=pro;
        
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

    public char getPeriodo() {
        return periodo;
    }

    public void setPeriodo(char periodo) {
        this.periodo = periodo;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public ArrayList<Clase> getClases() {
        return clases;
    }

    public void setClases(ArrayList<Clase> clases) {
        this.clases = clases;
    }

    public ArrayList<Profesor> getProfes() {
        return profes;
    }

    public void setProfes(ArrayList<Profesor> profes) {
        this.profes = profes;
    }
    
    
    @Override
    public String toString() {
        return "Clase{" + "id=" + id + ", periodo=" + periodo + '}';
    }
}
