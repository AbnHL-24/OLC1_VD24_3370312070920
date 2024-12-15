package edu.compscript.model.interprete.expresiones.operadores;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.OperacionResultante;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public abstract class OperacionBinaria extends Instruccion {
    protected Instruccion operadorIzq;
    protected Instruccion operadorDer;

    public OperacionBinaria(Instruccion operadorIzq,
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

        // Se obtiene la operación correspondiente a las operaciones de operadorIzq y operadorDer.
        var tipoResultado = OperacionResultante.calcular(getTablaOperacion(),
                operadorIzq.tipo.getTipoDato().ordinal(),
                operadorDer.tipo.getTipoDato().ordinal());

        this.tipo.setTipoDato(tipoResultado);

        return realizarOperacion(valorIzq, valorDer, tipoResultado);
    }

    /**
     * Método que devuelve la tabla de operaciones correspondiente a la operación.
     * @return Retorna la tabla de operaciones correspondiente a la operación.
     */
    protected abstract TipoDato[][] getTablaOperacion();

    /**
     * Método que realiza la operación correspondiente a la operación.
     * @param valorIzq És el valor izquierdo de la operación.
     * @param valorDer És el valor derecho de la operación.
     * @param tipoResultado És el tipo de dato resultante de la operación.
     * @return Retorna el resultado de la operación.
     */
    protected abstract Object realizarOperacion(Object valorIzq, Object valorDer, TipoDato tipoResultado);
}