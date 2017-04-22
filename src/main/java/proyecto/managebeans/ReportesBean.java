/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.managebeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import proyecto.entities.Cohorte;
import proyecto.entities.Profesor;
import proyecto.entities.Recurso;
import proyecto.services.ExceptionServiciosReporte;
import proyecto.services.ServiciosReporte;
import proyecto.services.ServiciosReporteFactory;

/**
 *
 * @author Laura RB
 */

@ManagedBean(name = "ReportesBean")
@SessionScoped

public class ReportesBean implements Serializable{
    
    
    ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    private String period;
    
    public ReportesBean(){
        period=null;
    }
    
    public List<Recurso> getRecursosXperiodo() throws ExceptionServiciosReporte{
        return report.consultarRecursosXperiodo(period);
    }
    
    public List<Cohorte> getPeriodos() throws ExceptionServiciosReporte{
        return report.obtenerPeriodos();
    }
    
    public List<Profesor> getProfesores() throws ExceptionServiciosReporte{
        List<Profesor> a=report.colsultarProfesor();
        System.out.println(a.toString());
        return a;
    }
    
     
    

    public String getPeriodo() {
        return period;
    }

    public void setPeriodo(String periodo) {
        this.period = periodo;
    }
    
    
    
    
}
