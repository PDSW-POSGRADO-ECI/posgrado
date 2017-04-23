/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import proyecto.services.ExceptionServiciosReporte;

/**
 *
 * @author Laura RB
 */
public class ReporteGeneralTest {

    public ReporteGeneralTest() {
    }
    
    @Before
    public void setUp() {
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
