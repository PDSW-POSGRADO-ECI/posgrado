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
public class Profesor implements Serializable{
    
    private int documento;
    private String nombre;
    private String correo;
    private int telefono;
    private String tipo_documento;
    private ArrayList<Materia> materias;
    private ArrayList<Cohorte> cortes;
    
    public Profesor(){
        this.materias = new ArrayList<>();
        this.cortes = new ArrayList<>();
    }
    public Profesor(ArrayList<Cohorte> cor,ArrayList<Materia> mat,int documento, String nombre, String correo, int telefono, String tipo_documento) {
        this.documento = documento;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.tipo_documento = tipo_documento;
        cortes=cor;
        materias=mat;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public ArrayList<Cohorte> getCortes() {
        return cortes;
    }

    public void setCortes(ArrayList<Cohorte> cortes) {
        this.cortes = cortes;
    }
    
    
    
    
    
}
