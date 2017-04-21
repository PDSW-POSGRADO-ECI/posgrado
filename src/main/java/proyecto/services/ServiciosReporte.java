/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services;

import java.util.List;
import proyecto.entities.Cohorte;
import proyecto.entities.Recurso;

/**
 *
 * @author Laura RB
 */
public interface ServiciosReporte {
    
    public List<Recurso> consultarRecursosXperiodo(char a) throws ExceptionServiciosReporte;
    
    public List<Cohorte> obtenerPeriodos() throws ExceptionServiciosReporte;
    
}
