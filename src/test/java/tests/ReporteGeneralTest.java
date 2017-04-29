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
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import proyecto.entities.Materia;
import proyecto.entities.Profesor;
import proyecto.services.ExceptionServiciosReporte;
import proyecto.services.ServiciosReporte;
import proyecto.services.ServiciosReporteFactory;

/**
 *
 * @author Laura RB
 *
 * /**
 *
 * Reporte General:
 *
 * Frontera: CF1: Si no se ha agregado nada a la base de datos volatil, esta
 * debe quedar completamente en blanco. Clases de equivalencia: CE1: Si agrego 2
 * Materias con el mismo cohorte y en el mismo periodo, estas deberian aparecer
 * Clases de equivalencia: CE2: Si agrego 1 materia la cual ya tiene un profesor
 * destinado para ese cohorte, este profesor deberia aparecer
 *
 *
 *
 */
public class ReporteGeneralTest {

    public ReporteGeneralTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void clearDB() throws SQLException, Exception {
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:./target/db/testdb;MODE=PostgreSQL", "anonymous", "");
    }

    @Test
    public void CF1TestReporteGeneral() throws ExceptionServiciosReporte, SQLException {
        Connection conn = getConnection();
        conn.close();
        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Materia> mat = report.consultarMaterias();
        assertTrue("No deberia haber nada en la base de datos", 0 == mat.size());
    }

    @Test
    public void CE1TestReporteGeneral() throws ExceptionServiciosReporte, SQLException {
        Statement stmt = getConnection().createStatement();
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
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'CONC',24,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(2, '15:00:00','17:00:00', '2017-01-01', 'CONC',24,1818428);");

        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Materia> mat = report.consultarMaterias();
        assertTrue("Deberian haber 2 Materias", 2 == mat.size());
    }

    @Test
    public void CE2TestReporteGeneral() throws ExceptionServiciosReporte, SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1);");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-08-08', '2017-12-17' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR', 'fundamentos gerenciales',1,1,'fundamentos de gerencia de proyectos para empresarios' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FGPR2', 'fundamentos gerenciales 2',1,1,'fundamentos de gerencia de proyectos para empresarios nivel 2' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR',4,1818428)");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FGPR2',4,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(1,'7:00:00','13:00:00 ', '2017-01-02', 'FGPR', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(2, '15:00:00 ','17:00:00 ', '2017-01-01', 'FGPR2',4,1818428);");
        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Materia> materia = report.consultarMaterias();
        Profesor profesor = report.consultarProfesor(4, materia.get(0).getSigla());
        assertEquals("", "Sergio Chacon", profesor.getNombre());
    }

    @After
    public void tearDown() {
    }
}
