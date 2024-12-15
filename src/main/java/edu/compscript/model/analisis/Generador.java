package edu.compscript.model.analisis;

import java.io.File;

/**
 * Clase encargada de generar ecompilador del proyecto.
 */
public class Generador {
    /**
     * Este método encargado de generar el compilador.
     */
    public static void generadorCompilador() {
        try {
            // Es la ruta donde están todos los archivos de los analizadores.
            String ruta = "src/main/java/edu/compscript/model/analisis/";
            // Eliminar archivos previamente generados
            eliminarArchivo(ruta + "Analizador.java");
            eliminarArchivo(ruta + "parser.java");
            eliminarArchivo(ruta + "sym.java");

            generarLexico(ruta);
            generarSintactico(ruta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método encargado de generar el archivo lexico.
     *
     * @param ruta És la ruta donde se generará el archivo.
     */
    private static void generarLexico(String ruta) {
        try {
            String rutaLexico = ruta + "Lexico.jflex";
            // Ruta donde se encuentra el archivo Lexico.jflex.
            // -d indica donde se crea el archivo del compilado.
            String[] flex = {rutaLexico, "-d", ruta};
            jflex.Main.generate(flex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generarSintactico(String ruta) {
        try {
            String rutaSintactico = ruta + "Sintactico.cup";
            /*
            destdir establece donde se generará el archivo.
            Ruta de salida.
            -parser nombra al archivo compilado.
            Ruta del archivo Sintactico.cup.
             */
            String[] cup = {"-destdir", ruta, "-parser", "parser", rutaSintactico};
            java_cup.Main.main(cup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método encargado de eliminar un archivo si existe.
     *
     * @param rutaArchivo Ruta completa del archivo a eliminar.
     */
    private static void eliminarArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("Archivo eliminado: " + rutaArchivo);
            } else {
                System.err.println("No se pudo eliminar el archivo: " + rutaArchivo);
            }
        }
    }
}