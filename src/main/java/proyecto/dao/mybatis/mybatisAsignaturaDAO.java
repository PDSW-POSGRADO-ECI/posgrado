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
import proyecto.entities.Posgrado;

/**
 *
 * @author Daniel Rodriguez
 */
public class mybatisAsignaturaDAO implements AsignaturaDAO{
    
    @Inject private AsignaturaMapper asignaturaMapper;
    
    /*
    *@see AsignaturaDAO loadAsignaturas
    **/
    @Override
    public List<Asignatura> loadAsignaturas() throws ExceptionPersistence {
        try{
            return asignaturaMapper.consultarAsignaturas();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar las asignaturas ",e);
        } 
    }

    @Override
    public List<Asignatura> loadAsignaturas(int posgrado) throws ExceptionPersistence {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Posgrado> loadPosgrados() throws ExceptionPersistence {
        try{
            return asignaturaMapper.consultarPosgrados();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar posgrados ",e);
        } 
    }
    
}
