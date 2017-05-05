/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.PrerequisitoMateriaDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mappers.PrerrequisitoMateriaMapper;
import edu.eci.pdsw.posgrado.entities.PrerrequisitoMateria;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author danie
 */
public class mybatisPrerequisitoMateriaDAO implements PrerequisitoMateriaDAO{
    
    @Inject
    private PrerrequisitoMateriaMapper prerrequisitoMateriaMapper;

    @Override
    public List<PrerrequisitoMateria> loadPrerrequisitos() {
         try {
            return prerrequisitoMateriaMapper.consultarPrerrequisitos();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar los prerrequisitos de las materias", e);
        }
    }
    
}
