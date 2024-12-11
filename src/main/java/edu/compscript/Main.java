package edu.compscript;

import edu.compscript.model.analisis.Analizador;
import edu.compscript.model.analisis.parser;

import java.io.BufferedReader;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) {
        try {
            String input = "imprimir(1-2*3+10);";
            Analizador analizador = new Analizador(new BufferedReader(new StringReader(input)));
            parser p = new parser(analizador);
            var resultado = p.parse();
            System.out.println(resultado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}