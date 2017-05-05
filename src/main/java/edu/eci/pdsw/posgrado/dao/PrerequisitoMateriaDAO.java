/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao;

import edu.eci.pdsw.posgrado.entities.PrerrequisitoMateria;
import java.util.List;

/**
 *
 * @author Daniel Rodriguez
 */
public interface PrerequisitoMateriaDAO {
    
    /**
     * Carga los prerrequisitos de todas las materias
     * @return List<PrerrequisitoMateria>
     */
    public List<PrerrequisitoMateria> loadPrerrequisitos();
    
}