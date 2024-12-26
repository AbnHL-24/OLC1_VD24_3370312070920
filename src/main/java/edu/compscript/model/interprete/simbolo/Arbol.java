package edu.compscript.model.interprete.simbolo;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.instrucciones.MetodoInstruccion;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class Arbol {
    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private LinkedList<ErroresExpresiones> errores;
    private TablaSimbolos tablaGlobal;
    private LinkedList<Instruccion> funciones;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        consola = "";
        this.errores = new LinkedList<>();
        this.tablaGlobal = new TablaSimbolos();
    }

    public void Print(String valor) {
        this.consola += valor + "\n";
    }

    public void agregarErrores(ErroresExpresiones error) {
        this.errores.add(error);
    }

    public void agregarFunciones(Instruccion funcion) {
        this.funciones.add(funcion);
    }

    public Instruccion getFuncion(String id) {
        for (var  i: this.funciones) {
            if (i instanceof MetodoInstruccion metodoInstruccion) {
                if (metodoInstruccion.getId().equalsIgnoreCase(id)) return i;
            }
        }
        return null;
    }
}
