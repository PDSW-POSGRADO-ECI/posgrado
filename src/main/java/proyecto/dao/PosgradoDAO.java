/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao;

import java.util.List;
import proyecto.entities.Posgrado;

/**
 *
 * @author 2122825
 */
public interface PosgradoDAO {
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
    
    public List<String> loadNames() throws ExceptionPersistence;
}
