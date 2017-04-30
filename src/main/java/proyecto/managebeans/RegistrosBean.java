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
import proyecto.services.ExceptionServiciosReporte;
import proyecto.services.ServiciosReporte;
import proyecto.services.ServiciosReporteFactory;

/**
 *
 * @author Laura RB
 */
@ManagedBean(name = "RegistrosBean")
@SessionScoped
public class RegistrosBean implements Serializable{
    ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    String posgrado;
    String asig;
    
    /*Constructor de la clase ReportesBean*/
    public RegistrosBean() {
        posgrado="";
        asig="";
    }
        
    /*
    *Obtener todas asignaturas de posgrado
    *@return retorna una lista de los posgrados
    **/
    public List<String> getPosgrados() throws ExceptionServiciosReporte {
        return report.consultarPosgrados();
    }
    
    /*
    *Obtener todas asignaturas de posgrado
    *@return retorna una lista de las asignaturas de posgrado
    **/
    public List<String> getAsignaturas() throws ExceptionServiciosReporte {
        return report.consultarAsignaturas(posgrado);
    }
    
    /*
    *Obtener todos las materias relacionadas a una asignatura en particular
    *@return retorna una lista de las materias de la asignatura
    **/
    public List<String> getMaterias() throws ExceptionServiciosReporte {
        return report.consultarMaterias(asig);
    }
    
    /**
     * Autocompletar los periodos de la base de datos
     * @param query el string registrado por el usuario
     * @return Lista de strings con el periodo que dijito el usuario
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    public List<String> completeText(String query) throws ExceptionServiciosReporte {
        return getPosgrados();
    }

    public String getPosgrado() {
        return posgrado;
    }

    public void setPosgrado(String posgrado) {
        this.posgrado = posgrado;
    }

    public String getAsig() {
        return asig;
    }

    public void setAsig(String asig) {
        this.asig = asig;
    }
    
    
    
}
