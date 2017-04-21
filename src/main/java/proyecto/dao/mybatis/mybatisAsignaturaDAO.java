/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;
import proyecto.dao.AsignaturaDAO;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.mybatis.mappers.AsignaturaMapper;
import proyecto.entities.Asignatura;

/**
 *
 * @author Daniel Rodriguez
 */
public class mybatisAsignaturaDAO implements AsignaturaDAO{
    
    @Inject private AsignaturaMapper asignaturaMapper;

    @Override
    public List<Asignatura> loadAsignaturas() throws ExceptionPersistence {
        List<Asignatura> asig=null;
        try{
            asig=asignaturaMapper.consultarAsignaturas();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar las asignaturas "+asig.toString(),e);
        } 
        return asig;
    }
    
}
