/**
 * @newclass
 * @description Clase para representar un AST de dos o más procedures-functions
 * @author Joseph
 * @codigo J.33
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

public abstract class ProcFuncs extends AST {

  public ProcFuncs (SourcePosition thePosition) {
    super (thePosition);
  }
}