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
%buffer 16384

// Símbolos del sistema.

CADENA = [\"]((\\\")|[^\"\n]*)[\"] // Cadena de texto.
DECIMAL = [0-9]+"."[0-9]+
ENTERO = [0-9]+
FIN_CADENA = ";"
CONSOLE = "console"
PUNTO = "."
DOS_PUNTOS = ":"
LOG = "log"

// Tipos de datos, variables y constantes.
STRING = "string"
INT = "int"
DOUBLE = "double"
BOOLEANO = "bool"
CARACTER = "char"
LET = "let"
CONST = "const"
LLAVE_IZQ = "{"
LLAVE_DER = "}"
IF = "if"
ELSE = "else"
TRUE = "true"
FALSE = "false"
FOR = "for"
INCREMENTO = "++"
DECREMENTO = "--"

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
IGUALDAD = "=="
DIFERENTE = "!="
MENOR = "<"
MENOR_IGUAL = "<="
MAYOR = ">"
MAYOR_IGUAL = ">="

PARENT_IZQ = "("
PARENT_DER = ")"
BLANCOS = [\ \r\t\f\n]+
ID = [a-zA-Z][a-zA-Z0-9_]*

%%
// Importa el orden, generalmente primero van las palabras reservadas y todos aquellos que puede crear conflico.
// Las palabras reservadas <NOMBRE_TOKEN, LEXEMA>.

// Simbolos
<YYINITIAL> {FIN_CADENA} { return new Symbol(sym.FIN_CADENA, yyline, yycolumn, yytext());  }
<YYINITIAL> {INCREMENTO} { return new Symbol(sym.INCREMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {DECREMENTO} { return new Symbol(sym.DECREMENTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAS} { return new Symbol(sym.MAS, yyline, yycolumn, yytext());  }
<YYINITIAL> {MENOS} { return new Symbol(sym.MENOS, yyline, yycolumn, yytext());  }
<YYINITIAL> {MULT} { return new Symbol(sym.MULT, yyline, yycolumn, yytext());  }
<YYINITIAL> {DIV} { return new Symbol(sym.DIV, yyline, yycolumn, yytext());  }
<YYINITIAL> {POW} { return new Symbol(sym.POW, yyline, yycolumn, yytext());  }
<YYINITIAL> {ROOT} { return new Symbol(sym.ROOT, yyline, yycolumn, yytext());  }
<YYINITIAL> {MOD} { return new Symbol(sym.MOD, yyline, yycolumn, yytext());  }
<YYINITIAL> {PARENT_IZQ} { return new Symbol(sym.PARENT_IZQ, yyline, yycolumn, yytext());  }
<YYINITIAL> {PARENT_DER} { return new Symbol(sym.PARENT_DER, yyline, yycolumn, yytext());  }
// Logicos y relacionales
<YYINITIAL> {OR} { return new Symbol(sym.OR, yyline, yycolumn, yytext()); }
<YYINITIAL> {AND} { return new Symbol(sym.AND, yyline, yycolumn, yytext()); }
<YYINITIAL> {NOT} { return new Symbol(sym.NOT, yyline, yycolumn, yytext()); }
<YYINITIAL> {IGUALDAD} { return new Symbol(sym.IGUALDAD, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIFERENTE} { return new Symbol(sym.DIFERENTE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOR} { return new Symbol(sym.MENOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOR_IGUAL} { return new Symbol(sym.MENOR_IGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYOR} { return new Symbol(sym.MAYOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYOR_IGUAL} { return new Symbol(sym.MAYOR_IGUAL, yyline, yycolumn, yytext()); }
// Imprimir en consola.
<YYINITIAL> {CONSOLE} { return new Symbol(sym.CONSOLE, yyline, yycolumn, yytext()); }
<YYINITIAL> {PUNTO} { return new Symbol(sym.PUNTO, yyline, yycolumn, yytext()); }
<YYINITIAL> {LOG} { return new Symbol(sym.LOG, yyline, yycolumn, yytext()); }
// Tipos de datos, variables y constantes.
<YYINITIAL> {STRING} { return new Symbol(sym.STRING, yyline, yycolumn, yytext()); }
<YYINITIAL> {INT} { return new Symbol(sym.INT, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOUBLE} { return new Symbol(sym.DOUBLE, yyline, yycolumn, yytext()); }
<YYINITIAL> {BOOLEANO} { return new Symbol(sym.BOOLEANO, yyline, yycolumn, yytext()); }
<YYINITIAL> {CARACTER} { return new Symbol(sym.CARACTER, yyline, yycolumn, yytext()); }
<YYINITIAL> {LET} { return new Symbol(sym.LET, yyline, yycolumn, yytext()); }
<YYINITIAL> {CONST} { return new Symbol(sym.CONST, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOS_PUNTOS} { return new Symbol(sym.DOS_PUNTOS, yyline, yycolumn, yytext()); }
<YYINITIAL> {ASIGNACION} { return new Symbol(sym.ASIGNACION, yyline, yycolumn, yytext()); }
<YYINITIAL> {LLAVE_IZQ} { return new Symbol(sym.LLAVE_IZQ, yyline, yycolumn, yytext()); }
<YYINITIAL> {LLAVE_DER} { return new Symbol(sym.LLAVE_DER, yyline, yycolumn, yytext()); }
<YYINITIAL> {TRUE} { return new Symbol(sym.TRUE, yyline, yycolumn, yytext()); }
<YYINITIAL> {FALSE} { return new Symbol(sym.FALSE, yyline, yycolumn, yytext()); }
<YYINITIAL> {IF} { return new Symbol(sym.IF, yyline, yycolumn, yytext()); }
<YYINITIAL> {ELSE} { return new Symbol(sym.ELSE, yyline, yycolumn, yytext()); }
<YYINITIAL> {FOR} { return new Symbol(sym.FOR, yyline, yycolumn, yytext()); }
// De ultimo lo que no es palabra reservada.
<YYINITIAL> {DECIMAL} { return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext());  }
<YYINITIAL> {ENTERO} { return new Symbol(sym.ENTERO, yyline, yycolumn, yytext());  }
<YYINITIAL> {ID} { return new Symbol(sym.ID, yyline, yycolumn, yytext());  }
<YYINITIAL> {BLANCOS} {}
<YYINITIAL> {CADENA} {String cadena = yytext();
                            cadena = cadena.substring(1, cadena.length()-1);
                            return new Symbol(sym.CADENA, yyline, yycolumn, cadena);  }
// Comentarios.
<YYINITIAL> "/*"([^*]|\*[^/])*"*"+"/" { /* Ignorar comentarios multilínea */ }
<YYINITIAL> "//".*                    { /* Ignorar comentarios de una línea */ }


// Errores lexicos.
<YYINITIAL> . {erroresLexicos.add(new ErroresExpresiones("LEXICO",
                "El caracter " +yytext()+" no pertenece al lenguaje",
                yyline, yycolumn));}

