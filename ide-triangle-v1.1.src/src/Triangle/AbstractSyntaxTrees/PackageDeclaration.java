/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * Represents the PackageDeclaration AST
 * @author Andres
 * A.127
 */
public abstract class PackageDeclaration extends AST {
    
    public PackageDeclaration(SourcePosition thePosition) {
        super(thePosition);
    }
    
}
