/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis.mappers;

import java.util.List;
import proyecto.entities.Asignatura;

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
    
}
