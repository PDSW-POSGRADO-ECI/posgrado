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
import edu.eci.pdsw.posgrado.entities.Horario;
import edu.eci.pdsw.posgrado.entities.Materia;
import edu.eci.pdsw.posgrado.entities.Periodo;
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
    @Inject 
    private HorarioDAO horario;

    /**
     * Consultar los recursos por periodo selccionado
     *
     * @param a
     * @return una lista de recursos del determinado periodo
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    @Override
    public ArrayList<Recurso> consultarRecursosXperiodo(String a) throws ExceptionServiciosReporte {
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
        List<Cohorte> cor= corte.loadMateriaCohorte(periodo, mat);
        for (Cohorte c : cor ) {
            pos.add(String.valueOf(c.getId()));
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
     * @param cor
     * @param mat
     * @param fecha
     * @param profe
     * @param horafin
     * @param horainit
     * @param periodo
     * @return 
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    @Override
    public String registrarClase(int cor, String mat, Date fecha, Time horainit, Time horafin, String profe,String periodo) throws ExceptionServiciosReporte {
        String ms = "Clase Agregada";
        try {
            Periodo per = corte.loadPeriodo(periodo);
            boolean var = true;
            if (per.getFecha_inicio().compareTo(fecha) <= 0 && per.getFecha_fin().compareTo(fecha) >= 0) {
                List<Clase> cl = clase.loadFechasProfesorClase(periodo, profe, fecha);
                for (int i=0;i<cl.size() && var ;i++) {
                    if (cl.get(i).getHora_inicio().compareTo(horainit) > 0 && cl.get(i).getHora_fin().compareTo(horainit) <= 0 && cl.get(i).getHora_inicio().compareTo(horafin) >= 0 && cl.get(i).getHora_fin().compareTo(horafin) < 0) {
                        var = false;
                        ms = "Error el profesor no tiene esa disponibilidad de horario, ya tiene una clase en ese horario";
                    }
                }
            } else {
                var=false;
                ms = "Error fecha incorrecta, no esta dentro del periodo seleccionado";
            }
            if (horainit.compareTo(horafin) < 0 && var) {
                Horario hor = horario.loadHorarioProfesor(profe, fecha);
                if (hor!=null && hor.getHora_inicio().compareTo(horainit) <= 0 && hor.getHora_fin().compareTo(horainit) > 0 && hor.getHora_inicio().compareTo(horafin) < 0 && hor.getHora_fin().compareTo(horafin) >= 0) {
                    List<Profesor> p = profesor.loadProfesoresCohorte(cor, mat);
                    int docp = -1;
                    for (Profesor prof : p) {
                        if (prof.getNombre().equals(profe)) {
                            docp = prof.getDocumento();
                            break;
                        }
                    }
                    clase.saveClase(cor, mat, fecha, horainit, horafin, docp);
                } else {
                    ms = "Error el profesor no tiene esa disponibilidad de horario";
                }
            } else {
                ms = "Error las Horas son incorrectas";
            }

        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al registrar el periodo ", ex);
        }
        return ms;
    }

    
    @Override
    public String registrarPeriodo(String per, Date fini, Date ffin) throws ExceptionServiciosReporte {
        String ms = "Periodo Agregado";
        try {
            List<String> pers= corte.loadPeriodos();
            if (!pers.contains(per)) {
                if(fini.compareTo(ffin) < 0){
                corte.savePeriodo(per, fini, ffin);}
                else{ms="Error La fecha inicial es mayor que la fecha de terminacion";}
            } else {
                ms = "Error el Periodo esta duplicado";
            }
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al registrar el periodo " + per, ex);
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
    public String registrarMateriaCohorte(String profe, int cort, String periodo, String materia) throws ExceptionServiciosReporte {
        String ms="Cohorte Agregado";
        try {
            List<Materia> mat=this.materia.loadMaterias();
            for(int i=0;i<mat.size();i++){
                if(mat.get(i).getNombre().equals(materia)){
                    materia=mat.get(i).getSigla();
                    break;
                };
            }
            
            if(corte.loadMateriaCohorteExistente(profe, materia, cort)) {
                if (corte.loadCohorte(cort) == null) {
                    corte.saveCohorte(cort, periodo);
                    corte.saveMateriaCohorte(cort, materia, profe);
                } else {
                    corte.saveMateriaCohorte(cort, materia, profe);
                }
            }else{
                ms="Error ya se ecuentra registrado este cohorte para la materia y profesor seleccionados";
            }
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar todos ls recursos", ex);
        }
        return ms;
    }   

    @Override
    public List<Recurso> consultarAllRecursos() throws ExceptionServiciosReporte {
        try {
            return recurso.loadRecursos();
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar todos ls recursos", ex);
        }
    }

    @Override
    public String registrarRecursoClase(List<Recurso> rec) throws ExceptionServiciosReporte  {
        String ms="Recursos registrados satisfactoriamente";
        try {
            if (rec.size() > 0) {
                List<Clase> c = clase.loadClase();
                int idcla = -1;
                for (Clase clas : c) {
                    idcla = Math.max(idcla, clas.getId());
                } 
                for (int i = 0; i < rec.size(); i++) {
                    recurso.saveRecursoClase(rec.get(i).getId(), idcla, 1);
                    
                }
               
            }else{ms="No a registrado ningun recurso";}
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar todos los recursos", ex);
        }
        return ms;
    }

    @Override
    public List<String> consultarProfesoresCohorte(int cor, String mat) throws ExceptionServiciosReporte {
        ArrayList<String> s=new ArrayList<>();
        try {
            List<Profesor> prof=profesor.loadProfesoresCohorte(cor, mat);
            for(Profesor p:prof){
                s.add(p.getNombre());
            }
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar todos ls profesores", ex);
        }
        return s;
    }

    @Override
    public List<Clase> consultarFechasRecursoClase(int rec) throws ExceptionServiciosReporte {
        try {
            return clase.loadFechasRecursoClase(rec);
        } catch (ExceptionPersistence ex) {
            throw new ExceptionServiciosReporte("Error al cargar todos ls profesores", ex);
        }
    }

    @Override
    public List<Materia> loadPrerrequisitosMateria(String sigla) throws ExceptionServiciosReporte {
        try{
              return materia.loadPrerrequisitosMateria(sigla);
        } catch (ExceptionPersistence ex){
              throw new ExceptionServiciosReporte("Error al cargar todos los prerrequisitos", ex);

        }
    }

    @Override
    public List<Materia> loadCorrequisitosMateria(String sigla) throws ExceptionServiciosReporte {
        try{
              return materia.loadCorrequisitosMateria(sigla);
        } catch (ExceptionPersistence ex){
              throw new ExceptionServiciosReporte("", ex);

        }
    }

    @Override
    public List<Clase> consultarClaseProfesor(int cor, String mat, String profe) throws ExceptionServiciosReporte {
        try{
              return clase.loadClaseProfesor(cor, mat, profe);
        } catch (ExceptionPersistence ex){
              throw new ExceptionServiciosReporte("Error al cargar las clases del profesor", ex);

        }
    }

    @Override
    public Horario consultarHorarioProfesor(String nom, Date fecha) throws ExceptionServiciosReporte {
        try{
              return horario.loadHorarioProfesor(nom, fecha);
        } catch (ExceptionPersistence ex){
              throw new ExceptionServiciosReporte("Error al cargar las clases del profesor", ex);

        }
    }

    @Override
    public void consultarClaseProfesor(String nombre, String get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarClaseProfesorSemana(String nombre, String get, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
