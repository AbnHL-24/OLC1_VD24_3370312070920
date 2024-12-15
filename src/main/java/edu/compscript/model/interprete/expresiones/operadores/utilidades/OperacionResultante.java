package edu.compscript.model.interprete.expresiones.operadores.utilidades;

import edu.compscript.model.interprete.simbolo.TipoDato;

/**
 * Ésta clase se encárga de determinar si una operación entre operadores és correcta o no.
 * Si és correcta, devuelve el tipo de dato resultante de la operación.
 * Si no és correcta, devuelve null.
 */
public class OperacionResultante {

    /**
     * Utiliza el ordinal de cada elemento del Enum TipoDato para determinar el tipo de operación resultante entre ambas operaciones.
     * @param TABLA_TIPOS Es la tabla que contiene los tipos de operaciones resultantes de cada operación.
     * @param indexIzq Es el índice del Enum TipoDato del operador izquierdo.
     * @param indexDer Es el índice del Enum TipoDato del operador derecho.
     * @See TipoDato para ver los tipos de datos que se pueden operar.
     * @return
     */
    public static TipoDato calcular(TipoDato[][] TABLA_TIPOS,
                                int indexIzq,
                                int indexDer) {
        // Verifica que los índices de indexIzq e indexDer no salgan del rango del Enum TipoDato.
        if (indexIzq >= TABLA_TIPOS.length || indexDer >= TABLA_TIPOS[indexIzq].length) {
            return null;
        }

        // Se retorna el tipo de operación resultante según la tabla recibida.
        return TABLA_TIPOS[indexIzq][indexDer];
    }
}
