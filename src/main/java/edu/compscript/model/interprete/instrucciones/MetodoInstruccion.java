package edu.compscript.model.interprete.instrucciones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Clase que representa una instrucción de un método.
 */
@Getter
public class MetodoInstruccion extends Instruccion {
    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;

    public MetodoInstruccion(String id,
                             LinkedList<HashMap> parametros,
                             LinkedList<Instruccion> instrucciones,
                             Tipo tipo,
                             int linea,
                             int columna) {
        super(tipo, linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Verifica si el LinkedList de instrucciones tiene errores.
        for (Instruccion instruccion : instrucciones) {
            var resultado = instruccion.interpretar(arbol, tabla);
            if (resultado instanceof ErroresExpresiones) {
                return resultado;
            }
            // Valida el valor de la variable.
            /*if (instruccion.tipo.getTipoDato()!=this.tipo.getTipoDato())
                return new ErroresExpresiones("SEMANTICO",
                    "El tipo de dato de la variable no coincide con el valor asignado.",
                    this.linea,
                    this.columna);*/
        }
        return null;
    }
}
