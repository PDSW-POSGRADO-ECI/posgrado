/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao;

import java.util.List;
import proyecto.entities.Profesor;

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
     
    /*
    *Obtener un profesor de un corte y materia especifica
    *@return retorna un profeso d eun corte y una materia especifica
    **/
    public Profesor loadProfesor(int cohorte, String materia) throws ExceptionPersistence;
}
