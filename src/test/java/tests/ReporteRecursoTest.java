package tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.eci.pdsw.posgrado.entities.Recurso;
import edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporteFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

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
        Statement stmt = getConnection().createStatement();
        stmt.execute("delete from RecursoClase");
        stmt.execute("delete from Recurso");
        stmt.execute("delete from Clase");
        stmt.execute("delete from MateriaCohorte");
        stmt.execute("delete from Profesor");
        stmt.execute("delete from Cohorte");
        stmt.execute("delete from Materia");
        stmt.execute("delete from Asignatura");
        stmt.execute("delete from Posgrado");
        stmt.execute("delete from Periodo");
        
        getConnection().commit();
        getConnection().close();
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
     * @throws java.sql.SQLException */
     
    @Test
    public void CERecursos1Test() throws ExceptionServiciosReporte,SQLException{
        Statement stmt=getConnection().createStatement(); 
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES (  'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES (  'FGPR',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(2,'15:00:00','17:00:00', '2017-01-01', 'FGPR',24,1418432);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible ,cantidad)  VALUES(1, 'libro de economia 1',true,10);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,cantidad)  VALUES(2, 'libro de economia 2',true,10);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(2,1,10);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(2,2,10);");
        getConnection().close();
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
     * @throws java.sql.SQLException */
     
    @Test
    public void CERecursos2Test() throws ExceptionServiciosReporte, SQLException{
        Statement stmt=getConnection().createStatement(); 
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES (  'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(2,'15:00:00','17:00:00', '2017-01-01', 'FGPR',24,1418432);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,cantidad)  VALUES(1, 'libro de economia 1',true,1);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible ,cantidad)  VALUES(2, 'libro de economia 4',true,1);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible ,cantidad)  VALUES(4, 'libro de economia 2',true,11);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible ,cantidad)  VALUES(3, 'libro de economia 3',true,10);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(1,1,10);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(1,2,10);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(2,4,10);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(1,3,10);");
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
     * @throws java.sql.SQLException */
     
    @Test
    public void CERecursos3Test() throws ExceptionServiciosReporte, SQLException{
        Statement stmt = getConnection().createStatement();
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(2, '15:00:00','17:00:00', '2017-01-01', 'FGPR',24,1418432);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,cantidad)  VALUES(1, 'libro de economia 1',true,40);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible ,cantidad)  VALUES(2, 'libro de economia 2',true,40);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(2,1,10);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(2,2,10);");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec=report.consultarRecursosXperiodo(report.obtenerPeriodos().get(0));
        assertEquals("Deberian devolver los recursos del periodo 2017-1",report.colsultarFechas(report.obtenerPeriodos().get(0)).get(0),report.colsultarFechas(report.obtenerPeriodos().get(0)).get(0));
    }
    
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Equivalencia
     * CE1: si no hay recursos asociados a el periodo elegino no deberia mostrar ningun recusro
     * @throws ExceptionServiciosReporte 
     * @throws java.sql.SQLException */
     
    @Test
    public void CERecursos5Test() throws ExceptionServiciosReporte, SQLException{
        Statement stmt = getConnection().createStatement();
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(2,'15:00:00','17:00:00', '2017-01-01', 'FGPR',24,1418432);");
        //stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'libro de economia 1',true, 1,1);");
        //stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(2, 'libro de economia 1',true, 1,1);");

        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Recurso> rec = report.consultarRecursosXperiodo(report.obtenerPeriodos().get(0));
        assertTrue("No deben aparecer recursos si no hay clases relacionadas con el periodo seleccionado", rec.isEmpty());
    }
    
    
    /**
     * Seleccion de peridodo academico para generar el reporte de recursos para 
     * un programa de posgrado
     * Clase de Equvalencia
     * CE4: 
     * @throws ExceptionServiciosReporte 
     * @throws java.sql.SQLException */
     
    @Test
    public void CERecursos4Test() throws ExceptionServiciosReporte, SQLException{
        Statement stmt=getConnection().createStatement(); 
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(2, '15:00:00','17:00:00', '2017-01-01', 'FGPR',24,1418432);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible ,cantidad)  VALUES(1, 'libro de economia 2',true,1);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,cantidad)  VALUES(2, 'libro de economia 3',true,1);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(1,1,10);");
        stmt.execute("INSERT INTO RecursoClase (clase_id,recurso_id,cantidadUSE)  VALUES(1,2,10);");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        assertTrue("No deben aparecer recursos de un periodo que no exista (2016-1)", report.consultarRecursosXperiodo("2016-1").isEmpty());
        
    }
   
    
    @After
    public void tearDown() {
    }
   
    
}
