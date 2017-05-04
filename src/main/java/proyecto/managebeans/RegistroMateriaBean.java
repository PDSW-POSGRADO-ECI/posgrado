/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.managebeans;

import java.util.List;
import proyecto.entities.Asignatura;
import proyecto.entities.Materia;
import proyecto.entities.Posgrado;

/**
 *
 * @author 2122825
 */
public class RegistroMateriaBean {
    public Posgrado programa_seleccionado;
    public Asignatura asignatura_seleccionada;
    public List<Materia> pre_requisitos;
    public List<Materia> co_requisitos;
    
    public String sigla;
    public String nombre;
    public String descripcion;

    public Posgrado getPrograma_seleccionado() {
        return programa_seleccionado;
    }

    public void setPrograma_seleccionado(Posgrado programa_seleccionado) {
        this.programa_seleccionado = programa_seleccionado;
    }

    public Asignatura getAsignatura_seleccionada() {
        return asignatura_seleccionada;
    }

    public void setAsignatura_seleccionada(Asignatura asignatura_seleccionada) {
        this.asignatura_seleccionada = asignatura_seleccionada;
    }

    public List<Materia> getPre_requisitos() {
        return pre_requisitos;
    }

    public void setPre_requisitos(List<Materia> pre_requisitos) {
        this.pre_requisitos = pre_requisitos;
    }

    public List<Materia> getCo_requisitos() {
        return co_requisitos;
    }

    public void setCo_requisitos(List<Materia> co_requisitos) {
        this.co_requisitos = co_requisitos;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
