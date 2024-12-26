package edu.compscript.model.interprete.abstracto;

import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Instruccion {
    public Tipo tipo;
    public int linea;
    public int columna;

    public abstract Object interpretar(Arbol arbol,
                                       TablaSimbolos tabla);

    // Para compi 2 se agrega el método abstracto convertirACodigoTresDirecciones().
}
