/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services.impl;

import com.google.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Inject
    private RecursoDAO recurso;
    @Inject
    private ProfesorDAO profesor;
    @Inject
    private CohorteDAO corte;
    @Inject
    private ClaseDAO clase;
    @Inject
    private MateriaDAO materia;

    @Override
    public List<Recurso> consultarRecursosXperiodo(String a) throws ExceptionServiciosReporte {
        try {
            return recurso.loadRecursoXperiodo(a);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al obtener Recurso por Perido " + a, ex);
        }
    }

    @Override
    public List<Cohorte> obtenerPeriodos() throws ExceptionServiciosReporte {
        try {
            return corte.loadPeriodos();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al obtener Periodos ", ex);
        }
    }

    @Override
    public List<Cohorte> obtenerPeriodo(String a) throws ExceptionServiciosReporte {
        try {
            return corte.loadPeriodo(a);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar Periodo ", ex);
        }
    }

    @Override
    public List<Profesor> colsultarProfesores() throws ExceptionServiciosReporte {
        try {
            return profesor.loadProfesores();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar Profesor ", ex);
        }
    }

    @Override
    public List<Clase> colsultarClaseXperiodo(String a) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Materia> consultarMaterias() throws ExceptionServiciosReporte {
        try {
            return materia.loadMaterias();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar materia ", ex);
        }
    }

    @Override
    public Profesor consultarProfesor(int cohorte, String materia) throws ExceptionServiciosReporte {
        try {
            return profesor.loadProfesor(cohorte, materia);
        } catch (ExceptionPersistence ex) {
            Logger.getLogger(ServiciosReporteImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExceptionServiciosReporte();
        }
    }
}
