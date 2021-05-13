/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa un Cases AST
 * @author Andres
 * A.63
 */
public abstract class Cases extends AST {
    
    public Cases(SourcePosition thePosition) {
        super(thePosition);
    }
    
}
