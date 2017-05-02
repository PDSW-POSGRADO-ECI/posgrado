/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import proyecto.services.ExceptionServiciosReporte;
import proyecto.services.ServiciosReporte;
import proyecto.services.ServiciosReporteFactory;

/**
 *
 * @author Laura RB
 */
public class ProgramarMateriaTest {

    public ProgramarMateriaTest() {
    }
    
    @Before
    public void setUp() {
    }
    @After 
    public void clearDB() throws SQLException, Exception{
        Statement stmt = getConnection().createStatement();
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
     * Clase registrada
     * Clase Equivalencia
     * CE1: Debe regiastrar clase  de un corte de un periodo con las horas y fechas especificadas 
     * @throws ExceptionServiciosReporte 
     * @throws java.sql.SQLException 
     
    @Test
    public void CF1TestProgramarMateria() throws ExceptionServiciosReporte, SQLException{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        report.registrarClase(24, "2017-1", new java.sql.Date(2017,06,01),  new java.sql.Time(7), new java.sql.Time(10),1418432);
        assertEquals("Deberian haber registrado una nueva clase",report.colsultarClaseXperiodo("2017-1").size(),1);  
    }
    */
    
    /**
     * Clases no registradas
     * Clase Equivalencia
     * CE1: No debe registrar la clase porque el profesor no esta disponible en esas horas especificas.
     * @throws java.sql.SQLException 
     
    @Test
    public void CF2TestProgramarMateria() throws SQLException{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        int documento=1418432;
        try{
            report.registrarClase(4, "2017-2", new java.sql.Date(2017,06,01),  new java.sql.Time(7), new java.sql.Time(10),documento);
            assertTrue("",report.colsultarClaseXperiodo("2017-2").size()==1);
            }
        catch(ExceptionServiciosReporte e){
            assertEquals("No deberia registrar la clase del profesor"
                    + "porque no tiene esa disponibilidad de horario",e,"error al registrar clase con el profesor "+documento);  
        }
        
    }
    */
    
      /**
     * recursos no registrados
     * Clase Equivalencia
     * CE1: No debe registrar recursos a una clase si no esta disponible
     * @throws java.sql.SQLException 
    @Test
    public void CF3TestProgramarMateria() throws SQLException{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'computador portatil',false, 1,1);");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        String nombreRecurso="computador portatil";
        try{
            report.registrarRecurso(1,nombreRecurso);
            assertTrue("",report.consultarRecursosXperiodo("2017-2").size()==1);
            }
        catch(ExceptionServiciosReporte e){
            assertEquals("No deberia registrar recursos a una clase"
                    + "porque no esta disponible",e,"Error al registrar recurso"+nombreRecurso);  
        }
        
    }
    */
    
     /**
     * registrar recursos
     * Clase Equivalencia
     * CE1: Deben registrar los recursos a la clase selccionada
     * @throws java.sql.SQLException 
     
    @Test
    public void CF4TestProgramarMateria() throws SQLException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'computador portatil',false, 1,1);");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        String nombreRecurso="router de alta velocidad";
        report.registrarRecurso(1, nombreRecurso);
        assertEquals("Deberian haber registrado el recurso a la clase",report.consultarRecursosXperiodo("2017-2").size(),2);
        
    }
    */
    
    /**
     * Profesor registrado en cohorte
     * Clase Equivalencia
     * CE1: Debe egistrar una nueva MateriaCohorte para un profesor
     * @throws java.sql.SQLException 
     
    @Test
    public void CF5TestProgramarMateria() throws SQLException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'computador portatil',false, 1,1);");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        String nombre="Sergio Chacon";
        int doc=1418432;
        report.registrarMateriaCohorte(doc, doc, nombre, nombre);
        assertEquals("Deberian haber registrado una nueva MateriaCohorte para"
                + "el profesor"+doc +nombre,report.consultarMateriaCohorte(1418432,25,"FDF"),true);
        
    }
    */
    
    /**
     * Profesor no registrado en cohorte
     * Clase Equivalencia
     * CE1: No debe registrar profesor en una cohorte si ya existe
     * @throws java.sql.SQLException 

    @Test
    public void CF6TestProgramarMateria() throws SQLException{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiacohorte_materia_sigla,materiacohorte_cohorte_id ,materiacohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,clase_id ,cantidad)  VALUES(1, 'computador portatil',false, 1,1);");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        int doc=1418432;
        try{
            report.registrarMateriaCohorte(doc,25,"2017-1","FDF");
            }
        catch(ExceptionServiciosReporte e){
            assertEquals("No deberia registrar profesor en una cohorte"
                    + "porque no esta disponible",e,"Error al registrar Cohorte del profesor"+doc);  
        }
    }
    */
}
