package edu.compscript.model.interprete.instrucciones.control;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

import java.util.LinkedList;

public class IfElseInstruccion extends Instruccion {
    private Instruccion condicion;
    private LinkedList<Instruccion> instruccionesIf;
    private Instruccion elseIf;

    public IfElseInstruccion(Instruccion condicion, LinkedList<Instruccion> instruccionesIf, Instruccion elseIf, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.elseIf = elseIf;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var condicion = this.condicion.interpretar(arbol, tabla);
        if (condicion instanceof ErroresExpresiones) return condicion;

        // La condicion siempre tiene que ser booleana.
        if (this.condicion.tipo.getTipoDato() != TipoDato.BOOLEANO) {
            return new ErroresExpresiones("SEMÁNTICO",
                    "La condición del if no es booleana",
                    this.linea,
                    this.columna);
        }

        var tablaLocal = new TablaSimbolos(tabla);

        if ((boolean) condicion) {
            for (Instruccion instruccion : this.instruccionesIf) {
                var resultado = instruccion.interpretar(arbol, tablaLocal);
                if (resultado instanceof ErroresExpresiones) arbol.agregarErrores((ErroresExpresiones) resultado);
            }
        } else if (this.elseIf != null) {
            var resultado = this.elseIf.interpretar(arbol, tablaLocal);
            if (resultado instanceof ErroresExpresiones) arbol.agregarErrores((ErroresExpresiones) resultado);
        }
        return null;
    }
}