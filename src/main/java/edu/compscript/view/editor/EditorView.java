package edu.compscript.view.editor;

import lombok.Getter;

import javax.swing.*;

@Getter
public class EditorView {
    private JPanel editorPane;
    private JSplitPane separator1;
    private JPanel menusJPanel;
    private JPanel edicionJPanel;
    private JButton nuevoArchivoButton;
    private JButton reporteErroresButton;
    private JButton ejecutarButton;
    private JButton abrirArchivoButton;
    private JButton guardarArchivoButton;
    private JButton reporteTablaSimbolos;
    private JButton reporteTokensButton;
    private JButton reporteASTButton;
    private JTextArea entradaJTextArea;
    private JTextArea consolaJTextArea;

    public void mostrarInterfaz() {
        JFrame frame = new JFrame("CompScript");
        frame.setContentPane(this.editorPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Abre la ventana en pantalla completa

        frame.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        // TODO Poner numero de linea al editor.
    }

}
