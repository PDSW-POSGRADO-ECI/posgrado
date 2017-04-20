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
    
    
    ServiciosReporte ss = ServiciosReporteFactory.getInstance().getServiciosReporte();
    
    public ReportesBean(){
        
      
    }
    public void getRecursos() throws ExceptionServiciosReporte{
        return ;
    }
    
}
