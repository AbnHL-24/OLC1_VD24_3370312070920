package edu.compscript.model.interprete.expresiones.operadores.aritmeticos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.expresiones.operadores.OperacionBinaria;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos.OperacionesModulo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class ModuloExpresion extends OperacionBinaria {

    public ModuloExpresion(Instruccion operadorIzq,
                           Instruccion operadorDer,
                           int linea,
                           int columna) {
        super(operadorIzq, operadorDer, linea, columna);
    }

    @Override
    protected TipoDato[][] getTablaOperacion() {
        return OperacionesModulo.tablaDeModulo;
    }

    @Override
    protected Object realizarOperacion(Object valorIzq,
                                       Object valorDer,
                                       TipoDato tipoResultado) {
        return switch (tipoResultado) {
            case DECIMAL -> ((Number) valorIzq).doubleValue() % ((Number) valorDer).doubleValue();
            default -> new ErroresExpresiones("SEMÁNTICO",
                    "Operación modulo entre tipos no soportada",
                    linea,
                    columna);
        };
    }
}
