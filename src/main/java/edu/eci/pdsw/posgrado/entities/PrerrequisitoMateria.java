/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.entities;

import java.io.Serializable;

/**
 *
 * @author Daniel Rodriguez
 */
public class PrerrequisitoMateria implements Serializable{
    
    private Materia materia_sigla;
    private Materia prerrequisito_sigla;
    private boolean correquisito;

    
    /**
     * Constructor Prerrequisito materia
     */
    public PrerrequisitoMateria() {
    }
    
    

    /**
     * Constructor Prerrequisito materia 2
     * @param materia_sigla
     * @param prerrequisito_sigla
     * @param correquisito 
     */
    public PrerrequisitoMateria(Materia materia_sigla, Materia prerrequisito_sigla, boolean correquisito) {
        this.materia_sigla = materia_sigla;
        this.prerrequisito_sigla = prerrequisito_sigla;
        this.correquisito = correquisito;
    }

    public Materia getMateria_sigla() {    
        return materia_sigla;
    }

    public void setMateria_sigla(Materia materia_sigla) {
        this.materia_sigla = materia_sigla;
    }

    public Materia getPrerrequisito_sigla() {
        return prerrequisito_sigla;
    }

    /**
     * Obtiene la sigla de la materia
     * @return  materia_sigla
     */
    public void setPrerrequisito_sigla(Materia prerrequisito_sigla) {    
        this.prerrequisito_sigla = prerrequisito_sigla;
    }

    /**
     * Comprueba si es coorequisito
     * @return 
     */
    public boolean isCorrequisito() {
        return correquisito;
    }

    /**
     * Establlece si es correquisito
     * @param correquisito 
     */
    public void setCorrequisito(boolean correquisito) {
        this.correquisito = correquisito;
    }
    
    
    
}
