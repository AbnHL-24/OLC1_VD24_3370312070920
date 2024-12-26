package edu.compscript.model.interprete.instrucciones.control;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

import java.util.LinkedList;
/**
 * Clase que representa una instrucción if.
 */
public class IfInstruccion extends Instruccion {
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    /**
     * Constructor de la instrucción if.
     *
     * @param condicion    Condición del if.
     * @param instrucciones Instrucciones del if.
     * @param linea        Línea en la que se encuentra la instrucción.
     * @param columna      Columna en la que se encuentra la instrucción.
     */
    public IfInstruccion(Instruccion condicion,
                         LinkedList<Instruccion> instrucciones,
                         int linea,
                         int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var condicion = this.condicion.interpretar(arbol, tabla);
        if (condicion instanceof ErroresExpresiones) return condicion;

        // La condicion siempre tiene que ser booleana.
        if (this.condicion.tipo.getTipoDato() != TipoDato.BOOLEANO) return new ErroresExpresiones("SEMÁNTICO",
                "La condición del if no es booleana",
                this.linea,
                this.columna);

        // Aquí hay un bloque, hay que crear un nuevo entorno.
        var tablaLocal = new TablaSimbolos(tabla);

        if ((boolean) condicion) {
            for (Instruccion instruccion : this.instrucciones) {
                var resultado = instruccion.interpretar(arbol, tablaLocal);
                if (resultado instanceof ErroresExpresiones) arbol.agregarErrores((ErroresExpresiones) resultado);
            }
        }
        return null;
    }
}
