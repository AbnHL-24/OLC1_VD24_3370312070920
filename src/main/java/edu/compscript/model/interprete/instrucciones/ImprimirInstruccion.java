package edu.compscript.model.interprete.instrucciones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class ImprimirInstruccion extends Instruccion {
    private Instruccion expresion;

    public ImprimirInstruccion(Instruccion expresion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valor = this.expresion.interpretar(arbol, tabla);
        if (valor instanceof ErroresExpresiones) return valor;
        arbol.Print(valor.toString());
        return null; // TODO cambiar por un valor de retorno porque nuy feo un null.
    }
}
