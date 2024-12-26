package edu.compscript.model.interprete.expresiones.operadores.logicos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class AndExpresion extends Instruccion {
    private Instruccion operadorIzq;
    private Instruccion operadorDer;

    public AndExpresion(Instruccion operadorIzq,
                        Instruccion operadorDer,
                        int linea,
                        int columna) {
        super(new Tipo(TipoDato.BOOLEANO), linea, columna);
        this.operadorIzq = operadorIzq;
        this.operadorDer = operadorDer;
    }

    @Override
    public Object interpretar(Arbol arbol,
                              TablaSimbolos tabla) {
        var valorIzq = operadorIzq.interpretar(arbol, tabla);
        var valorDer = operadorDer.interpretar(arbol, tabla);

        if (valorIzq instanceof ErroresExpresiones || valorDer instanceof ErroresExpresiones) {
            return valorIzq instanceof ErroresExpresiones ? valorIzq : valorDer;
        }

        if (operadorIzq.tipo.getTipoDato() != TipoDato.BOOLEANO || operadorDer.tipo.getTipoDato() != TipoDato.BOOLEANO) {
            return new ErroresExpresiones("SEMÁNTICO",
                    "Operación AND entre tipos no booleanos",
                    linea,
                    columna);
        }

        return (boolean) valorIzq && (boolean) valorDer;
    }
}