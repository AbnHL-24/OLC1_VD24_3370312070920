package edu.compscript.model.analisis;

//importaciones
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import edu.compscript.model.interprete.excepciones.ErroresExpresiones;

%%
// Codigo de usuario, si es necesario
%{
    public LinkedList<ErroresExpresiones> erroresLexicos = new LinkedList<>();
%}

%init{
yyline = 1;
yycolumn = 1;
erroresLexicos = new LinkedList<>();
%init}

// Definiciones de caracterisitca de JFlex.
%cup // Usaremos cup para la parte sintactica.
%class Analizador // Nombre de la clase.
%public // Modificador de acceso.
%line // Cointeo de lineas.
%column // Conteo de columnas.
%char // Conteo de caracteres.
%full // reconocimiento de caracteres completos.
%debug // Modo de depuracion.
%ignorecase // Ignorar mayusculas y minusculas.

// Creación de estados.
//%state CADENA

//  Palabras reservadas.
IMPRIMIR = "imprimir"

// Símbolos del sistema.

CADENA = [\"]((\\\")|[^\"\n]*)[\"] // Cadena de texto.
DECIMAL = [0-9]+"."[0-9]+
ENTERO = [0-9]+
FIN_CADENA = ";"

// Operadores aritméticos
MAS = "+"
MENOS = "-"
MULT = "*"
DIV = "/"
POW = "^"
ROOT = "$"
MOD = "%"
ASIGNACION = "="

// Operadores lógicos
OR = "||"
AND = "&&"
NOT = "!"

// Operadores relacionales
IGUAL = "=="
DIFERENTE = "!="
MENOR = "<"
MENOR_IGUAL = "<="
MAYOR = ">"
MAYOR_IGUAL = ">="

PARENT_IZQ = "("
PARENT_DER = ")"
BLANCOS = [\ \r\t\f\n]+

%%
// Importa el orden, generalmente primero van las palabras reservadas y todos aquellos que puede crear conflico.
// Las palabras reservadas <NOMBRE_TOKEN, LEXEMA>.
<YYINITIAL> {IMPRIMIR} { return new Symbol(sym.IMPRIMIR, yyline, yycolumn, yytext());  }
<YYINITIAL> {DECIMAL} { return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext());  }
<YYINITIAL> {ENTERO} { return new Symbol(sym.ENTERO, yyline, yycolumn, yytext());  }
<YYINITIAL> {CADENA} {String cadena = yytext();
                            cadena = cadena.substring(1, cadena.length()-1);
                            return new Symbol(sym.CADENA, yyline, yycolumn, cadena);  }
// Simbolos
<YYINITIAL> {FIN_CADENA} { return new Symbol(sym.FIN_CADENA, yyline, yycolumn, yytext());  }
<YYINITIAL> {MAS} { return new Symbol(sym.MAS, yyline, yycolumn, yytext());  }
<YYINITIAL> {MENOS} { return new Symbol(sym.MENOS, yyline, yycolumn, yytext());  }
<YYINITIAL> {MULT} { return new Symbol(sym.MULT, yyline, yycolumn, yytext());  }
<YYINITIAL> {DIV} { return new Symbol(sym.DIV, yyline, yycolumn, yytext());  }
<YYINITIAL> {POW} { return new Symbol(sym.POW, yyline, yycolumn, yytext());  }
<YYINITIAL> {ROOT} { return new Symbol(sym.ROOT, yyline, yycolumn, yytext());  }
<YYINITIAL> {MOD} { return new Symbol(sym.MOD, yyline, yycolumn, yytext());  }
<YYINITIAL> {PARENT_IZQ} { return new Symbol(sym.PARENT_IZQ, yyline, yycolumn, yytext());  }
<YYINITIAL> {PARENT_DER} { return new Symbol(sym.PARENT_DER, yyline, yycolumn, yytext());  }
<YYINITIAL> {BLANCOS} {}
// Logicos y relacionales
<YYINITIAL> {OR} { return new Symbol(sym.OR, yyline, yycolumn, yytext()); }
<YYINITIAL> {AND} { return new Symbol(sym.AND, yyline, yycolumn, yytext()); }
<YYINITIAL> {NOT} { return new Symbol(sym.NOT, yyline, yycolumn, yytext()); }
<YYINITIAL> {IGUAL} { return new Symbol(sym.IGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIFERENTE} { return new Symbol(sym.DIFERENTE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOR} { return new Symbol(sym.MENOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOR_IGUAL} { return new Symbol(sym.MENOR_IGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYOR} { return new Symbol(sym.MAYOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYOR_IGUAL} { return new Symbol(sym.MAYOR_IGUAL, yyline, yycolumn, yytext()); }
// Errores lexicos.
<YYINITIAL> . {erroresLexicos.add(new ErroresExpresiones("LEXICO",
                "El caracter " +yytext()+" no pertenece al lenguaje",
                yyline, yycolumn));}

