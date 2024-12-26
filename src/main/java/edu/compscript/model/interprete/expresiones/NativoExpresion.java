package edu.compscript.model.interprete.expresiones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class NativoExpresion extends Instruccion {
    public Object valor;

    public NativoExpresion(Object valor,
                           Tipo tipo,
                           int linea,
                           int columna) {
        super(tipo, linea, columna);
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
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
    }
}
