/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.managebeans;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import proyecto.entities.Clase;
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

public class ReportesBean implements Serializable {

    ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    private String periodo;
    private List<Recurso> rec;

    public ReportesBean() {
        periodo = "";
        rec = new ArrayList<>();
    }

    public void RecursosXperiodo() throws ExceptionServiciosReporte {

        rec = report.consultarRecursosXperiodo(periodo);

    }

    public List<Recurso> getRec() {
        return rec;
    }

    public List<String> getPeriodos() throws ExceptionServiciosReporte {
        List<Cohorte> corte = report.obtenerPeriodos();
        List<String> per = new ArrayList<>();
        for (int i = 0; i < corte.size(); i++) {
            per.add(corte.get(i).getPeriodo());
        }
        return per;
    }

    public List<Materia> getMaterias() throws ExceptionServiciosReporte {
        return report.consultarMaterias();
    }

    public List<Cohorte> getCohortes() throws ExceptionServiciosReporte {
        return report.obtenerPeriodo(periodo);
    }

    public String getProfesor(Clase clase) throws ExceptionServiciosReporte {
        return report.consultarProfesor(clase.getMateria_cohorte_id().getId(), clase.getMateria_cohorte_sigla().getSigla()).getNombre();
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<Clase> getClases() throws ExceptionServiciosReporte {
        return report.colsultarClaseXperiodo(periodo);
    }

    public List<Date> getFechas() throws ExceptionServiciosReporte {
        List<Date> result = new ArrayList<>();
        HashSet<String> temp = new HashSet<>();
        for (Clase c : getClases()) {
            if (!temp.contains(c.getFecha().toString())) {
                result.add(c.getFecha());
                temp.add(c.getFecha().toString());
            }
        }
        return result;
    }

    public String getHeader(Date header) {
            return new java.sql.Date(header.getTime()).toString();
        }
    
    public String getProperty(Clase property, Date header) {
            return property.getFecha().toString().equals(header.toString()) ? property.getHora_inicio().toString() : "";
        }
    
    public List<String> completeText(String query) throws ExceptionServiciosReporte {
        return getPeriodos();
    }
}
