/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao;

import edu.eci.pdsw.posgrado.entities.Cohorte;
import edu.eci.pdsw.posgrado.entities.Periodo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Laura RB
 */
public interface CohorteDAO { 
    
    /*
    *Obtener 
    *@return 
    **/
    public List<String> loadPeriodos() throws ExceptionPersistence;
     
    /*
    *Obtener  un corte de un periodo
    *@param p
    *@return una lista de cohortes
    **/
    public Periodo loadPeriodo(String p) throws ExceptionPersistence;
    
    /*
    *Obtener los cortes de una materia y periodo
    *@return una lista de cohortes
    **/
    public boolean loadMateriaCohorteExistente(String profe, String mat,int cort) throws ExceptionPersistence;

    public void savePeriodo(String per,Date fini,Date ffin)throws ExceptionPersistence;
    
    /*
    *registrar un nuevo cohorte
    **/
    public void saveCohorte(int id,String per) throws ExceptionPersistence;
    
     /*
    *registrar una materiaCohorte
    **/
    public void saveMateriaCohorte(int id,String mat,String profe) throws ExceptionPersistence;

    public Cohorte loadCohorte(int cort) throws ExceptionPersistence;

    public List<Cohorte> loadMateriaCohorte(String periodo, String mat);

}
