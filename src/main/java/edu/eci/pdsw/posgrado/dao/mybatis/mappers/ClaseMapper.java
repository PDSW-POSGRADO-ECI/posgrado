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
    public void registrarClase(@Param("idcor")int corte, @Param("periodo")String per,
            @Param("fecha")Date fecha, @Param("hinit")Time horainit,@Param("hfin") Time horafin,@Param("docu")int doc);
}
