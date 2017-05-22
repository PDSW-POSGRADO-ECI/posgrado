/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.ExceptionPersistence;
import edu.eci.pdsw.posgrado.dao.HorarioDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mappers.HorarioMapper;
import edu.eci.pdsw.posgrado.entities.Horario;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author Laura RB
 */
public class mybatisHorarioDAO implements HorarioDAO{
    
    @Inject private HorarioMapper horarioMapper;
    
    /**
     * Consulta el horario de profesor
     * @param nom
     * @param fecha
     * @return 
     */
    @Override
    public Horario loadHorarioProfesor(String nom,Date fecha) {
        
        try {
            return horarioMapper.consultarHorarioProfesor(nom,fecha);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar horario de profesores", e);
        }
    }

    
    /**
     * Consulta los horarios de un profesor
     * @param nom
     * @return
     * @throws ExceptionPersistence 
     */
    @Override
    public List<Horario> loadHorariosProfesor(String nom) throws ExceptionPersistence {
        try {
            return horarioMapper.consultarHorariosDeProfesor(nom);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar horario de profesores", e);
        }
    }
    
}
