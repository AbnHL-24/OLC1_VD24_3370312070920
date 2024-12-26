package edu.compscript;

import edu.compscript.controller.editor.EditorController;
import edu.compscript.model.analisis.Generador;
import edu.compscript.view.editor.EditorView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Generador generador = new Generador();
        generador.generadorCompilador();
        SwingUtilities.invokeLater(() -> {
            EditorView view = new EditorView();
            EditorController controller = new EditorController(view);
            controller.initController(); // Inicializa el controlador
        });
    }
}
