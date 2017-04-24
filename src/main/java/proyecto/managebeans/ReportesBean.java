/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import proyecto.entities.Cohorte;
import proyecto.entities.Materia;
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
    private String periodo;
    private List<Recurso> rec;
    
    public ReportesBean(){
        periodo="";
        rec = new ArrayList<>();
    }
    
    public void RecursosXperiodo() throws ExceptionServiciosReporte{ 
     
        rec=report.consultarRecursosXperiodo(periodo);
        
    }

    public List<Recurso> getRec() {
        return rec ;
    }
    
    public List<String> getPeriodos() throws ExceptionServiciosReporte{
        List<Cohorte> corte=report.obtenerPeriodos();
        List<String> per=new ArrayList<>();
        for (int i=0;i<corte.size();i++){
            per.add(corte.get(i).getPeriodo());
        }
        return per;
    }
    
    public List<Profesor> getProfesores() throws ExceptionServiciosReporte{
        List<Profesor> a=report.colsultarProfesor();
        //System.out.println(a.toString());
        return a;
    }
    
    public List<Materia> getMaterias() throws ExceptionServiciosReporte{
        return report.consultarMaterias();
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }    
}
