// DiferenciacionExpresion.java
package edu.compscript.model.interprete.expresiones.operadores.relacionales;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.expresiones.operadores.OperacionBinaria;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.relacionales.OperadoresRelacionales;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class DiferenciacionExpresion extends OperacionBinaria {

    public DiferenciacionExpresion(Instruccion operadorIzq, Instruccion operadorDer, int linea, int columna) {
        super(operadorIzq, operadorDer, linea, columna);
    }

    @Override
    protected TipoDato[][] getTablaOperacion() {
        return OperadoresRelacionales.tablaDeRelacionales;
    }

    @Override
    protected Object realizarOperacion(Object valorIzq, Object valorDer, TipoDato tipoResultado) {
        return switch (tipoResultado) {
            case BOOLEANO -> !valorIzq.equals(valorDer);
            default -> new ErroresExpresiones("SEMÁNTICO", "Operación diferenciación entre tipos no soportada", linea, columna);
        };
    }
}