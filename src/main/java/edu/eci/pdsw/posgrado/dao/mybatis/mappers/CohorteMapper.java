/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis.mappers;

import edu.eci.pdsw.posgrado.entities.Cohorte;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Laura RB
 */
public interface CohorteMapper {
     
    /*
    *Obtener todos los periodos
    *@return asig retorna una lista de asignaturas
    **/
    public List<String> consultarPeriodos();
     
    /*
    *Obtener todas los cortes d eun periodo
    *@return asig retorna una lista de asignaturas
    **/
    public List<Cohorte> consultarPeriodo(@Param("periodo") String per);
    
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public List<Cohorte> consultarMateriaCohorte(@Param("periodo")String per,@Param("nombremat") String mat);
    
    /*
    *Obtener todos los periodos
    *@return asig retorna una lista de asignaturas
    **/
    public void registrarPeriodo(@Param("periodo")String periodo,@Param("fecha_inicio") Date fini,@Param("fecha_fin") Date ffin);
}
