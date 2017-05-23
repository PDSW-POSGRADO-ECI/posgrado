/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.eci.pdsw.posgrado.managebeans;

import edu.eci.pdsw.posgrado.entities.Clase;
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
    
    private final ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    private Date hinicio;
    private Date hfin;
    private Date ClaseFecha;
    private String mensaje;
    private String corselect;
    private String posselect;
    private String proselect;
    private String asigselect;
    private String perselect;
    private String matselect;
    private List<Recurso> selectrec;
    
    /**
     * Bean que maneja las operaciones relacionadas a las clases
     */
    public ClaseBean() {

    }
    
    /**
     *
     * @return
     * @throws ExceptionServiciosReporte
     */
    public List<Clase> getClaseProfesor() throws ExceptionServiciosReporte {
        return report.consultarClaseProfesor(Integer.valueOf(corselect), matselect, proselect);
    }
    
    /**
     *
     * @return
     * @throws ExceptionServiciosReporte
     */
    public List<Recurso> getRecursos()throws ExceptionServiciosReporte {
        return report.consultarAllRecursos();
    }
    /*
    
    */

    /**
     *
     * @throws ExceptionServiciosReporte
     */

    public void registrarRecursosClase() throws ExceptionServiciosReporte{
        if(selectrec.isEmpty()){mensaje="!No registro recursos para la clase";}
        else{mensaje=report.registrarRecursoClase(selectrec);}
        
    }
    
    /**
     *
     * @throws ExceptionServiciosReporte
     */
    public void registrarClase() throws ExceptionServiciosReporte{
        if(!"".equals(proselect) && !"".equals(perselect) && !"".equals(corselect) && !"".equals(matselect)){
            if(ClaseFecha!=null && hinicio!=null && hfin!=null){
            mensaje=report.registrarClase(Integer.valueOf(corselect), matselect, ClaseFecha,new java.sql.Time(hinicio.getTime()), new java.sql.Time(hfin.getTime()), proselect,perselect);
            registrarRecursosClase();
            }else{mensaje="!Por Favor seleccione con anterioridad la fecha y las horas de inicio y fin";}
        }else{mensaje="!Por Favor seleccione con anterioridad el corte, profesor, materia, posgrado y asignatura.";}
        
    }

    /**
     *
     * @param per
     * @param pos
     * @param asig
     * @param mat
     * @param corte
     * @param prof
     */
    public void selection(String per,String pos,String asig,String mat,String corte,String prof){
        corselect=corte;
        posselect=pos;
        asigselect=asig;
        perselect=per;
        matselect=mat;
        proselect=prof;
    }
    
    /**
     *
     */
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (mensaje.contains("Error")) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", mensaje));
        } else if(mensaje.contains("!")){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion: ", mensaje));
        }else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful: ", mensaje));
        }
    }

    /**
     *
     * @return
     */
    public List<Recurso> getSelectrec() {
        return selectrec;
    }

    /**
     *
     * @param selectrec
     */
    public void setSelectrec(List<Recurso> selectrec) {
        this.selectrec = selectrec;
    }

    /**
     *
     * @return
     */
    public String getPosselect() {
        return posselect;
    }

    /**
     *
     * @param posselect
     */
    public void setPosselect(String posselect) {
        this.posselect = posselect;
    }

    /**
     *
     * @return
     */
    public String getAsigselect() {
        return asigselect;
    }

    /**
     *
     * @param asigselect
     */
    public void setAsigselect(String asigselect) {
        this.asigselect = asigselect;
    }

    /**
     *
     * @return
     */
    public String getProselect() {
        return proselect;
    }

    /**
     *
     * @param proselect
     */
    public void setProselect(String proselect) {
        this.proselect = proselect;
    }
    
    /**
     *
     * @return
     */
    public String getPerselect() {
        return perselect;
    }

    /**
     *
     * @param perselect
     */
    public void setPerselect(String perselect) {
        this.perselect = perselect;
    }

    /**
     *
     * @return
     */
    public Date getHinicio() {
        return hinicio;
    }

    /**
     *
     * @param hinicio
     */
    public void setHinicio(Date hinicio) {
        this.hinicio = hinicio;
    }

    /**
     *
     * @return
     */
    public Date getHfin() {
        return hfin;
    }

    /**
     *
     * @param hfin
     */
    public void setHfin(Date hfin) {
        this.hfin = hfin;
    }
   
    /**
     *
     * @return
     */
    public Date getClaseFecha() {
        return ClaseFecha;
    }

    /**
     *
     * @param ClaseFecha
     */
    public void setClaseFecha(Date ClaseFecha) {
        this.ClaseFecha = ClaseFecha;
    }

    /**
     *
     * @return
     */
    public String getCorselect() {
        return corselect;
    }

    /**
     *
     * @param corselect
     */
    public void setCorselect(String corselect) {
        this.corselect = corselect;
    }

    /**
     *
     * @return
     */
    public String getMatselect() {
        return matselect;
    }

    /**
     *
     * @param matselect
     */
    public void setMatselect(String matselect) {
        this.matselect = matselect;
    }
}
