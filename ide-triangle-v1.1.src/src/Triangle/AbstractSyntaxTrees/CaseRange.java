/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa un CaseRange AST
 * @author Andres
 * A.34
 */
public abstract class CaseRange extends AST {
    
    public CaseRange (SourcePosition thePosition) {
        super (thePosition);
    }
    
}
