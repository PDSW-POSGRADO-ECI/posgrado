/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dao;

/**
 *
 * @author Laura RB
 */
public class ExceptionPersistence extends Exception {

    /**
     * Crear una nueva istancia de ExceptionPersistence sin detalle
     * del mensaje.
     * @param message mensaje de error
     * @param cause  causa del error
     */
    public ExceptionPersistence(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor nueva istancia de  ExceptionPersistence con 
     * especificacion del mensaje
     *
     * @param msg mensaje en detalle.
     */
    public ExceptionPersistence(String msg) {
        super(msg);
    }
}
