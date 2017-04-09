/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.services;

/**
 *
 * @author Laura RB
 */
public class ExceptionServiciosReporte extends Exception {

    /**
     * Crear una nueva istancia de ExceptionServiciosReporte sin detalle
     * del mensaje.
     */
    public ExceptionServiciosReporte() {
    }

    /**
     * Construir una istancion de ExceptionServiciosReporte con una especificacion
     * del mensaje
     * @param msg mensaje en detalle.
     */
    public ExceptionServiciosReporte(String msg) {
        super(msg);
    }
    
    /**
     * Crear una nueva istancia de ExceptionServiciosReporte sin detalle
     * del mensaje.
     * @param message mensaje de error
     * @param cause  causa del error
     */
    public ExceptionServiciosReporte(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * Construir una istancion de ExceptionServiciosReporte con una especificacion
     * de  la causa
     * @param cause causa del error
     */
    public ExceptionServiciosReporte(Throwable cause) {
        super(cause);
    }
    
}
