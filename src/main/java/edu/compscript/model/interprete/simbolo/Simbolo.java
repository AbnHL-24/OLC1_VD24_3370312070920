package edu.compscript.model.interprete.simbolo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Crea los símbolos del lenguaje.
 */
@AllArgsConstructor
public class Simbolo {
    @Getter
    private boolean mutable;
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private Object valor;
    @Getter
    @Setter
    private Tipo tipo;

    public Simbolo(boolean mutable, Tipo tipo, String id, Object valor) {
        this.mutable = mutable;
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }
}
