/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import proyecto.entities.Profesor;

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
}
