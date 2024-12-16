package edu.compscript.model.interprete.simbolo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Crea los símbolos del lenguaje.
 */
@Getter
@Setter
@AllArgsConstructor
public class Simbolo {
    @Getter
    private boolean mutable;
    private String id;
    private Object valor;
    private Tipo tipo;

    public Simbolo(boolean mutable, Tipo tipo, String id, Object valor) {
        this.mutable = mutable;
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }
}
