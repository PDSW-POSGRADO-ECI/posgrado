/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import proyecto.entities.Posgrado;

/**
 *
 * @author 2122825
 */
public interface PosgradoMapper {
    /*
    *Obtener todos los periodos
    *@return asig retorna una lista de asignaturas
    **/
    public void registrarPosgrado(@Param("nompos")String nom, @Param("credit")int credit);
    
    /*
    *Obtener todas las asignaturas de un posgrado
    *@return asig retorna una lista de asignaturas 
    **/
    public List<Posgrado> consultarPosgrados();
    
    public List<String> consultarNombrePosgrados();
}
