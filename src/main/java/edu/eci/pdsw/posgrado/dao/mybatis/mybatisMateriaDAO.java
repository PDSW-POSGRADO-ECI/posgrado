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
import proyecto.dao.MateriaDAO;
import proyecto.dao.mybatis.mappers.MateriaMapper;
import proyecto.entities.Materia;

/**
 *
 * @author Laura RB
 */
public class mybatisMateriaDAO implements MateriaDAO {

    @Inject private MateriaMapper materiaMapper;
    
    /*
    *@see  MateriaDAO loadMaterias
    **/
    @Override
    public List<Materia> loadMaterias() throws ExceptionPersistence {
        try {
            return materiaMapper.consultarMaterias();
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar las materias.", e);
        }
    }

    @Override
    public boolean loadMateriaCohorte(int doc, int cor, String sigla) throws ExceptionPersistence {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Materia> loadMateriasXasignatura(String asignatura) throws ExceptionPersistence {
         try {
            return materiaMapper.consultarMateriasXasignatura(asignatura);
        } catch (PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar las materias de la asignatura"+ asignatura, e);
        }
    }

    @Override
    public List<Materia> loadMateriasXposgrado(String posgrado) {
         try {
            return materiaMapper.consultarMateriasXposgrado(posgrado);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al cargar las materias del posgrado"+ posgrado, e);
        }
    }

    @Override
    public void addMateria(String sigla, String nombre, int creditos, String asignatura_nombre, String descripcion) {
         try {
            materiaMapper.registrarMateria(sigla, nombre, creditos, asignatura_nombre, descripcion);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al Insertar materia"+  e);
        }
    }

    @Override
    public List<String> loadNames(String asignatura_id) throws ExceptionPersistence {
        try{
            return materiaMapper.consultarNombresMateriasxAsignatura(asignatura_id);
        } catch (PersistenceException e){
            throw new ExceptionPersistence("Error al consultar los nombres de las materias correspondientes a :"+asignatura_id, e);
        }
    }
    
   

}
