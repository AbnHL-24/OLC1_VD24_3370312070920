package edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos;

import edu.compscript.model.interprete.simbolo.TipoDato;

/**
 * Clase que contiene de manera estática la tabla con las combinaciones correctas para la potencia.
 */
public class OperacionesPotencia {
    /**
     * Tabla que define las combinaciones válidas para la operación potencia.
     */
    public static final TipoDato[][] tablaDePotencia = {
            //ENTERO, DECIMAL
            {TipoDato.ENTERO,   TipoDato.DECIMAL}, //ENTERO
            {TipoDato.DECIMAL,   TipoDato.DECIMAL}, //DECIMAL
    };
}
