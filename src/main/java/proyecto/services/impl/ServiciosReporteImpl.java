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
                Logger.getLogger(ServiciosReporteImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        return sp;
    }
    
    @Override
    public List<Profesor> colsultarProfesor() {
         List<Profesor> sp = null;
        try {
                sp=profesor.loadProfesores();
            } 
        catch (ExceptionPersistence ex) {
                Logger.getLogger(ServiciosReporteImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        return sp;
    }
   
}
