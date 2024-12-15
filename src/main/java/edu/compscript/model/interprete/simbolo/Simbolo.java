package edu.compscript.model.interprete.simbolo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Crea los símbolos del lenguaje.
 */
@AllArgsConstructor
@Getter
@Setter
public class Simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;
}
