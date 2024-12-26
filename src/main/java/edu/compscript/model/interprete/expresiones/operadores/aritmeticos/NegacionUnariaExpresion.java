package edu.compscript.model.interprete.expresiones.operadores.aritmeticos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class NegacionUnariaExpresion extends Instruccion {
    private Instruccion expresion;

    public NegacionUnariaExpresion(Instruccion expresion,
                                   int linea,
                                   int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol,
                              TablaSimbolos tabla) {
        var negacionUnaria = this.expresion.interpretar(arbol, tabla);
        if (negacionUnaria instanceof ErroresExpresiones) {
            return negacionUnaria;
        }

        return switch (this.expresion.tipo.getTipoDato()) {
            case ENTERO -> {
                this.tipo.setTipoDato(TipoDato.ENTERO);
                yield -(int) negacionUnaria;
            }
            case DECIMAL -> {
                this.tipo.setTipoDato(TipoDato.DECIMAL);
                yield -(double) negacionUnaria;
            }
            default -> new ErroresExpresiones("SEMÁNTICO",
                    "Negación unaria inválida",
                    this.linea,
                    this.columna);
        };
    }
}
