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
import proyecto.dao.mybatis.mappers.CohorteMapper;

/**
 *
 * @author Laura RB
 */
public class mybatisCohorteDAO implements CohorteMapper{
    @Inject private CohorteMapper corteMapper;

    
    @Override
    public List<Character> consultarPeriodos() {
        List<Character> per=null;
        try{
            per=corteMapper.consultarPeriodos();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al cargar Periodo ",e);
        }        
        
        return per;
    }
   
}
