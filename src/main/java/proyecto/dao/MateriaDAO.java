/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao;

import java.util.List;
import proyecto.entities.Materia;

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
    
    /*
    *Obtener todas las materias de una asignatura
    *@param asignatura
    *@return mat retorna una lista de materias
    **/
    public List<Materia> loadMateriasXasignatura(String asignatura)throws ExceptionPersistence;
    
    /*
    *Consultar si ya existe un profesor para un determinado cohorte y materia 
    *@param doc
    *@param cor
    *@param sigla
    *@return booleano de si ya existe o no
    **/
    public boolean loadMateriaCohorte(int doc, int cor, String sigla)throws ExceptionPersistence;
    
    public List<Materia>loadMateriasXposgrado(String posgrado);
}
