/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import java.util.List;
import proyecto.dao.ExceptionPersistence;
import proyecto.dao.ProfesorDAO;
import proyecto.dao.mybatis.mappers.ProfesorMapper;
import proyecto.entities.Profesor;

/**
 *
 * @author Laura RB
 */
public class mybatisProfesorDAO implements ProfesorDAO{
    
    @Inject private ProfesorMapper profesorMapper;

    @Override
    public List<Profesor> loadProfesores() throws ExceptionPersistence {
        List<Profesor> pro=null;
        try{
            pro=profesorMapper.consultarProfesores();
        }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar profesores "+pro.toString(),e);
        }  
        return pro;
    }

    @Override
    public List<Profesor> loadProfesorXmateriacorte(String siglamat,int idcort) throws ExceptionPersistence {
        List<Profesor> pro=null;
        try{
            pro=profesorMapper.consultarProfesorXmateriacohorte(siglamat, idcort);
        }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new ExceptionPersistence("Error al cargar profesor por corte y materia"+pro.toString(),e);
        }  
        return pro;
    }
    
}
