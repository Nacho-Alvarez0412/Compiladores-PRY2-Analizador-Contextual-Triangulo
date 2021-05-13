/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa el AST de un SequentialCaseRange
 * @author Andres
 * A.49
 */
public class SequentialCaseRange extends CaseRange {
    
    public SequentialCaseRange(CaseRange cr1AST, CaseRange cr2AST, 
            SourcePosition thePosition) {
        super(thePosition);
        
        CR1 = cr1AST;
        CR2 = cr2AST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitSequentialCaseRange(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitSequentialCaseRange(this, o);
    }
    
    public CaseRange CR1;
    public CaseRange CR2;
}
