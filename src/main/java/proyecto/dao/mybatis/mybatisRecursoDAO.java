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
        List<Recurso> rec=null;
        try{
            //System.out.println(per);
            rec=recursoMapper.consultarRecursoDePeriodo(per);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar Recurso "+rec.toString(),e);
        }        
        
        return rec;
    }
    
    /*
    *@see RecursoDAO loadRecursos
    **/
    @Override
    public List<Recurso> loadRecursos() throws ExceptionPersistence {
        List<Recurso> rec=null;
        try{
            rec=recursoMapper.consultarRecursos();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar Recurso "+rec.toString(),e);
        }        
        
        return rec;    }
    
}
