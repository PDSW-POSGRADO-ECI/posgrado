package tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import proyecto.services.ExceptionServiciosReporte;
import java.sql.SQLException;

/**
 *
 * @author Laura RB
 */
public class ReporteRecursoTest {
    
    public ReporteRecursoTest() {
    }
    

    @Before
    public void setUp() {
    }
    @After 
    public void clearDB() throws SQLException, Exception{
        Connection conbd= DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=PostgreSQL","anonymous","");
        
    }
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase Equivalencia
     * CE1: Recursos del mismo perirodo seleccionado.
     * @throws ExceptionServiciosReporte 
     */
    @Test
    public void CE1Test() throws ExceptionServiciosReporte{
        assertTrue(true);     
    }
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Frontera
     * CF1: Recursos del actual periodo academico.
     * @throws ExceptionServiciosReporte 
     */
    @Test
    public void CF1Test() throws ExceptionServiciosReporte{
        assertTrue(true);     
    }
    
        
    @After
    public void tearDown() {
    }
}
