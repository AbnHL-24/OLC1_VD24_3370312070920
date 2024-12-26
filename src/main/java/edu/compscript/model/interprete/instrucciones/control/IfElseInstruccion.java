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
    private LinkedList<Instruccion> instruccionesElse;

    public IfElseInstruccion(Instruccion condicion,
                             LinkedList<Instruccion> instruccionesIf,
                             LinkedList<Instruccion> instruccionesElse,
                             int linea,
                             int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.instruccionesElse = instruccionesElse;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var condicion = this.condicion.interpretar(arbol, tabla);
        if (condicion instanceof ErroresExpresiones) return condicion;

        if (this.condicion.tipo.getTipoDato() != TipoDato.BOOLEANO) return new ErroresExpresiones("SEMÁNTICO",
                "La condición del if no es booleana",
                this.linea,
                this.columna);

        var tablaLocal = new TablaSimbolos(tabla);

        if ((boolean) condicion) {
            for (Instruccion instruccion : this.instruccionesIf) {
                var resultado = instruccion.interpretar(arbol, tablaLocal);
                if (resultado instanceof ErroresExpresiones) arbol.agregarErrores((ErroresExpresiones) resultado);
            }
        } else {
            for (Instruccion instruccion : this.instruccionesElse) {
                var resultado = instruccion.interpretar(arbol, tablaLocal);
                if (resultado instanceof ErroresExpresiones) arbol.agregarErrores((ErroresExpresiones) resultado);
            }
        }
        return null;
    }
}