package edu.compscript.model.interprete.simbolo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class TablaSimbolos {
    private HashMap<String, Simbolo> tablaActual;
    private String nombre;
    private TablaSimbolos tablaPadre;

    public TablaSimbolos() {
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public TablaSimbolos(TablaSimbolos tablaPadre) {
        this.tablaPadre = tablaPadre;
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public boolean setVariable(Simbolo simbolo) {
        Simbolo busqueda = this.tablaActual.get(simbolo.getId().toLowerCase());
        if (busqueda == null) {
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
            return true;
        }
        return false;
    }

    public Simbolo getVariable(String id) {
        for (TablaSimbolos tabla = this; tabla != null; tabla = tabla.getTablaPadre()) {
            Simbolo busqueda = (Simbolo) tabla.getTablaActual().get(id.toLowerCase());
            if (busqueda != null) {
                return busqueda;
            }
        }
        return null;
    }
}
