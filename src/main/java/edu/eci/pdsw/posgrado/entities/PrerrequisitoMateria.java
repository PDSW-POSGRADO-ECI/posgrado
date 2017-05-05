/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.posgrado.entities;

/**
 *
 * @author Daniel Rodriguez
 */
public class PrerrequisitoMateria {
    
    private String materia_sigla;
    private String prerrequisito_sigla;
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
    public PrerrequisitoMateria(String materia_sigla, String prerrequisito_sigla, boolean correquisito) {
        this.materia_sigla = materia_sigla;
        this.prerrequisito_sigla = prerrequisito_sigla;
        this.correquisito = correquisito;
    }

    
    /**
     * Obtiene la sigla de la materia
     * @return  materia_sigla
     */
    public String getMateria_sigla() {
        return materia_sigla;
    }

    
    /**
     * Establece la sigla de la materia
     * @param materia_sigla 
     */
    public void setMateria_sigla(String materia_sigla) {
        this.materia_sigla = materia_sigla;
    }

    
    /**
     * Obtiene la sigla del prerrequisito de la materia
     * @return 
     */
    public String getPrerrequisito_sigla() {
        return prerrequisito_sigla;
    }
    

    /**
     * Establece la sigla del prerrequisito de la materia
     * @param prerrequisito_sigla 
     */
    public void setPrerrequisito_sigla(String prerrequisito_sigla) {
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
