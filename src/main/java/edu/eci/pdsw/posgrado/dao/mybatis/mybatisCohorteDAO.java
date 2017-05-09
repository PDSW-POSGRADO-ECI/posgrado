/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.posgrado.dao.CohorteDAO;
import edu.eci.pdsw.posgrado.dao.ExceptionPersistence;
import edu.eci.pdsw.posgrado.dao.mybatis.mappers.CohorteMapper;
import edu.eci.pdsw.posgrado.entities.Cohorte;
import edu.eci.pdsw.posgrado.entities.Periodo;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

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
    public Periodo loadPeriodo(String p ) {
        try{
            return corteMapper.consultarPeriodo(p);
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar Periodo ",e);
        }        
    }

    @Override
    public boolean loadMateriaCohorteExistente(String profe, String mat,int cort) throws ExceptionPersistence {
        try{
            return corteMapper.consultarMateriaCohorteExistente(profe, mat, cort)==0;
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar materiacorte del cohorte"+profe+"de la materia"+mat,e);
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

    @Override
    public void saveCohorte(int id, String per) throws ExceptionPersistence {
        try{
            corteMapper.registrarCohorte(id, per);
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar Periodo ",e);
        }  
    }

    @Override
    public void saveMateriaCohorte(int id, String mat, String profe) throws ExceptionPersistence {
        try{
            corteMapper.registrarMateriaCohorte(id, mat, profe);
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar Periodo ",e);
        }  
    }

    @Override
    public Cohorte loadCohorte(int cort) throws ExceptionPersistence {
       try{
            return corteMapper.consultarCohorteExistente(cort);
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar Periodo ",e);
        }  
    }

    @Override
    public List<Cohorte> loadMateriaCohorte(String periodo, String mat) {
        try{
            return corteMapper.consultarMateriaCohorte(periodo, mat);
        }
        catch(PersistenceException e){
            throw new PersistenceException("Error al cargar Periodo ",e);
        }  
    }
    
}
