package edu.compscript.model.interprete.expresiones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class NativoExpresion extends Instruccion {
    public Object valor;

    public NativoExpresion(Object valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // TODO Aqui se tiene que hacer lo de las secuencias de escape. Quizá se pueda hacer en el replace() para quitar las \t por cuatro espacios.
        if (this.tipo.getTipoDato() == TipoDato.CADENA) {
            String resultado = this.valor.toString();
            resultado = resultado.replace("\\t", "\t");
            resultado = resultado.replace("\\n", "\n");
            resultado = resultado.replace("\\r", "\r");
            resultado = resultado.replace("\\\"", "\"");
            resultado = resultado.replace("\\\\", "\\");
            valor = resultado;
        }
        return this.valor;

        /*// Aqui se hacen las validaciones semanticas. TODO Hacer validaciones semanticas.
        if (this.tipo.getTipoDato() == TipoDato.BOOLEANO) {
            if (this.valor.toString() == "true") {
                return true;
            } else {
                return false;
            }
        }*/
    }
}
