/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao;

import edu.eci.pdsw.posgrado.entities.Profesor;
import java.util.List;

/**
 *
 * @author Daniel Rodriguez
 */
public interface ProfesorDAO {
     
    /*
    *Obtener todas los profesores  de la base de datos
    *@return retorna una lista de profesores
    **/
    public List<Profesor> loadProfesores() throws ExceptionPersistence;
    
    public List<String> loadNames() throws ExceptionPersistence;
     
    /*
    *Obtener un profesor de un corte y materia especifica
    *@return retorna un profeso d eun corte y una materia especifica
    **/
    public Profesor loadProfesor(int claseid, String materia) throws ExceptionPersistence;
    

    public List<Profesor> loadProfesoresCohorte(int cor, String mat) throws ExceptionPersistence;

    public List<Profesor> loadProfesorPeriodo(String periodo) throws ExceptionPersistence;


}
