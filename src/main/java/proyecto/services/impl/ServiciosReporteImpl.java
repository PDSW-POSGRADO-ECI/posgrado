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
import org.apache.ibatis.exceptions.PersistenceException;
import proyecto.dao.ClaseDAO;
import proyecto.dao.CohorteDAO;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.MateriaDAO;
import proyecto.dao.ProfesorDAO;
import proyecto.dao.RecursoDAO;
import proyecto.entities.Clase;
import proyecto.entities.Cohorte;
import proyecto.entities.Profesor;
import proyecto.entities.Recurso;
import proyecto.services.ExceptionServiciosReporte;
import proyecto.services.ServiciosReporte;

/**
 *
 * @author Laura RB
 */
public class ServiciosReporteImpl implements ServiciosReporte{
    
    
    public ServiciosReporteImpl(){}
    
    @Inject private RecursoDAO recurso;
    @Inject private ProfesorDAO profesor;
    @Inject private CohorteDAO corte;
    @Inject private ClaseDAO clase;
    @Inject private MateriaDAO materia;

    @Override
    public List<Recurso> consultarRecursosXperiodo(String a) throws ExceptionServiciosReporte{
        List<Recurso> sp = null;
        try {
            try {
                sp=recurso.loadRecursoXperiodo(a);
            } catch (ExceptionPersistence ex) {
                Logger.getLogger(ServiciosReporteImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (PersistenceException ex) {
        } 
        return sp; 
    }

    @Override
    public List<Cohorte> obtenerPeriodos() throws ExceptionServiciosReporte {
        List<Cohorte> sp = null;
        try {
                sp=corte.loadPeriodos();
            } 
        catch (ExceptionPersistence ex) {
                throw new ExceptionServiciosReporte("Error al obtener Periodos ",ex);
            }
        return sp;
    }
    @Override
    public List<Cohorte> obtenerPeriodo(String a) throws ExceptionServiciosReporte {
        List<Cohorte> sp = null;
        try {
                sp=corte.loadPeriodo(a);
            } 
        catch (ExceptionPersistence ex) {
                throw new ExceptionServiciosReporte("Error al cargar Periodo ",ex);
            }
        return sp;
    }
    
    @Override
    public List<Profesor> colsultarProfesor() throws ExceptionServiciosReporte {
         List<Profesor> sp = null;
        try {
                sp=profesor.loadProfesores();
            } 
        catch (ExceptionPersistence ex) {
                throw new ExceptionServiciosReporte("Error al cargar Periodo ",ex);
            }
        return sp;
    }

    @Override
    public List<Clase> colsultarClaseXperiodo(String a) throws ExceptionServiciosReporte {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public List<Profesor> colsultarProfesorXmateria_corte(String siglamat, int idcorte) throws ExceptionServiciosReporte {
       List<Profesor> sp = null;
        try {
                sp=profesor.loadProfesorXmateriacorte(siglamat, idcorte);
            } 
        catch (ExceptionPersistence ex) {
                throw new ExceptionServiciosReporte("Error al cargar Profesor por materia y corte ",ex);
            }
        return sp;
    }
   
}
