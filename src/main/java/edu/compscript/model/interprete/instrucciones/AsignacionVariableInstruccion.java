package edu.compscript.model.interprete.instrucciones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class AsignacionVariableInstruccion extends Instruccion {
    private String id;
    private Instruccion valor;

    public AsignacionVariableInstruccion(String id, Instruccion valor, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Verificar que la variable exista.
        var variable = tabla.getVariable(id);
        if (variable == null) return new ErroresExpresiones("SEMANTICO", "La variable " + id + " no ha sido declarada.", this.linea, this.columna);

        // Verifica que no sea un error.
        var nuevoValor = this.valor.interpretar(arbol, tabla);
        if (nuevoValor instanceof ErroresExpresiones) return nuevoValor;

        // Si no es mutable marca él error.
        if (!variable.isMutable()) {
            return new ErroresExpresiones("SEMANTICO", "La variable " + id + " es constante y no puede ser modificada.", this.linea, this.columna);
        }
        // Si no es del tipo correcto marca el error.
        if (variable.getTipo().getTipoDato() != this.valor.tipo.getTipoDato()) {
            return new ErroresExpresiones("SEMANTICO", "El tipo de dato de la variable no coincide con el valor asignado.", this.linea, this.columna);
        }
        // Al fin, tras las comprobaciones, se asigna el valor.
        variable.setValor(nuevoValor);
        return null;
    }
}
