/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa el ElseCase AST
 * @author Andres
 * A.61
 */
public class ElseCase extends AST {
    
    public ElseCase(Command cAST, SourcePosition thePosition) {
        super(thePosition);
        
        C = cAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitElseCase(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitElseCase(this, o);
    }
    
    public Command C;
    
}
