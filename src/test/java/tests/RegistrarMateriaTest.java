/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import edu.eci.pdsw.posgrado.entities.Materia;
import edu.eci.pdsw.posgrado.services.ExceptionServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporte;
import edu.eci.pdsw.posgrado.services.ServiciosReporteFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Daniel Rodriguez 
 * 
 * CF1: Inicialmente no deberia haber una Materia Registrada
 * CE1: Si se registra una materia, esta deberia aparecer 
 * CE2: Si la materia tiene un prerrequisito esta se deberia comprobar
 * CE3: Si se asigna la materia a un periodo especifico y una asignatura, estos se deberian comprobar
 * CF2: Si hay 2 materias registradas para el mismo periodo, estas se deben mostrar
 */
public class RegistrarMateriaTest {

    public RegistrarMateriaTest() {
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
        stmt.execute("delete from PrerequisitoMateria");
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
    
    @Test
    public void CF1TestRegistrarMateria() throws ExceptionServiciosReporte, SQLException {
        Connection conn = getConnection();
        conn.close();
        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Materia> mat = report.consultarMaterias();
        assertTrue("No deberia haber nada en la base de datos", 0 == mat.size());
    }
    
    /**
     * Si se registra una materia, esta deberia aparecer 
     * @throws ExceptionServiciosReporte
     * @throws SQLException 
    */
    @Test
    public void CE1TestRegistrarMateria() throws ExceptionServiciosReporte, SQLException{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        report.registrarMateria("FDF",  "Fundamentos de Finanzas.",2,"Propuesta Elementales","Fundamentos de Finanzas.");
        List<Materia>materias=report.consultarMaterias();
        assertTrue("Deberian haber registrado una nueva Materia" , materias.size()==1);  
    }
    
    
    
    /**
     * Si la materia tiene un prerrequisito esta se deberia comprobar (HAY UN ERROR)
     * @throws ExceptionServiciosReporte
     * @throws SQLException 
    */
    @Test
    public void CE2TestRegistrarMateria() throws ExceptionServiciosReporte, SQLException{
        
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
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'CONC',24,1818428)");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(1,'7:00:00','10:00:00', '2017-01-02', 'FDF', 4,1818428);");
        stmt.execute("INSERT INTO Clase (id,hora_inicio,hora_fin,fecha,materiaCohorte_materia_sigla,materiaCohorte_cohorte_id ,materiaCohorte_profesor_documento)  VALUES(2, '15:00:00','17:00:00', '2017-01-01', 'CONC',24,1818428);");
        
        stmt.execute("INSERT INTO PrerequisitoMateria (materia_sigla, prerrequisito_sigla,correquisito) VALUES ('CONC','FDF',FALSE)");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Materia> res=report.loadPrerrequisitosMateria("CONC");
        assertTrue("No deberia tener prerrequisito",res.size()==1);  
    }
    
    
    /**
     * Si se asigna la materia a un periodo especifico y una asignatura, estos se deberian comprobar
     * @throws ExceptionServiciosReporte
     * @throws SQLException 
     */
    @Test
    public void CE3TestRegistrarMateria() throws ExceptionServiciosReporte, SQLException{
        Statement stmt=getConnection().createStatement();  
        stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-1' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (24,'2017-1');");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1818428, 'Sergio Chacon', 'sergio@coreo.com',3115134,'cc' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',24,1818428)");
        
        ServiciosReporte report=ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        String asig="Propuesta Elementales";
        List<String>res=report.consultarNombresMaterias(asig);
        assertTrue("Deberia haber una materia en la asignatura", res.size()==1);
        
        
    }
    
    
    
    /**
     * Si hay 2 materias registradas para el mismo periodo, estas se deben mostrar
     * @throws ExceptionServiciosReporte
     * @throws SQLException 
     */
    @Test
    public void CF2TestRegistrarMateria() throws ExceptionServiciosReporte, SQLException {

        Statement stmt=getConnection().createStatement();  stmt.execute("INSERT INTO Posgrado(id, nombre,creditos )  VALUES(1, 'Economias',100 );");
        stmt.execute("INSERT INTO Asignatura (id, nombre,posgrado_id )  VALUES(1, 'Propuesta Elementales',1 );");
        stmt.execute("INSERT INTO Periodo (fecha_inicio,fecha_fin,periodo )  VALUES ('2017-01-01', '2017-05-22' ,'2017-2' );");
        stmt.execute("INSERT INTO Cohorte (id, periodo_periodo )  VALUES (4,'2017-2');");
        stmt.execute("INSERT INTO Profesor (documento, nombre,correo,telefono,tipo_documento )  VALUES (1418432, 'Crisrtian Alba', 'sergo@correo.com',8165134,'cc' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF', 'Fundamentos de Finanzas.',2,1,'Fundamentos de Finanzas.' );");
        stmt.execute("INSERT INTO Materia (sigla, nombre,creditos,asignatura_id,descripcion )  VALUES ( 'FDF2', 'Fundamentos de Finanzas 2.',2,1,'Fundamentos de Finanzas 2.' );");
        stmt.execute("INSERT INTO MateriaCohorte (materia_sigla, cohorte_id,profesor_documento) VALUES ( 'FDF',4,1418432)");
        
        ServiciosReporte report = ServiciosReporteFactory.getInstance().getServiciosReporteForTesting();
        List<Materia> mat = report.consultarMaterias();
        assertTrue("Deberian haber 2 materias Registradas", 2 == mat.size());
    }
    
    
      
}
