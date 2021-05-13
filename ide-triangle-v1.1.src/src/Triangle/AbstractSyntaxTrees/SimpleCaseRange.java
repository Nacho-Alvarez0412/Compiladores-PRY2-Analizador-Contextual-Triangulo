/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa el AST de un SimpleCaseRange
 * @author Andres
 * A.35
 */
public class SimpleCaseRange extends CaseRange {
    
    public SimpleCaseRange(CaseLiteral clAST, SourcePosition thePosition) {
        super(thePosition);
        CL = clAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitSimpleCaseRange(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitSimpleCaseRange(this, o);
    }
    
    public CaseLiteral CL;
}
