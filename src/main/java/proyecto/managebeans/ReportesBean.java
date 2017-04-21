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
        period="";
    }
    
    public List<Recurso> getRecursosXperiodo() throws ExceptionServiciosReporte{
        return report.consultarRecursosXperiodo(period);
    }
    
    public List<Cohorte> getCohorte() throws ExceptionServiciosReporte{
        return report.obtenerPeriodos();
    }

    public String getPeriodo() {
        return period;
    }

    public void setPeriodo(String periodo) {
        this.period = periodo;
    }
    
    
    
    
}
