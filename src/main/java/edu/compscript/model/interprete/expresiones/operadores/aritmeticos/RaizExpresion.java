// RaizExpresion.java
package edu.compscript.model.interprete.expresiones.operadores.aritmeticos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.expresiones.operadores.OperacionBinaria;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos.OperacionesRaiz;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class RaizExpresion extends OperacionBinaria {

    public RaizExpresion(Instruccion operadorIzq,
                         Instruccion operadorDer,
                         int linea,
                         int columna) {
        super(operadorIzq, operadorDer, linea, columna);
    }

    @Override
    protected TipoDato[][] getTablaOperacion() {
        return OperacionesRaiz.tablaDeRadicaciones;
    }

    @Override
    protected Object realizarOperacion(Object valorIzq,
                                       Object valorDer,
                                       TipoDato tipoResultado) {
        if (((Number) valorIzq).doubleValue() < 0) {
            return new ErroresExpresiones("SEMÁNTICO", "Raíz de un número negativo", linea, columna);
        }
        return switch (tipoResultado) {
            case ENTERO -> Math.pow(((Number) valorIzq).intValue(), 1.0 / ((Number) valorDer).intValue());
            case DECIMAL -> Math.pow(((Number) valorIzq).doubleValue(), 1.0 / ((Number) valorDer).doubleValue());
            default -> new ErroresExpresiones("SEMÁNTICO",
                    "Operación raíz entre tipos no soportada",
                    linea,
                    columna);
        };
    }
}