package edu.compscript.model.interprete.expresiones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.*;

public class AccesoVariableExpresion extends Instruccion {
    private String id;

    public AccesoVariableExpresion(String id,
                                   int linea,
                                   int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new ErroresExpresiones("SEMANTICO",
                    "La variable " + id + " no ha sido declarada.",
                    this.linea,
                    this.columna);
        }
        this.tipo.setTipoDato(variable.getTipo().getTipoDato());
        return variable.getValor();
    }
}
