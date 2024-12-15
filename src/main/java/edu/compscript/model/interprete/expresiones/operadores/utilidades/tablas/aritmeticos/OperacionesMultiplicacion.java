package edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos;

import edu.compscript.model.interprete.simbolo.TipoDato;

/**
 * Clase que contiene de manera estática la tabla con las combinaciones correctas para la multiplicación.
 */
public class OperacionesMultiplicacion {
    /**
     * Tabla que define las combinaciones válidas para la operación multiplicació.
     */
    public static final TipoDato[][] tablaDeMultiplicaciones = {
            //ENTERO, DECIMAL, CARACTER
            {TipoDato.ENTERO,   TipoDato.DECIMAL,   TipoDato.ENTERO}, //ENTERO
            {TipoDato.DECIMAL,  TipoDato.DECIMAL,   TipoDato.DECIMAL}, //DECIMAL
            {TipoDato.ENTERO,   TipoDato.DECIMAL,   null}, //CARACTER
    };
}
