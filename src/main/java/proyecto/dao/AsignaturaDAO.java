/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao;

import java.util.List;
import proyecto.entities.Asignatura;

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
    
}
