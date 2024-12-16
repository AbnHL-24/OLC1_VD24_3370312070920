package edu.compscript.model.interprete.instrucciones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.NativoExpresion;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.Simbolo;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import lombok.Getter;

// Mutabilidad id: tipo = valor
@Getter
public class DeclaracionInstruccion extends Instruccion {
    private boolean mutable;
    private String id;
    private Instruccion valor;

    public DeclaracionInstruccion(boolean mutable,
                                  String id,
                                  Instruccion valor,
                                  Tipo tipo,
                                  int linea,
                                  int columna) {
        super(tipo, linea, columna);
        this.mutable = mutable;
        this.id = id;
        this.valor = valor != null ? valor : new NativoExpresion(this.tipo.getTipoDato().getValorPredeterminado(), tipo, linea, columna);
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Verificar el valor recibido.
        var valorInterpretado = this.valor.interpretar(arbol, tabla);
        if (valorInterpretado instanceof ErroresExpresiones) return valorInterpretado;

        // Verificar el valor de la variable.
        if (this.valor.tipo.getTipoDato()!=this.tipo.getTipoDato()) {
            return new ErroresExpresiones("SEMANTICO","El tipo de dato de la variable no coincide con el valor asignado.", this.linea, this.columna);
        }

        // Validar la existencia de la variable y declararla.
        if (tabla.setVariable(new Simbolo(mutable, this.tipo, this.id, valorInterpretado))) {
            // Si se declaró la variable.
            return null;
        } else {
            return new ErroresExpresiones("SEMANTICO","La variable " + this.id +" ya ha sido declarada.", this.linea, this.columna);
        }

    }
}
