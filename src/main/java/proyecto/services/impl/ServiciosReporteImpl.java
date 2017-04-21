/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services.impl;

import com.google.inject.Inject;
import java.util.List;
import proyecto.dao.RecursoDAO;
import proyecto.entities.Recurso;
import proyecto.services.ExceptionServiciosReporte;

/**
 *
 * @author Laura RB
 */
public class ServiciosReporteImpl implements ServiciosReporte{
    
    @Inject
    private RecursoDAO recurso;
    //private CohorteDAO corte;
    
    @Override
    public List<Recurso> recursosXcohorte(char a) throws ExceptionServiciosReporte{
        List<Recurso> sp = null;
        try {
             sp=recurso.loadRecursoXPeriodo(a);
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSuscripcionesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sp; 
    }
   
}
