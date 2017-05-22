/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.managebeans;

import edu.eci.pdsw.posgrado.entities.Materia;
import edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporteFactory;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author 2122825
 */
@ManagedBean(name = "RegistroMateriaBean")
@SessionScoped
public class RegistroMateriaBean implements Serializable {

    private ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporte();
    private List<Materia> pre_requisitos;
    private List<Materia> co_requisitos;
    private String asignatura;
    private String requisito;
    private String programa;
    private String sigla;
    private String nombre;
    private String descripcion;
    private String seleccion;
    private String mat;
    private String radio;
    private String request;
    private String mate;
    private boolean disabled = true;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getMate() {
        return mate;
    }

    public void setMate(String mate) {
        this.mate = mate;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
    public void changeRequest(ValueChangeEvent event){
        request = (String) event.getNewValue();
    }
    
    public void changeRadio(ValueChangeEvent event){
        radio = (String) event.getNewValue();
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }


    public RegistroMateriaBean(){
        
    }
    
    public int creditos;

    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

    
    
    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }
    
    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public List<Materia> getPre_requisitos() {
        return pre_requisitos;
    }

    public void setPre_requisitos(List<Materia> pre_requisitos) {
        this.pre_requisitos = pre_requisitos;
    }

    public List<Materia> getCo_requisitos() {
        return co_requisitos;
    }

    public void setCo_requisitos(List<Materia> co_requisitos) {
        this.co_requisitos = co_requisitos;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void registrarMateria() throws ExceptionServiciosReporte {
        report.registrarMateria(sigla, nombre, creditos, asignatura, descripcion);
    }

    public List<String> getPosgrados() throws ExceptionServiciosReporte {
        return report.consultarNombresPosgrado();
    }

    public List<String> getAsignaturas() throws ExceptionServiciosReporte {
        return report.consultarNombresAsignaturas();
    }

    public List<Materia> getRequisitosMateria() throws ExceptionServiciosReporte {
        return report.loadCorrequisitosMateria(sigla);
    }

    public List<String> getRequisitosDisponibles() throws ExceptionServiciosReporte{
        return null;
    }
    
    public void prueba(){
         System.out.println("Sigla : "+ sigla + " Nombre : " + nombre 
                 + " Creditos : " + creditos + " Asignatura : " + asignatura + " Descripcion : " + descripcion 
                 + "RequesT :" + request );
    }
    
    public List<String> autoComplete(String query, int type) throws ExceptionServiciosReporte {
        List<String> s = null;
        
        switch (type) {
            case 0:
                s = getMateria();
                break;
            case 1:
                s = getAsignaturas();
                break;
            case 2:
                s = getName();
                break;
            case 3:
                s = getNuevor();
                break;
            case 4:
                s = getRequisitos();
                break;
            case 5:
                s = getNomb();
                break;   
             
            default:
                break;
        }
        return s;
    }
    
    /* 
    Se usa para obtener el nombre de la materia seleccionada
    */
  
    /**
     * Obtener los nombres de los posgrados
     * @return una lista con el nombre de los posgrados
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    public List<String> getPosgrado() throws ExceptionServiciosReporte {
        return report.consultarNombresPosgrado();
    }
    
    /**
     * Obtener las asginaturas asociadas a un posgrado
     * Se uso el nombre de getMateria ya que con getAsignatura generaba errores
     * @return una lista con las asignaturas asociadas a un posgrado
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    public List<String> getMateria() throws ExceptionServiciosReporte {
        return report.consultarNombresAsignaturasXposgrado(programa);
    }
    
    public List<String> getMateri(String programa) throws ExceptionServiciosReporte {
        return report.consultarNombresAsignaturasXposgrado(programa);
    }
    
    /**
     * Obtener las asginaturas asociadas a un posgrado
     * @return una lista con las asignaturas asociadas a un posgrado
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    public List<String> getRequisitos() throws ExceptionServiciosReporte {
        return report.consultarNombresMaterias(asignatura);
    }
    
    /**
     * Obtener las asginaturas asociadas a un posgrado
     * @return una lista con las asignaturas asociadas a un posgrado
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    public List<String> getNuevor() throws ExceptionServiciosReporte {
        return report.consultarNombresAsignaturasXposgrado(programa);
    }
    /**
     * Consultar las materias asociadas a una asignatura
     * @param asignatura
     * @return Retorna una lista con las materias asociadas a una asignatura
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     */
    public List<String> getName() throws ExceptionServiciosReporte{
        return report.consultarNombresMaterias(asignatura); 
    }
    
    public List<String> getNam(String asignatura) throws ExceptionServiciosReporte{
        return report.consultarNombresMaterias(asignatura); 
    }
    
    public List<String> getNomb() throws ExceptionServiciosReporte{
        return report.consultarNombresMaterias(seleccion); 
    }
    
    /**
     * Activa y desactiva el boton en la seleccion de una asignatura
     * @param event 
     */
    public void onRowSelect(SelectEvent event) {
        disabled = false;
    }
    
  
    
   
}
