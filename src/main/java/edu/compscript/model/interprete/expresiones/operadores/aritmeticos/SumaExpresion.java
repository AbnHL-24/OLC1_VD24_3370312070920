package edu.compscript.model.interprete.expresiones.operadores.aritmeticos;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.OperacionResultante;
import edu.compscript.model.interprete.expresiones.operadores.utilidades.tablas.aritmeticos.OperacionesSuma;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.model.interprete.simbolo.Tipo;
import edu.compscript.model.interprete.simbolo.TipoDato;

// TODO refactorizar todas las operaciones aritmeticas menos el uniario, en una clase padre llamada OperacionAritmeticaExpresion; se extraeá como metodo la parte donde se revisa la compatibilidad de los tipos de los operandos y la parte del return para que cada operación haga lo que tenga que hacer. Se creará una clase hija por cada operación aritmética que herede de OperacionAritmeticaExpresion.
public class SumaExpresion extends Instruccion {
    private Instruccion operadorIzq;
    private Instruccion operadorDer;

    public SumaExpresion(Instruccion operadorIzq,
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
        var valorIzq = this.operadorIzq.interpretar(arbol, tabla);
        var valorDer = this.operadorDer.interpretar(arbol, tabla);

        // Verificar errores.
        if (valorIzq instanceof ErroresExpresiones || valorDer instanceof ErroresExpresiones) {
            return valorIzq instanceof ErroresExpresiones ? valorIzq : valorDer;
        }

        // Se obtiene la operación correspondiente a las operaciones de operadorIzq y operadorDer.
        var tipoResultado = OperacionResultante.calcular(OperacionesSuma.tablaDeSumas,
                operadorIzq.tipo.getTipoDato().ordinal(),
                operadorDer.tipo.getTipoDato().ordinal());

        // FIXME Verificar si hay problema en el switch de abajo cuando viene un null en lugar de un TipoDato. Si hay que rectificar algo se debe implementar en todas las operaciones entre tipos.
        /*if (tipoResultado == null) {
            return new ErroresExpresiones("SEMÁNTICO", "Suma entre tipos incompatibles", linea, columna);
        }*/

        // FIXME Verificar que se hace cuando un TipoDato es nulo.
        // Establece el tipo resultante en él atributo Tipo de la superclase Instrucción.
        this.tipo.setTipoDato(tipoResultado);

        // Realizar la operación según el resultado de la combinación.
        return switch (tipoResultado) {
            case ENTERO -> (int) valorIzq + (int) valorDer;
            //case DECIMAL -> ((Number) valorIzq).doubleValue() + ((Number) valorDer).doubleValue();
            case DECIMAL -> (double) valorIzq + (double) valorDer;
            case CADENA -> valorIzq.toString() + valorDer.toString();
            default -> new ErroresExpresiones("SEMÁNTICO", "Operación suma entre tipos no soportada", linea, columna);
        };
    }
}
