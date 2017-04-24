/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import proyecto.entities.Profesor;

/**
 *
 * @author Daniel Rodriguez
 */
public interface ProfesorMapper {
    
    public List<Profesor> consultarProfesores();

    public Profesor consultarProfesor(@Param("cohorte")int cohorte, @Param("materia")String materia);
}
