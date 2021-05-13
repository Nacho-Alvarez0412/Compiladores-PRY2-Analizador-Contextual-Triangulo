/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Representa un ChooseCommand AST
 * @author Andres
 * A.96
 */
public class ChooseCommand extends Command {
    
    public ChooseCommand(Expression eAST, Cases csAST, 
            SourcePosition thePosition) {
        super(thePosition);
        
        E = eAST;
        CS = csAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitChooseCommand(this, o);
    }

    public Object visitXML(Visitor v, Object o) {
        return v.visitChooseCommand(this, o);
    }
    
    public Expression E;
    public Cases CS;
    
}
