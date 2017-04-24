/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
import proyecto.dao.ClaseDAO;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.mybatis.mappers.ClaseMapper;
import proyecto.dao.mybatis.mappers.CohorteMapper;
import proyecto.entities.Clase;
import proyecto.entities.Cohorte;

/**
 *
 * @author Laura RB
 */
public class mybatisClaseDAO implements ClaseDAO {

    @Inject
    private ClaseMapper claseMapper;

    @Override
    public List<Clase> loadClase() throws ExceptionPersistence {
        List<Clase> per = null;
        try {
            per = claseMapper.consultarClase();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al cargar Clase ", e);
        }

        return per;
    }

    @Override
    public List<Clase> loadClaseXperiodo(String periodo) throws ExceptionPersistence {
        try {
            return claseMapper.consultarClaseXperiodo(periodo);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al cargar Clase por periodo (" + periodo + ")", e);
        }
    }
}
