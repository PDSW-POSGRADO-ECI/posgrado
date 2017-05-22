/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis.mappers;

import edu.eci.pdsw.posgrado.entities.Clase;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Laura RB
 */
public interface ClaseMapper {
     
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public List<Clase> consultarClase();
     
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public List<Date> consultarFechas(@Param("periodo") String periodo);
     
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public List<Clase> consultarClaseXperiodo(@Param("periodo") String periodo);
    
    /*
    *Reguistar una nueva clase 
    *@param corte
    *@param per
    *@param fecha
    *@param horainit 
    *@param horafin
    *@doc
    **/
    public void registrarClase(@Param("idcor")int corte, @Param("mat")String mat,
            @Param("fecha")Date fecha, @Param("hinit")Time horainit,@Param("hfin") Time horafin,@Param("docu")int doc);
    
    /*
    *Obtener todas las clases de un recurso
    *@return asig retorna una lista de asignaturas
    **/
    public List<Clase> consultarFechasRecursoClase(@Param("idrec") int rec);
    
    /*
    *Obtener todas las clases de un recurso
    *@return asig retorna una lista de asignaturas
    **/
    public List<Clase> consultarFechasProfesorClase(@Param("periodo") String periodo,@Param("nomprof") String nom,@Param("date") Date fec);
    
    /*
    *Obtener todas las clases de un profesor
    *@return asig retorna una lista de asignaturas
    **/
    public List<Clase> consultarClaseProfesor(@Param("id")int cor,@Param("mat") String mat,@Param("profe") String profe);
     
     /*
    *Obtener todas las clases de un profesor
    *@return asig retorna una lista de asignaturas
    **/
    public List<Clase> consultarHorarioClaseDeProfesor(@Param("nombre")String nombre, @Param("per")String per);

}
