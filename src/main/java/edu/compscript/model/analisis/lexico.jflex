package model.analisis;

//importaciones
import java_cup.runtime.Symbol;

%%

// Codigo de usuario, si es necesario
%{
    String cadena = "";
%}

%init{
   yyline = 1;
   yycolumn = 1;
%init}

// Definiciones de caracterisitca de JFlex.
%cup // Usaremos cup para la parte sintactica.
%class AnalizadorLexico // Nombre de la clase.
%public // Modificador de acceso.
%line // Cointeo de lineas.
%column // Conteo de columnas.
%char // Conteo de caracteres.
%full // reconocimiento de caracteres completos.
%debug // Modo de depuracion.
%ignorecase // Ignorar mayusculas y minusculas.

// Creación de estados.
%state CADEMA

//  Palabras reservadas.
IMPRIMIR = "imprimir"

// Símbolos del sistema.
DECIMAL = [0-9]+"."[0-9]+
ENTERO = [0-9]+
FIN_DE_CADENA = ";"
MAS = "+"
MENOS = "-"
MULTIPLICACION = "*"
DIVISION = "/"
PARENTESIS_IZQ = "("
PARENTESIS_DER = ")"
BLANCOS = [\ \r\t\f\n]+

%%
// Importa el orden, generalmente primero van las palabras reservadas y todos aquellos que puede crear conflico.
// Las palabras reservadas <NOMBRE_TOKEN, LEXEMA>.
<YYINITIAL> {IMPRIMIR} { return new Symbol(sym.IMPRIMIR, yyline, yycolumn, yytext());  }
<YYINITIAL> {DECIMAL} { return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext());  }
<YYINITIAL> {ENTERO} { return new Symbol(sym.ENTEROS, yyline, yycolumn, yytext());  }
// Simbolos
<YYINITIAL> {FIN_DE_CADENA} { return new Symbol(sym.FIN_DE_CADENA, yyline, yycolumn, yytext());  }
<YYINITIAL> {MAS} { return new Symbol(sym.MAS, yyline, yycolumn, yytext());  }
<YYINITIAL> {MENOS} { return new Symbol(sym.MENO, yyline, yycolumn, yytext());  }
<YYINITIAL> {MULTIPLICACION} { return new Symbol(sym.MULTIPLICACION, yyline, yycolumn, yytext());  }
<YYINITIAL> {DIVISION} { return new Symbol(sym.DIVISION, yyline, yycolumn, yytext());  }
<YYINITIAL> {PARENTESIS_IZQ} { return new Symbol(sym.PARENTESIS_IZQ, yyline, yycolumn, yytext());  }
<YYINITIAL> {PARENTESIS_DER} { return new Symbol(sym.PARENTESIS_DER, yyline, yycolumn, yytext());  }
<YYINITIAL> {BLANCOS} {}

// Moverme a estado cadena.
<YYINITIAL> [\"] { yybegin(CADEMA); cadena = ""; }

<CADEMA> {
    [\"]    {String temporal = cadena; cadena = "";
            yybegin(YYINITIAL);
            return new Symbol(sym.CADENA, yyline, yycolumn, temporal); }
    [^\"]   { cadena += yytext(); }

}