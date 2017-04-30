/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.managebeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import proyecto.entities.Clase;
import proyecto.entities.Cohorte;
import proyecto.entities.Materia;
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

public class ReportesBean implements Serializable {

    ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    String periodo;
    
    /*Constructor de la clase ReportesBean*/
    public ReportesBean() {
        periodo = "";
    }
    
    /*
    *Obtener todos los recursos de un periodo especifico
    *@return retorna una lista de los recursos del periodo seleccionado
    **/
    public List<Recurso> getRecursos() throws ExceptionServiciosReporte {
        return report.consultarRecursosXperiodo(periodo);
    }
    
    
    /*
    *Obtener todos los periodos 
    *@return retorna una lista de strings los periodos de la base de datos
    **/
    public List<String> getPeriodos() throws ExceptionServiciosReporte {
        return report.obtenerPeriodos();
    }
     
    /**
     * Get the value of Materias
     * @return the value of Materias
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    public List<Materia> getMaterias() throws ExceptionServiciosReporte {
        return report.consultarMaterias();
    }
    
    /**
     * Consultar los cohortes de un periodo
     * @return Lista de cohortes del periodo
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    public List<Cohorte> getCohortes() throws ExceptionServiciosReporte {
        return report.obtenerPeriodo(periodo);
    }
    
     /**
     * Consultar los cohortes de un periodo
     * @param clase  la clase dada por un profesor
     * @return Retorna un string con el nombre del profesor
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    public String getProfesor(Clase clase) throws ExceptionServiciosReporte {
        return report.consultarProfesor(clase.getId(), clase.getMateria_sigla().getSigla()).getNombre();
    }
    
     /**
     * obtener todas las clases de un perido seleccionado
     * @return Lista de clases del periodo seleccionado
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    public List<Clase> getClases() throws ExceptionServiciosReporte {
        return report.colsultarClaseXperiodo(periodo);
    }
    
    /**
     * Obtener las fehcas 
     * @return result una lista de fechas de clase
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    public List<Date> getFechas() throws ExceptionServiciosReporte {
        List<Date> dates=report.colsultarFechas(periodo);
        return dates;
    }
    
    /**
     * Cambia el formato de fecha para que se vea yyyy-mm-dd
     * @param header fecha
     * @return la fecha en formato yyyy-mm-dd string
     */
    public String getHeader(Date header) {
        return new java.sql.Date(header.getTime()).toString();
    }
    
     /**
     * Cambia el formato de fecha para que se vea yyyy-mm-dd
     * @param property
     * @param header
     * @return la fecha en formato yyyy-mm-dd string
     */
    public String getProperty(Clase property, Date header) {
        return property.getFecha().toString().equals(header.toString()) ? property.getHora_inicio().toString() : "";
    }
    
     /**
     * Autocompletar los periodos de la base de datos
     * @param query el string registrado por el usuario
     * @return Lista de strings con el periodo que dijito el usuario
     * @throws proyecto.services.ExceptionServiciosReporte
     */
    public List<String> completeText(String query) throws ExceptionServiciosReporte {
        return getPeriodos();
    }
    /**
     * Get the value of Periodo
     * @return periodo
     */
    public String getPeriodo() {
        return periodo;
    }
    
    /**
     * Set the value of Periodo
     * @param periodo String del perido seleccionado
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    
}
