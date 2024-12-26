package edu.compscript.model.interprete.expresiones.operadores.relacionales;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.expresiones.operadores.OperacionBinaria;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.relacionales.OperadoresRelacionales;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class MenorIgualQueExpresion extends OperacionBinaria {

    public MenorIgualQueExpresion(Instruccion operadorIzq,
                                  Instruccion operadorDer,
                                  int linea,
                                  int columna) {
        super(operadorIzq, operadorDer, linea, columna);
    }

    @Override
    protected TipoDato[][] getTablaOperacion() {
        return OperadoresRelacionales.tablaDeRelacionales;
    }

    @Override
    protected Object realizarOperacion(Object valorIzq,
                                       Object valorDer,
                                       TipoDato tipoResultado) {
        if (valorIzq instanceof Integer) valorIzq = ((Integer) valorIzq).doubleValue();
        if (valorDer instanceof Integer) valorDer = ((Integer) valorDer).doubleValue();
        return switch (tipoResultado) {
            case BOOLEANO -> ((Comparable) valorIzq).compareTo(valorDer) <= 0;
            default -> new ErroresExpresiones("SEMÁNTICO",
                    "Operación menor o igual que entre tipos no soportada",
                    linea,
                    columna);
        };
    }
}