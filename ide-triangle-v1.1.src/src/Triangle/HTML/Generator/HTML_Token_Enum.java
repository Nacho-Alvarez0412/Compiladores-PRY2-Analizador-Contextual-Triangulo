/**
 * @newEnum
 * @description Enum que representa los tokens de un código, para poder ser interpretados por el generador de HTML
 * @author Ignacio Alvarez
 * @codigo I.0
 */
package Triangle.HTML.Generator;

/**
 *
 * @author nacho
 */
public enum HTML_Token_Enum {
            RESERVED, 
            IDENTIFIER,
            OPERATOR,
            SEPARATOR,
            LITERAL,
            COMMENT,
            TAB,
            SPACE,
            ENTER
}
