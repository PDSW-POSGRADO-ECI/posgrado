/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services.impl;

import com.google.inject.Inject;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.dao.AsignaturaDAO;
import proyecto.dao.ClaseDAO;
import proyecto.dao.CohorteDAO;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.MateriaDAO;
import proyecto.dao.PosgradoDAO;
import proyecto.dao.ProfesorDAO;
import proyecto.dao.RecursoDAO;
import proyecto.entities.Asignatura;
import proyecto.entities.Clase;
import proyecto.entities.Cohorte;
import proyecto.entities.Materia;
import proyecto.entities.Posgrado;
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
    @Inject
    private AsignaturaDAO asignatura;
    @Inject
    private PosgradoDAO posgrado;

    /**
     * Consultar los recursos por periodo selccionado
     *
     * @param a
     * @return una lista de recursos del determinado periodo
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
     * Consultar todos los periodos
     *
     * @return una lista de string con los periodos
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
     * consultar todos los cohortes de un periodo
     *
     * @param a
     * @return lista de cohortes
     * @throws proyecto.services.ExceptionServiciosReporte
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
     * Consultar un clase de un periodo
     *
     * @param a
     * @return retorna una lista de clases de un periodo
     * @throws proyecto.services.ExceptionServiciosReporte
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
     * Consultar Profesor de una clase
     *
     * @param claseid
     * @param materia
     * @return retorna un profesor de la clase
     * @throws proyecto.services.ExceptionServiciosReporte
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

    /**
     * registrar una nueva clase en la programacion
     *
     * @param i
     * @param per
     * @param fecha
     * @param horafin
     * @param horainit
     * @param doc
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public String registrarClase(int i, String per, Date fecha, Time horainit, Time horafin, int doc) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * registrar un recurso a una clase determinada
     *
     * @param idclase
     * @param nombreRecurso
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public String registrarRecurso(int idclase, String nombreRecurso) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * registrar un nuevo corte de una materia
     *
     * @param doc
     * @param cort
     * @param periodo
     * @param sigla
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public String registrarMateriaCohorte(int doc, int cort, String periodo, String sigla) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Consultar MateriaCohorte
     *
     * @param doc
     * @param cor
     * @param sigla
     * @return retorna un boleano si se ecuentra registrado el profesor
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public boolean consultarMateriaCohorte(int doc, int cor, String sigla) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Consultar
     *
     * @param posgrado
     * @return una lista de strings con los nombres de las asignaturas
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<Asignatura> consultarAsignaturas(String posgrado) throws ExceptionServiciosReporte {

        try {
            return asignatura.loadAsignaturas(posgrado);

        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar asignaturas del posgrado" + posgrado, ex);
        }
    }

    /**
     * Consultar Materias de una asignatura
     *
     * @param asignatura
     * @return una lsita de strings con los nombres de las materias
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<Materia> consultarMaterias(String asignatura) throws ExceptionServiciosReporte {
        ArrayList<String> mat = new ArrayList<>();
        try {
            return materia.loadMateriasXasignatura(asignatura);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar materias de asignatura" + asignatura, ex);
        }
    }

    /**
     * Consultar
     *
     * @return lista de strings con el nombre de los posgrados
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<Posgrado> consultarPosgrados() throws ExceptionServiciosReporte {
        try {
            return posgrado.loadPosgrados();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar Posgrados ", ex);
        }
    }

    /**
     * Consultar Los cohortes de una materia
     *
     * @param periodo
     * @param mat
     * @return lista de strings con el corte de la materia
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    @Override
    public List<String> consultarMateriaCohorte(String periodo, String mat) throws ExceptionServiciosReporte {
        ArrayList<String> pos = new ArrayList<>();
        try {
            for (Cohorte c : corte.loadMateriaCohorte(periodo, mat)) {
                pos.add(String.valueOf(c.getId()));
            }
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar Posgrados ", ex);
        }
        return pos;
    }

    @Override
    public String registrarPeriodo(String per, Date fini, Date ffin) throws ExceptionServiciosReporte {
        String ms = "Periodo Agregado";
        try {
            if (!corte.loadPeriodos().contains(per) && fini.compareTo(ffin) < 0) {
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
            for (int i = 0; i < posgrado.loadPosgrados().size() && var; i++) {
                if (posgrado.loadPosgrados().get(i).getNombre().equals(nom) || credit <= 0) {
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
            for (int i = 0; i < asignatura.loadAsignaturas().size() && var; i++) {
                if (asignatura.loadAsignaturas().get(i).getNombre().equals(nom)) {
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
    public void registrarMateria(String sigla, String nombre, int creditos, int asignatura_id, String descripcion) throws ExceptionServiciosReporte {
        try {
            materia.addMateria(sigla, nombre, creditos, asignatura_id, descripcion);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al registrar la materia: " + sigla + ". Verifique que no esté duplicada", ex);
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
    public List<String> consultarNombresAsignaturas(String posgrado) throws ExceptionServiciosReporte {
        try {
            return asignatura.loadNames(posgrado);
        } catch (ExceptionPersistence ex) {
            Logger.getLogger(ServiciosReporteImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExceptionServiciosReporte("Error al cargar los nombres de las asignaturas del posgrado: " + posgrado, ex);
        }
    }

    @Override
    public List<String> consultarNombresMaterias(String asig) throws ExceptionServiciosReporte {
        try {
            return materia.loadNames(asig);
        } catch (ExceptionPersistence ex) {
            Logger.getLogger(ServiciosReporteImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExceptionServiciosReporte("Error al cargar los nombres de las materias de la asignatura: " + asig, ex);
        }
    }

    @Override
    public List<String> consultarNombresProfesores() throws ExceptionServiciosReporte {
        try {
            return profesor.loadNames();
        } catch (ExceptionPersistence ex) {
            Logger.getLogger(ServiciosReporteImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExceptionServiciosReporte("Error al cargar los nombres de los profesores", ex);
        }
    }
}
