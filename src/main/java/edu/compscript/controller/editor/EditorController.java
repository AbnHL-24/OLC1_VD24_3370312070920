package edu.compscript.controller.editor;

import edu.compscript.view.editor.editorView;

import javax.swing.*;
import java.io.*;

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
        String resultado = ejecutarInterprete(codigo); // Llama a tu lógica de interpretación
        view.getConsolaJTextArea().setText(resultado);
    }

    /**
     * Método que se encarga de ejecutar la lógica del intérprete para imprimir en la consola.
     * @param codigo
     * @return
     */
    private String ejecutarInterprete(String codigo) {
        // Aquí puedes invocar tu analizador léxico, sintáctico, o semántico
        return "Resultado de la ejecución del código:\n" + codigo;
    }
}
