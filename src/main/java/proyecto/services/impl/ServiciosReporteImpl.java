/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services.impl;

import com.google.inject.Inject;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import proyecto.dao.ClaseDAO;
import proyecto.dao.CohorteDAO;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.MateriaDAO;
import proyecto.dao.ProfesorDAO;
import proyecto.dao.RecursoDAO;
import proyecto.entities.Clase;
import proyecto.entities.Cohorte;
import proyecto.entities.Materia;
import proyecto.entities.Profesor;
import proyecto.entities.Recurso;
import proyecto.services.ExceptionServiciosReporte;
import proyecto.services.ServiciosReporte;

/**
 *
 * @author Laura RB
 */
public class ServiciosReporteImpl implements ServiciosReporte {

    public ServiciosReporteImpl() {
    }

    @Inject private RecursoDAO recurso;
    @Inject private ProfesorDAO profesor;
    @Inject private CohorteDAO corte;
    @Inject private ClaseDAO clase;
    @Inject private MateriaDAO materia;
    
    /**
     * Consultar 
     * @param a
     * @return 
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<Recurso> consultarRecursosXperiodo(String a) throws ExceptionServiciosReporte {
        try {
            return recurso.loadRecursoXperiodo(a);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al obtener Recurso del periodo " + a, ex);
        }
    }
    
    /**
     * Consultar 
     * @return 
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<String> obtenerPeriodos() throws ExceptionServiciosReporte {
        try {
            return corte.loadPeriodos();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al obtener Periodos ", ex);
        }
    }
    
    /**
     * Consultar 
     * @param a
     * @return 
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<Cohorte> obtenerPeriodo(String a) throws ExceptionServiciosReporte {
        try {
            return corte.loadPeriodo(a);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar Periodo "+a, ex);
        }
    }
    
    /**
     * Consultar 
     * @return 
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<Profesor> colsultarProfesores() throws ExceptionServiciosReporte {
        try {
            return profesor.loadProfesores();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar Profesores ", ex);
        }
    }
    
    /**
     * Consultar 
     * @param a
     * @return 
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<Clase> colsultarClaseXperiodo(String a) throws ExceptionServiciosReporte {
        try{
            return clase.loadClaseXperiodo(a);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte ("Error al cargar las clases del periodo "+ a, ex);
        }
    }
    
     /**
     * Consultar 
     * @return 
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<Materia> consultarMaterias() throws ExceptionServiciosReporte {
        try {
            return materia.loadMaterias();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar materias", ex);
        }
    }
    
    /**
     * Consultar 
     * @param cohorte
     * @param materia
     * @return 
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public Profesor consultarProfesor(int claseid, String materia) throws ExceptionServiciosReporte {
        Profesor pro=null;
        try {
            pro=profesor.loadProfesor(claseid, materia);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar profesor "+pro.toString(), ex);
        }
        return pro;
    }
    
    /**
     * Consultar 
     * @param periodo
     * @return 
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<Date> colsultarFechas(String periodo) throws ExceptionServiciosReporte {
         try {
            return clase.loadFechas(periodo);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar fecha ", ex);
        }
    }

    @Override
    public void registrarClase(int i, String per, Date fecha, Time horainit, Time horafin ,int doc) throws ExceptionServiciosReporte{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarRecurso(int idclase, String nombreRecurso) throws ExceptionServiciosReporte{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarProfesorCohorte(int doc, int cort, String periodo, String fdf) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarMateriaCohorte(int doc, int cor, String sigla) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
