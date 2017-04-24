package tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import proyecto.entities.Recurso;
import proyecto.services.ExceptionServiciosReporte;
import proyecto.services.ServiciosReporte;
import proyecto.services.ServiciosReporteFactory;

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
        Connection conn= DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=PostgreSQL","anonymous","");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from Recurso");
        stmt.execute("delete from Clase");
        stmt.execute("delete from MateriaCohorte");
        stmt.execute("delete from Profesor");
        stmt.execute("delete from Cohorte");
        stmt.execute("delete from Materia");
        stmt.execute("delete from Asignatura");
        stmt.execute("delete from Posgrado");
        
        
        
        conn.commit();
        conn.close();
    }
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=PostgreSQL", "anonymous", "");        
    }
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase Equivalencia
     * CE1: Recursos del mismo perirodo seleccionado.
     * @throws ExceptionServiciosReporte 
     */
    @Test
    public void CERecursos1Test() throws ExceptionServiciosReporte,SQLException{
        Connection conn=getConnection();
        Statement stmt=conn.createStatement(); 
        stmt.execute("INSERT INTO POSGRADO (id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2017-01-01', '2017-06-02' ,'2017-1' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018428, 'Sergio Chacon', 'sergio@correo.com',8115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',1,1018428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(1, '07:00:00','09:00:00', '2017-01-02', 'FGPR', 1 );");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(2, 'libro de economia 1',true, 1,1);");
        conn.close(); 
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec=report.consultarRecursosXperiodo("2017-1");
        assertTrue("Deberia haber un recurso del periodo 2017-1",1==rec.size());
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
    public void CFRecursos1Test() throws ExceptionServiciosReporte{
        assertTrue(true);     
    }
    
        
    @After
    public void tearDown() {
    }
}
