/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao;

import java.util.List;
import proyecto.entities.Clase;

/**
 *
 * @author Laura RB
 */
public interface ClaseDAO {
    public List<Clase> loadClase() throws ExceptionPersistence;
}