/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services;

import java.util.List;
import proyecto.entities.Cohorte;
import proyecto.entities.Materia;
import proyecto.entities.Profesor;
import proyecto.entities.Recurso;

/**
 *
 * @author Laura RB
 */
public interface ServiciosReporte {
    
    public List<Recurso> consultarRecursosXperiodo(String a) throws ExceptionServiciosReporte;
    
    public List<Cohorte> obtenerPeriodos() throws ExceptionServiciosReporte;
    public List<Cohorte> obtenerPeriodo(String a) throws ExceptionServiciosReporte;

    public List<Profesor> colsultarProfesor() throws ExceptionServiciosReporte;
    
    public List<Materia> consultarMaterias() throws ExceptionServiciosReporte;
    
}
