/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.managebeans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import proyecto.entities.Materia;
import proyecto.services.ExceptionServiciosReporte;
import proyecto.services.ServiciosReporte;
import proyecto.services.ServiciosReporteFactory;

/**
 *
 * @author 2122825
 */
@ManagedBean(name = "RegistroMateriaBean")
@SessionScoped
public class RegistroMateriaBean {

    ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();

    public List<Materia> pre_requisitos;
    public List<Materia> co_requisitos;

    public String posgrado;
    public String asignatura;
    public String requisito;

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }
    public String sigla;
    public String nombre;
    public String descripcion;
    public int creditos;

    public String getPosgrado() {
        return posgrado;
    }

    public void setPosgrado(String posgrado) {
        this.posgrado = posgrado;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
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

    public void registrarMateria() throws ExceptionServiciosReporte {
        report.registrarMateria(sigla, nombre, creditos, asignatura, descripcion);
    }

    public List<String> getPosgrados() throws ExceptionServiciosReporte {
        return report.consultarNombresPosgrado();
    }

    public List<String> getAsignaturas() throws ExceptionServiciosReporte {
        return report.consultarNombresAsignaturas();
    }

    public List<String> getRequisitosMateria() throws ExceptionServiciosReporte {
        return null;
    }

    public List<String> getRequisitosDisponibles() throws ExceptionServiciosReporte{
        return null;
    }
    
    public List<String> autoComplete(String query, int type) throws ExceptionServiciosReporte {
        List<String> s = null;
        switch (type) {
            case 0:
                s = getPosgrados();
                break;
            case 1:
                s = getAsignaturas();
                break;
            case 2:
                s = getRequisitosDisponibles();
                break;
            default:
                break;
        }
        return s;
    }
}
