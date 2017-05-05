/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao;

import edu.eci.pdsw.posgrado.entities.Clase;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Laura RB
 */
public interface ClaseDAO { 
    /*
    *Obtener 
    *@return 
    **/
    public List<Clase> loadClase() throws ExceptionPersistence;
    
    /*
    *Obtener 
    *@return 
    **/
    public List<Date> loadFechas(String periodo) throws ExceptionPersistence; 
    
    /*
    *Obtener 
    *@return 
    **/
    public List<Clase> loadClaseXperiodo(String periodo) throws ExceptionPersistence;
    
    /*
    *Reguistar una nueva clase 
    *@param corte
    *@param per
    *@param fecha
    *@param horainit 
    *@param horafin
    *@doc
    **/
    public void saveClase(int corte, String per,Date fecha, Time horainit,Time horafin,int doc)throws ExceptionPersistence;
}
