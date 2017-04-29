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
import org.junit.Before;

/**
 *
 * @author Laura RB
 */
public class RegistrarMateria {

    public RegistrarMateria() {
    }
     @Before
    public void setUp() {
    }
    @After 
    public void clearDB() throws SQLException, Exception{
        Statement stmt = getConnection().createStatement();
        stmt.execute("delete from Recurso");
        stmt.execute("delete from Clase");
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
}
