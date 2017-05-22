/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao;

import edu.eci.pdsw.posgrado.entities.Horario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Laura RB
 */
public interface HorarioDAO {
    /**
     * 
     * @param nom
     * @param fecha
     * @return 
     * @throws edu.eci.pdsw.posgrado.dao.ExceptionPersistence 
     **/
    public Horario loadHorarioProfesor(String nom,Date fecha) throws ExceptionPersistence;
    
    /**
     * Retorna una lista de horarios del profesor 
     * @param nom
     * @return
     * @throws ExceptionPersistence 
     */
    public List<Horario> loadHorariosProfesor(String nom) throws ExceptionPersistence;
}
