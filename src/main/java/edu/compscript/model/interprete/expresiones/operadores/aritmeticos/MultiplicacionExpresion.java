package edu.compscript.model.interprete.expresiones.operadores.aritmeticos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.OperacionResultante;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos.OperacionesMultiplicacion;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class MultiplicacionExpresion extends Instruccion {
    private Instruccion operadorIzq;
    private Instruccion operadorDer;

    public MultiplicacionExpresion(Instruccion operadorIzq,
                          Instruccion operadorDer,
                          int linea,
                          int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.operadorIzq = operadorIzq;
        this.operadorDer = operadorDer;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Verificamos que no haya errores en los operadores.
        var valorIzq = operadorIzq.interpretar(arbol, tabla);
        var valorDer = operadorDer.interpretar(arbol, tabla);

        // Verificar errores.
        if (valorIzq instanceof ErroresExpresiones || valorDer instanceof ErroresExpresiones) {
            return valorIzq instanceof ErroresExpresiones ? valorIzq : valorDer;
        }

        var tipoResultado = OperacionResultante.calcular(OperacionesMultiplicacion.tablaDeMultiplicaciones,
                operadorIzq.tipo.getTipoDato().ordinal(),
                operadorDer.tipo.getTipoDato().ordinal());

        // Establece el tipo resultante en él atributo Tipo de la superclase Instrucción.
        this.tipo.setTipoDato(tipoResultado);

        // Realizar la operación según el resultado de la combinación.
        return switch (tipoResultado) {
            case ENTERO -> (int) valorIzq * (int) valorDer;
            case DECIMAL -> (double) valorIzq * (double) valorDer;
            default -> new ErroresExpresiones("SEMÁNTICO", "Operación multiplicación entre tipos no soportada", linea, columna);
        };
    }
}