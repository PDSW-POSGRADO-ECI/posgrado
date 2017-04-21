/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services;

import java.util.List;
import proyecto.entities.Recurso;

/**
 *
 * @author Laura RB
 */
public interface ServiciosReporte {
    
    public List<Recurso> recursosXcohorte(int a) throws ExceptionServiciosReporte;
    
    public List<String> obtenerPeriodos() throws ExceptionServiciosReporte;
    
}
