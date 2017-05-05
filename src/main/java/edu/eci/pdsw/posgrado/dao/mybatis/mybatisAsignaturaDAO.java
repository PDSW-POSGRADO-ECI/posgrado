/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.AsignaturaDAO;
import edu.eci.pdsw.posgrado.dao.ExceptionPersistence;
import edu.eci.pdsw.posgrado.dao.mybatis.mappers.AsignaturaMapper;
import edu.eci.pdsw.posgrado.entities.Asignatura;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author Daniel Rodriguez
 */
public class mybatisAsignaturaDAO implements AsignaturaDAO {

    @Inject
    private AsignaturaMapper asignaturaMapper;

    /*
    *@see AsignaturaDAO loadAsignaturas
    **/
    @Override
    public List<Asignatura> loadAsignaturas() throws ExceptionPersistence {
        try {
            return asignaturaMapper.consultarAsignaturas();
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar las asignaturas ", e);
        }
    }

    @Override
    public List<Asignatura> loadAsignaturas(String posgrado) throws ExceptionPersistence {
        try {
            return asignaturaMapper.consultarAsignaturasXposgrado(posgrado);
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar las asignaturas del  posgrado" + posgrado, e);
        }
    }

    @Override
    public void saveAsignatura(String nom, String posgrado) throws ExceptionPersistence {
        try {
            asignaturaMapper.registrarAsignatura(nom, posgrado);
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al registrar asignatura ", e);
        }
    }

    @Override
    public List<String> loadNames(String posgrado) throws ExceptionPersistence {
        try {
            return asignaturaMapper.consultarNombresAsignaturasXposgrado(posgrado);
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar el nombre de las asignaturas", e);
        }
    }

    @Override
    public List<String> loadAllNames() throws ExceptionPersistence {
        try {
            return asignaturaMapper.consultarNombresAsignaturas();
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar el nombre de las asignaturas", e);
        }
    }

}
