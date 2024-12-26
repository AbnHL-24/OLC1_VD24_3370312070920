package edu.compscript.model.interprete.expresiones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.*;

public class IncrementoExpresion extends Instruccion {
    private String id;

    public IncrementoExpresion(String id,
                               int linea,
                               int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Verifica que la variable exista.
        var variable = tabla.getVariable(id);
        if (variable == null) return new ErroresExpresiones("SEMANTICO",
                "La variable " + id + " no ha sido declarada.",
                this.linea,
                this.columna);

        // Si no es mutable marca él error.
        if (!variable.isMutable()) {
            return new ErroresExpresiones("SEMANTICO",
                    "La variable " + id + " es constante y no puede ser modificada.",
                    this.linea,
                    this.columna);
        }

        if (variable != null) {
            Object valor = variable.getValor();
            if (valor instanceof Integer) {
                variable.setValor((Integer) valor + 1);
            } else if (valor instanceof Double) {
                variable.setValor((Double) valor + 1.0);
            } else if (valor instanceof Character) {
                variable.setValor((char) (((Character) valor) + 1));
            }
            return variable.getValor();
        }

        return null;
    }
}