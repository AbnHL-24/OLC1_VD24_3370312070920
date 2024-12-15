package edu.compscript.model.interprete.expresiones.operadores.aritmeticos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.expresiones.operadores.OperacionBinaria;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos.OperacionesResta;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class RestaExpresion extends OperacionBinaria {

    public RestaExpresion(Instruccion operadorIzq,
                          Instruccion operadorDer,
                          int linea,
                          int columna) {
        super(operadorIzq, operadorDer, linea, columna);
    }

    @Override
    protected TipoDato[][] getTablaOperacion() {
        return OperacionesResta.tablaDeRestas;
    }

    @Override
    protected Object realizarOperacion(Object valorIzq, Object valorDer, TipoDato tipoResultado) {
        return switch (tipoResultado) {
            case ENTERO -> ((Number) valorIzq).intValue() - ((Number) valorDer).intValue();
            case DECIMAL -> ((Number) valorIzq).doubleValue() - ((Number) valorDer).doubleValue();
            default -> new ErroresExpresiones("SEMÁNTICO", "Operación resta entre tipos no soportada", linea, columna);
        };
    }
}