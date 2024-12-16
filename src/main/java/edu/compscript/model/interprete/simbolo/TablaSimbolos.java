package edu.compscript.model.interprete.simbolo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class TablaSimbolos {
    private HashMap<String, Simbolo> tablaActual;
    private String nombre;

    public TablaSimbolos() {
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public boolean setVariable(Simbolo simbolo) {
        Simbolo busqueda = (Simbolo) this.tablaActual.get(simbolo.getId().toLowerCase());
        if (busqueda == null) {
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
            return true;
        }
        return false;
    }

    public Simbolo getVariable(String id) {
        Simbolo busqueda = (Simbolo) this.tablaActual.get(id.toLowerCase());
        if (busqueda != null) {
            return busqueda;
        }
        return null;
    }
}
