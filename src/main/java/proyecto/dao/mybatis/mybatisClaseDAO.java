/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
import proyecto.dao.ClaseDAO;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.mybatis.mappers.ClaseMapper;
import proyecto.entities.Clase;

/**
 *
 * @author Laura RB
 */
public class mybatisClaseDAO implements ClaseDAO {

    @Inject private ClaseMapper claseMapper;
    
    /*
    *@see ClaseDAO loadClase
    **/
    @Override
    public List<Clase> loadClase() throws ExceptionPersistence {
        List<Clase> per = null;
        try {
            per = claseMapper.consultarClase();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al cargar Clase "+per.toString(), e);
        }

        return per;
    }
    
    /*
    *@see ClaseDAO loadClaseXperiodo
    **/
    @Override
    public List<Clase> loadClaseXperiodo(String periodo) throws ExceptionPersistence {
        try {
            return claseMapper.consultarClaseXperiodo(periodo);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al cargar Clase por periodo (" + periodo + ")", e);
        }
    }
    
    /*
    *Obtener todas las fechas de las clases de un periodo
    *@return claseMapper.consultarFechas() Lista de String con las fechas de un periodo
    **/
    @Override
    public List<Date> loadFechas(String periodo) throws ExceptionPersistence {
        try {
            return claseMapper.consultarFechas(periodo);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar fechas", e);
        }
    }
}
