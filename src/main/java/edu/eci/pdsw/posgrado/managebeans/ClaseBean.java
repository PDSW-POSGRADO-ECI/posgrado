/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.eci.pdsw.posgrado.managebeans;

import edu.eci.pdsw.posgrado.entities.Recurso;
import edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporteFactory;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author OscarAlba
 */

@ManagedBean(name = "ClaseBean")
@SessionScoped

public class ClaseBean implements Serializable{
    
    private ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    private Date hinicio;
    private Date hfin;
    private Date ClaseFecha;
    private String mensaje;
    private String mensaje2;
    private String corselect;
    private String posselect;
    private String proselect;
    private String asigselect;
    private String perselect;
    private String matselect;
    private List<Recurso> selectrec;
    
    public ClaseBean() {

    }
    /*
    
    */
    
    public List<Recurso> getRecursos()throws ExceptionServiciosReporte {
        return report.consultarAllRecursos();
    }
    /*
    
    */
    public void registrarRecursosClase() throws ExceptionServiciosReporte{
        if(selectrec.isEmpty()){mensaje2="!No registro recursos para la clase";}
        else{mensaje2=report.registrarRecursoClase(selectrec);}
        
    }
    
    public void registrarClase() throws ExceptionServiciosReporte{
        if(!"".equals(proselect) && !"".equals(perselect) && !"".equals(corselect) && !"".equals(matselect)){
            mensaje=report.registrarClase(Integer.valueOf(corselect), matselect, ClaseFecha,new java.sql.Time(hinicio.getTime()), new java.sql.Time(hfin.getTime()), proselect,perselect);
            registrarRecursosClase();
        }else{mensaje="!Por Favor seleccione con anterioridad el corte, profesor, materia, posgrado y asignatura.";}
        
    }
    public void selection(String per,String pos,String asig,String mat,String corte,String prof){
        corselect=corte;
        posselect=pos;
        asigselect=asig;
        perselect=per;
        matselect=mat;
        proselect=prof;
    }
    
     public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (mensaje.contains("Error")) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", mensaje));
        } else if(mensaje.contains("!")){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion: ", mensaje));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion: ", mensaje2));
        }else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful: ", mensaje));
        }
    }

    public List<Recurso> getSelectrec() {
        return selectrec;
    }

    public void setSelectrec(List<Recurso> selectrec) {
        this.selectrec = selectrec;
    }


    public String getPosselect() {
        return posselect;
    }

    public void setPosselect(String posselect) {
        this.posselect = posselect;
    }

    public String getAsigselect() {
        return asigselect;
    }

    public void setAsigselect(String asigselect) {
        this.asigselect = asigselect;
    }

    public String getProselect() {
        return proselect;
    }

    public void setProselect(String proselect) {
        this.proselect = proselect;
    }
    
    public String getPerselect() {
        return perselect;
    }

    public void setPerselect(String perselect) {
        this.perselect = perselect;
    }

    public Date getHinicio() {
        return hinicio;
    }

    public void setHinicio(Date hinicio) {
        this.hinicio = hinicio;
    }

    public Date getHfin() {
        return hfin;
    }

    public void setHfin(Date hfin) {
        this.hfin = hfin;
    }
   
    public Date getClaseFecha() {
        return ClaseFecha;
    }

    public void setClaseFecha(Date ClaseFecha) {
        this.ClaseFecha = ClaseFecha;
    }

    public String getCorselect() {
        return corselect;
    }

    public void setCorselect(String corselect) {
        this.corselect = corselect;
    }

    public String getMatselect() {
        return matselect;
    }

    public void setMatselect(String matselect) {
        this.matselect = matselect;
    }
     

}
