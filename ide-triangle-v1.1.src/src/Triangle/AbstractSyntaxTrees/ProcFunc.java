package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 * @newclass
 * @description Clase abstracta para representar un AST de un procedure o una función
 * @author Joseph
 * @codigo J.32
 */
public abstract class ProcFunc extends ProcFuncs {

  public ProcFunc (SourcePosition thePosition) {
    super (thePosition);
  }
}