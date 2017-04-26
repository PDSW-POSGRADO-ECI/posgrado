/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao;

import java.util.List;
import proyecto.entities.Cohorte;

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
    *Obtener 
    *@return 
    **/
    public List<Cohorte> loadPeriodo(String p) throws ExceptionPersistence;
}
