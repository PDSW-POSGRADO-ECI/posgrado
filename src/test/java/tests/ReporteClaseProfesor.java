/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import edu.eci.pdsw.posgrado.entities.Clase;
import edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporteFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Laura RB
 */
public class ReporteClaseProfesor {
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
     * Clase Profesor Por semestre
     * CE1: Deberia mostrar todas las clases del profesor en el semestre seleccionado
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte
     * @throws java.sql.SQLException
     */
    @Test
    public void CE1TestReporteClaseProfesor() throws ExceptionServiciosReporte, SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'CONC',24,1418432)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-01', 'CONC', 24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(2, '15:00:00','17:00:00', '2017-01-02', 'CONC',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(3, '15:00:00','17:00:00', '2017-01-04', 'CONC',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(4, '15:00:00','17:00:00', '2017-01-05', 'CONC',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(5, '15:00:00','17:00:00', '2017-01-06', 'CONC',24,1418432);");

        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Clase> c=report.consultarHorarioClaseProfesor(report.colsultarProfesores().get(0).getNombre(),report.obtenerPeriodos().get(0));
        assertEquals("Deberia mostrar todas las clases del profesor en el semestre",c.size(),5);
    }
    /**
     *
     * Clase Profesor Por semestre
     * CE2: No Deberia mostrar las clases del profesor de otro semestre
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte 
     * @throws java.sql.SQLException 
     * @throws java.text.ParseException 
     */
    @Test
    public void CE2TestReporteClaseProfesor() throws ExceptionServiciosReporte, SQLException, ParseException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2016-1' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'CONC',24,1418432)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-01', 'CONC', 24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(2, '15:00:00','17:00:00', '2017-01-02', 'CONC',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(3, '15:00:00','17:00:00', '2017-01-04', 'CONC',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(4, '15:00:00','17:00:00', '2017-01-05', 'CONC',24,1418432);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(5, '15:00:00','17:00:00', '2017-01-06', 'CONC',24,1418432);");

        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Clase> c=report.consultarHorarioClaseProfesor(report.colsultarProfesores().get(0).getNombre(),report.obtenerPeriodos().get(1));
        assertEquals("No Deberia mostrar las clases del profesor de otro semestre",c.size(),0);
    }
    
    /**
     *
     * Clase Profesor Por semana
     * CE3: Deberia mostrar las clases que tenga en los proximos 7 dias del semestre seleccionado
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte 
     * @throws java.sql.SQLException 
     * @throws java.text.ParseException 
     */
    @Test
    public void CE3TestReporteClaseProfesor() throws ExceptionServiciosReporte, SQLException, ParseException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2016-01-01', '2016-05-22' ,'2016-1' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2016-1');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'CONC',24,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(2, '15:00:00','17:00:00', '2017-01-01', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(3, '15:00:00','17:00:00', '2017-01-02', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(4, '15:00:00','17:00:00', '2017-01-03', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(5, '15:00:00','17:00:00', '2017-01-04', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(6, '15:00:00','17:00:00', '2017-01-05', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(7, '15:00:00','17:00:00', '2017-01-06', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(8, '15:00:00','17:00:00', '2017-01-07', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(9, '15:00:00','17:00:00', '2017-01-08', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(10, '15:00:00','17:00:00', '2017-01-09', 'CONC',24,1818428);");
        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");Date date = ff.parse("2017-01-01");
        ArrayList<Clase> c=report.consultarHorarioClaseProfesorSemana(report.colsultarProfesores().get(0).getNombre(),report.obtenerPeriodos().get(0),date);
        List<Clase> c2=report.consultarHorarioClaseProfesor(report.colsultarProfesores().get(0).getNombre(),report.obtenerPeriodos().get(0));
        assertEquals("Deberia mostrar las clases del profesor del semestre seleccionado"+report.obtenerPeriodos().get(0),c2.size(),9);
        assertEquals("Deberia mostrar las clases del profesor los proximos 7 dias del semestre seleccionado",c.size(),7);
    }
    /**
     *
     * Clase Profesor Por semana
     * CE4: No Deberia mostrar las clases que tenga depues de esta semana en el semetsre seleccionado
     * @throws edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte 
     * @throws java.sql.SQLException 
     * @throws java.text.ParseException 
     */
    @Test
    public void CE4TestReporteClaseProfesor() throws ExceptionServiciosReporte, SQLException, ParseException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'CONC', 'Control conjunto',2,1,'Descripcion: Control conjunto' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1818428)");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'CONC',24,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(2, '15:00:00','17:00:00', '2017-01-01', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(9, '15:00:00','17:00:00', '2017-01-08', 'CONC',24,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(10, '15:00:00','17:00:00', '2017-01-09', 'CONC',24,1818428);");
        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        DateFormat ff = new SimpleDateFormat("yyyy-MM-dd");Date date = ff.parse("2017-01-01");
        ArrayList<Clase> c=report.consultarHorarioClaseProfesorSemana(report.colsultarProfesores().get(0).getNombre(),report.obtenerPeriodos().get(0),date);
        List<Clase> c2=report.consultarHorarioClaseProfesor(report.colsultarProfesores().get(0).getNombre(),report.obtenerPeriodos().get(0));
        assertEquals("Deberia mostrar las clases del profesor del semestre seleccionado"+report.obtenerPeriodos().get(0),c2.size(),3);
        assertEquals("No Deberia mostrar las clases del profesor que estan depues d elos 7 dias",c.size(),1);
    }
}
