/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.managebeans;


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
 * @author Laura RB
 */
@ManagedBean(name = "RegistroClaseBean")
@SessionScoped
public class RegistroClaseBean implements Serializable {

    final ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    private String posgrado;
    private String asig;
    private String periodo;
    private String mat;
    private String corte;
    private String profesor;
    private String nuevoper;
    private String nuevopos;
    private String nuevoasig;
    private String nuevocorte;
    private String selectprofe;
    private static String mensaje="";
    private String selectpos;
    private Date fini;
    private Date ffin;
    private int credit;
   

    /*Constructor de la clase ReportesBean*/
    public RegistroClaseBean() {
        posgrado = " ";periodo = " ";asig = " ";
        mat = " ";corte = " ";nuevoper="";nuevopos=" ";
        nuevoasig=" ";nuevocorte=" ";selectprofe=" ";selectpos=" ";
        credit=0;
    }
    
    /*
    *Obtener todas asignaturas de posgrado
    *@return retorna una lista de los posgrados
    **/
    public List<String> getPosgrados() throws ExceptionServiciosReporte {
//      
        return report.consultarNombresPosgrado();
    }

    /*
    *Obtener todas asignaturas de posgrado
    *@return retorna una lista de las asignaturas de posgrado
    **/
    public List<String> getAsignaturas() throws ExceptionServiciosReporte {
        return report.consultarNombresAsignaturasXposgrado(posgrado);
    }

    /*
    *insertar n nuevo periodo
    **/
    public void registrarPeriodo() throws ExceptionServiciosReporte {
        mensaje = report.registrarPeriodo(nuevoper, fini, ffin);
        nuevoper=" ";
    }
     /*
    *insertar n nuevo periodo
    **/
    public List<String> getProfesoresC() throws ExceptionServiciosReporte {
        return report.consultarProfesoresCohorte(Integer.valueOf(corte),mat);
    }

    /*
    *insertar n nuevo periodo
    **/
    public void registrarPosgrado() throws ExceptionServiciosReporte {
        mensaje = report.registrarPosgrado(nuevopos, credit);
        nuevopos=" ";credit=0;
    }

    /*
    *insertar n nuevo periodo
    *@throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
    **/
    public void registrarAsignatura() throws ExceptionServiciosReporte {
        mensaje = report.registrarAsignatura(nuevoasig, selectpos);
        nuevoasig=" ";selectpos=" ";
    }

    /*
    *Obtener todos las materias relacionadas a una asignatura en particular
    *@return retorna una lista de las materias de la asignatura
    *@throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
    **/
    public List<String> getMaterias() throws ExceptionServiciosReporte {
        return report.consultarNombresMaterias(asig);
    }

    /*
    *Obtener todos los periodos 
    *@return retorna una lista de strings los periodos de la base de datos
    *@throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
    **/
    public List<String> getPeriodos() throws ExceptionServiciosReporte {
        return report.obtenerPeriodos();
    }

    /*
    *Obtener todos los periodos 
    *@return retorna una lista de strings los periodos de la base de datos
    *@throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
    **/
    public void registrarMateriaCohorte() throws ExceptionServiciosReporte {
        mensaje = report.registrarMateriaCohorte(selectprofe, Integer.valueOf(nuevocorte),periodo,mat);
        selectprofe=" ";nuevocorte=" ";
    }

    /*
    *Obtener los cortes de la materia determinada
    *@return retorna una lista de strings con los cortes
    *@throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
    **/
    public List<String> getCohorte() throws ExceptionServiciosReporte {
        return report.consultarCohorte(periodo, mat);
    }

    /*
    *Obtener los cortes de la materia determinada
    *@return retorna una lista de strings con los cortes
    *@throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
    **/
    public List<String> getProfesores() throws ExceptionServiciosReporte {    
        return report.consultarNombresProfesores();
    }
    
    /**
     * Autocompletar los periodos de la base de datos
     *
     * @param query el string registrado por el usuario
     * @param num
     * @return Lista de strings con el periodo que dijito el usuario
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    public List<String> completeText(String query, int num) throws ExceptionServiciosReporte {
        List<String> s = null;
        switch (num) {
            case 0:
                s = getPeriodos();
                break;
            case 1:
                s = getPosgrados();
                break;
            case 2:
                s = getAsignaturas();
                break;
            case 3:
                s = getMaterias();
                break;
            case 4:
                s = getCohorte();
                break;
            case 5:
                s = getProfesores();
                break;
             case 6:
                s = getProfesoresC();
                break;
            default:
                break;
        }
        return s;
    }

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
     * Get the value of Periodo
     *
     * @return periodo
     */
    public String getPeriodo() {
        return periodo;
    }
    
    /**
     * Set the value of Periodo
     *
     * @param periodo String del perido seleccionado
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    /**
     * Set the value of Periodo
     * @return 
    */
    public String getPosgrado() {
        return posgrado;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
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
    
    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getCorte() {
        return corte;
    }

    public void setCorte(String corte) {
        this.corte = corte;
    }

    public String getNuevoper() {
        return nuevoper;
    }

    public void setNuevoper(String nuevoper) {
        this.nuevoper = nuevoper;
    }

    public String getNuevopos() {
        return nuevopos;
    }

    public void setNuevopos(String nuevopos) {
        this.nuevopos = nuevopos;
    }

    public String getNuevoasig() {
        return nuevoasig;
    }

    public void setNuevoasig(String nuevoasig) {
        this.nuevoasig = nuevoasig;
    }

    public Date getFini() {
        return fini;
    }

    public void setFini(Date fini) {
        this.fini = fini;
    }

    public Date getFfin() {
        return ffin;
    }

    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getSelectpos() {
        return selectpos;
    }

    public void setSelectpos(String selectpos) {
        this.selectpos = selectpos;
    }

    public String getNuevocorte() {
        return nuevocorte;
    }

    public void setNuevocorte(String nuevocorte) {
        this.nuevocorte = nuevocorte;
    }

    public String getSelectprofe() {
        return selectprofe;
    }

    public void setSelectprofe(String profe) {
        this.selectprofe = profe;
    }   
    
}
