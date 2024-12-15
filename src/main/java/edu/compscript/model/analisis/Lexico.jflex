package edu.compscript.model.analisis;

//importaciones
import java_cup.runtime.Symbol;

%%
// Codigo de usuario, si es necesario
%{
//    String cadena = "";
%}

%init{
yyline = 1;
yycolumn = 1;
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

MAS = "+"
MENOS = "-"
MULT = "*"
DIV = "/"
POW = "^"
ROOT = "$"
MOD = "%"

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

/*
// Moverme a estado cadena.
<YYINITIAL> [\"] { yybegin(CADENA); cadena = ""; }

<CADENA> {
    [\"]    {String temporal = cadena; cadena = "";
            yybegin(YYINITIAL);
            return new Symbol(sym.CADENA, yyline, yycolumn, temporal); }
    [^\"]   { cadena += yytext(); }

}*/
