package edu.compscript.model.interprete.instrucciones.ciclos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

import java.util.LinkedList;

public class ForInstruccion extends Instruccion {
    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    LinkedList<Instruccion> instrucciones;

    public ForInstruccion(Instruccion asignacion,
                          Instruccion condicion,
                          Instruccion actualizacion,
                          LinkedList<Instruccion> instrucciones,
                          int linea,
                          int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Asignacion a la variable.
        var res1 = asignacion.interpretar(arbol, tabla);
        if (res1 instanceof String) return res1;

        // Validar la condicion.
        var cond = condicion.interpretar(arbol, tabla);
        if (cond instanceof String) return cond;
        if (!(cond instanceof Boolean)) return new ErroresExpresiones("SEMÁNTICO",
                "La condición del for debe ser de tipo booleano.",
                linea,
                columna);

        var nuevaTabla = new TablaSimbolos(tabla);
        while ((Boolean) this.condicion.interpretar(arbol, nuevaTabla)) {
            var nuevaTabla2 = new TablaSimbolos(nuevaTabla);
            for (var resInstruccion : instrucciones) {
                var res = resInstruccion.interpretar(arbol, nuevaTabla2);
                if (res instanceof ErroresExpresiones) return new ErroresExpresiones("SEMÁNTICO",
                        "Error en la instrucción del for.",
                        linea,
                        columna);
            }
            var actualizacion = this.actualizacion.interpretar(arbol, nuevaTabla);
            if (actualizacion instanceof ErroresExpresiones) return new ErroresExpresiones("SEMÁNTICO",
                    "Error en la actualización del for.",
                    linea,
                    columna);
        }
        return null;
    }
}
