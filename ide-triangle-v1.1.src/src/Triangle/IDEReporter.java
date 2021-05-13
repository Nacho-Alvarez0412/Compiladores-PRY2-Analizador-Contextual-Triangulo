/*
 * IDE-Triangle v1.0
 * IDEReporter.java
 */

package Triangle;
import Triangle.SyntacticAnalyzer.SourcePosition;
import Triangle.*;

/**
 * Extends the Triangle.ErrorReporter class. Used to get the source lines
 * where errors are found. 
 *
 * @author Luis Leopoldo Pérez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class IDEReporter extends ErrorReporter {
     
    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    
    /** 
     * Creates a new instance of IDEReporter.
     */
    public IDEReporter() {
        super();
    }
    
    /**
     * Overrides the reportError method, adding the line number to an array.
     * @param message Error message
     * @param tokenName Name of the token
     * @param pos Position in the source file.
     */
    public void reportError(String message, String tokenName, SourcePosition pos) {
        errorPositions[numErrors] = pos.start;
        super.reportError(message, tokenName, pos);        
    }
    
    /**
     * Returns the line where the first error is.
     * @return Line number.
     */
    public int getFirstErrorPosition() {
        return(errorPositions[0]);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    private int errorPositions[] = new int[10];     // Array of error positions.
    // </editor-fold>
}
