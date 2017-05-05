/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.eci.pdsw.posgrado.dao.AsignaturaDAO;
import edu.eci.pdsw.posgrado.dao.ClaseDAO;
import edu.eci.pdsw.posgrado.dao.CohorteDAO;
import edu.eci.pdsw.posgrado.dao.MateriaDAO;
import edu.eci.pdsw.posgrado.dao.PosgradoDAO;
import edu.eci.pdsw.posgrado.dao.ProfesorDAO;
import edu.eci.pdsw.posgrado.dao.RecursoDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mybatisAsignaturaDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mybatisClaseDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mybatisCohorteDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mybatisMateriaDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mybatisPosgradoDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mybatisProfesorDAO;
import edu.eci.pdsw.posgrado.dao.mybatis.mybatisRecursoDAO;
import edu.eci.pdsw.posgrado.services.impl.ServiciosReporteImpl;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

/**
 *
 * @author OscarAlba
 */
public class ServiciosReporteFactory {

    private static ServiciosReporteFactory instance;

    private static Injector injector;
    private static Injector testingInjector;

    private ServiciosReporteFactory() {
        injector = Guice.createInjector(new XMLMyBatisModule() {

            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setClassPathResource("mybatis-configDatabase.xml");
                bind(ServiciosReporte.class).to(ServiciosReporteImpl.class);
                bind(RecursoDAO.class).to(mybatisRecursoDAO.class);
                bind(ClaseDAO.class).to(mybatisClaseDAO.class);
                bind(MateriaDAO.class).to(mybatisMateriaDAO.class);
                bind(CohorteDAO.class).to(mybatisCohorteDAO.class);
                bind(ProfesorDAO.class).to(mybatisProfesorDAO.class);
                bind(AsignaturaDAO.class).to(mybatisAsignaturaDAO.class);
                bind(PosgradoDAO.class).to(mybatisPosgradoDAO.class);
            }

        }
        );
        testingInjector = Guice.createInjector(new XMLMyBatisModule() {

            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setClassPathResource("h2-mybatis-config.xml");
                bind(ServiciosReporte.class).to(ServiciosReporteImpl.class);
                bind(RecursoDAO.class).to(mybatisRecursoDAO.class);
                bind(CohorteDAO.class).to(mybatisCohorteDAO.class);
                bind(ClaseDAO.class).to(mybatisClaseDAO.class);
                bind(MateriaDAO.class).to(mybatisMateriaDAO.class);
                bind(ProfesorDAO.class).to(mybatisProfesorDAO.class);
                bind(AsignaturaDAO.class).to(mybatisAsignaturaDAO.class);
                bind(PosgradoDAO.class).to(mybatisPosgradoDAO.class);
            }

        }
        );

    }

    public ServiciosReporte getServiciosReporteForTesting() {
        return testingInjector.getInstance(ServiciosReporte.class);
    }

    public ServiciosReporte getServiciosReporte() {
        return injector.getInstance(ServiciosReporte.class);
    }

    public static Injector getInjector() {
        return injector;
    }

    public static void setInjector(Injector injector) {
        ServiciosReporteFactory.injector = injector;
    }

    public static Injector getTestingInjector() {
        return testingInjector;
    }

    public static void setTestingInjector(Injector testingInjector) {
        ServiciosReporteFactory.testingInjector = testingInjector;
    }

    public static ServiciosReporteFactory getInstance() {
        return instance == null ? new ServiciosReporteFactory() : instance;
    }

    public static void main(String a[]){

    }

}
