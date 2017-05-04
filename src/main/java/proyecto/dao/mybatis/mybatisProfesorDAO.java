/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.ProfesorDAO;
import proyecto.dao.mybatis.mappers.ProfesorMapper;
import proyecto.entities.Profesor;

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
}
