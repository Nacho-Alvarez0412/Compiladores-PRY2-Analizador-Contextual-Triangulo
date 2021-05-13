/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents a LongIdentifier AST
 * @author Andres
 * A.122
 */
public abstract class LongIdentifier extends AST {
    
    public LongIdentifier(SourcePosition thePosition) {
        super(thePosition);
    }
    
    public Identifier I;
    
}
