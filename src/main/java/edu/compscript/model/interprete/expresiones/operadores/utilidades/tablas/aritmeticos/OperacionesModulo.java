package edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos;

import edu.compscript.model.interprete.simbolo.TipoDato;

/**
 * Clase que contiene de manera estática la tabla con las combinaciones correctas para el módulo.
 */
public class OperacionesModulo {
    /**
     * Tabla que define las combinaciones válidas para la operación módulo.
     */
    public static final TipoDato[][] tablaDeModulo = {
            //ENTERO, DECIMAL
            {TipoDato.DECIMAL,   TipoDato.DECIMAL}, //ENTERO
            {TipoDato.DECIMAL,   TipoDato.DECIMAL}, //DECIMAL
    };
}
