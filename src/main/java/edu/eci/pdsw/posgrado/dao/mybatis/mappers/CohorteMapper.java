/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.dao.mybatis.mappers;

import edu.eci.pdsw.posgrado.entities.Cohorte;
import edu.eci.pdsw.posgrado.entities.Periodo;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Laura RB
 */
public interface CohorteMapper {
     
    /*
    *Obtener todos los periodos
    **/
    public List<String> consultarPeriodos();
     
    /*
    *Obtener todas los cortes d eun periodo
    *@return asig retorna una lista de asignaturas
    **/
    public Periodo consultarPeriodo(@Param("periodo") String per);
    
    /*
    *Obtener todas las asignaturas de la base de datos
    *@return asig retorna una lista de asignaturas
    **/
    public int consultarMateriaCohorteExistente(@Param("profe")String profe,@Param("mat") String mat,@Param("cor") int cor);
    
    /*
    *registrar un nuevo cohorte
    **/
    public void registrarCohorte(@Param("id") int id,@Param("periodo")String per);
    
    /*
    *registrar una materiaCohorte
    **/
    public void registrarMateriaCohorte(@Param("cor") int id,@Param("mat")String mat,@Param("profe")String profe);
    
   
    /*
    *registrar un nuevo periodo
    **/
    public void registrarPeriodo(@Param("periodo")String periodo,@Param("fecha_inicio") Date fini,@Param("fecha_fin") Date ffin);

    public Cohorte consultarCohorteExistente(@Param("cort")int cort);

    public List<Cohorte> consultarMateriaCohorte(@Param("periodo")String periodo, @Param("nombremat")String mat);
    
    
}
