/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis.mappers;

import edu.eci.pdsw.posgrado.entities.Profesor;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Daniel Rodriguez
 */
public interface ProfesorMapper {
     
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public List<Profesor> consultarProfesores();
     
    /*
    *Obtener un profesor de una clase y materia
    *@return Profesor 
    **/
    public Profesor consultarProfesor(@Param("claid")int idcla, @Param("materia")String materia);
    
    /*
    *registrar un profesor a un corte de un periodo
    *@param doc
    *@param cort
    *@param periodo
    *@param sigla
    **/
    public void registrarProfesorCohorte(@Param("docu")int doc, @Param("idcor")int cort, @Param("period")String periodo, @Param("sigla")String sigla);

    public List<String> consultarNombresProfesores();

    public List<Profesor> consultarProfesoresCohorte(@Param("corte")int cor, @Param("mate")String mat);
}
