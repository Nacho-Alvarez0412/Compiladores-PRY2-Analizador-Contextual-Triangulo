/*
 * @(#)Token.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.SyntacticAnalyzer;

/** @author        Ignacio Alvarez
* @description   Cambios a la clase Token para modificar accesibilidad
* @funcionalidad Permitir acceso a la clase Token y funciones kind
* @codigo        I.4 
*/
final public class Token extends Object {

  public int kind;
  protected String spelling;
  protected SourcePosition position;
/*I.4 
  * final  class Token extends Object {
        protected int kind;
        protected String spelling;
        protected SourcePosition position;
  */
  // END CAMBIO IGNACIO

  public Token(int kind, String spelling, SourcePosition position) {

    if (kind == Token.IDENTIFIER) {
      int currentKind = firstReservedWord;
      boolean searching = true;

      while (searching) {
        int comparison = tokenTable[currentKind].compareTo(spelling);
        if (comparison == 0) {
          this.kind = currentKind;
          searching = false;
        } else if (comparison > 0 || currentKind == lastReservedWord) {
          this.kind = Token.IDENTIFIER;
          searching = false;
        } else {
          currentKind ++;
        }
      }
    } else
      this.kind = kind;

    this.spelling = spelling;
    this.position = position;

  }

  public static String spell (int kind) {
    return tokenTable[kind];
  }

  public String toString() {
    return "Kind=" + kind + ", spelling=" + spelling +
      ", position=" + position;
  }
  
  // @author        Andres
  // @description   Cambio en los identifiers de los tokens
  // @funcionalidad Cambios de la lista de tokens
  // @codigo        A.1 
  // Token classes...
  
  public static final int

    // literals, identifiers, operators...
    INTLITERAL	= 0,
    CHARLITERAL	= 1,
    IDENTIFIER	= 2,
    OPERATOR	= 3,

    // reserved words - must be in alphabetical order...
    ARRAY		= 4,
    CHOOSE              = 5,
    CONST		= 6,
    DO			= 7,
    ELSE		= 8,
    ELSIF               = 9,
    END			= 10,
    FOR                 = 11,
    FROM                = 12,
    FUNC		= 13,
    IF			= 14,
    IN			= 15,
    LET			= 16,
    LOOP                = 17,
    NOTHING             = 18,
    OF			= 19,
    PACKAGE             = 20,
    PRIVATE             = 21,
    PROC		= 22,
    RECORD		= 23,
    RECURSIVE           = 24,
    THEN		= 25,
    TO                  = 26,
    TYPE		= 27,
    UNTIL               = 28,
    VAR			= 29,
    WHEN                = 30,
    WHILE		= 31,
     
    // punctuation...
    DOT			= 32,
    COLON		= 33,
    SEMICOLON	= 34,
    COMMA		= 35,
    BECOMES		= 36,
    IS			= 37,
    // Triangle Extended punctuation...
    PIPE                = 38,
    DOLLAR              = 39,
    DOUBLEDOT           = 40,

    // brackets...
    LPAREN		= 41,
    RPAREN		= 42,
    LBRACKET	= 43,
    RBRACKET	= 44,
    LCURLY		= 45,
    RCURLY		= 46,

    // special tokens...
    EOT			= 47,
    ERROR		= 48;
  /* A.1
  // Token classes...

  public static final int

    // literals, identifiers, operators...
    INTLITERAL	= 0,
    CHARLITERAL	= 1,
    IDENTIFIER	= 2,
    OPERATOR	= 3,

    // reserved words - must be in alphabetical order...
    ARRAY		= 4,
    BEGIN		= 5,
    CONST		= 6,
    DO			= 7,
    ELSE		= 8,
    END			= 9,
    FUNC		= 10,
    IF			= 11,
    IN			= 12,
    LET			= 13,
    OF			= 14,
    PROC		= 15,
    RECORD		= 16,
    THEN		= 17,
    TYPE		= 18,
    VAR			= 19,
    WHILE		= 20,

    // punctuation...
    DOT			= 21,
    COLON		= 22,
    SEMICOLON	= 23,
    COMMA		= 24,
    BECOMES		= 25,
    IS			= 26,

    // brackets...
    LPAREN		= 27,
    RPAREN		= 28,
    LBRACKET	= 29,
    RBRACKET	= 30,
    LCURLY		= 31,
    RCURLY		= 32,

    // special tokens...
    EOT			= 33,
    ERROR		= 34;
  */
  // END CAMBIO Andres
  
  // @author        Andres
  // @description   Cambio en la tabla de tokens
  // @funcionalidad Cambios de la lista de tokens
  // @codigo        A.2 
  // Triangle extended token table
  private static String[] tokenTable = new String[] {
    "<int>",
    "<char>",
    "<identifier>",
    "<operator>",
    "array",
    "choose",
    "const",
    "do",
    "else",
    "elsif",
    "end",
    "for",
    "from",
    "func",
    "if",
    "in",
    "let",
    "loop",
    "nothing", 
    "of",
    "package",
    "private",
    "proc",
    "record",
    "recursive",
    "then",
    "to",
    "type",
    "until",
    "var",
    "when",
    "while",
    ".",
    ":",
    ";",
    ",",
    ":=",
    "~",
    "|",
    "$",
    "..",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "",
    "<error>"
  };
  /* A.2
  private static String[] tokenTable = new String[] {
    "<int>",
    "<char>",
    "<identifier>",
    "<operator>",
    "array",
    "begin",
    "const",
    "do",
    "else",
    "end",
    "func",
    "if",
    "in",
    "let",
    "of",
    "proc",
    "record",
    "then",
    "type",
    "var",
    "while",
    ".",
    ":",
    ";",
    ",",
    ":=",
    "~",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "",
    "<error>"
  };
  */
  // END Cambios Andres

  private final static int	firstReservedWord = Token.ARRAY,
  				lastReservedWord  = Token.WHILE;

}
