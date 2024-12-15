package edu.compscript.controller.reportes;

import edu.compscript.model.interprete.excepciones.ErroresExpresiones;
import edu.compscript.view.reportes.ReporteDeErroresView;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

public class ReporteDeErroresController {
    private ReporteDeErroresView vista;

    public ReporteDeErroresController(ReporteDeErroresView vista) {
        this.vista = vista;
        //inicializar();
    }

    public void inicializar(LinkedList<ErroresExpresiones> errores) {
        // Configurar la tabla de errores
        String[] columnNames = {"#", "Tipo", "Descripción", "Línea", "Columna"};
        Class[] columnTypes = {Integer.class, String.class, String.class, Integer.class, Integer.class};

        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        };

        // Limpiar la tabla antes de agregar nuevos errores.
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        // Agregar los errores a la tabla.
        int i = 1;
        for (var e : errores) {
            var error = e.getError();
            error[0] = i;
            model.addRow(error);
            i++;
        }

        vista.getReporteErroresJTable().setModel(model);


    }

    public void agregarErrores(List<ErroresExpresiones> errores) {
        DefaultTableModel model = (DefaultTableModel) vista.getReporteErroresJTable().getModel();
        model.setRowCount(0); // Limpiar la tabla antes de agregar nuevos errores

        for (ErroresExpresiones error : errores) {
            model.addRow(error.getError());
        }
    }
}