/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao;

import edu.eci.pdsw.posgrado.entities.Asignatura;
import java.util.List;

/**
 *
 * @author Daniel Rodriguez
 */
public interface AsignaturaDAO {
     
    /*
    *Obtener 
    *@return 
    **/
    public List<Asignatura> loadAsignaturas() throws ExceptionPersistence;
    
    /*
    *Obtener todas las asignaturas de un posgrado
    *@return asig retorna una lista de asignaturas 
    **/
    public List<Asignatura> loadAsignaturas(String posgrado)throws ExceptionPersistence;
    
    public List<String> loadNames(String posgrado) throws ExceptionPersistence;
    
    public List<String> loadAllNames() throws ExceptionPersistence;

    /*
    *Obtener todas los posgrados
    *@return pos lista de posgrados
    **/
    public void saveAsignatura(String nom, String posgrado)throws ExceptionPersistence;
}
