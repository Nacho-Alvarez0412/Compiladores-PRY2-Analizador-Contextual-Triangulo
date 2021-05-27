/*
 * @(#)Checker.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.ContextualAnalyzer;

import Triangle.ErrorReporter;
import Triangle.StdEnvironment;
import Triangle.AbstractSyntaxTrees.AnyTypeDenoter;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.BinaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.BoolTypeDenoter;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.Declaration;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.FieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.FormalParameter;
import Triangle.AbstractSyntaxTrees.FormalParameterSequence;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.Terminal;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.TypeDenoter;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.VarTDDeclaration;
import Triangle.AbstractSyntaxTrees.VarExpDeclaration;
import Triangle.AbstractSyntaxTrees.WhileLoopCommand;
import Triangle.AbstractSyntaxTrees.UntilLoopCommand;
import Triangle.AbstractSyntaxTrees.CompoundIfCommand;
import Triangle.AbstractSyntaxTrees.SequentialElsifCommand;
import Triangle.AbstractSyntaxTrees.SingleElsifCommand;
import Triangle.AbstractSyntaxTrees.DoLoopUntilCommand;
import Triangle.AbstractSyntaxTrees.DoLoopWhileCommand;
import Triangle.AbstractSyntaxTrees.ForLoopDoCommand;
import Triangle.AbstractSyntaxTrees.ForLoopWhileCommand;
import Triangle.AbstractSyntaxTrees.ForLoopUntilCommand;
import Triangle.AbstractSyntaxTrees.CaseLiteral;
import Triangle.AbstractSyntaxTrees.SimpleCaseRange;
import Triangle.AbstractSyntaxTrees.CompoundCaseRange;
import Triangle.AbstractSyntaxTrees.CaseLiterals;
import Triangle.AbstractSyntaxTrees.SequentialCaseRange;
import Triangle.AbstractSyntaxTrees.ElseCase;
import Triangle.AbstractSyntaxTrees.SingleCase;
import Triangle.AbstractSyntaxTrees.SequentialCase;
import Triangle.AbstractSyntaxTrees.SimpleCases;
import Triangle.AbstractSyntaxTrees.CompoundCases;
import Triangle.AbstractSyntaxTrees.ChooseCommand;
import Triangle.AbstractSyntaxTrees.Procedure;
import Triangle.AbstractSyntaxTrees.Function;
import Triangle.AbstractSyntaxTrees.SequentialProcFuncs;
import Triangle.AbstractSyntaxTrees.PrivDeclaration;
import Triangle.AbstractSyntaxTrees.RecDeclaration;
import Triangle.AbstractSyntaxTrees.ForFromCommand;
import Triangle.AbstractSyntaxTrees.SimpleVarName;
import Triangle.AbstractSyntaxTrees.DotVarName;
import Triangle.AbstractSyntaxTrees.SubscriptVarName;
import Triangle.AbstractSyntaxTrees.PackageIdentifier;
import Triangle.AbstractSyntaxTrees.PackageVname;
import Triangle.AbstractSyntaxTrees.SimpleLongIdentifier;
import Triangle.AbstractSyntaxTrees.PackageLongIdentifier;
import Triangle.AbstractSyntaxTrees.SinglePackageDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialPackageDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleProgram;
import Triangle.AbstractSyntaxTrees.CompoundProgram;
import Triangle.SyntacticAnalyzer.SourcePosition;

import java.util.ArrayList;

public final class Checker implements Visitor {

  //////////////////////////////////////////////////////////////////////////
  ///
  ///
  /// Metodos de an�lisis contextual nuevos o modificados
  ///
  ///
  //////////////////////////////////////////////////////////////////////////

  /*
   * public Object visitCallCommand(CallCommand ast, Object o) {
   * 
   * Declaration binding = (Declaration) ast.I.visit(this, null); if (binding ==
   * null) reportUndeclared(ast.I); else if (binding instanceof ProcDeclaration) {
   * ast.APS.visit(this, ((ProcDeclaration) binding).FPS); } else if (binding
   * instanceof ProcFormalParameter) { ast.APS.visit(this, ((ProcFormalParameter)
   * binding).FPS); } else
   * reporter.reportError("\"%\" is not a procedure identifier", ast.I.spelling,
   * ast.I.position); return null; }
   */

  public Object visitProcedure(Procedure ast, Object o) {
    return null;
  }

  public Object visitFunction(Function ast, Object o) {
    return null;
  }

  public Object visitSequentialProcFuncs(SequentialProcFuncs ast, Object o) {
    return null;
  }

  public Object visitRecDeclaration(RecDeclaration ast, Object o) {
    return null;
  }

  public Object visitChooseCommand(ChooseCommand ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    if (!eType.equals(StdEnvironment.integerType) && !eType.equals(StdEnvironment.charType)) {
      reporter.reportError("Choose expression must be an integer or character", null, ast.E.position);
    }
    ArrayList<ArrayList<String>> cases = (ArrayList<ArrayList<String>>) ast.CS.visit(this, eType);
    return null;
  }

  // @author Andres
  // @description Analisis contextual para case literal
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.1
  public Object visitCaseLiteral(CaseLiteral ast, Object o) {
    TypeDenoter chooseType = (TypeDenoter) o;
    TypeDenoter literalType = (TypeDenoter) ast.T.visit(this, null);
    if (!literalType.equals(chooseType)) {
      reporter.reportError("\"%\" is not of the same type as the choose expression", ast.T.spelling, ast.T.position);
    }
    return ast.T.spelling;
  }
  // END CAMBIO

  // @author Andres
  // @description Analisis contextual para simple case range
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.2
  public Object visitSimpleCaseRange(SimpleCaseRange ast, Object o) {
    String caseLiteralSpelling = (String) ast.CL.visit(this, o);
    ArrayList<String> caseRangeValues = new ArrayList<>();
    caseRangeValues.add(caseLiteralSpelling);

    return caseRangeValues;
  }
  // END CAMBIO

  // @author Andres
  // @description Analisis contextual para compound case range
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.3
  public Object visitCompoundCaseRange(CompoundCaseRange ast, Object o) {
    TypeDenoter chooseType = (TypeDenoter) o;

    String caseLiteral1Spelling = (String) ast.CL1.visit(this, o);
    String caseLiteral2Spelling = (String) ast.CL2.visit(this, o);

    ArrayList<String> caseRangeValues = new ArrayList<>();

    // Choose type is integer
    if (chooseType.equals(StdEnvironment.integerType)) {
      if (Integer.parseInt(caseLiteral1Spelling) > Integer.parseInt(caseLiteral2Spelling)) {
        reporter.reportError("First literal in case range must be less than the second literal", "", ast.position);
      }
      // Create case range values
      for (int i = Integer.parseInt(caseLiteral1Spelling); i < Integer.parseInt(caseLiteral2Spelling) + 1; i++) {
        caseRangeValues.add(String.valueOf(i));
      }
    } else if (chooseType.equals(StdEnvironment.charType)) {
      if (caseLiteral1Spelling.charAt(1) > caseLiteral2Spelling.charAt(1)) {
        reporter.reportError(
            "First character literal in case range must have a minor ascii code than the second literal", "",
            ast.position);
      }
      // Create range of values
      for (char c = caseLiteral1Spelling.charAt(1); c <= caseLiteral2Spelling.charAt(1); c++) {
        caseRangeValues.add("'" + c + "'");
      }
    }
    return caseRangeValues;
  }
  // END CAMBIO

  // @author Andres
  // @description Analisis contextual para case literals
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.6
  public Object visitCaseLiterals(CaseLiterals ast, Object o) {
    ArrayList<String> caseRangeValues = (ArrayList<String>) ast.CR.visit(this, o);
    for (int i = 0; i < caseRangeValues.size(); i++) {
      for (int j = i + 1; j < caseRangeValues.size(); j++) {
        if (caseRangeValues.get(i).equals(caseRangeValues.get(j))) {
          reporter.reportError("Repeated element in case literals", "", ast.position);
        }
      }
    }
    return caseRangeValues;
  }
  // END CAMBIO

  // @author Andres
  // @description Analisis contextual para sequential case range
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.4
  public Object visitSequentialCaseRange(SequentialCaseRange ast, Object o) {
    ArrayList<String> caseRangeValues1 = (ArrayList<String>) ast.CR1.visit(this, o);
    ArrayList<String> caseRangeValues2 = (ArrayList<String>) ast.CR2.visit(this, o);

    ArrayList<String> newCaseRangeValues = new ArrayList<String>();
    newCaseRangeValues.addAll(caseRangeValues1);
    newCaseRangeValues.addAll(caseRangeValues2);

    return newCaseRangeValues;
  }
  // END CAMBIO

  // @author Andres
  // @description Analisis contextual para else case
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.5
  public Object visitElseCase(ElseCase ast, Object o) {
    ast.C.visit(this, o);
    return null;
  }
  // END CAMBIO

  // @author Andres
  // @description Analisis contextual para compound cases
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.7
  public Object visitCompoundCases(CompoundCases ast, Object o) {
    // Get all case values
    ArrayList<ArrayList<String>> casesValues = new ArrayList<>();
    if (ast.C instanceof SequentialCase) {
      ArrayList<ArrayList<String>> cases = (ArrayList<ArrayList<String>>) ast.C.visit(this, o);
      casesValues.addAll(cases);
    } else if (ast.C instanceof SingleCase) {
      ArrayList<String> cases = (ArrayList<String>) ast.C.visit(this, o);
      casesValues.add(cases);
    }
    // Check if an element in the set has been repeated
    if (casesValues.size() > 1) {
      for (int i = 0; i < casesValues.size(); i++) {
        ArrayList<String> currentValues = casesValues.get(i);
        for (int j = 0; j < currentValues.size(); j++) {
          // Check if another set repeats the value
          for (int x = i + 1; x < casesValues.size(); x++) {
            if (casesValues.get(x).indexOf(currentValues.get(j)) != -1) {
              reporter.reportError("\"%\" is repeated in choose command", currentValues.get(j), ast.position);
            }
          }
        }
      }
    }
    ast.EC.visit(this, null);
    return casesValues;
  }
  // END CAMBIO

  // @author Andres
  // @description Analisis contextual para simple cases
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.8
  public Object visitSimpleCases(SimpleCases ast, Object o) {
    // Get all case values
    ArrayList<ArrayList<String>> casesValues = new ArrayList<>();
    if (ast.C instanceof SequentialCase) {
      ArrayList<ArrayList<String>> cases = (ArrayList<ArrayList<String>>) ast.C.visit(this, o);
      casesValues.addAll(cases);
    } else if (ast.C instanceof SingleCase) {
      ArrayList<String> cases = (ArrayList<String>) ast.C.visit(this, o);
      casesValues.add(cases);
    }
    // Check if an element in the set has been repeated
    if (casesValues.size() > 1) {
      for (int i = 0; i < casesValues.size(); i++) {
        ArrayList<String> currentValues = casesValues.get(i);
        for (int j = 0; j < currentValues.size(); j++) {
          // Check if another set repeats the value
          for (int x = i + 1; x < casesValues.size(); x++) {
            if (casesValues.get(x).indexOf(currentValues.get(j)) != -1) {
              reporter.reportError("\"%\" is repeated in choose command", currentValues.get(j), ast.position);
            }
          }
        }
      }
    }

    return casesValues;
  }
  // END CAMBIO

  // @author Andres
  // @description Analisis contextual para sequential case
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.9
  public Object visitSequentialCase(SequentialCase ast, Object o) {
    ArrayList<String> caseValues1 = (ArrayList<String>) ast.C2.visit(this, o);
    ArrayList<ArrayList<String>> casesValues = new ArrayList<>();
    casesValues.add(caseValues1);

    if (ast.C1 instanceof SequentialCase) {
      ArrayList<ArrayList<String>> caseValues2 = (ArrayList<ArrayList<String>>) ast.C1.visit(this, o);
      casesValues.addAll(caseValues2);
    } else {
      ArrayList<String> casesValues2 = (ArrayList<String>) ast.C1.visit(this, o);
      casesValues.add(casesValues2);
    }

    return casesValues;
  }
  // END CAMBIO

  // @author Andres
  // @description Analisis contextual para single case
  // @funcionlidad Analisis contextual instruccion choose
  // @codigo A.10
  public Object visitSingleCase(SingleCase ast, Object o) {
    ArrayList<String> caseValues = (ArrayList<String>) ast.CL.visit(this, o);
    ast.C.visit(this, null);
    return caseValues;
  }
  // END CAMBIO

  // Program

  public Object visitSimpleProgram(SimpleProgram ast, Object o) {
    ast.C.visit(this, null);
    return null;
  }

  public Object visitCompoundProgram(CompoundProgram ast, Object o) {
    ast.PD.visit(this, null);
    ast.C.visit(this, null);

    return null;
  }
  /*
   * public Object visitProgram(Program ast, Object o) { ast.C.visit(this, null);
   * return null; }
   */

  // Packages

  // @author Ignacio
  // @descripcion Modificacion visitPackageIdentifier
  // @funcionalidad Implementaci�n visitPackageIdentifier
  // @codigo I.6
  public Object visitPackageIdentifier(PackageIdentifier ast, Object o) {
    return ast.I.visit(this, o);
  }
  // END CAMBIO IGNACIO

  // @author Ignacio
  // @descripcion Modificacion visitPackageIdentifier
  // @funcionalidad Implementaci�n visitPackageIdentifier
  // @codigo I.8
  public Object visitPackageVname(PackageVname ast, Object o) {

    VarTDDeclaration packageDeclaration = (VarTDDeclaration) ast.PI.visit(this, null);

    if (packageDeclaration != null) {
      String variable = "";
      String packageId = packageDeclaration.I.spelling;
      if (ast.VN instanceof SimpleVarName) {
        SimpleVarName var = (SimpleVarName) ast.VN;
        variable = var.I.spelling;
      }

      else if (ast.VN instanceof DotVarName) {
        DotVarName var = (DotVarName) ast.VN;
        SimpleVarName var2 = (SimpleVarName) var.V;
        variable = var2.I.spelling;
      } else {
        SubscriptVarName var = (SubscriptVarName) ast.VN;
        SimpleVarName var2 = (SimpleVarName) var.V;
        variable = var2.I.spelling;
      }

      if (idTable.inPackage(packageId, variable)) {
        TypeDenoter vnType = (TypeDenoter) ast.VN.visit(this, packageId);

        ast.variable = ast.VN.variable;
        return vnType;
      }

      else {
        reporter.reportError(" \"%\" is not declared in the package", variable, ast.position);
        return null;
      }

    }
    reporter.reportError("package \"%\" is not declared", ast.PI.I.spelling, ast.position);
    return null;

  }
  // END CAMBIO IGNACIO

  // @author Ignacio
  // @descripcion Modificacion visitPackageIdentifier
  // @funcionalidad Implementaci�n visitPackageIdentifier
  // @codigo I.7
  public Object visitSimpleLongIdentifier(SimpleLongIdentifier ast, Object o) {
    return ast.I.visit(this, null);
  }

  public Object visitPackageLongIdentifier(PackageLongIdentifier ast, Object o) {
    return ast.I.visit(this, ast.PI.I.spelling);
  }

  public Object visitSinglePackageDeclaration(SinglePackageDeclaration ast, Object o) {
    if (ast.PI.visit(this, null) == null) {
      Declaration dummyDeclaration = new VarTDDeclaration(ast.PI.I, null, dummyPos);
      idTable.enter(ast.PI.I.spelling, dummyDeclaration);
      idTable.openPackageScope(ast.PI.I.spelling);
      ast.D.visit(this, null);
      idTable.closePackageScope();
    } else
      reporter.reportError("package \"%\" already declared", ast.PI.I.spelling, ast.position);
    return null;
  }

  public Object visitSequentialPackageDeclaration(SequentialPackageDeclaration ast, Object o) {
    ast.PD1.visit(this, null);
    ast.PD2.visit(this, null);
    return null;
  }

  // END CAMBIO IGNACIO

  // Teminan metodos nuevos o modificados

  // Commands

  // Always returns null. Does not use the given object.

  public Object visitCallCommand(CallCommand ast, Object o) {
    Declaration binding = (Declaration) ast.LI.visit(this, null);
    if (binding == null)
      reportUndeclared(ast.LI.I);
    else if (binding instanceof ProcDeclaration) {
      ast.APS.visit(this, ((ProcDeclaration) binding).FPS);
    } else if (binding instanceof ProcFormalParameter) {
      ast.APS.visit(this, ((ProcFormalParameter) binding).FPS);
    } else
      reporter.reportError("\"%\" is not a procedure identifier", ast.LI.I.spelling, ast.LI.position);
    return null;
  }
  /*
   * public Object visitCallCommand(CallCommand ast, Object o) {
   * 
   * Declaration binding = (Declaration) ast.I.visit(this, null); if (binding ==
   * null) reportUndeclared(ast.I); else if (binding instanceof ProcDeclaration) {
   * ast.APS.visit(this, ((ProcDeclaration) binding).FPS); } else if (binding
   * instanceof ProcFormalParameter) { ast.APS.visit(this, ((ProcFormalParameter)
   * binding).FPS); } else
   * reporter.reportError("\"%\" is not a procedure identifier", ast.I.spelling,
   * ast.I.position); return null; }
   */

  public Object visitAssignCommand(AssignCommand ast, Object o) {

    TypeDenoter vType = (TypeDenoter) ast.V.visit(this, null);
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);

    if (!ast.V.variable)
      reporter.reportError("LHS of assignment is not a variable", "", ast.V.position);
    if (!eType.equals(vType))
      reporter.reportError("assignment incompatibilty", "", ast.position);
    return null;
  }

  public Object visitEmptyCommand(EmptyCommand ast, Object o) {
    return null;
  }

  // @author Joseph
  // @descripcion Modificacion de metodos checker de if
  // @funcionalidad Implementacion de metodos checker para comandos
  // @codigo J.8

  public Object visitIfCommand(IfCommand ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    if (!eType.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E.position);
    ast.C1.visit(this, null);
    ast.C2.visit(this, null);
    return null;
  }

  public Object visitCompoundIfCommand(CompoundIfCommand ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    if (!eType.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E.position);
    ast.C1.visit(this, null);
    ast.EIC.visit(this, null);
    ast.C2.visit(this, null);
    return null;
  }

  public Object visitSingleElsifCommand(SingleElsifCommand ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    if (!eType.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E.position);
    ast.C.visit(this, null);
    return null;
  }

  public Object visitSequentialElsifCommand(SequentialElsifCommand ast, Object o) {
    ast.SE1.visit(this, null);
    ast.SE2.visit(this, null);
    return null;
  }

  /*
   * J.8 public Object visitIfCommand(IfCommand ast, Object o) { TypeDenoter eType
   * = (TypeDenoter) ast.E.visit(this, null); if (!
   * eType.equals(StdEnvironment.booleanType))
   * reporter.reportError("Boolean expression expected here", "", ast.E.position);
   * ast.C1.visit(this, null); ast.C2.visit(this, null); return null; }
   */

  // END CAMBIO Joseph

  public Object visitLetCommand(LetCommand ast, Object o) {
    idTable.openScope();
    ast.D.visit(this, null);
    ast.C.visit(this, null);
    idTable.closeScope();
    return null;
  }

  public Object visitSequentialCommand(SequentialCommand ast, Object o) {
    ast.C1.visit(this, null);
    ast.C2.visit(this, null);
    return null;
  }

  // @author Joseph
  // @descripcion Modificacion de metodos checker de comandos loop
  // @funcionalidad Implementacion de metodos checker para comandos
  // @codigo J.6
  public Object visitWhileLoopCommand(WhileLoopCommand ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    if (!eType.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E.position);
    ast.C.visit(this, null);
    return null;
  }

  public Object visitUntilLoopCommand(UntilLoopCommand ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    if (!eType.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E.position);
    ast.C.visit(this, null);
    return null;
  }

  public Object visitDoLoopWhileCommand(DoLoopWhileCommand ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    if (!eType.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E.position);
    ast.C.visit(this, null);
    return null;
  }

  public Object visitDoLoopUntilCommand(DoLoopUntilCommand ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    if (!eType.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E.position);
    ast.C.visit(this, null);
    return null;
  }
  // END CAMBIO Joseph

  // @author Joseph
  // @descripcion Modificacion de metodos checker de comandos loop for
  // @funcionalidad Implementacion de metodos checker para comandos
  // @codigo J.7
  public Object visitForFromCommand(ForFromCommand ast, Object o) {
    ConstDeclaration controlVarDecl = new ConstDeclaration(ast.I,
        new IntegerExpression(new IntegerLiteral("0", ast.position), ast.position), ast.position);
    idTable.openScope();
    controlVarDecl.visit(this, null);
    if (controlVarDecl.duplicated)
      reporter.reportError("identifier \"%\" already declared", ast.I.spelling, ast.position);
    return null;
  }

  public Object visitForLoopWhileCommand(ForLoopWhileCommand ast, Object o) {
    TypeDenoter e1Type = (TypeDenoter) ast.E1.visit(this, null);
    if (!e1Type.equals(StdEnvironment.integerType))
      reporter.reportError("Integer expression expected here", "", ast.E1.position);
    TypeDenoter e2Type = (TypeDenoter) ast.FFC.E.visit(this, null);
    if (!e2Type.equals(StdEnvironment.integerType))
      reporter.reportError("Integer expression expected here", "", ast.FFC.E.position);
    ast.FFC.visit(this, null);
    TypeDenoter e3Type = (TypeDenoter) ast.E2.visit(this, null);
    if (!e3Type.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E2.position);
    ast.C.visit(this, null);
    idTable.closeScope();
    return null;
  }

  public Object visitForLoopUntilCommand(ForLoopUntilCommand ast, Object o) {
    TypeDenoter e1Type = (TypeDenoter) ast.E1.visit(this, null);
    if (!e1Type.equals(StdEnvironment.integerType))
      reporter.reportError("Integer expression expected here", "", ast.E1.position);
    TypeDenoter e2Type = (TypeDenoter) ast.FFC.E.visit(this, null);
    if (!e2Type.equals(StdEnvironment.integerType))
      reporter.reportError("Integer expression expected here", "", ast.FFC.E.position);
    ast.FFC.visit(this, null);
    TypeDenoter e3Type = (TypeDenoter) ast.E2.visit(this, null);
    if (!e3Type.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E2.position);
    ast.C.visit(this, null);
    idTable.closeScope();
    return null;
  }

  public Object visitForLoopDoCommand(ForLoopDoCommand ast, Object o) {
    TypeDenoter e1Type = (TypeDenoter) ast.E.visit(this, null);
    if (!e1Type.equals(StdEnvironment.integerType))
      reporter.reportError("Integer expression expected here", "", ast.E.position);
    TypeDenoter e2Type = (TypeDenoter) ast.FFC.E.visit(this, null);
    if (!e2Type.equals(StdEnvironment.integerType))
      reporter.reportError("Integer expression expected here", "", ast.FFC.E.position);
    ast.FFC.visit(this, null);
    ast.C.visit(this, null);
    idTable.closeScope();
    return null;
  }

  // END CAMBIO Joseph

  // Expressions

  // Returns the TypeDenoter denoting the type of the expression. Does
  // not use the given object.

  public Object visitCallExpression(CallExpression ast, Object o) {
    Declaration binding = (Declaration) ast.LI.visit(this, null);

    if (binding == null) {
      reportUndeclared(ast.LI.I);
      ast.type = StdEnvironment.errorType;
    } else if (binding instanceof FuncDeclaration) {
      ast.APS.visit(this, ((FuncDeclaration) binding).FPS);
      ast.type = ((FuncDeclaration) binding).T;
    } else if (binding instanceof FuncFormalParameter) {
      ast.APS.visit(this, ((FuncFormalParameter) binding).FPS);
      ast.type = ((FuncFormalParameter) binding).T;
    } else
      reporter.reportError("\"%\" is not a function identifier", ast.LI.I.spelling, ast.LI.position);
    return ast.type;
  }

  public Object visitArrayExpression(ArrayExpression ast, Object o) {
    TypeDenoter elemType = (TypeDenoter) ast.AA.visit(this, null);
    IntegerLiteral il = new IntegerLiteral(new Integer(ast.AA.elemCount).toString(), ast.position);
    ast.type = new ArrayTypeDenoter(il, elemType, ast.position);
    return ast.type;
  }

  public Object visitBinaryExpression(BinaryExpression ast, Object o) {

    TypeDenoter e1Type = (TypeDenoter) ast.E1.visit(this, null);
    TypeDenoter e2Type = (TypeDenoter) ast.E2.visit(this, null);
    Declaration binding = (Declaration) ast.O.visit(this, null);

    if (binding == null)
      reportUndeclared(ast.O);
    else {
      if (!(binding instanceof BinaryOperatorDeclaration))
        reporter.reportError("\"%\" is not a binary operator", ast.O.spelling, ast.O.position);
      BinaryOperatorDeclaration bbinding = (BinaryOperatorDeclaration) binding;
      if (bbinding.ARG1 == StdEnvironment.anyType) {
        // this operator must be "=" or "\="
        if (!e1Type.equals(e2Type))
          reporter.reportError("incompatible argument types for \"%\"", ast.O.spelling, ast.position);
      } else if (!e1Type.equals(bbinding.ARG1))
        reporter.reportError("wrong argument type for \"%\"", ast.O.spelling, ast.E1.position);
      else if (!e2Type.equals(bbinding.ARG2))
        reporter.reportError("wrong argument type for \"%\"", ast.O.spelling, ast.E2.position);
      ast.type = bbinding.RES;
    }
    return ast.type;
  }

  public Object visitCharacterExpression(CharacterExpression ast, Object o) {
    ast.type = StdEnvironment.charType;
    return ast.type;
  }

  public Object visitEmptyExpression(EmptyExpression ast, Object o) {
    ast.type = null;
    return ast.type;
  }

  public Object visitIfExpression(IfExpression ast, Object o) {
    TypeDenoter e1Type = (TypeDenoter) ast.E1.visit(this, null);
    if (!e1Type.equals(StdEnvironment.booleanType))
      reporter.reportError("Boolean expression expected here", "", ast.E1.position);
    TypeDenoter e2Type = (TypeDenoter) ast.E2.visit(this, null);
    TypeDenoter e3Type = (TypeDenoter) ast.E3.visit(this, null);
    if (!e2Type.equals(e3Type))
      reporter.reportError("incompatible limbs in if-expression", "", ast.position);
    ast.type = e2Type;
    return ast.type;
  }

  public Object visitIntegerExpression(IntegerExpression ast, Object o) {
    ast.type = StdEnvironment.integerType;
    return ast.type;
  }

  public Object visitLetExpression(LetExpression ast, Object o) {
    idTable.openScope();
    ast.D.visit(this, null);
    ast.type = (TypeDenoter) ast.E.visit(this, null);
    idTable.closeScope();
    return ast.type;
  }

  public Object visitRecordExpression(RecordExpression ast, Object o) {

    FieldTypeDenoter rType = (FieldTypeDenoter) ast.RA.visit(this, null);
    ast.type = new RecordTypeDenoter(rType, ast.position);
    return ast.type;
  }

  public Object visitUnaryExpression(UnaryExpression ast, Object o) {

    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    Declaration binding = (Declaration) ast.O.visit(this, null);
    if (binding == null) {
      reportUndeclared(ast.O);
      ast.type = StdEnvironment.errorType;
    } else if (!(binding instanceof UnaryOperatorDeclaration))
      reporter.reportError("\"%\" is not a unary operator", ast.O.spelling, ast.O.position);
    else {
      UnaryOperatorDeclaration ubinding = (UnaryOperatorDeclaration) binding;
      if (!eType.equals(ubinding.ARG))
        reporter.reportError("wrong argument type for \"%\"", ast.O.spelling, ast.O.position);
      ast.type = ubinding.RES;
    }
    return ast.type;
  }

  public Object visitVnameExpression(VnameExpression ast, Object o) {

    ast.type = (TypeDenoter) ast.V.visit(this, null);
    return ast.type;
  }

  // Declarations

  // Always returns null. Does not use the given object.
  public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o) {
    return null;
  }

  // @author Joseph
  // @description Implementacion del metodo checker de declaraciones de variables
  // inicializadas
  // @funcionalidad Implementacion de metodos checker de ASTs de declaraciones
  // @codigo J.1
  public Object visitVarExpDeclaration(VarExpDeclaration ast, Object o) {
    ast.T = (TypeDenoter) ast.E.visit(this, null);
    idTable.enter(ast.I.spelling, ast);
    if (ast.duplicated)
      reporter.reportError("identifier \"%\" already declared", ast.I.spelling, ast.position);
    return null;
  }
  // END CAMBIO Joseph

  // @author Joseph
  // @description Implementacion del metodo checker de declaraciones de variables
  // @funcionalidad Implementacion de metodos checker de ASTs de declaraciones
  // @codigo J.2
  public Object visitVarTDDeclaration(VarTDDeclaration ast, Object o) {
    ast.T = (TypeDenoter) ast.T.visit(this, null);
    idTable.enter(ast.I.spelling, ast);
    if (ast.duplicated)
      reporter.reportError("identifier \"%\" already declared", ast.I.spelling, ast.position);
    return null;
  }
  /*
   * J.2 public Object visitWhileCommand(WhileCommand ast, Object o) { TypeDenoter
   * eType = (TypeDenoter) ast.E.visit(this, null); if (!
   * eType.equals(StdEnvironment.booleanType))
   * reporter.reportError("Boolean expression expected here", "", ast.E.position);
   * ast.C.visit(this, null); return null; }
   */
  // END CAMBIO Joseph

  // @author Joseph
  // @description Implementacion del metodo checker de declaraciones privadas
  // @funcionalidad Implementacion de metodos checker de ASTs de declaraciones
  // @codigo J.3
  public Object visitPrivDeclaration(PrivDeclaration ast, Object o) {
    idTable.openScope();
    ast.D1.visit(this, null);
    idTable.togglePrivateFlag();
    ast.D2.visit(this, null);
    idTable.togglePrivateFlag();
    idTable.closeScope();
    return null;
  }
  // END CAMBIO Joseph

  public Object visitConstDeclaration(ConstDeclaration ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    idTable.enter(ast.I.spelling, ast);
    if (ast.duplicated)
      reporter.reportError("identifier \"%\" already declared", ast.I.spelling, ast.position);
    return null;
  }

  public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {
    ast.T = (TypeDenoter) ast.T.visit(this, null);
    idTable.enter(ast.I.spelling, ast); // permits recursion
    if (ast.duplicated)
      reporter.reportError("identifier \"%\" already declared", ast.I.spelling, ast.position);
    idTable.openScope();
    ast.FPS.visit(this, null);
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    idTable.closeScope();
    if (!ast.T.equals(eType))
      reporter.reportError("body of function \"%\" has wrong type", ast.I.spelling, ast.E.position);
    return null;
  }

  public Object visitProcDeclaration(ProcDeclaration ast, Object o) {
    idTable.enter(ast.I.spelling, ast); // permits recursion
    if (ast.duplicated)
      reporter.reportError("identifier \"%\" already declared", ast.I.spelling, ast.position);
    idTable.openScope();
    ast.FPS.visit(this, null);
    ast.C.visit(this, null);
    idTable.closeScope();
    return null;
  }

  public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {
    ast.D1.visit(this, null);
    ast.D2.visit(this, null);
    return null;
  }

  public Object visitTypeDeclaration(TypeDeclaration ast, Object o) {
    ast.T = (TypeDenoter) ast.T.visit(this, null);
    idTable.enter(ast.I.spelling, ast);
    if (ast.duplicated)
      reporter.reportError("identifier \"%\" already declared", ast.I.spelling, ast.position);
    return null;
  }

  public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o) {
    return null;
  }

  // Array Aggregates

  // Returns the TypeDenoter for the Array Aggregate. Does not use the
  // given object.

  public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    TypeDenoter elemType = (TypeDenoter) ast.AA.visit(this, null);
    ast.elemCount = ast.AA.elemCount + 1;
    if (!eType.equals(elemType))
      reporter.reportError("incompatible array-aggregate element", "", ast.E.position);
    return elemType;
  }

  public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) {
    TypeDenoter elemType = (TypeDenoter) ast.E.visit(this, null);
    ast.elemCount = 1;
    return elemType;
  }

  // Record Aggregates

  // Returns the TypeDenoter for the Record Aggregate. Does not use the
  // given object.

  public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o) {

    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    FieldTypeDenoter rType = (FieldTypeDenoter) ast.RA.visit(this, null);
    TypeDenoter fType = checkFieldIdentifier(rType, ast.I);
    if (fType != StdEnvironment.errorType)
      reporter.reportError("duplicate field \"%\" in record", ast.I.spelling, ast.I.position);
    ast.type = new MultipleFieldTypeDenoter(ast.I, eType, rType, ast.position);
    return ast.type;
  }

  public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o) {
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    ast.type = new SingleFieldTypeDenoter(ast.I, eType, ast.position);
    return ast.type;
  }

  // Formal Parameters

  // Always returns null. Does not use the given object.

  public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {
    ast.T = (TypeDenoter) ast.T.visit(this, null);
    idTable.enter(ast.I.spelling, ast);
    if (ast.duplicated)
      reporter.reportError("duplicated formal parameter \"%\"", ast.I.spelling, ast.position);
    return null;
  }

  public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {
    idTable.openScope();
    ast.FPS.visit(this, null);
    idTable.closeScope();
    ast.T = (TypeDenoter) ast.T.visit(this, null);
    idTable.enter(ast.I.spelling, ast);
    if (ast.duplicated)
      reporter.reportError("duplicated formal parameter \"%\"", ast.I.spelling, ast.position);
    return null;
  }

  public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {
    idTable.openScope();
    ast.FPS.visit(this, null);
    idTable.closeScope();
    idTable.enter(ast.I.spelling, ast);
    if (ast.duplicated)
      reporter.reportError("duplicated formal parameter \"%\"", ast.I.spelling, ast.position);
    return null;
  }

  public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {
    ast.T = (TypeDenoter) ast.T.visit(this, null);
    idTable.enter(ast.I.spelling, ast);
    if (ast.duplicated)
      reporter.reportError("duplicated formal parameter \"%\"", ast.I.spelling, ast.position);
    return null;
  }

  public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o) {
    return null;
  }

  public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o) {
    ast.FP.visit(this, null);
    ast.FPS.visit(this, null);
    return null;
  }

  public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o) {
    ast.FP.visit(this, null);
    return null;
  }

  // Actual Parameters

  // Always returns null. Uses the given FormalParameter.

  public Object visitConstActualParameter(ConstActualParameter ast, Object o) {
    FormalParameter fp = (FormalParameter) o;
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);

    if (!(fp instanceof ConstFormalParameter))
      reporter.reportError("const actual parameter not expected here", "", ast.position);
    else if (!eType.equals(((ConstFormalParameter) fp).T))
      reporter.reportError("wrong type for const actual parameter", "", ast.E.position);
    return null;
  }

  public Object visitFuncActualParameter(FuncActualParameter ast, Object o) {
    FormalParameter fp = (FormalParameter) o;

    Declaration binding = (Declaration) ast.I.visit(this, null);
    if (binding == null)
      reportUndeclared(ast.I);
    else if (!(binding instanceof FuncDeclaration || binding instanceof FuncFormalParameter))
      reporter.reportError("\"%\" is not a function identifier", ast.I.spelling, ast.I.position);
    else if (!(fp instanceof FuncFormalParameter))
      reporter.reportError("func actual parameter not expected here", "", ast.position);
    else {
      FormalParameterSequence FPS = null;
      TypeDenoter T = null;
      if (binding instanceof FuncDeclaration) {
        FPS = ((FuncDeclaration) binding).FPS;
        T = ((FuncDeclaration) binding).T;
      } else {
        FPS = ((FuncFormalParameter) binding).FPS;
        T = ((FuncFormalParameter) binding).T;
      }
      if (!FPS.equals(((FuncFormalParameter) fp).FPS))
        reporter.reportError("wrong signature for function \"%\"", ast.I.spelling, ast.I.position);
      else if (!T.equals(((FuncFormalParameter) fp).T))
        reporter.reportError("wrong type for function \"%\"", ast.I.spelling, ast.I.position);
    }
    return null;
  }

  public Object visitProcActualParameter(ProcActualParameter ast, Object o) {
    FormalParameter fp = (FormalParameter) o;

    Declaration binding = (Declaration) ast.I.visit(this, null);
    if (binding == null)
      reportUndeclared(ast.I);
    else if (!(binding instanceof ProcDeclaration || binding instanceof ProcFormalParameter))
      reporter.reportError("\"%\" is not a procedure identifier", ast.I.spelling, ast.I.position);
    else if (!(fp instanceof ProcFormalParameter))
      reporter.reportError("proc actual parameter not expected here", "", ast.position);
    else {
      FormalParameterSequence FPS = null;
      if (binding instanceof ProcDeclaration)
        FPS = ((ProcDeclaration) binding).FPS;
      else
        FPS = ((ProcFormalParameter) binding).FPS;
      if (!FPS.equals(((ProcFormalParameter) fp).FPS))
        reporter.reportError("wrong signature for procedure \"%\"", ast.I.spelling, ast.I.position);
    }
    return null;
  }

  public Object visitVarActualParameter(VarActualParameter ast, Object o) {
    FormalParameter fp = (FormalParameter) o;

    TypeDenoter vType = (TypeDenoter) ast.V.visit(this, null);
    if (!ast.V.variable)
      reporter.reportError("actual parameter is not a variable", "", ast.V.position);
    else if (!(fp instanceof VarFormalParameter))
      reporter.reportError("var actual parameter not expected here", "", ast.V.position);
    else if (!vType.equals(((VarFormalParameter) fp).T))
      reporter.reportError("wrong type for var actual parameter", "", ast.V.position);
    return null;
  }

  public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o) {
    FormalParameterSequence fps = (FormalParameterSequence) o;
    if (!(fps instanceof EmptyFormalParameterSequence))
      reporter.reportError("too few actual parameters", "", ast.position);
    return null;
  }

  public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o) {
    FormalParameterSequence fps = (FormalParameterSequence) o;
    if (!(fps instanceof MultipleFormalParameterSequence))
      reporter.reportError("too many actual parameters", "", ast.position);
    else {
      ast.AP.visit(this, ((MultipleFormalParameterSequence) fps).FP);
      ast.APS.visit(this, ((MultipleFormalParameterSequence) fps).FPS);
    }
    return null;
  }

  public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o) {
    FormalParameterSequence fps = (FormalParameterSequence) o;
    if (!(fps instanceof SingleFormalParameterSequence))
      reporter.reportError("incorrect number of actual parameters", "", ast.position);
    else {
      ast.AP.visit(this, ((SingleFormalParameterSequence) fps).FP);
    }
    return null;
  }

  // Type Denoters

  // Returns the expanded version of the TypeDenoter. Does not
  // use the given object.

  public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) {

    Declaration binding = (Declaration) ast.LI.visit(this, null);
    if (binding == null) {
      reportUndeclared(ast.LI.I);
      return StdEnvironment.errorType;
    } else if (!(binding instanceof TypeDeclaration)) {
      reporter.reportError("\"%\" is not a type identifier", ast.LI.I.spelling, ast.LI.position);
      return StdEnvironment.errorType;
    }
    return ((TypeDeclaration) binding).T;
  }

  public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {
    return StdEnvironment.anyType;
  }

  public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) {
    ast.T = (TypeDenoter) ast.T.visit(this, null);
    if ((Integer.valueOf(ast.IL.spelling).intValue()) == 0)
      reporter.reportError("arrays must not be empty", "", ast.IL.position);
    return ast;
  }

  public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {
    return StdEnvironment.booleanType;
  }

  public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) {
    return StdEnvironment.charType;
  }

  public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) {
    return StdEnvironment.errorType;
  }

  public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) {
    return StdEnvironment.integerType;
  }

  public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {
    ast.FT = (FieldTypeDenoter) ast.FT.visit(this, null);
    return ast;
  }

  public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o) {
    ast.T = (TypeDenoter) ast.T.visit(this, null);
    ast.FT.visit(this, null);
    return ast;
  }

  public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o) {
    ast.T = (TypeDenoter) ast.T.visit(this, null);
    return ast;
  }

  // Literals, Identifiers and Operators
  public Object visitCharacterLiteral(CharacterLiteral CL, Object o) {
    return StdEnvironment.charType;
  }

  public Object visitIdentifier(Identifier I, Object o) {
    Declaration binding;
    if (o instanceof String) {
      binding = idTable.retrieveFromPackage(I.spelling, (String) o);
    } else
      binding = idTable.retrieve(I.spelling);

    if (binding != null)
      I.decl = binding;
    return binding;
  }

  public Object visitIntegerLiteral(IntegerLiteral IL, Object o) {
    return StdEnvironment.integerType;
  }

  public Object visitOperator(Operator O, Object o) {
    Declaration binding = idTable.retrieve(O.spelling);
    if (binding != null)
      O.decl = binding;
    return binding;
  }

  // Value-or-variable names

  // Determines the address of a named object (constant or variable).
  // This consists of a base object, to which 0 or more field-selection
  // or array-indexing operations may be applied (if it is a record or
  // array). As much as possible of the address computation is done at
  // compile-time. Code is generated only when necessary to evaluate
  // index expressions at run-time.
  // currentLevel is the routine level where the v-name occurs.
  // frameSize is the anticipated size of the local stack frame when
  // the object is addressed at run-time.
  // It returns the description of the base object.
  // offset is set to the total of any field offsets (plus any offsets
  // due to index expressions that happen to be literals).
  // indexed is set to true iff there are any index expressions (other
  // than literals). In that case code is generated to compute the
  // offset due to these indexing operations at run-time.

  // Returns the TypeDenoter of the Var name. Does not use the
  // given object.

  // @author Joseph
  // @description Metodo checker para simple var name
  // @funcionalidad Cambio en los metodos checker de comando de asignacion
  // @codigo J.3
  public Object visitSimpleVarName(SimpleVarName ast, Object o) {
    ast.variable = false;
    ast.type = StdEnvironment.errorType;
    Declaration binding;
    if (o instanceof String) {
      binding = (Declaration) ast.I.visit(this, o);
    } else
      binding = (Declaration) ast.I.visit(this, null);

    if (binding == null)
      reportUndeclared(ast.I);
    else if (binding instanceof ConstDeclaration) {
      ast.type = ((ConstDeclaration) binding).E.type;
      ast.variable = false;
    } else if (binding instanceof VarTDDeclaration) {

      ast.type = ((VarTDDeclaration) binding).T;
      VarTDDeclaration var = (VarTDDeclaration) binding;
      ast.variable = true;
    } else if (binding instanceof VarExpDeclaration) {
      ast.type = ((VarExpDeclaration) binding).T;
      ast.variable = true;
    } else if (binding instanceof ConstFormalParameter) {
      ast.type = ((ConstFormalParameter) binding).T;
      ast.variable = false;
    } else if (binding instanceof VarFormalParameter) {
      ast.type = ((VarFormalParameter) binding).T;
      ast.variable = true;
    } else
      reporter.reportError("\"%\" is not a const or var identifier", ast.I.spelling, ast.I.position);
    return ast.type;
  }
  // END CAMBIO Joseph

  // @author Joseph
  // @description Metodo checker para simple vname
  // @funcionalidad Cambio en los metodos checker de comando de asignacion
  // @codigo J.4
  public Object visitSimpleVname(SimpleVname ast, Object o) {

    TypeDenoter vnType = (TypeDenoter) ast.VN.visit(this, null);
    ast.variable = ast.VN.variable;
    return vnType;
  }
  /*
   * J.4 public Object visitSimpleVname(SimpleVname ast, Object o) { ast.variable
   * = false; ast.type = StdEnvironment.errorType; Declaration binding =
   * (Declaration) ast.I.visit(this, null); if (binding == null)
   * reportUndeclared(ast.I); else if (binding instanceof ConstDeclaration) {
   * ast.type = ((ConstDeclaration) binding).E.type; ast.variable = false; } else
   * if (binding instanceof VarDeclaration) { ast.type = ((VarDeclaration)
   * binding).T; ast.variable = true; } else if (binding instanceof
   * ConstFormalParameter) { ast.type = ((ConstFormalParameter) binding).T;
   * ast.variable = false; } else if (binding instanceof VarFormalParameter) {
   * ast.type = ((VarFormalParameter) binding).T; ast.variable = true; } else
   * reporter.reportError ("\"%\" is not a const or var identifier",
   * ast.I.spelling, ast.I.position); return ast.type; }
   */

  // END CAMBIO Joseph

  // @author Joseph
  // @description Cambio de vname por var name en el metodo checker de Subscript
  // var name y dor var name
  // @funcionalidad Cambio en los metodos checker de vname por var name
  // @codigo J.5
  public Object visitDotVarName(DotVarName ast, Object o) {

    ast.type = null;

    TypeDenoter vType;
    if (o instanceof String)
      vType = (TypeDenoter) ast.V.visit(this, o);

    else
      vType = (TypeDenoter) ast.V.visit(this, o);

    ast.variable = ast.V.variable;
    if (!(vType instanceof RecordTypeDenoter))
      reporter.reportError("record expected here", "", ast.V.position);
    else {
      ast.type = checkFieldIdentifier(((RecordTypeDenoter) vType).FT, ast.I);
      if (ast.type == StdEnvironment.errorType)
        reporter.reportError("no field \"%\" in this record type", ast.I.spelling, ast.I.position);
    }
    return ast.type;
  }

  public Object visitSubscriptVarName(SubscriptVarName ast, Object o) {

    TypeDenoter vType;
    if (o instanceof String)
      vType = (TypeDenoter) ast.V.visit(this, o);

    else
      vType = (TypeDenoter) ast.V.visit(this, o);

    ast.variable = ast.V.variable;
    TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null);
    if (vType != StdEnvironment.errorType) {
      if (!(vType instanceof ArrayTypeDenoter))
        reporter.reportError("array expected here", "", ast.V.position);
      else {
        if (!eType.equals(StdEnvironment.integerType))
          reporter.reportError("Integer expression expected here", "", ast.E.position);
        ast.type = ((ArrayTypeDenoter) vType).T;
      }
    }
    return ast.type;
  }
  /*
   * J.5 public Object visitSubscriptVname(SubscriptVname ast, Object o) {
   * TypeDenoter vType = (TypeDenoter) ast.V.visit(this, null); ast.variable =
   * ast.V.variable; TypeDenoter eType = (TypeDenoter) ast.E.visit(this, null); if
   * (vType != StdEnvironment.errorType) { if (! (vType instanceof
   * ArrayTypeDenoter)) reporter.reportError ("array expected here", "",
   * ast.V.position); else { if (! eType.equals(StdEnvironment.integerType))
   * reporter.reportError ("Integer expression expected here", "",
   * ast.E.position); ast.type = ((ArrayTypeDenoter) vType).T; } } return
   * ast.type; }
   */
  // END CAMBIO Joseph

  // Checks whether the source program, represented by its AST, satisfies the
  // language's scope rules and type rules.
  // Also decorates the AST as follows:
  // (a) Each applied occurrence of an identifier or operator is linked to
  // the corresponding declaration of that identifier or operator.
  // (b) Each expression and value-or-variable-name is decorated by its type.
  // (c) Each type identifier is replaced by the type it denotes.
  // Types are represented by small ASTs.

  public void check(Program ast) {
    ast.visit(this, null);
  }

  /////////////////////////////////////////////////////////////////////////////

  public Checker(ErrorReporter reporter) {
    this.reporter = reporter;
    this.idTable = new IdentificationTable();
    establishStdEnvironment();
  }

  private IdentificationTable idTable;
  private static SourcePosition dummyPos = new SourcePosition();
  private ErrorReporter reporter;

  // Reports that the identifier or operator used at a leaf of the AST
  // has not been declared.

  private void reportUndeclared(Terminal leaf) {
    reporter.reportError("\"%\" is not declared", leaf.spelling, leaf.position);
  }

  private static TypeDenoter checkFieldIdentifier(FieldTypeDenoter ast, Identifier I) {
    if (ast instanceof MultipleFieldTypeDenoter) {
      MultipleFieldTypeDenoter ft = (MultipleFieldTypeDenoter) ast;
      if (ft.I.spelling.compareTo(I.spelling) == 0) {
        I.decl = ast;
        return ft.T;
      } else {
        return checkFieldIdentifier(ft.FT, I);
      }
    } else if (ast instanceof SingleFieldTypeDenoter) {
      SingleFieldTypeDenoter ft = (SingleFieldTypeDenoter) ast;
      if (ft.I.spelling.compareTo(I.spelling) == 0) {
        I.decl = ast;
        return ft.T;
      }
    }
    return StdEnvironment.errorType;
  }

  // Creates a small AST to represent the "declaration" of a standard
  // type, and enters it in the identification table.

  private TypeDeclaration declareStdType(String id, TypeDenoter typedenoter) {
    TypeDeclaration binding;

    binding = new TypeDeclaration(new Identifier(id, dummyPos), typedenoter, dummyPos);
    idTable.enter(id, binding);
    return binding;
  }

  // Creates a small AST to represent the "declaration" of a standard
  // type, and enters it in the identification table.

  private ConstDeclaration declareStdConst(String id, TypeDenoter constType) {

    IntegerExpression constExpr;
    ConstDeclaration binding;

    // constExpr used only as a placeholder for constType
    constExpr = new IntegerExpression(null, dummyPos);
    constExpr.type = constType;
    binding = new ConstDeclaration(new Identifier(id, dummyPos), constExpr, dummyPos);
    idTable.enter(id, binding);
    return binding;
  }

  // Creates a small AST to represent the "declaration" of a standard
  // type, and enters it in the identification table.

  private ProcDeclaration declareStdProc(String id, FormalParameterSequence fps) {

    ProcDeclaration binding;

    binding = new ProcDeclaration(new Identifier(id, dummyPos), fps, new EmptyCommand(dummyPos), dummyPos);
    idTable.enter(id, binding);
    return binding;
  }

  // Creates a small AST to represent the "declaration" of a standard
  // type, and enters it in the identification table.

  private FuncDeclaration declareStdFunc(String id, FormalParameterSequence fps, TypeDenoter resultType) {

    FuncDeclaration binding;

    binding = new FuncDeclaration(new Identifier(id, dummyPos), fps, resultType, new EmptyExpression(dummyPos),
        dummyPos);
    idTable.enter(id, binding);
    return binding;
  }

  // Creates a small AST to represent the "declaration" of a
  // unary operator, and enters it in the identification table.
  // This "declaration" summarises the operator's type info.

  private UnaryOperatorDeclaration declareStdUnaryOp(String op, TypeDenoter argType, TypeDenoter resultType) {

    UnaryOperatorDeclaration binding;

    binding = new UnaryOperatorDeclaration(new Operator(op, dummyPos), argType, resultType, dummyPos);
    idTable.enter(op, binding);
    return binding;
  }

  // Creates a small AST to represent the "declaration" of a
  // binary operator, and enters it in the identification table.
  // This "declaration" summarises the operator's type info.

  private BinaryOperatorDeclaration declareStdBinaryOp(String op, TypeDenoter arg1Type, TypeDenoter arg2type,
      TypeDenoter resultType) {

    BinaryOperatorDeclaration binding;

    binding = new BinaryOperatorDeclaration(new Operator(op, dummyPos), arg1Type, arg2type, resultType, dummyPos);
    idTable.enter(op, binding);
    return binding;
  }

  // Creates small ASTs to represent the standard types.
  // Creates small ASTs to represent "declarations" of standard types,
  // constants, procedures, functions, and operators.
  // Enters these "declarations" in the identification table.

  private final static Identifier dummyI = new Identifier("", dummyPos);

  private void establishStdEnvironment() {

    // idTable.startIdentification();
    StdEnvironment.booleanType = new BoolTypeDenoter(dummyPos);
    StdEnvironment.integerType = new IntTypeDenoter(dummyPos);
    StdEnvironment.charType = new CharTypeDenoter(dummyPos);
    StdEnvironment.anyType = new AnyTypeDenoter(dummyPos);
    StdEnvironment.errorType = new ErrorTypeDenoter(dummyPos);

    StdEnvironment.booleanDecl = declareStdType("Boolean", StdEnvironment.booleanType);
    StdEnvironment.falseDecl = declareStdConst("false", StdEnvironment.booleanType);
    StdEnvironment.trueDecl = declareStdConst("true", StdEnvironment.booleanType);
    StdEnvironment.notDecl = declareStdUnaryOp("\\", StdEnvironment.booleanType, StdEnvironment.booleanType);
    StdEnvironment.andDecl = declareStdBinaryOp("/\\", StdEnvironment.booleanType, StdEnvironment.booleanType,
        StdEnvironment.booleanType);
    StdEnvironment.orDecl = declareStdBinaryOp("\\/", StdEnvironment.booleanType, StdEnvironment.booleanType,
        StdEnvironment.booleanType);

    StdEnvironment.integerDecl = declareStdType("Integer", StdEnvironment.integerType);
    StdEnvironment.maxintDecl = declareStdConst("maxint", StdEnvironment.integerType);
    StdEnvironment.addDecl = declareStdBinaryOp("+", StdEnvironment.integerType, StdEnvironment.integerType,
        StdEnvironment.integerType);
    StdEnvironment.subtractDecl = declareStdBinaryOp("-", StdEnvironment.integerType, StdEnvironment.integerType,
        StdEnvironment.integerType);
    StdEnvironment.multiplyDecl = declareStdBinaryOp("*", StdEnvironment.integerType, StdEnvironment.integerType,
        StdEnvironment.integerType);
    StdEnvironment.divideDecl = declareStdBinaryOp("/", StdEnvironment.integerType, StdEnvironment.integerType,
        StdEnvironment.integerType);
    StdEnvironment.moduloDecl = declareStdBinaryOp("//", StdEnvironment.integerType, StdEnvironment.integerType,
        StdEnvironment.integerType);
    StdEnvironment.lessDecl = declareStdBinaryOp("<", StdEnvironment.integerType, StdEnvironment.integerType,
        StdEnvironment.booleanType);
    StdEnvironment.notgreaterDecl = declareStdBinaryOp("<=", StdEnvironment.integerType, StdEnvironment.integerType,
        StdEnvironment.booleanType);
    StdEnvironment.greaterDecl = declareStdBinaryOp(">", StdEnvironment.integerType, StdEnvironment.integerType,
        StdEnvironment.booleanType);
    StdEnvironment.notlessDecl = declareStdBinaryOp(">=", StdEnvironment.integerType, StdEnvironment.integerType,
        StdEnvironment.booleanType);

    StdEnvironment.charDecl = declareStdType("Char", StdEnvironment.charType);
    StdEnvironment.chrDecl = declareStdFunc("chr", new SingleFormalParameterSequence(
        new ConstFormalParameter(dummyI, StdEnvironment.integerType, dummyPos), dummyPos), StdEnvironment.charType);
    StdEnvironment.ordDecl = declareStdFunc("ord", new SingleFormalParameterSequence(
        new ConstFormalParameter(dummyI, StdEnvironment.charType, dummyPos), dummyPos), StdEnvironment.integerType);
    StdEnvironment.eofDecl = declareStdFunc("eof", new EmptyFormalParameterSequence(dummyPos),
        StdEnvironment.booleanType);
    StdEnvironment.eolDecl = declareStdFunc("eol", new EmptyFormalParameterSequence(dummyPos),
        StdEnvironment.booleanType);
    StdEnvironment.getDecl = declareStdProc("get",
        new SingleFormalParameterSequence(new VarFormalParameter(dummyI, StdEnvironment.charType, dummyPos), dummyPos));
    StdEnvironment.putDecl = declareStdProc("put", new SingleFormalParameterSequence(
        new ConstFormalParameter(dummyI, StdEnvironment.charType, dummyPos), dummyPos));
    StdEnvironment.getintDecl = declareStdProc("getint", new SingleFormalParameterSequence(
        new VarFormalParameter(dummyI, StdEnvironment.integerType, dummyPos), dummyPos));
    StdEnvironment.putintDecl = declareStdProc("putint", new SingleFormalParameterSequence(
        new ConstFormalParameter(dummyI, StdEnvironment.integerType, dummyPos), dummyPos));
    StdEnvironment.geteolDecl = declareStdProc("geteol", new EmptyFormalParameterSequence(dummyPos));
    StdEnvironment.puteolDecl = declareStdProc("puteol", new EmptyFormalParameterSequence(dummyPos));
    StdEnvironment.equalDecl = declareStdBinaryOp("=", StdEnvironment.anyType, StdEnvironment.anyType,
        StdEnvironment.booleanType);
    StdEnvironment.unequalDecl = declareStdBinaryOp("\\=", StdEnvironment.anyType, StdEnvironment.anyType,
        StdEnvironment.booleanType);

  }
}
