package edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.relacionales;

import edu.compscript.model.interprete.simbolo.TipoDato;

/**
 * Clase que contiene de manera estática la tabla con las combinaciones correctas para las operaciones relacionales.
 */
public class OperadoresRelacionales {
    /**
     * Tabla que define las combinaciones válidas para la operación suma.
     */
    public static final TipoDato[][] tablaDeRelacionales = {
            // ENTERO,          DECIMAL,            BOOLEANO,           CARACTER,           CADENA
            {TipoDato.BOOLEANO, TipoDato.BOOLEANO,   null,               TipoDato.BOOLEANO, null}, // ENTERO
            {TipoDato.BOOLEANO, TipoDato.BOOLEANO,   null,               TipoDato.BOOLEANO, null}, // DECIMAL
            {null,              null,               TipoDato.BOOLEANO,  null,               null}, // BOOLEANO
            {TipoDato.BOOLEANO, TipoDato.DECIMAL,   null,               TipoDato.BOOLEANO,  null}, // CARACTER
            {null,              null,               null,               null,               TipoDato.BOOLEANO}  // CADENA
    };
}
