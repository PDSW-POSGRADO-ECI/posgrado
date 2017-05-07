/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao;

import edu.eci.pdsw.posgrado.entities.Recurso;
import java.util.List;

/**
 *
 * @author Laura RB
 */
public interface RecursoDAO {
     
    /*
    *Obtener todas los recursos de un peruiodo
    *@param per
    *@return asig retorna una lista de asignaturas
    **/
    public List<Recurso> loadRecursoXperiodo(String per)throws ExceptionPersistence;
     
    /*
    *Obtener todos los recursos de la base de datos
    *@return retorna lista de todos los recursos
    **/
    public List<Recurso> loadRecursos()throws ExceptionPersistence;
    
    /*
    *Registrar un nuevo recurso
    *@param idclase
    *@nombreRecurso
    **/
    public void saveRecurso(String nombreRecurso,int cnatidad)throws ExceptionPersistence;
    
     /*
    *Registrar un nuevo recurso para una clase
    *@param idclase
    *@nombreRecurso
    **/
    public void saveRecursoClase(int id, int idcla, int i) throws ExceptionPersistence;

    
    
}
