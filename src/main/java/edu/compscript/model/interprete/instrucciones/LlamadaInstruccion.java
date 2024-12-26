package edu.compscript.model.interprete.instrucciones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.*;

import java.util.HashMap;
import java.util.LinkedList;

public class LlamadaInstruccion extends Instruccion {
    private String id;
    private LinkedList<HashMap> parametros;

    public LlamadaInstruccion(String id,
                              LinkedList<HashMap> parametros,
                              int linea,
                              int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Verificar que la función exista
        var busqueda = arbol.getFuncion(id);
        if (busqueda == null) return new ErroresExpresiones("SEMANTICO",
                "No se encontró la función " + id,
                this.linea,
                this.columna);

        // Verificando que la función sea una instancia de MetodoInstruccion.
        if (!(busqueda instanceof MetodoInstruccion metodoInstruccion)) return new ErroresExpresiones("SEMANTICO",
                "La llamada a la función " + id + " es errónea.",
                this.linea,
                this.columna);
        else {
            var nuevaTabla = new TablaSimbolos(arbol.getTablaGlobal());
            nuevaTabla.setNombre("Llamada " + id);

            // Declarando parametros.
            for (int i = 0; i < metodoInstruccion.getParametros().size(); i++) {
                var identificador = (String) metodoInstruccion.getParametros().get(i).get("id");
                var tipo = (Tipo) metodoInstruccion.getParametros().get(i).get("tipo");
                var expresion = (Instruccion) metodoInstruccion.getParametros().get(i).get("expresion");
                // var declaracionParametro = new DeclaracionInstruccion();
                Object valExp = null;
                if (expresion != null) {
                    valExp = expresion.interpretar(arbol, nuevaTabla);
                    if (valExp instanceof ErroresExpresiones) return valExp;
                    if (tipo.getTipoDato() != expresion.tipo.getTipoDato()) {
                        return new ErroresExpresiones("SEMANTICO",
                                "El tipo de dato del parámetro no coincide con el valor asignado.",
                                this.linea,
                                this.columna);
                    }
                }

                // ¿No se declaró la variable?
                if (!nuevaTabla.setVariable(new Simbolo(true, tipo, identificador, valExp))) {
                    return new ErroresExpresiones("SEMANTICO",
                            "Error en el parametro",
                            this.linea,
                            this.columna);
                } /*else {
                    return new ErroresExpresiones("SEMANTICO",
                            "La variable " + identificador + " ya ha sido declarada.",
                            this.linea,
                            this.columna);
                }*/
            }
            // Reasignar su valor.
            for (int i = 0; i < this.parametros.size(); i++) {
                var variable = nuevaTabla.getVariable((String) this.parametros.get(i).get("id"));
                if (variable == null) {
                    return new ErroresExpresiones("SEMANTICO",
                            "No se encontró la variable " + this.parametros.get(i).get("id"),
                            this.linea,
                            this.columna);
                }
                var valor = (Instruccion) this.parametros.get(i).get("expresion");
                var resValor = valor.interpretar(arbol, nuevaTabla);
                if (resValor instanceof ErroresExpresiones) return resValor;

                // Verificar tipos.
                if(valor.tipo.getTipoDato() != variable.getTipo().getTipoDato()) {
                    return new ErroresExpresiones("SEMANTICO",
                            "El tipo de dato del parámetro no coincide con el valor asignado.",
                            this.linea,
                            this.columna);
                }
                variable.setValor(resValor);
            }

            // Verificar que no existan variables como null
            for (int i = 0; i < metodoInstruccion.getParametros().size(); i++) {
                var identificador = (String) metodoInstruccion.getParametros().get(i).get("id");
                var resultado = nuevaTabla.getVariable(identificador);
                if (resultado == null) {
                    return new ErroresExpresiones("SEMANTICO",
                            "La variable " + identificador + " no ha sido declarada.",
                            this.linea,
                            this.columna);
                }

                if (resultado.getValor() == null) {
                    return new ErroresExpresiones("SEMANTICO",
                            "La variable " + identificador + " no tiene un valor asignado.",
                            this.linea,
                            this.columna);
                }

                var resultadoMetodo = metodoInstruccion.interpretar(arbol, nuevaTabla);
                if (resultadoMetodo instanceof ErroresExpresiones) {
                    return resultadoMetodo;
                }
            }
        }
        return null;
    }
    // TODO queda pendiendte el RETURN y descomentar LLAMADA en la seccion instruccion en el LEXICO.
}
