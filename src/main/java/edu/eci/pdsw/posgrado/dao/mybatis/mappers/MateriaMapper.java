/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis.mappers;

import edu.eci.pdsw.posgrado.entities.Materia;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Daniel Rodriguez
 */
public interface MateriaMapper {
     
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public List<Materia> consultarMaterias();
    
    
    
    /*
    *Consultar si ya existe un profesor para un determinado cohorte y materia 
    *@param doc
    *@param cor
    *@param sigla
    *@return booleano de si ya existe o no
    **/
    public boolean consultarMateriaCohorte(@Param("docu")int doc, @Param("idcpor")int cor, @Param("sigla")String sigla);

    public List<Materia> consultarMateriasXposgrado(@Param("nomposgrado")String posgrado);
    
    public void registrarMateria(@Param("sigla") String sigla, @Param("nombre") String nombre,@Param("creditos") int creditos, @Param("asig_nom") String asignatura_nombre, @Param("descripcion")String descripcion);
    

    public List<String> consultarNombresMateriasxAsignatura(@Param("asig")String asignatura_nombre);
    
}
