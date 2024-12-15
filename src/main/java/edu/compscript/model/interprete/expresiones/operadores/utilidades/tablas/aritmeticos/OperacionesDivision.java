package edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos;

import edu.compscript.model.interprete.simbolo.TipoDato;

/**
 * Clase que contiene de manera estática la tabla con las combinaciones correctas para la división.
 */
public class OperacionesDivision {
    /**
     * Tabla que define las combinaciones válidas para la operación division.
     */
    public static final TipoDato[][] tablaDeDivisiones = {
            //ENTERO, DECIMAL, CARACTER
            {TipoDato.DECIMAL,   TipoDato.DECIMAL,   TipoDato.DECIMAL}, //ENTERO
            {TipoDato.DECIMAL,   TipoDato.DECIMAL,   TipoDato.DECIMAL}, //DECIMAL
            {TipoDato.DECIMAL,   TipoDato.DECIMAL,   null}, //CARACTER
    };
}
