/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;
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
            return materiaMapper.ConsultarMaterias();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new ExceptionPersistence("Error al cargar las materias.", e);
        }
    }

    @Override
    public List<Materia> consultarMaterias(int asignatura) throws ExceptionPersistence {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean consultarMateriaCohorte(int doc, int cor, String sigla) throws ExceptionPersistence {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
