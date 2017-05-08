/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao;

import edu.eci.pdsw.posgrado.entities.Horario;
import java.util.Date;

/**
 *
 * @author Laura RB
 */
public interface HorarioDAO {
    /**
     * Retorna una lista de horarios del profesor 
     * @param nom
     * @return 
     * @throws edu.eci.pdsw.posgrado.dao.ExceptionPersistence 
     **/
    public Horario loadHorarioProfesor(String nom,Date fecha) throws ExceptionPersistence;
}
