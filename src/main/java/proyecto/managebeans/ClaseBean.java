/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.managebeans;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import proyecto.entities.Recurso;
import proyecto.services.ExceptionServiciosReporte;
import proyecto.services.ServiciosReporte;
import proyecto.services.ServiciosReporteFactory;

/**
 *
 * @author OscarAlba
 */

@ManagedBean(name = "ClaseBean")
@SessionScoped
public class ClaseBean implements Serializable{
    
    ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    Time hinicio;
    Time hfin;
    Date ClaseFecha;

    public ClaseBean() {

    }
    /*
    
    */
    
    public List<Recurso> getAllRecursos()throws ExceptionServiciosReporte {
        ArrayList<Recurso> pos=new ArrayList<>();
        return pos;
    }
    /*
    
    */
    public List<Recurso> getRecursosClase()throws ExceptionServiciosReporte {
        ArrayList<Recurso> pos=new ArrayList<>();
        return pos;
    }
    
    public Time getHinicio() {
        return hinicio;
    }

    public void setHinicio(Time hinicio) {
        this.hinicio = hinicio;
    }

    public Time getHfin() {
        return hfin;
    }

    public void setHfin(Time hfin) {
        this.hfin = hfin;
    }

    public Date getClaseFecha() {
        return ClaseFecha;
    }

    public void setClaseFecha(Date ClaseFecha) {
        this.ClaseFecha = ClaseFecha;
    }

}
