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

    @Inject private ProfesorMapper profesorMapper;
    
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
    public Profesor loadProfesor(int cohorte, String materia) throws ExceptionPersistence {
        try {
            return profesorMapper.consultarProfesor(cohorte, materia);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar al profesor de la materia " + materia + " en el cohorte " + cohorte + ".", e);
        }
    }
}
