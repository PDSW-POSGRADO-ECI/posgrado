/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.entities;

import java.io.Serializable;
import java.util.ArrayList;
//import org.primefaces.expression.SearchExpressionResolver;

/**
 *
 * @author Daniel Rodriguez
 */
public class Materia implements Serializable{
    
    private String sigla;
    private String nombre;
    private int creditos;
    private Asignatura Asignatura_id;
    private String descripcion;
    private ArrayList<Cohorte> cortes;
    private ArrayList<Clase> clases;
    
    public Materia(){
        this.cortes = new ArrayList<>();
        this.clases= new ArrayList<>();
    }
    
    public Materia(String sigla,ArrayList<Clase> cla ,ArrayList<Cohorte> cor ,String nombre, int creditos, Asignatura Asignatura_id, String descripcion) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.creditos = creditos;
        this.Asignatura_id = Asignatura_id;
        this.descripcion = descripcion;
        clases=cla;
        cortes=cor;
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
        return Asignatura_id;
    }

    public void setAsignatura_id(Asignatura Asignatura_id) {
        this.Asignatura_id = Asignatura_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Cohorte> getCortes() {
        return cortes;
    }

    public void setCortes(ArrayList<Cohorte> cortes) {
        this.cortes = cortes;
    }

    public ArrayList<Clase> getClases() {
        return clases;
    }

    public void setClases(ArrayList<Clase> clases) {
        this.clases = clases;
    }
    
    
    

}
