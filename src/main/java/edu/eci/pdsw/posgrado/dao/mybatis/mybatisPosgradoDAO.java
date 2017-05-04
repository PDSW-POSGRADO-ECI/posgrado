/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.PosgradoDAO;
import proyecto.dao.mybatis.mappers.PosgradoMapper;
import proyecto.entities.Posgrado;

/**
 *
 * @author 2122825
 */
public class mybatisPosgradoDAO implements PosgradoDAO {

    @Inject
    private PosgradoMapper posgradoMapper;

    @Override
    public List<Posgrado> loadPosgrados() throws ExceptionPersistence {
        try {
            return posgradoMapper.consultarPosgrados();
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar posgrados ", e);
        }
    }

    @Override
    public void savePosgrado(String nom, int credit) throws ExceptionPersistence {
        try {
            posgradoMapper.registrarPosgrado(nom, credit);
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al registrar posgrado ", e);
        }
    }

    @Override
    public List<String> loadNames() throws ExceptionPersistence {
        try {
            return posgradoMapper.consultarNombrePosgrados();
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar los nombres de los posgrados", e);
        }
    }

}
