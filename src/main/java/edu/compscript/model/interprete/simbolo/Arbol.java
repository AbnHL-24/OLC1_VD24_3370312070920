package edu.compscript.model.interprete.simbolo;

import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class Arbol {
    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private LinkedList<ErroresExpresiones> errores;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        consola = "";
        this.errores = new LinkedList<>();
    }

    public void Print(String valor) {
        this.consola += valor + "\n";
    }

    public void agregarErrores(ErroresExpresiones error) {
        this.errores.add(error);
    }
}
