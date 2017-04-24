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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
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
     * CE1: Recursos del mismo perirodo seleccionado.
     * @throws ExceptionServiciosReporte 
     */
    @Test
    public void CERecursos1Test() throws ExceptionServiciosReporte,SQLException, Exception{
        Connection conn=getConnection();
        Statement stmt=conn.createStatement(); 
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2017-01-01', '2017-06-02' ,'2017-1' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018428, 'Sergio Chacon', 'sergio@correo.com',8115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',1,1018428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(1, '07:00:00','09:00:00', '2017-01-02', 'FGPR', 1 );");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'libro de economia 1',true, 1,1);");
        conn.close(); 
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec=report.consultarRecursosXperiodo("2017-1");
        assertTrue("Deberia haber un recurso del periodo 2017-1",1==rec.size());
    }
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Equivalencia
     * CE2: No bede consultar recursos de un periodo academico diferente al seleccionado.
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
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGP',1,1018428)");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGP',2,1018428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(1, '07:00:00','09:00:00', '2017-01-02', 'FGP', 1 );");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'libro de economia 1',true, 1,11);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(2, '07:00:00','09:00:00', '2017-01-02', 'FGP', 2 );");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'libro de economia 1',true, 1,10);");
        conn.close(); 
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec=report.consultarRecursosXperiodo("2016-1");
        assertTrue("No deberia haber clases con un recurso del periodo 2017-1", 2!=rec.get(0).getClase_id().getId());
    }
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Frontera
     * CE1: Deberia consultar todos los periodos diferentes que existen en cohorte ordenados
     * @throws ExceptionServiciosReporte 
     */
    @Test
    public void CERecursos3Test() throws ExceptionServiciosReporte, SQLException, Exception{
        Connection conn=getConnection();
        Statement stmt=conn.createStatement(); 
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2017-01-01', '2017-06-02' ,'2017-1' );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (2, '2017-01-01', '2017-06-02' ,'2016-2' );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (3, '2017-01-01', '2017-06-02' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (4, '2017-01-01', '2017-06-02' ,'2016-1' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018428, 'Sergio Chacon', 'sergio@correo.com',8115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',1,1018428)");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',2,1018428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(1, '13:00:00','15:00:00', '2017-01-02', 'FGPR', 2 );");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(3, 'libro de economia 1',true, 1,1);");
        conn.close(); 
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Cohorte> rec=report.obtenerPeriodos();
        assertTrue("Deberia consultar todos los periodos diferentes que existen",rec.size()==4);
    }
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Frontera
     * CE1: No deben aparecer recursos si no hay clases relacionadas con el periodo seleccionado
     * @throws ExceptionServiciosReporte 
     */
    @Test
    public void CERecursos5Test() throws ExceptionServiciosReporte, SQLException, Exception{
        Connection conn=getConnection();
        Statement stmt=conn.createStatement(); 
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (1, '2017-01-01', '2017-06-02' ,'2017-1' );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (2, '2017-01-01', '2017-06-02' ,'2017-1' );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (3, '2017-01-01', '2017-06-02' ,'2017-1' );");
        stmt.execute("INSERT INTO Cohorte (id, fecha_inicio,fecha_fin,periodo )  VALUES (4, '2017-01-01', '2017-06-02' ,'2017-2' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1018428, 'Sergio Chacon', 'sergio@correo.com',8115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',4,1018428)");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',2,1018428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(1, '13:00:00','15:00:00', '2017-01-02', 'FGPR', 4 );");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(3, 'libro de economia 1',true, 1,1);");
        conn.close(); 
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec=report.consultarRecursosXperiodo("2017-1");
        assertTrue("No deben aparecer recursos si no hay clases relacionadas con el periodo seleccionado",rec.size()==0);
    }
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Frontera
     * CE4: NO Deberia consultar cohortes con periodos reptidos
     * @throws ExceptionServiciosReporte 
     */
    @Test
    public void CERecursos4Test() throws ExceptionServiciosReporte, SQLException, Exception{
        Connection conn=getConnection();
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
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materia_cohorte_materia_sigla,materia_cohorte_cohorte_id )  VALUES(1, '13:00:00','15:00:00', '2017-01-02', 'FGPR', 2 );");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(3, 'libro de economia 1',true, 1,1);");
        conn.close(); 
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Cohorte> rec=report.obtenerPeriodos();
        assertTrue("No deberian devolver los periodos repetidos de cohorte solo debe aparecer una existencia de cada periodo"
                + "este caso solo una existencia de 2017",rec.size()==1 && "2017-1".equals(rec.get(0).getPeriodo()));
    }
        
    @After
    public void tearDown() {
    }
}
