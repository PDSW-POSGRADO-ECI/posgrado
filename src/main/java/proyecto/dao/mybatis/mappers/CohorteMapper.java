/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis.mappers;

import java.util.List;
import proyecto.entities.Cohorte;

/**
 *
 * @author Laura RB
 */
public interface CohorteMapper {
    public List<Cohorte> consultarPeriodos();
}
