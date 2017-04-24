/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import proyecto.entities.Clase;

/**
 *
 * @author Laura RB
 */
public interface ClaseMapper {
    public List<Clase> consultarClase();
    
    public List<Clase> consultarClaseXperiodo(@Param("periodo") String periodo);
}
