/**
 * @newclass
 * @description Clase para agrupar los ASTs de las alternativas de single-declaration
 * @author Joseph
 * @codigo J.51
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public abstract class SingleDeclaration extends Declaration {

  public SingleDeclaration (SourcePosition thePosition) {
    super (thePosition);
  }
}