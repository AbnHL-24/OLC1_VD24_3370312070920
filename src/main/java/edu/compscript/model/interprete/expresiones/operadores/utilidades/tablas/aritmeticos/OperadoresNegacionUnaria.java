package edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos;

import edu.compscript.model.interprete.simbolo.TipoDato;

/**
 * Clase que contiene de manera estática la tabla con las combinaciones correctas para la negación unaria.
 */
public class OperadoresNegacionUnaria {
    /**
     * Tabla que define las combinaciones válidas para la operación negación unaria.
     */
    public static final TipoDato[][] tablaDeNegacionUnaria = {
            {TipoDato.ENTERO}, //ENTERO
            {TipoDato.DECIMAL}, //DECIMAL
    };
}
