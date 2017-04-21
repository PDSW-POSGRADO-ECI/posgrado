package proyecto.dao.mybatis;

import com.google.inject.Inject;
import proyecto.dao.SalonDAO;
import proyecto.dao.mybatis.mappers.SalonMapper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura RB
 */
public class mybatisSalonDAO implements SalonDAO{
        @Inject private SalonMapper salonMapper;

}
