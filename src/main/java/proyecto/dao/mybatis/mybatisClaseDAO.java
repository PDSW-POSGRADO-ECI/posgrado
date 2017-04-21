/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao.mybatis;

import com.google.inject.Inject;
import proyecto.dao.ClaseDAO;
import proyecto.dao.mybatis.mappers.ClaseMapper;
import proyecto.dao.mybatis.mappers.CohorteMapper;

/**
 *
 * @author Laura RB
 */
public class mybatisClaseDAO implements ClaseDAO{

    @Inject private ClaseMapper claseMapper;
}
