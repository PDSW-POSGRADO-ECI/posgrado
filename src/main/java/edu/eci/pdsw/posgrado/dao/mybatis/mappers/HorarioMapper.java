/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis.mappers;

import edu.eci.pdsw.posgrado.entities.Horario;
import java.util.Date;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Laura RB
 */
public interface HorarioMapper {
    /**
     * Retorna una lista de horarios del profesor 
     * @param nom
     * @param fecha
     * @return 
     **/
    public Horario consultarHorarioProfesor(@Param("nomprof")String nom,@Param("fe")Date fecha);
}
