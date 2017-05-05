/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.services.impl;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.*;
import edu.eci.pdsw.posgrado.entities.Asignatura;
import edu.eci.pdsw.posgrado.entities.Clase;
import edu.eci.pdsw.posgrado.entities.Cohorte;
import edu.eci.pdsw.posgrado.entities.Materia;
import edu.eci.pdsw.posgrado.entities.Posgrado;
import edu.eci.pdsw.posgrado.entities.Profesor;
import edu.eci.pdsw.posgrado.entities.Recurso;
import edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporte;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Inject
    private AsignaturaDAO asignatura;
    @Inject
    private PosgradoDAO posgrado;

    /**
     * Consultar los recursos por periodo selccionado
     *
     * @param a
     * @return una lista de recursos del determinado periodo
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
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
     * Consultar todos los periodos
     *
     * @return una lista de string con los periodos
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
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
     * consultar todos los cohortes de un periodo
     *
     * @param a
     * @return lista de cohortes
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    @Override
    public List<Cohorte> obtenerPeriodo(String a) throws ExceptionServiciosReporte {
        try {
            return corte.loadPeriodo(a);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar Periodo " + a, ex);
        }
    }

    /**
     * Consultar todos los Profesores
     *
     * @return una lisrta de todos los profesores
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
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
     * Consultar un clase de un periodo
     *
     * @param a
     * @return retorna una lista de clases de un periodo
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    @Override
    public List<Clase> colsultarClaseXperiodo(String a) throws ExceptionServiciosReporte {
        try {
            return clase.loadClaseXperiodo(a);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar las clases del periodo " + a, ex);
        }
    }

    /**
     * Consultar todas las materias
     *
     * @return una lista de todas las materias
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
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
     * Consultar Profesor de una clase
     *
     * @param claseid
     * @param materia
     * @return retorna un profesor de la clase
     */
    @Override
    public Profesor consultarProfesor(int claseid, String materia) throws ExceptionServiciosReporte {
        Profesor pro = null;
        try {
            pro = profesor.loadProfesor(claseid, materia);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar profesor " + pro.toString(), ex);
        }
        return pro;
    }

    /**
     * Consultar fechas de clases d eun periodo seleccionado
     *
     * @param periodo
     * @return lista de fechas
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
    public List<String> consultarNombresPosgrado() throws ExceptionServiciosReporte {
        try {
            return posgrado.loadNames();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar los nombres de los posgrados.", ex);
        }
    }

    
     @Override
    public boolean consultarMateriaCohorte(String sigla, String nombre, int cor) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
   

    /**
     * Consultar Los cohortes de una materia
     *
     * @param periodo
     * @param mat
     * @return lista de strings con el corte de la materia
     */
    @Override
    public List<String> consultarCohorte(String periodo, String mat) throws ExceptionServiciosReporte {
        ArrayList<String> pos = new ArrayList<>();
        try {
            List<Cohorte> cor= corte.loadMateriaCohorte(periodo, mat);
            for (Cohorte c : cor ) {
                pos.add(String.valueOf(c.getId()));
            }
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar Posgrados ", ex);
        }
        return pos;
    }

    @Override
    public List<String> consultarNombresAsignaturasXposgrado(String posgrado) throws ExceptionServiciosReporte {
        try {
            return asignatura.loadNames(posgrado);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar los nombres de las asignaturas del posgrado: " + posgrado, ex);
        }
    }

    @Override
    public List<String> consultarNombresMaterias(String asig) throws ExceptionServiciosReporte {
        try {
            return materia.loadNames(asig);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar los nombres de las materias de la asignatura: " + asig, ex);
        }
    }

    @Override
    public List<String> consultarNombresProfesores() throws ExceptionServiciosReporte {
        try {
            return profesor.loadNames();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar los nombres de los profesores", ex);
        }
    }

    @Override
    public List<String> consultarNombresAsignaturas() throws ExceptionServiciosReporte {
        try {
            return asignatura.loadAllNames();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar los nombres de las asignaturas del posgrado: " + posgrado, ex);
        }
    }
     
     
    /**
     * registrar una nueva clase en la programacion
     *
     * @param i
     * @param per
     * @param fecha
     * @param horafin
     * @param horainit
     * @param doc
     */
    @Override
    public String registrarClase(int cor, String mat, Date fecha, Time horainit, Time horafin, String profe) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * registrar un recurso a una clase determinada
     *
     * @param idclase
     * @param nombreRecurso
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    @Override
    public String registrarRecurso(int idclase, String nombreRecurso) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    @Override
    public String registrarPeriodo(String per, Date fini, Date ffin) throws ExceptionServiciosReporte {
        String ms = "Periodo Agregado";
        try {
            List<String> pers= corte.loadPeriodos();
            if (!pers.contains(per) && fini.compareTo(ffin) < 0) {
                corte.savePeriodo(per, fini, ffin);
            } else {
                ms = "Error el Periodo esta duplicado - La fecha inicial es mayor que la fecha de terminacion";
            }
        } catch (ExceptionPersistence ex) {
            return new ExceptionServiciosReporte("Error al registrar el periodo " + per, ex).getMessage();
        }
        return ms;
    }

    @Override
    public String registrarPosgrado(String nom, int credit) throws ExceptionServiciosReporte {
        String ms = "Posgrago Agregado";
        boolean var = true;
        try {
            List<Posgrado> pos= posgrado.loadPosgrados();
            for (int i = 0; i < pos.size() && var; i++) {
                if (pos.get(i).getNombre().equals(nom) || credit <= 0) {
                    var = false;
                    ms = "Error el Posgrado esta duplicado - creditos invalidos";
                }
            }
            if (var) {
                posgrado.savePosgrado(nom, credit);
            }
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("El posgrado " + nom + " ya existe" + ex, ex);
        }
        return ms;

    }

    @Override
    public String registrarAsignatura(String nom, String posgrado) throws ExceptionServiciosReporte {
        String ms = "Asignatura Agregada";
        boolean var = true;
        try {
            List<Asignatura> asig= asignatura.loadAsignaturas();
            for (int i = 0; i < asig.size() && var; i++) {
                if (asig.get(i).getNombre().equals(nom)) {
                    var = false;
                    ms = "Error la asignatura esta duplicada";
                }
            }
            if (var) {
                asignatura.saveAsignatura(nom, posgrado);
            }
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("La asignatura " + nom + " ya existe" + ex, ex);
        }
        return ms;
    }

    @Override
    public void registrarMateria(String sigla, String nombre, int creditos, String asignatura_nombre, String descripcion) throws ExceptionServiciosReporte {
        try {
            materia.addMateria(sigla, nombre, creditos, asignatura_nombre, descripcion);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al registrar la materia: " + sigla + ". Verifique que no esté duplicada", ex);
        }
    }

    @Override
    public String registrarMateriaCohorte(String profe, int cort, String periodo, String sigla) throws ExceptionServiciosReporte {
        
        return null;
    
    }   

   

   
}
