package edu.compscript.model.interprete.excepciones;

import lombok.AllArgsConstructor;

/**
 * Esta es la clase para generar errores.
 */
@AllArgsConstructor
public class ErroresExpresiones {
    private String tipo;
    private String descripcion;
    private int linea;
    private int columna;

    @Override
    public String toString() {
        return "ErroresExpresiones{" +
                "tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", linea=" + linea +
                ", columna=" + columna +
                '}';
    }

    public Object[] getError() {
        return new Object[]{0, this.tipo, this.descripcion, this.linea, this.columna};
    }
}
