/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import proyecto.entities.Clase;
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

    public List<String> obtenerPeriodos() throws ExceptionServiciosReporte;

    public List<Cohorte> obtenerPeriodo(String a) throws ExceptionServiciosReporte;

    public List<Profesor> colsultarProfesores() throws ExceptionServiciosReporte;

    public Profesor consultarProfesor(int claseid, String materia) throws ExceptionServiciosReporte;

    public List<Clase> colsultarClaseXperiodo(String a) throws ExceptionServiciosReporte;
    
    public List<Date> colsultarFechas(String per) throws ExceptionServiciosReporte;

    public List<Materia> consultarMaterias() throws ExceptionServiciosReporte;

    public void registrarClase(int corte, String per, Date fecha, Time horainit, Time horafin,int doc) throws ExceptionServiciosReporte;

    public void registrarRecurso(int idclase, String nombreRecurso) throws ExceptionServiciosReporte;

    public void registrarProfesorCohorte(int doc, int cort, String periodo, String fdf)throws ExceptionServiciosReporte;

    public Object consultarMateriaCohorte(int doc, int cor, String sigla) throws ExceptionServiciosReporte;

}
