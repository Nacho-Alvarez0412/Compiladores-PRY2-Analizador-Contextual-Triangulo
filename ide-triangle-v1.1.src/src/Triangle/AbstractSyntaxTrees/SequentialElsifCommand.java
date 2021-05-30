/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * AST para SequentialElsifCommand
 * @author Andres
 * A.24
 */
public class SequentialElsifCommand extends ElsifCommand {
    public ElsifCommand SE1;
    public ElsifCommand SE2;
    
    public SequentialElsifCommand(ElsifCommand c1AST, 
            ElsifCommand c2AST, SourcePosition thePosition) {
        super(thePosition);
        SE1 = c1AST;
        SE2 = c2AST;
    }
    
    
    public Object visit(Visitor v, Object o) {
        return v.visitSequentialElsifCommand(this, o);
    }
    

    public Object visitXML(Visitor v, Object o) {
        return v.visitSequentialElsifCommand(this, o);
    }

    @Override
    public Object visit2(Visitor v, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
