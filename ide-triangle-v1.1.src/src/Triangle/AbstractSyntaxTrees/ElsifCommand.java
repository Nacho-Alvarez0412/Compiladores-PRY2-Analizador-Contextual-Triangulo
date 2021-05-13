/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Clase para representar un AST de un elsif
 * @author Andres
 * A.103
 */
public abstract class ElsifCommand extends Command {
    
    public ElsifCommand(SourcePosition thePosition) {
        super(thePosition);
    }
}
