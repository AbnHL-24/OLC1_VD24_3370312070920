package edu.compscript.model.analisis;

/**
 * Clase encargada de generar ecompilador del proyecto.
 */
public class Generador {
    public static void main(String[] args) {
        generadorCompilador();
    }

    public static void generadorCompilador() {
        try {
            String ruta = "src/main/java/edu/compscript/model/analisis/";
            String rutaLexico = ruta + "Lexico.jflex";
            String rutaSintactico = ruta + "Sintactico.cup";
            /*
            Ruta del archivo de JFlex.
            -d indica donde se crea el archivo del compilado.
            Ruta de donde generar el compilado.
             */
            String[] flex = {rutaLexico, "-d", ruta};
            jflex.Main.generate(flex);

            /*
            destdir establece donde se generará el archivo.
            Ruta de salida.
            -parser nombra al archivo compilado.
            Ruta del archivo cup
             */
            String[] cup = {"-destdir", ruta, "-parser", "parser",rutaSintactico};
            java_cup.Main.main(cup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
