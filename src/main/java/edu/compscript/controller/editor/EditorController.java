package edu.compscript.controller.editor;

import edu.compscript.model.analisis.Analizador;
import edu.compscript.model.analisis.parser;
import edu.compscript.model.interprete.abstracto.Instruccion;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.model.interprete.simbolo.Arbol;
import edu.compscript.model.interprete.simbolo.TablaSimbolos;
import edu.compscript.view.editor.editorView;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

/**
 * Controlador de la vista del editor de código.
 */
public class EditorController {
    private final editorView view;

    public EditorController(editorView view) {
        this.view = view;
        initController();
    }

    /**
     * Inicializa el controlador de la vista del editor de código.
     */
    private void initController() {
        // Conectar los eventos a los métodos correspondientes
        view.getNuevoArchivoButton().addActionListener(e -> limpiarEditor());
        view.getAbrirArchivoButton().addActionListener(e -> abrirArchivo());
        view.getGuardarArchivoButton().addActionListener(e -> guardarArchivo());
        view.getEjecutarButton().addActionListener(e -> ejecutarCodigo());
    }

    /**
     * Limpia el editor de código al usar la opción de nuevo archivo.
     */
    private void limpiarEditor() {
        view.getEntradaJTextArea().setText("");
        view.getConsolaJTextArea().setText("");
    }

    /**
     * Abre un archivo de código fuente y lo muestra en el editor.
     */
    private void abrirArchivo() {
        // Lógica para abrir un archivo
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
                view.getEntradaJTextArea().setText(content.toString());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el archivo: " + ex.getMessage());
            }
        }
    }

    /**
     * Guarda el contenido del editor de código en un archivo.
     */
    private void guardarArchivo() {
        // Lógica para guardar un archivo
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(view.getEntradaJTextArea().getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage());
            }
        }
    }

    /**
     * Ejecuta el código fuente del editor y muestra el resultado en la consola.
     */
    private void ejecutarCodigo() {
        // Lógica para ejecutar el código
        String codigo = view.getEntradaJTextArea().getText();
        String resultado = ejecutarInterprete(codigo); // Llama a la lógica de interpretación
        view.getConsolaJTextArea().setText(resultado);
    }

    /**
     * Método que se encarga de ejecutar la lógica del intérprete para imprimir en la consola.
     * @param codigo
     * @return
     */
    private String ejecutarInterprete(String codigo) {
        try {
            Analizador analizador = new Analizador(new BufferedReader(new StringReader(codigo)));
            parser p = new parser(analizador);
            var resultado = p.parse();

            var ast = new Arbol((LinkedList<Instruccion>) resultado.value );
            var tabla = new TablaSimbolos();

            for (var a : ast.getInstrucciones()) {
                var res = a.interpretar(ast, tabla);
                if (res instanceof ErroresExpresiones) {
                    System.out.println(res.toString());
                }
                //System.out.println(res);
            }
            //System.out.println(ast.getConsola());

            System.out.println(ast);
            return ast.getConsola();
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }
}
