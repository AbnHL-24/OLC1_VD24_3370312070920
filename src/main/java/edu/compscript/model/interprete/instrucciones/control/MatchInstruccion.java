// MatchInstruccion.java
package edu.compscript.model.interprete.instrucciones.control;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.instrucciones.control.utilidades.CaseInstruccion;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

import java.util.LinkedList;

public class MatchInstruccion extends Instruccion {
    private Instruccion expresion;
    private LinkedList<CaseInstruccion> casesList;
    private CaseInstruccion defaultInstrucciones;

    public MatchInstruccion(Instruccion expresion, LinkedList<CaseInstruccion> casesList, CaseInstruccion defaultInstrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
        this.casesList = casesList;
        this.defaultInstrucciones = defaultInstrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valorExpresion = this.expresion.interpretar(arbol, tabla);
        if (valorExpresion instanceof ErroresExpresiones) return valorExpresion;

        for (CaseInstruccion caseInstruccion : casesList) {
            var valorCase = caseInstruccion.getCaso().interpretar(arbol, tabla);
            if (valorCase instanceof ErroresExpresiones) return valorCase;

            if (valorExpresion.equals(valorCase)) {
                var tablaLocal = new TablaSimbolos(tabla);
                for (Instruccion instruccion : caseInstruccion.getInstrucciones()) {
                    var resultado = instruccion.interpretar(arbol, tablaLocal);
                    if (resultado instanceof ErroresExpresiones) arbol.agregarErrores((ErroresExpresiones) resultado);
                }
                return null;
            }
        }

        if (defaultInstrucciones != null) {
            var tablaLocal = new TablaSimbolos(tabla);
            for (Instruccion instruccion : defaultInstrucciones.getInstrucciones()) {
                var resultado = instruccion.interpretar(arbol, tablaLocal);
                if (resultado instanceof ErroresExpresiones) arbol.agregarErrores((ErroresExpresiones) resultado);
            }
        }

        return null;
    }
}