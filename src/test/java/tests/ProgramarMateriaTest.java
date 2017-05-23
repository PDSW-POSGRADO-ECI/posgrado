/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import edu.eci.pdsw.posgrado.entities.Recurso;
import edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporteFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class ProgramarMateriaTest {

    public ProgramarMateriaTest() {
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
        stmt.execute("delete from profesorxhorario");
        stmt.execute("delete from Profesor");
        stmt.execute("delete from Horario");
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
     * @throws java.text.ParseException */
     
    @Test
    public void CF1TestProgramarMateria() throws ExceptionServiciosReporte, SQLException, ParseException{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Horario (id,hora_inicio,hora_fin,fecha)VALUES(1,'07:00:00','15:00:00','2017-09-09');");
        stmt.execute("INSERT INTO Horario (id,hora_inicio,hora_fin,fecha)VALUES(2,'07:00:00','15:00:00','2017-08-21');");
        stmt.execute("INSERT INTO Profesorxhorario (profesor_documento,horario_id)VALUES(1818428,1);");
        stmt.execute("INSERT INTO Profesorxhorario (profesor_documento,horario_id)VALUES(1818428,2);");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");DateFormat fh = new SimpleDateFormat("HH:mm");
        Date date = ff.parse("2017-08-21");Time hi=new java.sql.Time(fh.parse("08:00:00").getTime());Time hf=new java.sql.Time(fh.parse("13:00:00").getTime());
        report.registrarClase(4, "Fundamentos de Finanzas.",date ,hi,hf , "Sergio Chacon", "2017-2");
        assertEquals("Deberian haber registrado una nueva clase",report.colsultarClaseXperiodo("2017-2").size(),1);  
    }
    
    
    /**
     * Clases no registradas
     * Clase Equivalencia
     * CE2: No debe registrar la clase porque el profesor no esta disponible en esas horas especificas.
     * @throws java.sql.SQLException
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     * @throws java.text.ParseException */
     
    @Test
    public void CF2TestProgramarMateria() throws SQLException, ExceptionServiciosReporte, ParseException{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Horario (id,hora_inicio,hora_fin,fecha)VALUES(1,'07:00:00','15:00:00','2017-09-09');");
        stmt.execute("INSERT INTO Profesorxhorario (profesor_documento,horario_id)VALUES(1818428,1);");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");DateFormat fh = new SimpleDateFormat("HH:mm");
        Date date = ff.parse("2017-08-21");Time hi=new java.sql.Time(fh.parse("08:00:00").getTime());Time hf=new java.sql.Time(fh.parse("13:00:00").getTime());
        assertTrue("No Deberian haber registrado una nueva clase",report.registrarClase(4, "Fundamentos de Finanzas.",date ,hi,hf , "Sergio Chacon", "2017-2").contains("Error"));
        assertEquals("No Deberian haber registrado una nueva clase",report.registrarClase(4, "Fundamentos de Finanzas.",date ,hi,hf , "Sergio Chacon", "2017-2"),"Error el profesor no tiene esa disponibilidad de horario");
    }
    
    
     /**
     * Clase Equivalencia
     * CE3: registrar recursos para una clase creada
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte */
    @Test
    public void CF3TestProgramarMateria() throws SQLException, ParseException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Horario (id,hora_inicio,hora_fin,fecha)VALUES(1,'07:00:00','15:00:00','2017-09-09');");
        stmt.execute("INSERT INTO Profesorxhorario (profesor_documento,horario_id)VALUES(1818428,1);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,cantidad)  VALUES(1, 'libro de economia 2',true,10);");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");DateFormat fh = new SimpleDateFormat("HH:mm");
        Date date = ff.parse("2017-09-09");Time hi=new java.sql.Time(fh.parse("08:00:00").getTime());Time hf=new java.sql.Time(fh.parse("13:00:00").getTime());
        report.registrarClase(4, "Fundamentos de Finanzas.",date ,hi,hf , "Sergio Chacon", "2017-2");
        ArrayList<Recurso> rec=new ArrayList<>();
        rec.add(new Recurso(1,"libro de economia 2",true,10));
        assertEquals("Deberian haber registrado recursos a clase creada",report.registrarRecursoClase(rec),"Recursos registrados satisfactoriamente");    
    }
    
    /**
     * recursos no registrados
     * Clase Equivalencia
     * CE4: registrar recursos todos los recursos seleccionados para una clase creada
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte */
    @Test
    public void CF4TestProgramarMateria() throws SQLException, ParseException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Horario (id,hora_inicio,hora_fin,fecha)VALUES(1,'07:00:00','15:00:00','2017-09-09');");
        stmt.execute("INSERT INTO Profesorxhorario (profesor_documento,horario_id)VALUES(1818428,1);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,cantidad)  VALUES(1, 'libro de economia 2',true,10);");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");DateFormat fh = new SimpleDateFormat("HH:mm");
        Date date = ff.parse("2017-09-09");Time hi=new java.sql.Time(fh.parse("08:00:00").getTime());Time hf=new java.sql.Time(fh.parse("13:00:00").getTime());
        report.registrarClase(4, "Fundamentos de Finanzas.",date ,hi,hf , "Sergio Chacon", "2017-2");
        List<Recurso> rec=report.consultarAllRecursos();
        assertEquals("Deberian haber registrado recursos a clase creada",report.registrarRecursoClase(rec),"Recursos registrados satisfactoriamente");    
    }
    
     /**
     * Clase Equivalencia
     * CE5: Deben registrar una nueva materiaCohorte
     * @throws java.sql.SQLException
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     * @throws java.text.ParseException */
     
    @Test
    public void CF5TestProgramarMateria() throws SQLException, ExceptionServiciosReporte, ParseException{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO Horario (id,hora_inicio,hora_fin,fecha)VALUES(1,'07:00:00','15:00:00','2017-09-09');");
        stmt.execute("INSERT INTO Profesorxhorario (profesor_documento,horario_id)VALUES(1818428,1);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,cantidad)  VALUES(1, 'libro de economia 2',true,10);");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        assertEquals("Deben registrar una nueva materiaCohorte",
                report.registrarMateriaCohorte(report.colsultarProfesores().get(0).getNombre(),4 ,report.obtenerPeriodos().get(0), report.consultarMaterias().get(0).getNombre()),
                "Cohorte Agregado");  
    }
    
    
    /**
     * Profesor registrado en cohorte
     * Clase Equivalencia
     * CE6: no Debe egistrar una nueva MateriaCohorte que ya existe
     * @throws java.sql.SQLException
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte */
     
    @Test
    public void CF6TestProgramarMateria() throws SQLException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Horario (id,hora_inicio,hora_fin,fecha)VALUES(1,'07:00:00','15:00:00','2017-09-09');");
        stmt.execute("INSERT INTO Profesorxhorario (profesor_documento,horario_id)VALUES(1818428,1);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,cantidad)  VALUES(1, 'libro de economia 2',true,10);");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        assertEquals("Deberian haber registrado recursos a clase creada",
                report.registrarMateriaCohorte(report.colsultarProfesores().get(0).getNombre(),4 ,report.obtenerPeriodos().get(0), report.consultarMaterias().get(0).getNombre()),
                "Error ya se ecuentra registrado este cohorte para la materia y profesor seleccionados");  
    }
    
    
    /**
     * nuevo periodo
     * Clase Equivalencia
     * CE7: Deberia registrar un nuevo periodo academico
     * @throws java.sql.SQLException */

    @Test
    public void CF7TestProgramarMateria() throws SQLException, ParseException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
       stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO Horario (id,hora_inicio,hora_fin,fecha)VALUES(1,'07:00:00','15:00:00','2017-09-09');");
        stmt.execute("INSERT INTO Profesorxhorario (profesor_documento,horario_id)VALUES(1818428,1);");
        stmt.execute("INSERT INTO Recurso (id,recurso,disponible,cantidad)  VALUES(1, 'libro de economia 2',true,10);");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ff.parse("2017-01-16");
        Date datee=ff.parse("2017-05-20");
        assertEquals("Deberian haber registrado un nuevo periodo",report.registrarPeriodo("2017-1", date, datee),"Periodo Agregado");
        assertEquals("Deberian haber registrado un nuevo periodo",report.obtenerPeriodos().size(),2);
    }
    
    /**
     * nuevo periodo
     * Clase Equivalencia
     * CE8: No Deberia registrar un nuevo periodo academico si ya existe
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte */

    @Test
    public void CF8TestProgramarMateria() throws SQLException, ParseException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
       stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ff.parse("2017-01-16");
        Date datee=ff.parse("2017-05-20");
        assertEquals("Deberian haber registrado un nuevo periodo",report.registrarPeriodo("2017-2", date, datee),"Error el Periodo esta duplicado");
        assertEquals("Deberian haber registrado un nuevo periodo",report.obtenerPeriodos().size(),1);
    }
    
    /**
     * nuevo periodo
     * Clase Equivalencia
     * CE9: No Deberia registrar un nuevo periodo academico si las fechas son inconcistentes
     * @throws java.sql.SQLException */

    @Test
    public void CF9TestProgramarMateria() throws SQLException, ParseException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ff.parse("2017-05-16");
        Date datee=ff.parse("2017-01-20");
        assertEquals("Deberian haber registrado un nuevo periodo",report.registrarPeriodo("2017-1", date, datee),"Error La fecha inicial es mayor que la fecha de terminacion");
        assertEquals("Deberian haber registrado un nuevo periodo",report.obtenerPeriodos().size(),1);
    }
    
    /**
     * nuevo psogrado
     * Clase Equivalencia
     * CE1: Deberia registrar un nuevo posgrado
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte */

    @Test
    public void CF10TestProgramarMateria() throws SQLException, ParseException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(2, 'Economias',100 );");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        report.registrarPosgrado("Nuevo Posgrado",56);
        assertEquals("Deberian haber registrado un nuevo posgrado",report.consultarNombresPosgrado().get(0),"Nuevo Posgrado");
    }
    
    /**
     * nuevo psogrado
     * Clase Equivalencia
     * CE1: No Deberia registrar un posgrado si ya existe
     * @throws java.sql.SQLException */

    @Test
    public void CF11TestProgramarMateria() throws SQLException, ParseException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(2, 'Economias',100 );");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        report.registrarPosgrado("Economias",56);
        assertEquals("No Deberian haber registrado un posgrado existente",report.consultarNombresPosgrado().size(),1);
    }
    
    /**
     * nueva asignatura
     * Clase Equivalencia
     * CE1: No Deberia registrar una asignatura existente
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte */

    @Test
    public void CF12TestProgramarMateria() throws SQLException, ParseException, ExceptionServiciosReporte{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        report.registrarAsignatura("Propuesta Elementales", "Economias");
        assertEquals("Deberian haber registrado una asignatura nueva",report.consultarNombresAsignaturas().size(),1);
    }
}
