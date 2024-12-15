package edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos;

import edu.compscript.model.interprete.simbolo.TipoDato;

/**
 * Clase que contiene de manera estática la tabla con las combinaciones correctas para la suma.
 */
public class OperacionesSuma {
    /**
     * Tabla que define las combinaciones válidas para la operación suma.
     */
    public static final TipoDato[][] tablaDeSumas = {
            // ENTERO,      DECIMAL,     BOOLEANO,   CARACTER,   CADENA
            {TipoDato.ENTERO,   TipoDato.DECIMAL,   TipoDato.ENTERO,    TipoDato.ENTERO,    TipoDato.CADENA}, // ENTERO
            {TipoDato.DECIMAL,  TipoDato.DECIMAL,   TipoDato.DECIMAL,   TipoDato.DECIMAL,   TipoDato.CADENA}, // DECIMAL
            {TipoDato.ENTERO,   TipoDato.DECIMAL,   null,               null,               TipoDato.CADENA}, // BOOLEANO
            {TipoDato.ENTERO,   TipoDato.DECIMAL,   null,               TipoDato.CADENA,    TipoDato.CADENA}, // CARACTER
            {TipoDato.CADENA,   TipoDato.CADENA,    TipoDato.CADENA,    TipoDato.CADENA,    TipoDato.CADENA}  // CADENA
    };
}
