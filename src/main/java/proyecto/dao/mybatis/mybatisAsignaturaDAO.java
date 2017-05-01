/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
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
        catch(PersistenceException e){
            throw new ExceptionPersistence("Error al cargar las asignaturas ",e);
        } 
    }

    @Override
    public List<Asignatura> loadAsignaturas(String posgrado) throws ExceptionPersistence {
         try{
            return asignaturaMapper.consultarAsignaturasXposgrado(posgrado);
        }
        catch(PersistenceException e){
            throw new ExceptionPersistence("Error al cargar las asignaturas del  posgrado"+posgrado,e);
        } 
    }

    @Override
    public List<Posgrado> loadPosgrados() throws ExceptionPersistence {
        try{
            return asignaturaMapper.consultarPosgrados();
        }
        catch(PersistenceException e){
            throw new ExceptionPersistence("Error al cargar posgrados ",e);
        } 
    }
    
    @Override
    public void savePosgrado(String nom, int credit) throws ExceptionPersistence {
        try{
            asignaturaMapper.registrarPosgrado(nom,credit);
        }
        catch(PersistenceException e){
            throw new ExceptionPersistence("Error al registrar posgrado ",e);
        } 
    }
    
    @Override
    public void saveAsignatura(String nom, String posgrado) throws ExceptionPersistence {
        try{
            asignaturaMapper.registrarAsignatura(nom,posgrado);
        }
        catch(PersistenceException e){
            throw new ExceptionPersistence("Error al registrar asignatura ",e);
        } 
    }

    
}
