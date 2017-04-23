/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao;

import java.util.List;
import proyecto.entities.Cohorte;

/**
 *
 * @author Laura RB
 */
public interface CohorteDAO {
    public List<Cohorte> loadPeriodos() throws ExceptionPersistence;
    public List<Cohorte> loadPeriodo(String p) throws ExceptionPersistence;
}
