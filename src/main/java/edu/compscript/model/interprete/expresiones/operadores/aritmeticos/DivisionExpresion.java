package edu.compscript.model.interprete.expresiones.operadores.aritmeticos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.expresiones.operadores.OperacionBinaria;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos.OperacionesDivision;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class DivisionExpresion extends OperacionBinaria {

    public DivisionExpresion(Instruccion operadorIzq,
                             Instruccion operadorDer,
                             int linea,
                             int columna) {
        super(operadorIzq, operadorDer, linea, columna);
    }

    @Override
    protected TipoDato[][] getTablaOperacion() {
        return OperacionesDivision.tablaDeDivisiones;
    }

    @Override
    protected Object realizarOperacion(Object valorIzq,
                                       Object valorDer,
                                       TipoDato tipoResultado) {
        if (((Number) valorDer).doubleValue() == 0) {
            return new ErroresExpresiones("SEMÁNTICO",
                    "División por cero",
                    linea,
                    columna);
        }
        return switch (tipoResultado) {
            case ENTERO -> ((Number) valorIzq).intValue() / ((Number) valorDer).intValue();
            case DECIMAL -> ((Number) valorIzq).doubleValue() / ((Number) valorDer).doubleValue();
            default -> new ErroresExpresiones("SEMÁNTICO",
                    "Operación división entre tipos no soportada",
                    linea,
                    columna);
        };
    }
}