/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis.mappers;


import edu.eci.pdsw.posgrado.entities.Recurso;
import java.util.List;
import org.apache.ibatis.annotations.Param;


/**
 *
 * @author Laura RB
 */
public interface RecursoMapper {
     
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public List<Recurso> consultarRecursoDePeriodo(@Param("periodo") String per );
     
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public List<Recurso> consultarRecursos();
    
    /*
    *Registrar un nuevo recurso
    *@param idclase
    *@nombreRecurso
    *@return asig retorna una lista de asignaturas
    **/
    public void registrarRecurso(@Param("cant")int cant);

    public void registrarRecursosClase(@Param("idrec")int id, @Param("idcla")int idcla, @Param("cant")int cant);
}
