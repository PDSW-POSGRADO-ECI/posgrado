/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.managebeans;

import edu.eci.pdsw.posgrado.entities.Clase;
import edu.eci.pdsw.posgrado.entities.Profesor;
import edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporteFactory;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Laura RB
 */
@ManagedBean(name = "ReporteProfesorBean")
@SessionScoped
public class ReporteProfesorBean implements Serializable{
    
    private final ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    private String periodo;
    private Profesor profe;
    private boolean mode;
    
     /*Constructor de la clase ReporteProfesorBean*/
    public ReporteProfesorBean() {
        periodo=" ";profe=null;mode=true;
    }
    
        
    public List<Profesor> getProfePeriodo()throws ExceptionServiciosReporte {
        return report.ProfesorPeriodo(periodo);
    }
    
    public List<Clase> getGenerarReporteClases()throws ExceptionServiciosReporte {
        List<Clase> cl;
        if(mode){
            cl=report.consultarHorarioClaseProfesor(profe!=null?profe.getNombre():"", periodo);}
        else{
            cl=report.consultarHorarioClaseProfesorSemana(profe!=null?profe.getNombre():"", periodo,new Date());}
        return cl;
    }
    
    /*
    *Obtener todos los periodos 
    *@return retorna una lista de strings los periodos de la base de datos
    **/
    public boolean getRender() throws ExceptionServiciosReporte {
        return profe!=null;
    }
    
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Profesor getProfe() {
        return profe;
    }

    public void setProfe(Profesor profe) {
        this.profe = profe;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }
    
    
}
