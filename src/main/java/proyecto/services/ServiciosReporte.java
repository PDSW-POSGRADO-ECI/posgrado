/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import proyecto.entities.Asignatura;
import proyecto.entities.Clase;
import proyecto.entities.Cohorte;
import proyecto.entities.Materia;
import proyecto.entities.Posgrado;
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
    
    /*
    *Sprint2
    */
    public String registrarClase(int corte, String per, Date fecha, Time horainit, Time horafin,int doc) throws ExceptionServiciosReporte;

    public String registrarRecurso(int idclase, String nombreRecurso) throws ExceptionServiciosReporte;

    public String registrarMateriaCohorte(int doc, int cort,String periodo, String sigla)throws ExceptionServiciosReporte;

    public boolean consultarMateriaCohorte(int doc, int cor, String sigla) throws ExceptionServiciosReporte;
    
    public List<Materia> consultarMaterias(String asig) throws ExceptionServiciosReporte;
    public List<Asignatura> consultarAsignaturas(String pos) throws ExceptionServiciosReporte;

    public List<Posgrado> consultarPosgrados()throws ExceptionServiciosReporte;

    public List<String> consultarMateriaCohorte(String periodo, String mat)throws ExceptionServiciosReporte;

    public String registrarPeriodo(String per,Date fini,Date ffin)throws ExceptionServiciosReporte;
    
    public String registrarPosgrado(String nom,int credit)throws ExceptionServiciosReporte;
    
    public String registrarAsignatura(String nom,String posgrado)throws ExceptionServiciosReporte;

    public void registrarMateria(String sigla, String nombre, int creditos, int asignatura_id, String descripcion) throws ExceptionServiciosReporte;
    
    public List<String> consultarNombresPosgrado() throws ExceptionServiciosReporte;
    
    public List<String> consultarNombresAsignaturas(String periodo) throws ExceptionServiciosReporte;
}
