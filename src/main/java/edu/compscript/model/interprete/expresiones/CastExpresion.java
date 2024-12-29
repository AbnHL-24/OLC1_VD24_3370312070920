package edu.compscript.model.interprete.expresiones;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

public class CastExpresion extends Instruccion {
    private Instruccion instruccion;
    private Tipo tipo;

    public CastExpresion(Instruccion instruccion,
                         Tipo tipo,
                         int linea,
                         int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.instruccion = instruccion;
        this.tipo = tipo;
    }


    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        // Verificar el valor recibido.
        var valorInterpretado = this.instruccion.interpretar(arbol, tabla);
        if (valorInterpretado instanceof ErroresExpresiones) return valorInterpretado;

        // Verificar el tipo de casteo
        switch (this.tipo.getTipoDato()) {
            case ENTERO:
                if (valorInterpretado instanceof Double) {
                    super.tipo.setTipoDato(TipoDato.ENTERO);
                    return ((Double) valorInterpretado).intValue();
                } else if (valorInterpretado instanceof Character) {
                    super.tipo.setTipoDato(TipoDato.ENTERO);
                    return (int) ((Character) valorInterpretado).charValue();
                }
                break;
            case DECIMAL:
                if (valorInterpretado instanceof Integer) {
                    super.tipo.setTipoDato(TipoDato.DECIMAL);
                    return ((Integer) valorInterpretado).doubleValue();
                } else if (valorInterpretado instanceof Character) {
                    super.tipo.setTipoDato(TipoDato.DECIMAL);
                    return (double) ((Character) valorInterpretado).charValue();
                }
                break;
            case CARACTER:
                if (valorInterpretado instanceof Integer) {
                    super.tipo.setTipoDato(TipoDato.CARACTER);
                    return (char) ((Integer) valorInterpretado).intValue();
                }
                break;
            default:
                return new ErroresExpresiones("SEMANTICO", "Casteo no soportado.", this.linea, this.columna);
        }

        return new ErroresExpresiones("SEMANTICO", "Casteo no válido.", this.linea, this.columna);
    }
}
