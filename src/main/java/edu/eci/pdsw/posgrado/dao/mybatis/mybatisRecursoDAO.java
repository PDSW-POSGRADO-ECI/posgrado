/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.RecursoDAO;
import proyecto.dao.mybatis.mappers.RecursoMapper;
import proyecto.entities.Recurso;

/**
 *
 * @author Laura RB
 */
public class mybatisRecursoDAO implements RecursoDAO {
    
    @Inject private RecursoMapper recursoMapper;
    
    /*
    *@see RecursoDAO  loadRecursoXperiodo
    **/
    @Override
    public List<Recurso> loadRecursoXperiodo(String per) throws ExceptionPersistence {
        try{
            return recursoMapper.consultarRecursoDePeriodo(per);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar Recurso ",e);
        }        
    }
    
    /*
    *@see RecursoDAO loadRecursos
    **/
    @Override
    public List<Recurso> loadRecursos() throws ExceptionPersistence {
        try{
            return recursoMapper.consultarRecursos();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar Recurso ",e);
        }        
    }

    @Override
    public void saveRecurso(int idclase, String nombreRecurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
