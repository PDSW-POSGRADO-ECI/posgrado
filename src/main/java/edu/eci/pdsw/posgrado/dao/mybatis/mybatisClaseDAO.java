/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.ClaseDAO;
import edu.eci.pdsw.posgrado.dao.ExceptionPersistence;
import edu.eci.pdsw.posgrado.dao.mybatis.mappers.ClaseMapper;
import edu.eci.pdsw.posgrado.entities.Clase;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

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
        try {
            return claseMapper.consultarClase();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al cargar Clase ", e);
        }
    }
    
    /*
    *@see ClaseDAO loadClaseXperiodo
    **/
    @Override
    public List<Clase> loadClaseXperiodo(String periodo) throws ExceptionPersistence {
        try {
            return claseMapper.consultarClaseXperiodo(periodo);
        } catch (PersistenceException e) {
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
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar fechas", e);
        }
    }

    /**
     * Registra clase
     * @param corte
     * @param mat
     * @param fecha
     * @param horainit
     * @param horafin
     * @param doc
     * @throws ExceptionPersistence 
     */
    @Override
    public void saveClase(int corte, String mat, Date fecha, Time horainit, Time horafin, int doc)  throws ExceptionPersistence {
        try {
            claseMapper.registrarClase(corte, mat, fecha, horainit, horafin, doc);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al registrar clase", e);
        }
    }

    /**
     * Consulta las fechas del recurso por clase
     * @param rec
     * @return
     * @throws ExceptionPersistence 
     */
    @Override
    public List<Clase> loadFechasRecursoClase(int rec)  throws ExceptionPersistence {
        try {
            return claseMapper.consultarFechasRecursoClase(rec);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar fechas de recursos", e);
        }
    }

    /**
     * Consulta las fechas del profesor por clase
     * @param periodo
     * @param nom
     * @param fecha
     * @return
     * @throws ExceptionPersistence 
     */
    @Override
    public List<Clase> loadFechasProfesorClase(String periodo, String nom,Date fecha)  throws ExceptionPersistence {

        try {
            return claseMapper.consultarFechasProfesorClase(periodo, nom,fecha);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar fechas de profesores", e);
        }
    }

    @Override
    public List<Clase> loadClaseProfesor(int cor, String mat, String profe) throws ExceptionPersistence{
        try {
            return claseMapper.consultarClaseProfesor(cor, mat, profe);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar fechas de profesores", e);
        }
    }

    /**
     * Consulta el horario de clases del profesor
     * @param nom
     * @return 
     */
    @Override
    public List<Clase> loadHorarioProfesor(String nom) {
        try {
            return claseMapper.consultarHorarioClaseDeProfesor(nom);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el horario de clases del profesor", e);
        }
    }
}
