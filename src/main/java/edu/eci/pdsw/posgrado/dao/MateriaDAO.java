/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao;

import edu.eci.pdsw.posgrado.entities.Materia;
import java.util.List;

/**
 *
 * @author Daniel Rodriguez
 */
public interface MateriaDAO {
     
    /*
    *Obtener 
    *@return 
    **/
    public List<Materia> loadMaterias() throws ExceptionPersistence;
    
    public List<String> loadNames(String asignatura_id) throws ExceptionPersistence;
    
       
    /*
    *Consultar si ya existe un profesor para un determinado cohorte y materia 
    *@param doc
    *@param cor
    *@param sigla
    *@return booleano de si ya existe o no
    **/
    public boolean loadMateriaCohorte(int doc, int cor, String sigla)throws ExceptionPersistence;
    
    public List<Materia>loadMateriasXposgrado(String posgrado) ;
    
    public void addMateria(String sigla,String nombre, int creditos, String asignatura_id, String descripcion) throws ExceptionPersistence;
    
    public List<Materia> loadPrerrequisitosMateria(String sigla) throws ExceptionPersistence;
    
    public List<Materia> loadCorrequisitosMateria(String sigla) throws ExceptionPersistence;
    
    
}
