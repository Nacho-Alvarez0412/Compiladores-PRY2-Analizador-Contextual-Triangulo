/**
 * @newClass
 * @description Clase que representa los tokens interpretados por el generado de HTML
 * @author Ignacio Alvarez
 * @codigo I.1
 */
package Triangle.HTML.Generator;

import java.util.regex.Pattern;

/**
 *
 * @author nacho
 */
public class HTML_Token {
    private String spelling;
    private HTML_Token_Enum type;
    
    private static String[] reservedWords = new String[] {"array","choose","const","do","else","elsif","end",
                                                          "for","from","func","if","in","let","loop","nothing",
                                                          "of","package","private","proc","record","recursive",
                                                          "then","to","type","until","var","when","while" 
                                                        };
    private static String[] operators = new String[] {".",":",";",",",":=","~","|","$","..","+","-","*","/"};
    private static String[] separators = new String[] {"(",")","[","]","{","}","",};
    
    public HTML_Token(String spelling){
        this.spelling = spelling;
        this.type = identifyType(spelling);
    }
    
    public HTML_Token(){
        this.spelling = null;
        this.type = null;
    }

    private HTML_Token_Enum identifyType(String spelling) {
        if (spelling.contentEquals("\n")){
            return HTML_Token_Enum.ENTER;
        }
        if (spelling.contentEquals("\t")){
            return HTML_Token_Enum.TAB;
        }
        else if (spelling.contentEquals(" ")){
            return HTML_Token_Enum.SPACE;
        }
        
        else if (isReserved(spelling)){
            return HTML_Token_Enum.RESERVED;
        }
        
        else if (isOperator(spelling)){
            return HTML_Token_Enum.OPERATOR;
        }
        
        else if (isSeparator(spelling)){
            return HTML_Token_Enum.SEPARATOR;
        }
        
        else if (isLiteral(spelling)){
            return HTML_Token_Enum.LITERAL;
        }
        
        else if (spelling.charAt(0) == '!'){
            return HTML_Token_Enum.COMMENT;
        }
        return HTML_Token_Enum.IDENTIFIER;
    }

    public boolean isReserved(String spelling) {
        for (String reservedWord : HTML_Token.reservedWords) {
            if (spelling.contentEquals(reservedWord)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isOperator(String spelling) {
        for (String operator : HTML_Token.operators) {
            if (spelling.contentEquals(operator)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSeparator(String spelling) {
        for (String separator : HTML_Token.separators) {
            if (spelling.contentEquals(separator)) {
                return true;
            }
        }
        return false;
    }
    
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false; 
        }
        return pattern.matcher(strNum).matches();
    }
    
    public boolean isLiteral(String spelling){
        return (isNumeric(spelling) || isChar(spelling));
        
    }
    
    public boolean isChar(String spelling){
        return (spelling.charAt(0) == '\'') && (spelling.charAt(spelling.length()-1) == '\'');
    }
    
    @Override
    public String toString(){
        return this.spelling;
    }

    public HTML_Token_Enum getType() {
        return type;
    }

    public void setType(HTML_Token_Enum type) {
        this.type = type;
    }
            
}
