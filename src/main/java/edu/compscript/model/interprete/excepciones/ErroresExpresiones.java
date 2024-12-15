package edu.compscript.model.interprete.excepciones;

import lombok.AllArgsConstructor;

/**
 * Esta es la clase para generar errores.
 */
@AllArgsConstructor
public class ErroresExpresiones {
    private String tipo;
    private String descripcion;
    private int linea;
    private int columna;
}
