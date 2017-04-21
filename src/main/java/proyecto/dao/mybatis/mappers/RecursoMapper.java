/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis.mappers;

import proyecto.entities.Recurso;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 *
 * @author Laura RB
 */
public interface RecursoMapper {
    public List<Recurso> consultarRecursoDePeriodo(@Param("periodo") char per );
}
