package edu.compscript.model.interprete.expresiones.operadores.logicos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class NotExpresion extends Instruccion {
    private Instruccion expresion;

    public NotExpresion(Instruccion expresion, int linea, int columna) {
        super(new Tipo(TipoDato.BOOLEANO), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valor = expresion.interpretar(arbol, tabla);

        if (valor instanceof ErroresExpresiones) {
            return valor;
        }

        if (expresion.tipo.getTipoDato() != TipoDato.BOOLEANO) {
            return new ErroresExpresiones("SEMÁNTICO", "Operación NOT sobre tipo no booleano", linea, columna);
        }

        return !(boolean) valor;
    }
}