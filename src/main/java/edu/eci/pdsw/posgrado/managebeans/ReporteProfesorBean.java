/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.managebeans;

import edu.eci.pdsw.posgrado.services.ServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporteFactory;

/**
 *
 * @author Laura RB
 */
public class ReporteProfesorBean {
    
    private final ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    private String periodo;
     /*Constructor de la clase ReporteProfesorBean*/
    public ReporteProfesorBean() {
        periodo="";
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    
}
