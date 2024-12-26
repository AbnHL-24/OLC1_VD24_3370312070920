package edu.compscript.model.interprete.instrucciones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;

public class ReturnInstruccion extends Instruccion {
    private final Instruccion valorRetorno;

    public ReturnInstruccion(Instruccion valorRetorno, int linea, int columna) {
        super(null, linea, columna); // El tipo se establece en tiempo de ejecución
        this.valorRetorno = valorRetorno;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        if (valorRetorno != null) {
            var resultado = valorRetorno.interpretar(arbol, tabla);
            if (resultado instanceof ErroresExpresiones) {
                return resultado;
            }
            return resultado;
        }
        return null; // Return vacío
    }
}
