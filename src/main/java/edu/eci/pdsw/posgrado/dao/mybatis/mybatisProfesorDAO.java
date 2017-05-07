/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.ExceptionPersistence;
import edu.eci.pdsw.posgrado.dao.ProfesorDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mappers.ProfesorMapper;
import edu.eci.pdsw.posgrado.entities.Profesor;
import java.util.List;

/**
 *
 * @author Laura RB
 */
public class mybatisProfesorDAO implements ProfesorDAO {

    @Inject
    private ProfesorMapper profesorMapper;

    /*
    *@see ProfesorDAO loadProfesores
    **/
    @Override
    public List<Profesor> loadProfesores() throws ExceptionPersistence {
        try {
            return profesorMapper.consultarProfesores();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar profesores", e);
        }
    }

    /*
    *@see ProfesorDAO loadProfesor
    **/
    @Override
    public Profesor loadProfesor(int claseid, String materia) throws ExceptionPersistence {
        try {
            return profesorMapper.consultarProfesor(claseid, materia);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar al profesor de la materia " + materia + " en el cohorte " + claseid + ".", e);
        }
    }

    @Override
    public void saveProfesorCohorte(int doc, int cort, String periodo, String sigla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> loadNames() throws ExceptionPersistence {
        try {
            return profesorMapper.consultarNombresProfesores();
        } catch (org.apache.ibatis.exceptions.PersistenceException ex) {
            throw new ExceptionPersistence("Error al cargar los nombres de los profesores", ex);
        }
    }

    @Override
    public List<Profesor> loadProfesoresCohorte(int cor, String mat) throws ExceptionPersistence{
        try {
            return profesorMapper.consultarProfesoresCohorte(cor, mat);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar Recurso ", e);
        }
    }    
}
