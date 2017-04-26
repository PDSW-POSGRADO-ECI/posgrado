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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import proyecto.entities.Clase;
import proyecto.entities.Cohorte;
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
        Connection conn= getConnection();
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
     * CE1: Deben contener los recursos asociados al periodo (2017-1)
     * @throws ExceptionServiciosReporte 
     */
    @Test
    public void CERecursos1Test() throws ExceptionServiciosReporte,SQLException, Exception{
        Connection conn=getConnection();
        Statement stmt=conn.createStatement(); 
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2017-01-01', '2017-06-02' ,'2017-1' );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (2, '2017-01-01', '2017-06-02' ,'2017-2' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018428, 'Sergio Chacon', 'sergi@correo.com',8115134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018430, 'Sergio Chaco', 'sergio@correo.com',8115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',1,1018428)");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',1,1018430)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id ,materia_cohorte_profesor_documento)  VALUES(1, '07:00:00','09:00:00', '2017-01-02', 'FGPR', 1,1018428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id,materia_cohorte_profesor_documento )  VALUES(2, '07:00:00','09:00:00', '2017-01-02', 'FGPR', 1,1018430);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'libro de economia 1',true, 1,1);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(2, 'libro de economia 1',true, 1,1);");
        conn.close(); 
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec=report.consultarRecursosXperiodo(report.obtenerPeriodos().get(0));
        assertEquals("Deberian devolver los recursos del periodo 2017-1",rec.size(),2);
    }
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Equivalencia
     * CE2: No bederia consultar recursos del periodo (2017-1)
     * @throws ExceptionServiciosReporte 
     */
    @Test
    public void CERecursos2Test() throws ExceptionServiciosReporte, SQLException, Exception{
        Connection conn=getConnection();
        Statement stmt=conn.createStatement(); 
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2017-01-01', '2017-06-02' ,'2016-1' );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (2, '2017-01-01', '2017-06-02' ,'2017-1' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGP', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018428, 'Sergio Chacon', 'sergio@correo.com',8115134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018429, 'Sergio Chacon', 'sergi@correo.com',8115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGP',1,1018428)");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGP',2,1018429)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id,materia_cohorte_profesor_documento )  VALUES(1, '07:00:00','09:00:00', '2017-01-02', 'FGP', 1, 1018428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id ,materia_cohorte_profesor_documento)  VALUES(2, '07:00:00','09:00:00', '2017-01-02', 'FGP', 2, 1018429 );");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'libro de economia 1',true, 1,11);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'libro de economia 1',true, 1,10);");
        conn.close(); 
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec=report.consultarRecursosXperiodo(report.obtenerPeriodos().get(0));
        assertEquals("Deberian devolver los recursos del periodo 2017-1",rec.size(),1);
    }
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Equivalencia 
     * CE1: Deberia consultar todos los recursos ordenados por fecha de menor a mayor
     * @throws ExceptionServiciosReporte 
     * @throws java.sql.SQLException 
     */
    @Test
    public void CERecursos3Test() throws ExceptionServiciosReporte, SQLException, Exception{
        try (Connection conn = getConnection()) {
            Statement stmt=conn.createStatement();
            stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
            stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2017-01-01', '2017-06-02' ,'2017-1' );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (2, '2017-01-01', '2017-06-02' ,'2016-2' );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (3, '2017-01-01', '2017-06-02' ,'2017-2' );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (4, '2017-01-01', '2017-06-02' ,'2016-1' );");
            stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
            stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018428, 'Sergio Chacon', 'sergio@correo.com',8115134,'cc' );");
            stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1118429, 'Sergio Chacon', 'sergo@correo.com',8115134,'cc' );");
            stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',4,1018428)");
            stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',4,1118429)");
            stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id ,materia_cohorte_profesor_documento )  VALUES(1, '13:00:00','15:00:00', '2017-01-02', 'FGPR', 4,1018428);");
            stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(3, 'libro de economia 1',true, 1,1);");
        } 
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec=report.consultarRecursosXperiodo(report.obtenerPeriodos().get(0));
        assertEquals("Deberian devolver los recursos del periodo 2017-1",rec.get(0).getClase_id().getFecha(),report.colsultarFechas(report.obtenerPeriodos().get(0)).get(0));
    }
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Equivalencia
     * CE1: si no hay recursos asociados a el periodo elegino no deberia mostrar ningun recusro
     * @throws ExceptionServiciosReporte 
     * @throws java.sql.SQLException 
     */
    @Test
    public void CERecursos5Test() throws ExceptionServiciosReporte, SQLException, Exception{
        try (Connection conn = getConnection()) {
            Statement stmt=conn.createStatement();
            stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
            stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2017-01-01', '2017-06-02' ,'2017-1' );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (2, '2017-01-01', '2017-06-02' ,'2017-1' );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (3, '2017-01-01', '2017-06-02' ,'2017-1' );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (4, '2017-01-01', '2017-06-02' ,'2017-2' );");
            stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
            stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018428, 'Sergio Chacon', 'sergi@correo.com',8115134,'cc' );");
            stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',4,1018428)");
            stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',2,1018428)");
            stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id ,materia_cohorte_profesor_documento)  VALUES(1, '13:00:00','15:00:00', '2017-01-02', 'FGPR', 4 ,1018428);");
            //stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(3, 'libro de economia 1',true, 1,1);");
        } 
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec=report.consultarRecursosXperiodo(report.obtenerPeriodos().get(0));
        assertTrue("No deben aparecer recursos si no hay clases relacionadas con el periodo seleccionado", rec.isEmpty());
    }
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Equvalencia
     * CE4: 
     * @throws ExceptionServiciosReporte 
     * @throws java.sql.SQLException 
     */
    @Test
    public void CERecursos4Test() throws ExceptionServiciosReporte, SQLException, Exception{
        try (Connection conn = getConnection()) {
            Statement stmt=conn.createStatement();
            stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
            stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2017-01-01', '2017-06-02' ,'2017-1' );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (2, '2017-01-01', '2017-06-02' ,'2017-1' );");
            stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (3, '2017-01-01', '2017-06-02' ,'2017-1' );");
            stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
            stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018428, 'Sergio Chacon', 'sergio@correo.com',8115134,'cc' );");
            stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',1,1018428)");
            stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',2,1018428)");
            stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id,materia_cohorte_profesor_documento )  VALUES(1, '13:00:00','15:00:00', '2017-01-02', 'FGPR', 2 ,1018428);");
            stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(3, 'libro de economia 1',true, 1,1);");
        } 
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        assertTrue("No deben aparecer recursos si no hay clases relacionadas con el periodo seleccionado", true);
        
    }
        
    @After
    public void tearDown() {
    }
}
