package edu.compscript.model.interprete.simbolo;

/**
 * Esta enumeración contiene los tipos de datos de los símbolos.
 */
public enum TipoDato {
    ENTERO(0),
    DECIMAL(0.0),
    BOOLEANO(true),
    CARACTER('\u0000'),
    CADENA(""),
    VOID(null);

    private final Object valorPredeterminado;

    TipoDato(Object valorPredeterminado) {
        this.valorPredeterminado = valorPredeterminado;
    }

    public Object getValorPredeterminado() {
        return valorPredeterminado;
    }
}
