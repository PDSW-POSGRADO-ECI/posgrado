/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.ExceptionPersistence;
import edu.eci.pdsw.posgrado.dao.RecursoDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mappers.RecursoMapper;
import edu.eci.pdsw.posgrado.entities.Recurso;
import java.util.ArrayList;
import java.util.List;

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
    public ArrayList<Recurso> loadRecursoXperiodo(String per) throws ExceptionPersistence {
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
    public void saveRecurso(String nombreRecurso, int cantidad) throws ExceptionPersistence{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveRecursoClase(int id, int idcla, int i) throws ExceptionPersistence{
        try{
            recursoMapper.registrarRecursosClase(id,idcla,i);
            recursoMapper.cantidadRecursos(id, idcla, i);
            recursoMapper.disponibleRecursos(id, idcla, i);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar Recurso ",e);
        } 
    }

   

}
