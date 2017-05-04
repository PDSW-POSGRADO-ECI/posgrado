/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis.mappers;

import edu.eci.pdsw.posgrado.entities.Asignatura;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Daniel Rodriguez
 */
public interface AsignaturaMapper {
    
     
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public List<Asignatura> consultarAsignaturas();
    
    /*
    *Obtener todas las asignaturas de un posgrado
    *@return asig retorna una lista de asignaturas 
    **/
    public List<Asignatura> consultarAsignaturasXposgrado(@Param("nomposgrado")String posgrado);
    
    
    /*
    *Obtener todos los periodos
    *@return asig retorna una lista de asignaturas
    **/
    public void registrarAsignatura(@Param("nomasig")String nom,@Param("nomposgrado")String posgrado);

    
    public List<String> consultarNombresAsignaturasXposgrado(@Param("posgrado") String posgrado);

    public List<String> consultarNombresAsignaturas();
}
