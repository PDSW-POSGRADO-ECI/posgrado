/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.ExceptionPersistence;
import edu.eci.pdsw.posgrado.dao.PosgradoDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mappers.PosgradoMapper;
import edu.eci.pdsw.posgrado.entities.Posgrado;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2122825
 */
public class mybatisPosgradoDAO implements PosgradoDAO {

    @Inject
    private PosgradoMapper posgradoMapper;

    /**
     * Consulta los posgrados
     * @return
     * @throws ExceptionPersistence 
     */
    @Override
    public List<Posgrado> loadPosgrados() throws ExceptionPersistence {
        try {
            return posgradoMapper.consultarPosgrados();
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar posgrados ", e);
        }
    }

    /**
     * Registra los posgrados
     * @param nom
     * @param credit
     * @throws ExceptionPersistence 
     */
    @Override
    public void savePosgrado(String nom, int credit) throws ExceptionPersistence {
        try {
            posgradoMapper.registrarPosgrado(nom, credit);
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al registrar posgrado ", e);
        }
    }

    /**
     * Consulta los nombres del posgrado
     * @return
     * @throws ExceptionPersistence 
     */
    @Override
    public List<String> loadNames() throws ExceptionPersistence {
        try {
            return posgradoMapper.consultarNombrePosgrados();
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar los nombres de los posgrados", e);
        }
    }

}
