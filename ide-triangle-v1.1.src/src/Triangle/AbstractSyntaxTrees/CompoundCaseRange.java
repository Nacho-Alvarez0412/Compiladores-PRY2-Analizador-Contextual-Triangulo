/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa el AST de un CompounCaseRange
 * @author Andres
 * A.36
 */
public class CompoundCaseRange extends CaseRange {
    
    public CompoundCaseRange(CaseLiteral clAST, CaseLiteral c2AST, 
            SourcePosition thePosition) {
        super(thePosition);
        CL1 = clAST;
        CL2 = c2AST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitCompoundCaseRange(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitCompoundCaseRange(this, o);
    }
    
    public CaseLiteral CL1;
    public CaseLiteral CL2;
}
