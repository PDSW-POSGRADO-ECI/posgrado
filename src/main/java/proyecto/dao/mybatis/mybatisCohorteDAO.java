/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
import proyecto.dao.CohorteDAO;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.mybatis.mappers.CohorteMapper;
import proyecto.entities.Cohorte;

/**
 *
 * @author Laura RB
 */
public class mybatisCohorteDAO implements CohorteDAO{
    
    @Inject private CohorteMapper corteMapper;

    /*
    *@see CohorteDAO loadPeriodos
    **/
    @Override
    public List<String> loadPeriodos() {
        try{
            return corteMapper.consultarPeriodos();
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar Periodo ",e);
        }        
    }
    
    /*
    *@see loadPeriodos loadPeriodo
    **/
    @Override
    public List<Cohorte> loadPeriodo(String p ) {
        try{
            return corteMapper.consultarPeriodo(p);
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar Periodo ",e);
        }        
    }

    @Override
    public List<Cohorte> loadMateriaCohorte(String periodo, String mat) throws ExceptionPersistence {
        try{
            return corteMapper.consultarMateriaCohorte(periodo,mat);
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar materiacorte del periodo"+periodo+"de la materia"+mat,e);
        }   
    }

    @Override
    public void savePeriodo(String per,Date fini,Date ffin) {
        try{
            corteMapper.registrarPeriodo(per, fini, ffin);
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar Periodo ",e);
        }  
    }
    
}
