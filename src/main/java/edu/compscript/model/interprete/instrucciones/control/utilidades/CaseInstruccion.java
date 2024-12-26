// CaseInstruccion.java
package edu.compscript.model.interprete.instrucciones.control.utilidades;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.expresiones.NativoExpresion;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;
import lombok.Getter;

import java.util.LinkedList;

@Getter
public class CaseInstruccion extends Instruccion {
    private NativoExpresion caso;
    private LinkedList<Instruccion> instrucciones;

    public CaseInstruccion(NativoExpresion caso,
                           LinkedList<Instruccion> instrucciones,
                           int linea,
                           int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.caso = caso;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        return null; // No se usa directamente
    }
}