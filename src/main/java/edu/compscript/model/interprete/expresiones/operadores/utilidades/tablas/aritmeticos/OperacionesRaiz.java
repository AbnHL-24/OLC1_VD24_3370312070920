package edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos;

import edu.compscript.model.interprete.simbolo.TipoDato;

/**
 * Clase que contiene de manera estática la tabla con las combinaciones correctas para la raíz.
 */
public class OperacionesRaiz {
    /**
     * Tabla que define las combinaciones válidas para la operación raíz.
     */
    public static final TipoDato[][] tablaDeRaiz = {
            //ENTERO, DECIMAL
            {TipoDato.DECIMAL,   TipoDato.DECIMAL}, //ENTERO
            {TipoDato.DECIMAL,   TipoDato.DECIMAL}, //DECIMAL
    };
}
