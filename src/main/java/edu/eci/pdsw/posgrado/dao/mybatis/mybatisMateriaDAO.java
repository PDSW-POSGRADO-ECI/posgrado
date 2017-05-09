/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.ExceptionPersistence;
import edu.eci.pdsw.posgrado.dao.MateriaDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mappers.MateriaMapper;
import edu.eci.pdsw.posgrado.entities.Materia;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

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

    /**
     * 
     * @param doc
     * @param cor
     * @param sigla
     * @return
     * @throws ExceptionPersistence 
     */
    @Override
    public boolean loadMateriaCohorte(int doc, int cor, String sigla) throws ExceptionPersistence {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param posgrado
     * @return 
     */
    @Override
    public List<Materia> loadMateriasXposgrado(String posgrado) {
         try {
            return materiaMapper.consultarMateriasXposgrado(posgrado);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al cargar las materias del posgrado"+ posgrado, e);
        }
    }

    /**
     * 
     * @param sigla
     * @param nombre
     * @param creditos
     * @param asignatura_nombre
     * @param descripcion 
     */
    @Override
    public void addMateria(String sigla, String nombre, int creditos, String asignatura_nombre, String descripcion) {
         try {
            materiaMapper.registrarMateria(sigla, nombre, creditos, asignatura_nombre, descripcion);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al Insertar materia"+  e);
        }
    }

    /**
     * 
     * @param asignatura_id
     * @return
     * @throws ExceptionPersistence 
     */
    @Override
    public List<String> loadNames(String asignatura_id) throws ExceptionPersistence {
        try{
            return materiaMapper.consultarNombresMateriasxAsignatura(asignatura_id);
        } catch (PersistenceException e){
            throw new ExceptionPersistence("Error al consultar los nombres de las materias correspondientes a :"+asignatura_id, e);
        }
    }

    /**
     * 
     * @param sigla
     * @return
     * @throws ExceptionPersistence 
     */
    @Override
    public List<Materia> loadPrerrequisitosMateria(String sigla) throws ExceptionPersistence {
         try{
            return materiaMapper.consultarPrerrequisitosDeMateria(sigla);
        } catch (PersistenceException e){
            throw new ExceptionPersistence("Error al consultar prerrequisitos de la materia "+sigla, e);
        }
    }

    /**
     * 
     * @param sigla
     * @return
     * @throws ExceptionPersistence 
     */
    @Override
    public List<Materia> loadCorrequisitosMateria(String sigla) throws ExceptionPersistence {
         try{
            return materiaMapper.consultarCorrequisitosDeMateria(sigla);
        } catch (PersistenceException e){
            throw new ExceptionPersistence("Error al consultar prerrequisitos de la materia "+sigla, e);
        }
    }
    
   

}
