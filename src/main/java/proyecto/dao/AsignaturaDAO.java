/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao;

import java.util.List;
import proyecto.entities.Asignatura;
import proyecto.entities.Posgrado;

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
    
    /*
    *Obtener todas los posgrados
    *@return pos lista de posgrados
    **/
    public List<Posgrado> loadPosgrados()throws ExceptionPersistence;
    
    /*
    *Obtener todas los posgrados
    *@return pos lista de posgrados
    **/
    public void savePosgrado(String nom, int credit)throws ExceptionPersistence;
    
    /*
    *Obtener todas los posgrados
    *@return pos lista de posgrados
    **/
    public void saveAsignatura(String nom, String posgrado)throws ExceptionPersistence;
}
