/*
 * @(#)Parser.java                        2.1 2003/10/07
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

package Triangle.SyntacticAnalyzer;

import Triangle.ErrorReporter;
import Triangle.AbstractSyntaxTrees.ActualParameter;
import Triangle.AbstractSyntaxTrees.ActualParameterSequence;
import Triangle.AbstractSyntaxTrees.ArrayAggregate;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.Command;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.Declaration;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.Expression;
import Triangle.AbstractSyntaxTrees.FieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.FormalParameter;
import Triangle.AbstractSyntaxTrees.FormalParameterSequence;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.CompoundIfCommand;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
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
import Triangle.AbstractSyntaxTrees.RecordAggregate;
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
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.TypeDenoter;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Vname;
import Triangle.AbstractSyntaxTrees.VnameExpression;
// @author        Joseph
// @descripcion   Importacion de nuevos ASTs
// @funcionalidad Parseo de nuevos ASTs
// @codigo        J.16
import Triangle.AbstractSyntaxTrees.VarTDDeclaration;
import Triangle.AbstractSyntaxTrees.VarExpDeclaration;
import Triangle.AbstractSyntaxTrees.WhileLoopCommand;
import Triangle.AbstractSyntaxTrees.UntilLoopCommand;
import Triangle.AbstractSyntaxTrees.ElsifCommand;
import Triangle.AbstractSyntaxTrees.DoLoopUntilCommand;
import Triangle.AbstractSyntaxTrees.DoLoopWhileCommand;
import Triangle.AbstractSyntaxTrees.SequentialElsifCommand;
import Triangle.AbstractSyntaxTrees.SingleElsifCommand;
import Triangle.AbstractSyntaxTrees.ForLoopDoCommand;
import Triangle.AbstractSyntaxTrees.ForLoopWhileCommand;
import Triangle.AbstractSyntaxTrees.ForLoopUntilCommand;
import Triangle.AbstractSyntaxTrees.CaseLiteral;
import Triangle.AbstractSyntaxTrees.CaseRange;
import Triangle.AbstractSyntaxTrees.SimpleCaseRange;
import Triangle.AbstractSyntaxTrees.CompoundCaseRange;
import Triangle.AbstractSyntaxTrees.CaseLiterals;
import Triangle.AbstractSyntaxTrees.SequentialCaseRange;
import Triangle.AbstractSyntaxTrees.ElseCase;
import Triangle.AbstractSyntaxTrees.Case;
import Triangle.AbstractSyntaxTrees.SingleCase;
import Triangle.AbstractSyntaxTrees.Cases;
import Triangle.AbstractSyntaxTrees.SequentialCase;
import Triangle.AbstractSyntaxTrees.SimpleCases;
import Triangle.AbstractSyntaxTrees.CompoundCases;
import Triangle.AbstractSyntaxTrees.ChooseCommand;
import Triangle.AbstractSyntaxTrees.ProcFunc;
import Triangle.AbstractSyntaxTrees.ProcFuncs;
import Triangle.AbstractSyntaxTrees.SequentialProcFuncs;
import Triangle.AbstractSyntaxTrees.Procedure;
import Triangle.AbstractSyntaxTrees.Function;
import Triangle.AbstractSyntaxTrees.SingleDeclaration;
import Triangle.AbstractSyntaxTrees.RecDeclaration;
import Triangle.AbstractSyntaxTrees.PrivDeclaration;
import Triangle.AbstractSyntaxTrees.ForFromCommand;
import Triangle.AbstractSyntaxTrees.SimpleVarName;
import Triangle.AbstractSyntaxTrees.DotVarName;
import Triangle.AbstractSyntaxTrees.SubscriptVarName;
import Triangle.AbstractSyntaxTrees.VarName;
import Triangle.AbstractSyntaxTrees.PackageIdentifier;
import Triangle.AbstractSyntaxTrees.PackageVname;
import Triangle.AbstractSyntaxTrees.LongIdentifier;
import Triangle.AbstractSyntaxTrees.SimpleLongIdentifier;
import Triangle.AbstractSyntaxTrees.PackageLongIdentifier;
import Triangle.AbstractSyntaxTrees.SinglePackageDeclaration;
import Triangle.AbstractSyntaxTrees.PackageDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialPackageDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleProgram;
import Triangle.AbstractSyntaxTrees.CompoundProgram;
import Triangle.AbstractSyntaxTrees.SimpleVname;
/* J.16
import Triangle.AbstractSyntaxTrees.WhileCommand;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
*/
// END CAMBIO Joseph
public class Parser {

  private Scanner lexicalAnalyser;
  private ErrorReporter errorReporter;
  private Token currentToken;
  private SourcePosition previousTokenPosition;
  // @author        Andres
  // @descripcion   Atributo para determinar el tipo de programa
  // @funcionalidad Parsear el programa
  // @codigo        A.140
  private boolean isSimpleProgram = false;
  private SimpleProgram simpleProgram = null;
  private CompoundProgram compoundProgram = null;
  // END Cambio Andres

  public Parser(Scanner lexer, ErrorReporter reporter) {
    lexicalAnalyser = lexer;
    errorReporter = reporter;
    previousTokenPosition = new SourcePosition();
  }
  
  // @author        Andres
  // @descripcion   Meotodo para determinar el tipo de programa
  // @funcionalidad Parsear el programa
  // @codigo        A.141
  public boolean getIsSimpleProgram() {
      return isSimpleProgram;
  }
  
  public SimpleProgram getSimpleProgram() {
      return simpleProgram;
  }
  
  public CompoundProgram getCompoundProgram() {
      return compoundProgram;
  }
  // END CAMBIO Andres

// accept checks whether the current token matches tokenExpected.
// If so, fetches the next token.
// If not, reports a syntactic error.

  void accept (int tokenExpected) throws SyntaxError {
    if (currentToken.kind == tokenExpected) {
      previousTokenPosition = currentToken.position;
      currentToken = lexicalAnalyser.scan();
    } else {
      syntacticError("\"%\" expected here", Token.spell(tokenExpected));
    }
  }

  void acceptIt() {
    previousTokenPosition = currentToken.position;
    currentToken = lexicalAnalyser.scan();
  }

// start records the position of the start of a phrase.
// This is defined to be the position of the first
// character of the first token of the phrase.

  void start(SourcePosition position) {
    position.start = currentToken.position.start;
  }

// finish records the position of the end of a phrase.
// This is defined to be the position of the last
// character of the last token of the phrase.

  void finish(SourcePosition position) {
    position.finish = previousTokenPosition.finish;
  }

  void syntacticError(String messageTemplate, String tokenQuoted) throws SyntaxError {
    SourcePosition pos = currentToken.position;
    errorReporter.reportError(messageTemplate, tokenQuoted, pos);
    throw(new SyntaxError());
  }

///////////////////////////////////////////////////////////////////////////////
//
// PROGRAMS
//
///////////////////////////////////////////////////////////////////////////////

   // @author        Andres
   // @descripcion   Determinar que tipo de Program parsear
   // @funcionalidad Parsear Program
   // @codigo        A.143
  public void parseProgram() {

    previousTokenPosition.start = 0;
    previousTokenPosition.finish = 0;
    currentToken = lexicalAnalyser.scan();
    
    try {
      // Check package declaration parsing
      PackageDeclaration pdAST = null;
      if (currentToken.kind == Token.PACKAGE) {
          pdAST = parsePackageDeclaration();
      }
      Command cAST = parseCommand();
      if (pdAST == null) {
          this.isSimpleProgram = true;
          simpleProgram = new SimpleProgram(cAST, previousTokenPosition);
      } else {
          this.isSimpleProgram = false;
          compoundProgram = new CompoundProgram(pdAST, cAST, previousTokenPosition);
      }
      if (currentToken.kind != Token.EOT) {
        syntacticError("\"%\" not expected after end of program",
          currentToken.spelling);
      }
    }
    catch (SyntaxError s) {
        compoundProgram = null;
        simpleProgram = null;
    }
    /*
        try {
      Command cAST = parseCommand();
      programAST = new Program(cAST, previousTokenPosition);
      
      
      if (currentToken.kind != Token.EOT) {
        syntacticError("\"%\" not expected after end of program",
          currentToken.spelling);
      }
    }
    catch (SyntaxError s) { return null; }
    */
    // END CAMBIO Andres
  }

///////////////////////////////////////////////////////////////////////////////
//
// LITERALS
//
///////////////////////////////////////////////////////////////////////////////

// parseIntegerLiteral parses an integer-literal, and constructs
// a leaf AST to represent it.

  IntegerLiteral parseIntegerLiteral() throws SyntaxError {
    IntegerLiteral IL = null;

    if (currentToken.kind == Token.INTLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      IL = new IntegerLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      IL = null;
      syntacticError("integer literal expected here", "");
    }
    return IL;
  }

// parseCharacterLiteral parses a character-literal, and constructs a leaf
// AST to represent it.

  CharacterLiteral parseCharacterLiteral() throws SyntaxError {
    CharacterLiteral CL = null;

    if (currentToken.kind == Token.CHARLITERAL) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      CL = new CharacterLiteral(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      CL = null;
      syntacticError("character literal expected here", "");
    }
    return CL;
  }

// parseIdentifier parses an identifier, and constructs a leaf AST to
// represent it.

  Identifier parseIdentifier() throws SyntaxError {
    Identifier I = null;

    if (currentToken.kind == Token.IDENTIFIER) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      I = new Identifier(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      I = null;
      syntacticError("identifier expected here", "");
    }
    return I;
  }

// parseOperator parses an operator, and constructs a leaf AST to
// represent it.

  Operator parseOperator() throws SyntaxError {
    Operator O = null;

    if (currentToken.kind == Token.OPERATOR) {
      previousTokenPosition = currentToken.position;
      String spelling = currentToken.spelling;
      O = new Operator(spelling, previousTokenPosition);
      currentToken = lexicalAnalyser.scan();
    } else {
      O = null;
      syntacticError("operator expected here", "");
    }
    return O;
  }

///////////////////////////////////////////////////////////////////////////////
//
// COMMANDS
//
///////////////////////////////////////////////////////////////////////////////

// parseCommand parses the command, and constructs an AST
// to represent its phrase structure.

  Command parseCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();

    start(commandPos);
    commandAST = parseSingleCommand();
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Command c2AST = parseSingleCommand();
      finish(commandPos);
      commandAST = new SequentialCommand(commandAST, c2AST, commandPos);
    }
    return commandAST;
  }

  Command parseSingleCommand() throws SyntaxError {
    Command commandAST = null; // in case there's a syntactic error

    SourcePosition commandPos = new SourcePosition();
    start(commandPos);

    switch (currentToken.kind) {

    // @author        Joseph
    // @description   Cambio de parsing de primary expression, de identifier a long identifier
    // @funcionalidad Cambio en las alternativas de primary expression
    // @codigo        J.82
    case Token.IDENTIFIER:
      {
        LongIdentifier liAST = parseLongIdentifier();
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(commandPos);
          commandAST = new CallCommand(liAST, apsAST, commandPos);

        } else {
          if (liAST instanceof PackageLongIdentifier) {
            VarName varnAST = parseRestOfVarName(liAST.I);
            PackageVname vnameAST = new PackageVname (((PackageLongIdentifier) liAST).PI, varnAST, varnAST.position);
            accept(Token.BECOMES);
            Expression eAST = parseExpression();
            finish(commandPos);
            commandAST = new AssignCommand(vnameAST, eAST, commandPos);  
          } else {
            VarName varnAST = parseRestOfVarName(liAST.I);
            SimpleVname vnameAST = new SimpleVname (varnAST, varnAST.position);
            accept(Token.BECOMES);
            Expression eAST = parseExpression();
            finish(commandPos);
            commandAST = new AssignCommand(vnameAST, eAST, commandPos);              
          }
        }
      }
      break;
    /* J.82 
    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(commandPos);
          commandAST = new CallCommand(iAST, apsAST, commandPos);

        } else {

          Vname vAST = parseRestOfVname(iAST);
          accept(Token.BECOMES);
          Expression eAST = parseExpression();
          finish(commandPos);
          commandAST = new AssignCommand(vAST, eAST, commandPos);
        }
      }
      break;
    */
    // END CAMBIO Joseph
        
    // @author        Andres
    // @descripcion   Alternativa nothing para single command
    // @funcionalidad Parsear alternativas de single-command
    // @codigo        A.4
    case Token.NOTHING:
    {
        acceptIt();
        finish(commandPos);
        commandAST = new EmptyCommand(commandPos);
    }
    break;
    // END Cambio Andres
        
    // @author        Andres
    // @descripcion   Alternativa let para single command
    // @funcionalidad Parsear alternativas de single-command
    // @codigo        A.5
    case Token.LET:
    {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Command cAST = parseCommand();
        accept(Token.END);
        finish(commandPos);
        commandAST = new LetCommand(dAST, cAST, commandPos);
    }
    break;   
    // END Cambio Andres
        
    // @author        Andres
    // @descripcion   Alternativa if para single command
    // @funcionalidad Parsear alternativas de single-command
    // @codigo        A.6
    case Token.IF:
    {
        acceptIt();
        // Parse expression for if expression
        Expression eAST = parseExpression();
        accept(Token.THEN);
        // Parse command for if command
        Command cAST = parseCommand();
        ElsifCommand eiAST = null;
        // Start parsing elsif instructions
        if (currentToken.kind == Token.ELSIF) {
            acceptIt();
            // Parse elsif expression
            Expression e2AST = parseExpression();
            accept(Token.THEN);
            // Parse elsif command
            Command c2AST = parseCommand();
            finish(commandPos);
            // Create AST for the single elsif
            eiAST = new SingleElsifCommand(e2AST, c2AST, commandPos);
        }
        while(currentToken.kind == Token.ELSIF) {
            acceptIt();
            // Parse elsif expression
            Expression e2AST = parseExpression();
            accept(Token.THEN);
            // Parse elsif command
            Command c2AST = parseCommand();
            finish(commandPos);
            // Create AST for the single elsif
            SingleElsifCommand seiAST = new SingleElsifCommand(e2AST, c2AST, commandPos);
            // Group elsif ASTs
            eiAST = new SequentialElsifCommand(eiAST, seiAST, commandPos);
        }
        // Parse else instruction
        accept(Token.ELSE);
        Command c2AST = parseCommand();
        accept(Token.END);
        finish(commandPos);
        // Check if compound if or simple if
        commandAST = eiAST != null ? new CompoundIfCommand(eAST, cAST, eiAST, c2AST, commandPos)
                : new IfCommand(eAST, cAST, c2AST, commandPos);
    }
    break;
    // END Cambio ANDRES
        
    // @author        Andres
    // @description   Creacion de la alternativa choose
    // @funcionalidad Cambio en las alternativas de single-command
    // @codigo        A.102
    case Token.CHOOSE:
    {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.FROM);
        Cases csAST = parseCases();
        accept(Token.END);
        finish(commandPos);
        commandAST = new ChooseCommand(eAST, csAST, commandPos);
    }
    break;
    // END CAMBIO Andres
    
    // @author        Joseph
    // @description   Borrado de la alternativa: "begin" Command "end"
    // @funcionalidad Cambio en las alternativas de single-command
    // @codigo        J.3
    /* J.3 
    case Token.BEGIN:
      acceptIt();
      commandAST = parseCommand();
      accept(Token.END);
      break;
    */
    // END CAMBIO Joseph
    
    // @author        Andres
    // @descripcion   Borrado de las alternativas let, if, while, epsilon
    // @funcionalidad Cambio en las alternativas de single-command
    // @codigo        A.3
    /* A.3
    case Token.LET:
      {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Command cAST = parseSingleCommand();
        finish(commandPos);
        commandAST = new LetCommand(dAST, cAST, commandPos);
      }
      break;

    case Token.IF:
      {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.THEN);
        Command c1AST = parseSingleCommand();
        accept(Token.ELSE);
        Command c2AST = parseSingleCommand();
        finish(commandPos);
        commandAST = new IfCommand(eAST, c1AST, c2AST, commandPos);
      }
      break;

    case Token.WHILE:
      {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.DO);
        Command cAST = parseSingleCommand();
        finish(commandPos);
        commandAST = new WhileCommand(eAST, cAST, commandPos);
      }
      break;
    case Token.SEMICOLON:
    case Token.END:
    case Token.ELSE:
    case Token.IN:
    case Token.EOT:
       finish(commandPos);
       commandAST = new EmptyCommand(commandPos);
       break;
    */
    // END CAMBIO Andres
    
    // @author        Joseph
    // @descripcion   Alternativas loop para single command
    // @funcionalidad Parsear alternativas de single-command
    // @codigo        J.17
    case Token.LOOP:
      {
        acceptIt();
        switch (currentToken.kind) {

        case Token.WHILE:
          {
            acceptIt();
            Expression eAST = parseExpression();
            accept(Token.DO);
            Command cAST = parseCommand();
            accept(Token.END);
            finish(commandPos);
            commandAST = new WhileLoopCommand(eAST, cAST, commandPos);
          }
        break;
          
        case Token.UNTIL:
          {
            acceptIt();
            Expression eAST = parseExpression();
            accept(Token.DO);
            Command cAST = parseCommand();
            accept(Token.END);
            finish(commandPos);
            commandAST = new UntilLoopCommand(eAST, cAST, commandPos);
          }
        break;
        
        case Token.DO:
          {
            acceptIt();
            Command cAST = parseCommand();
            switch (currentToken.kind) {
            case Token.WHILE:
             {
               acceptIt();
               Expression eAST = parseExpression();
               accept(Token.END);
               finish(commandPos);
               commandAST = new DoLoopWhileCommand(cAST, eAST, commandPos);                
             }
             break;
            case Token.UNTIL:
             {
               acceptIt();
               Expression eAST = parseExpression();
               accept(Token.END);
               finish(commandPos);
               commandAST = new DoLoopUntilCommand(cAST, eAST, commandPos);               
             }
             break;
            default:
              syntacticError("\"%\" cannot follow a loop-do command, expected while or until",
                currentToken.spelling);
              break;                
            }
          }
        break;
        
        case Token.FOR:
          {
            ForFromCommand ffcAST = parseForFromCommand();
            accept(Token.TO);
            Expression e1AST = parseExpression();
            switch (currentToken.kind) {
            case Token.DO:
             {
               acceptIt();
               Command cAST = parseCommand();
               accept(Token.END);
               finish(commandPos);
               commandAST = new ForLoopDoCommand(ffcAST, e1AST, cAST, commandPos);                
             }
             break;
            case Token.WHILE:
             {
               acceptIt();
               Expression e2AST = parseExpression();
               accept(Token.DO);
               Command cAST = parseCommand();
               accept(Token.END);
               finish(commandPos);
               commandAST = new ForLoopWhileCommand(ffcAST, e1AST ,e2AST, cAST, commandPos);               
             }
             break;
             case Token.UNTIL:
             {
               acceptIt();
               Expression e2AST = parseExpression();
               accept(Token.DO);
               Command cAST = parseCommand();
               accept(Token.END);
               finish(commandPos);
               commandAST = new ForLoopUntilCommand(ffcAST, e1AST, e2AST, cAST, commandPos);               
             }
             break;
             
            default:
              syntacticError("\"%\" cannot follow a loop-for-from-to command, expected do, while or until",
                currentToken.spelling);
              break;                
            }
          }
        break;
        
        default:
          syntacticError("\"%\" cannot follow a loop command, expected while, until, do or for",
           currentToken.spelling);
        break;
          
        }
      }
      break;
    // END CAMBIO Joseph

    default:
      syntacticError("\"%\" cannot start a command",
        currentToken.spelling);
      break;
    }

    return commandAST;
  }
  
   // @author        Joseph
   // @descripcion   Parseo de ForFromCommand
   // @funcionalidad Parsear alternativas de ForFromCommand
   // @codigo        J.62
   ForFromCommand parseForFromCommand() throws SyntaxError {
    ForFromCommand ffcAST = null; // in case there's a syntactic error

    SourcePosition ffcPos = new SourcePosition();
    start(ffcPos);
    accept(Token.FOR);
    Identifier iAST = parseIdentifier();
    accept(Token.FROM);
    Expression eAST = parseExpression();
    finish(ffcPos);
    ffcAST = new ForFromCommand (iAST,eAST, ffcPos);
    return ffcAST;
  }
  // END CAMBIO Joseph
  

 /////////////////////////////////////////////////////////////////////////////////
  //
  // CASES
  //
  ///////////////////////////////////////////////////////////////////////////////
    // @author        Andres
    // @descripcion   Metodo para parsear case literal
    // @funcionalidad Parsear comando Case
    // @codigo        A.33
    CaseLiteral parseCaseLiteral() throws SyntaxError {
        CaseLiteral caseLiteralAST = null;
        SourcePosition caseLiteralSourcePos = new SourcePosition();
        start(caseLiteralSourcePos);
        
        switch (currentToken.kind) {
            case Token.INTLITERAL:
            {
                IntegerLiteral ilAST = parseIntegerLiteral();
                finish(caseLiteralSourcePos);
                caseLiteralAST = new CaseLiteral(ilAST, caseLiteralSourcePos);
            }
            break;
            case Token.CHARLITERAL:
            {
                CharacterLiteral clAST = parseCharacterLiteral();
                finish(caseLiteralSourcePos);
                caseLiteralAST = new CaseLiteral(clAST, caseLiteralSourcePos);
            }
            break;
            default:
                syntacticError("\"%\" cannot start a case literal",
                    currentToken.spelling);
                break;
        }
        return caseLiteralAST;
    }
    // END cambio Andres
    
    // @author        Andres
    // @descripcion   Metodo para parsear case range
    // @funcionalidad Parsear comando Case
    // @codigo        A.47
    CaseRange parseCaseRange() throws SyntaxError {
        CaseRange caseRangeAST = null;
        SourcePosition caseRangePos = new SourcePosition();
        start(caseRangePos);
        
        CaseLiteral clAST = parseCaseLiteral();
        CaseLiteral c2AST = null;
        // Check for more case literals
        if (currentToken.kind == Token.DOUBLEDOT) {
            accept(Token.DOUBLEDOT);
            c2AST = parseCaseLiteral();
        }
        finish(caseRangePos);
        caseRangeAST = c2AST == null ? new SimpleCaseRange(clAST, caseRangePos)
                : new CompoundCaseRange(clAST, c2AST, caseRangePos);
        return caseRangeAST;
    }
    // END cambio Andres
    
    // @author        Andres
    // @descripcion   Metodo para parsear case literals
    // @funcionalidad Parsear comando Case
    // @codigo        A.60
    CaseLiterals parseCaseLiterals() throws SyntaxError {
        CaseLiterals caseLiteralsAST = null;
        SourcePosition caseLiteralsPos = new SourcePosition();
        start(caseLiteralsPos);
        
        CaseRange cr1AST = parseCaseRange();
        while(currentToken.kind == Token.PIPE) {
            accept(Token.PIPE);
            CaseRange cr2AST = parseCaseRange();
            finish(caseLiteralsPos);
            cr1AST = new SequentialCaseRange(cr1AST, cr2AST, caseLiteralsPos);
        }
        finish(caseLiteralsPos);
        
        caseLiteralsAST = new CaseLiterals(cr1AST, caseLiteralsPos);
        
        return caseLiteralsAST;
    }
    // END cambio Andres
    
    // @author        Andres
    // @descripcion   Metodo para parsear else case
    // @funcionalidad Parsear comando else case
    // @codigo        A.93
    ElseCase parseElseCase() throws SyntaxError {
        ElseCase elseCaseAST = null;
        SourcePosition elseCasePos = new SourcePosition();
        start(elseCasePos);
        
        // Parse else case
        accept(Token.ELSE);
        Command cAST = parseCommand();
        finish(elseCasePos);
        
        elseCaseAST = new ElseCase(cAST, elseCasePos);
        return elseCaseAST;
    }
     // END cambio Andres
    
    // @author        Andres
    // @descripcion   Metodo para parsear el case
    // @funcionalidad Parsear comando case
    // @codigo        A.94
    Case parseCase() throws SyntaxError {
        Case caseAST = null;
        SourcePosition casePos = new SourcePosition();
        start(casePos);
        
        accept(Token.WHEN);
        CaseLiterals clAST = parseCaseLiterals();
        accept(Token.THEN);
        Command cAST = parseCommand();
        finish(casePos);
        
        caseAST = new SingleCase(clAST, cAST, casePos);     
        return caseAST;
    }
    // END cambio Andres
    
    // @author        Andres
    // @descripcion   Metodo para parsear el cases
    // @funcionalidad Parsear comando cases
    // @codigo        A.95
    Cases parseCases() throws SyntaxError {
        Cases casesAST = null;
        SourcePosition casesPos = new SourcePosition();
        start(casesPos);
        
        Case case1AST = parseCase();
        while(currentToken.kind == Token.WHEN) {
            Case case2AST = parseCase();
            finish(casesPos);
            case1AST = new SequentialCase(case1AST, case2AST, casesPos);
        }
        
        ElseCase elseCaseAST = null;
        if (currentToken.kind == Token.ELSE) {
            elseCaseAST = parseElseCase();
        }
        finish(casesPos);
        
        casesAST = elseCaseAST == null ? new SimpleCases(case1AST, casesPos)
                : new CompoundCases(case1AST, elseCaseAST, casesPos);
        
        return casesAST;
    }
    // END cambio Andres



// @author        Joseph
// @description   Metodos de parseo de ProcFuncs
// @funcionalidad A�adido de los ProcFuncs al parser
// @codigo        J.38
  
///////////////////////////////////////////////////////////////////////////////
//
// ProcFuncs
//
///////////////////////////////////////////////////////////////////////////////

  ProcFuncs parseProcFuncs() throws SyntaxError {
    ProcFuncs procfuncsAST = null; // in case there's a syntactic error

    SourcePosition procfuncsPos = new SourcePosition();
    start(procfuncsPos);
    procfuncsAST = parseProcFunc();
    accept(Token.PIPE);
    ProcFuncs procfuncs2AST = parseProcFunc();
    finish(procfuncsPos);
    procfuncsAST = new SequentialProcFuncs(procfuncsAST, procfuncs2AST, procfuncsPos);
    while (currentToken.kind == Token.PIPE) {
      acceptIt();
      procfuncs2AST = parseProcFunc();
      finish(procfuncsPos);
      procfuncsAST = new SequentialProcFuncs(procfuncsAST, procfuncs2AST, procfuncsPos);
    }
    return procfuncsAST;
  }
  
  ProcFunc parseProcFunc() throws SyntaxError {
    ProcFunc procfuncAST = null; // in case there's a syntactic error
    SourcePosition procfuncPos = new SourcePosition();
    start (procfuncPos);
    switch (currentToken.kind) {
    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.IS);
        Command cAST = parseCommand();
        accept(Token.END);
        finish(procfuncPos);
        procfuncAST = new Procedure(iAST, fpsAST, cAST, procfuncPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tdAST = parseTypeDenoter();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(procfuncPos);
        procfuncAST = new Function(iAST, fpsAST, tdAST, eAST, procfuncPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a procedure or a function",
        currentToken.spelling);
      break;
    }
    return procfuncAST;
  }
 // END Cambio  
  
///////////////////////////////////////////////////////////////////////////////
//
// EXPRESSIONS
//
///////////////////////////////////////////////////////////////////////////////

  Expression parseExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();

    start (expressionPos);

    switch (currentToken.kind) {

    case Token.LET:
      {
        acceptIt();
        Declaration dAST = parseDeclaration();
        accept(Token.IN);
        Expression eAST = parseExpression();
        finish(expressionPos);
        expressionAST = new LetExpression(dAST, eAST, expressionPos);
      }
      break;

    case Token.IF:
      {
        acceptIt();
        Expression e1AST = parseExpression();
        accept(Token.THEN);
        Expression e2AST = parseExpression();
        accept(Token.ELSE);
        Expression e3AST = parseExpression();
        finish(expressionPos);
        expressionAST = new IfExpression(e1AST, e2AST, e3AST, expressionPos);
      }
      break;

    default:
      expressionAST = parseSecondaryExpression();
      break;
    }
    return expressionAST;
  }

  Expression parseSecondaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    expressionAST = parsePrimaryExpression();
    while (currentToken.kind == Token.OPERATOR) {
      Operator opAST = parseOperator();
      Expression e2AST = parsePrimaryExpression();
      expressionAST = new BinaryExpression (expressionAST, opAST, e2AST,
        expressionPos);
    }
    return expressionAST;
  }

  Expression parsePrimaryExpression() throws SyntaxError {
    Expression expressionAST = null; // in case there's a syntactic error

    SourcePosition expressionPos = new SourcePosition();
    start(expressionPos);

    switch (currentToken.kind) {

    case Token.INTLITERAL:
      {
        IntegerLiteral ilAST = parseIntegerLiteral();
        finish(expressionPos);
        expressionAST = new IntegerExpression(ilAST, expressionPos);
      }
      break;

    case Token.CHARLITERAL:
      {
        CharacterLiteral clAST= parseCharacterLiteral();
        finish(expressionPos);
        expressionAST = new CharacterExpression(clAST, expressionPos);
      }
      break;

    case Token.LBRACKET:
      {
        acceptIt();
        ArrayAggregate aaAST = parseArrayAggregate();
        accept(Token.RBRACKET);
        finish(expressionPos);
        expressionAST = new ArrayExpression(aaAST, expressionPos);
      }
      break;

    case Token.LCURLY:
      {
        acceptIt();
        RecordAggregate raAST = parseRecordAggregate();
        accept(Token.RCURLY);
        finish(expressionPos);
        expressionAST = new RecordExpression(raAST, expressionPos);
      }
      break;

    // @author        Joseph
    // @description   Cambio de parsing de primary expression, de identifier a long identifier
    // @funcionalidad Cambio en las alternativas de primary expression
    // @codigo        J.81
    case Token.IDENTIFIER:
      {
        LongIdentifier liAST = parseLongIdentifier();
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(expressionPos);
          expressionAST = new CallExpression(liAST, apsAST, expressionPos);

        } else {
          if (liAST instanceof PackageLongIdentifier) {
             VarName varnAST = parseRestOfVarName(liAST.I);
             finish(expressionPos);
             PackageVname pvnAST = new PackageVname (((PackageLongIdentifier) liAST).PI,varnAST, expressionPos);    
             expressionAST = new VnameExpression(pvnAST, expressionPos); 
          } else {
            VarName varnAST = parseRestOfVarName(liAST.I);
            finish(expressionPos);
            SimpleVname svnAST = new SimpleVname (varnAST, expressionPos);
            expressionAST = new VnameExpression(svnAST, expressionPos);      
          }
        }
      }
      break;
    /* J.81
    case Token.IDENTIFIER:
      {
        Identifier iAST= parseIdentifier();
        if (currentToken.kind == Token.LPAREN) {
          acceptIt();
          ActualParameterSequence apsAST = parseActualParameterSequence();
          accept(Token.RPAREN);
          finish(expressionPos);
          expressionAST = new CallExpression(iAST, apsAST, expressionPos);

        } else {
          Vname vAST = parseRestOfVname(iAST);
          finish(expressionPos);
          expressionAST = new VnameExpression(vAST, expressionPos);
        }
      }
      break;
    */
    // END CAMBIO Joseph

    case Token.OPERATOR:
      {
        Operator opAST = parseOperator();
        Expression eAST = parsePrimaryExpression();
        finish(expressionPos);
        expressionAST = new UnaryExpression(opAST, eAST, expressionPos);
      }
      break;

    case Token.LPAREN:
      acceptIt();
      expressionAST = parseExpression();
      accept(Token.RPAREN);
      break;

    default:
      syntacticError("\"%\" cannot start an expression",
        currentToken.spelling);
      break;

    }
    return expressionAST;
  }

  RecordAggregate parseRecordAggregate() throws SyntaxError {
    RecordAggregate aggregateAST = null; // in case there's a syntactic error

    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Identifier iAST = parseIdentifier();
    accept(Token.IS);
    Expression eAST = parseExpression();

    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      RecordAggregate aAST = parseRecordAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleRecordAggregate(iAST, eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleRecordAggregate(iAST, eAST, aggregatePos);
    }
    return aggregateAST;
  }

  ArrayAggregate parseArrayAggregate() throws SyntaxError {
    ArrayAggregate aggregateAST = null; // in case there's a syntactic error

    SourcePosition aggregatePos = new SourcePosition();
    start(aggregatePos);

    Expression eAST = parseExpression();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ArrayAggregate aAST = parseArrayAggregate();
      finish(aggregatePos);
      aggregateAST = new MultipleArrayAggregate(eAST, aAST, aggregatePos);
    } else {
      finish(aggregatePos);
      aggregateAST = new SingleArrayAggregate(eAST, aggregatePos);
    }
    return aggregateAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// VALUE-OR-VARIABLE NAMES
//
///////////////////////////////////////////////////////////////////////////////
  
  
  
  // @author        Andres
  // @descripcion   Metodo para parsear LongIdentifier
  // @funcionalidad Parsear LongIdentifier
  // @codigo        A.126
  LongIdentifier parseLongIdentifier() throws SyntaxError {
      LongIdentifier longIdentifierAST = null;
      SourcePosition longIdentifierPos = new SourcePosition();
      start(longIdentifierPos);
      
      Identifier iAST = parseIdentifier();
      if (currentToken.kind == Token.DOLLAR) {
          finish(longIdentifierPos);
          PackageIdentifier piAST = new PackageIdentifier(iAST, longIdentifierPos);
          accept(Token.DOLLAR);
          
          Identifier i2AST = parseIdentifier();
          finish(longIdentifierPos);
          longIdentifierAST = new PackageLongIdentifier(piAST, i2AST, longIdentifierPos);
      } else {
          finish(longIdentifierPos);
          longIdentifierAST = new SimpleLongIdentifier(iAST, longIdentifierPos);
      }
      
      return longIdentifierAST;
  }
  
  // @author        Andres
  // @descripcion   Metodo para parsear SinglePackageDeclaration
  // @funcionalidad Parsear SinglePackageDeclaration
  // @codigo        A.128
  SinglePackageDeclaration parseSinglePackageDeclaration() throws SyntaxError {
      SinglePackageDeclaration singlePackageDeclarationAST = null;
      SourcePosition spdPos = new SourcePosition();
      start(spdPos);
      
      accept(Token.PACKAGE);
      PackageIdentifier piAST = parsePackageIdentifier();
      accept(Token.IS);
      Declaration dAST = parseDeclaration();
      accept(Token.END);
      finish(spdPos);
      // Se acepta despues porque no es parte de la frase
      // accept(Token.SEMICOLON);
      
      singlePackageDeclarationAST = new SinglePackageDeclaration(piAST, dAST, spdPos);
      
      return singlePackageDeclarationAST;
  }
  // END CAMBIO Andres
  
  // @author        Andres
  // @descripcion   Metodo para parsear PackageDeclaration
  // @funcionalidad Parsear PackageDeclaration
  // @codigo        A.142
  PackageDeclaration parsePackageDeclaration() throws SyntaxError {
      PackageDeclaration packageDeclarationAST = null;
      SourcePosition pdPos = new SourcePosition();
      start(pdPos);
      
      packageDeclarationAST = parseSinglePackageDeclaration();
      accept(Token.SEMICOLON);
      while (currentToken.kind == Token.PACKAGE) {
          SinglePackageDeclaration pd2AST = parseSinglePackageDeclaration();
          finish(pdPos);
          accept(Token.SEMICOLON);
          packageDeclarationAST = new SequentialPackageDeclaration(packageDeclarationAST, pd2AST, pdPos);
      }
      
      return packageDeclarationAST;
  }
  // END CAMBIO Andres
  
  // @author        Andres
  // @descripcion   Metodo para parsear el vname nuevo
  // @funcionalidad Parsear vname nuevo
  // @codigo        A.121
  Vname parseVname() throws SyntaxError {
      Vname vnameAST = null;
      SourcePosition vnamePos = new SourcePosition();
      start(vnamePos);
      
      Identifier iAST = parseIdentifier();
      if (currentToken.kind == Token.DOLLAR) {
          finish(vnamePos);
          PackageIdentifier piAST = new PackageIdentifier(iAST, vnamePos);
          accept(Token.DOLLAR);
          
          VarName vnAST = parseVarName();
          finish(vnamePos);
          vnameAST = new PackageVname(piAST, vnAST, vnamePos);
      } else {
          VarName vnAST = parseRestOfVarName(iAST);
          finish(vnamePos);
          vnameAST = new SimpleVname(vnAST, vnamePos);
      }
      
      return vnameAST;
  }
  /*
    Vname parseVname () throws SyntaxError {
    Vname vnameAST = null; // in case there's a syntactic error
    Identifier iAST = parseIdentifier();
    vnameAST = parseRestOfVname(iAST);
    return vnameAST;
  }

  Vname parseRestOfVname(Identifier identifierAST) throws SyntaxError {
    SourcePosition vnamePos = new SourcePosition();
    vnamePos = identifierAST.position;
    Vname vAST = new SimpleVname(identifierAST, vnamePos);

    while (currentToken.kind == Token.DOT ||
           currentToken.kind == Token.LBRACKET) {

      if (currentToken.kind == Token.DOT) {
        acceptIt();
        Identifier iAST = parseIdentifier();
        vAST = new DotVname(vAST, iAST, vnamePos);
      } else {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.RBRACKET);
        finish(vnamePos);
        vAST = new SubscriptVname(vAST, eAST, vnamePos);
      }
    }
    return vAST;
  }
  */
  // END CAMBIO Andres
  
  // @author        Andres
  // @descripcion   Metodo para parsear el package-identifier
  // @funcionalidad Parsear package-identifier
  // @codigo        A.120
  PackageIdentifier parsePackageIdentifier() throws SyntaxError {
      PackageIdentifier piAST = null;
      SourcePosition packageIdentifierPos = new SourcePosition();
      
      start(packageIdentifierPos);
      Identifier iAST = parseIdentifier();
      finish(packageIdentifierPos);
      
      piAST = new PackageIdentifier(iAST, packageIdentifierPos);
      return piAST;
  }
  // END CAMBIO Andres
  
  // @author        Andres
  // @descripcion   Metodo para parsear el VarName
  // @funcionalidad Parsear VarName
  // @codigo        A.117
  VarName parseVarName() throws SyntaxError {
    VarName varNameAST = null; // in case there's a syntactic error
    Identifier iAST = parseIdentifier();
    varNameAST = parseRestOfVarName(iAST);
    return varNameAST;
  }
  
  VarName parseRestOfVarName(Identifier identifierAST) throws SyntaxError {
    SourcePosition varNamePos = new SourcePosition();
    varNamePos = identifierAST.position;
    VarName vAST = new SimpleVarName(identifierAST, varNamePos);

    while (currentToken.kind == Token.DOT ||
           currentToken.kind == Token.LBRACKET) {

      if (currentToken.kind == Token.DOT) {
        acceptIt();
        Identifier iAST = parseIdentifier();
        vAST = new DotVarName(vAST, iAST, varNamePos);
      } else {
        acceptIt();
        Expression eAST = parseExpression();
        accept(Token.RBRACKET);
        finish(varNamePos);
        vAST = new SubscriptVarName(vAST, eAST, varNamePos);
      }
    }
    return vAST;
  }
  // END Cambio Andres

///////////////////////////////////////////////////////////////////////////////
//
// DECLARATIONS
//
///////////////////////////////////////////////////////////////////////////////

  // @author        Joseph
  // @description   Cambio en las alternativas de Declaration para que concatene compound declarations en lugar del single declarations
  // @funcionalidad Cambio en las alternativas de Declaration
  // @codigo        J.60
  Declaration parseDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);
    declarationAST = parseCompoundDeclaration();
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Declaration d2AST = parseCompoundDeclaration();
      finish(declarationPos);
      declarationAST = new SequentialDeclaration(declarationAST, d2AST,
        declarationPos);
    }
    return declarationAST;
  }
  /*J.60
  Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);
    declarationAST = parseSingleDeclaration();
    while (currentToken.kind == Token.SEMICOLON) {
      acceptIt();
      Declaration d2AST = parseSingleDeclaration();
      finish(declarationPos);
      declarationAST = new SequentialDeclaration(declarationAST, d2AST,
        declarationPos);
    }
    return declarationAST;
  */
  //END CAMBIO Joseph 
  
  // @author        Joseph
  // @description   Creacion del metodo de parseo de compound declarations
  // @funcionalidad Creacion del no terminal compound declarations
  // @codigo        J.59
   Declaration parseCompoundDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos);
    
    switch (currentToken.kind) {

    case Token.CONST:
    case Token.VAR:
    case Token.PROC:
    case Token.FUNC:
    case Token.TYPE:
      {
        declarationAST = parseSingleDeclaration();
      }
      break;
      
    case Token.RECURSIVE:
      {
        acceptIt();
        ProcFuncs pfsAST = parseProcFuncs();
        accept(Token.END);
        finish(declarationPos);
        declarationAST = new RecDeclaration(pfsAST, declarationPos);
      }
      break;
      
    case Token.PRIVATE:
      {
        acceptIt();
        Declaration d1AST = parseDeclaration();
        accept(Token.IN);
        Declaration d2AST = parseDeclaration();
        accept(Token.END);
        finish(declarationPos);
        declarationAST = new PrivDeclaration(d1AST, d2AST, declarationPos);
      }
      break;  
    default:
      syntacticError("\"%\" cannot start a declaration",
        currentToken.spelling);
      break;

    }
    return declarationAST;
  }
  // END CAMBIO Joseph
  
  
  
  // @author        Joseph
  // @description   Cambio del tipo de salida del metodo de parseo de single declaration a SingleDeclaration
  // adem�s se aplica el cambio de nombre de las variables al resto del m�todo
  // @funcionalidad Cambio en las alternativas de single declaration
  // @codigo        J.58
  SingleDeclaration parseSingleDeclaration() throws SyntaxError {
    SingleDeclaration singleDeclarationAST = null; // in case there's a syntactic error

    SourcePosition singleDeclarationPos = new SourcePosition();
    start(singleDeclarationPos);
  /* J.58
  Declaration parseSingleDeclaration() throws SyntaxError {
    Declaration declarationAST = null; // in case there's a syntactic error

    SourcePosition declarationPos = new SourcePosition();
    start(declarationPos); 
  */
  //END CAMBIO Joseph

    switch (currentToken.kind) {

    case Token.CONST:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        Expression eAST = parseExpression();
        finish(singleDeclarationPos);
        singleDeclarationAST = new ConstDeclaration(iAST, eAST, singleDeclarationPos);
      }
      break;
      
    // @author        Joseph
    // @description   Cambio en el parsing de la declaracion de variables 
    // @funcionalidad Modificaciones en las alternativas de single-declaration
    // @codigo        J.41
    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        switch (currentToken.kind) {
            
        case Token.COLON:
          {
           acceptIt();
           TypeDenoter tAST = parseTypeDenoter();
           finish(singleDeclarationPos);
           singleDeclarationAST = new VarTDDeclaration(iAST, tAST, singleDeclarationPos);
            
          }
          break;
        case Token.BECOMES:
          {
           acceptIt();
           Expression eAST = parseExpression();
           finish(singleDeclarationPos);
           singleDeclarationAST = new VarExpDeclaration(iAST, eAST, singleDeclarationPos);
          }
          break;
        default:
          syntacticError("\"%\" cannot follow var declaration",
           currentToken.spelling);
          break;                       
        } 
      }
      break;
    /* J.41
    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(declarationPos);
        declarationAST = new VarDeclaration(iAST, tAST, declarationPos);
      }
      break;      
    */      
    // END CAMBIO Joseph

    // @author        Joseph
    // @description   Cambio en el parsing de la declaracion de procedures 
    // @funcionalidad Modificaciones en las alternativas de single-declaration
    // @codigo        J.40
    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.IS);
        Command cAST = parseSingleCommand();
        accept(Token.END);
        finish(singleDeclarationPos);
        singleDeclarationAST = new ProcDeclaration(iAST, fpsAST, cAST, singleDeclarationPos);
      }
      break;
    /* J.40
    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.IS);
        Command cAST = parseSingleCommand();
        finish(declarationPos);
        declarationAST = new ProcDeclaration(iAST, fpsAST, cAST, declarationPos);
      }
      break;    
    */     
    //END Cambio Joseph

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        accept(Token.IS);
        Expression eAST = parseExpression();        
        finish(singleDeclarationPos);
        singleDeclarationAST = new FuncDeclaration(iAST, fpsAST, tAST, eAST,
          singleDeclarationPos);
      }
      break;

    case Token.TYPE:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.IS);
        TypeDenoter tAST = parseTypeDenoter();
        finish(singleDeclarationPos);
        singleDeclarationAST = new TypeDeclaration(iAST, tAST, singleDeclarationPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a declaration",
        currentToken.spelling);
      break;

    }
    return singleDeclarationAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// PARAMETERS
//
///////////////////////////////////////////////////////////////////////////////

  FormalParameterSequence parseFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST;

    SourcePosition formalsPos = new SourcePosition();

    start(formalsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(formalsPos);
      formalsAST = new EmptyFormalParameterSequence(formalsPos);

    } else {
      formalsAST = parseProperFormalParameterSequence();
    }
    return formalsAST;
  }

  FormalParameterSequence parseProperFormalParameterSequence() throws SyntaxError {
    FormalParameterSequence formalsAST = null; // in case there's a syntactic error;

    SourcePosition formalsPos = new SourcePosition();
    start(formalsPos);
    FormalParameter fpAST = parseFormalParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FormalParameterSequence fpsAST = parseProperFormalParameterSequence();
      finish(formalsPos);
      formalsAST = new MultipleFormalParameterSequence(fpAST, fpsAST,
        formalsPos);

    } else {
      finish(formalsPos);
      formalsAST = new SingleFormalParameterSequence(fpAST, formalsPos);
    }
    return formalsAST;
  }

  FormalParameter parseFormalParameter() throws SyntaxError {
    FormalParameter formalAST = null; // in case there's a syntactic error;

    SourcePosition formalPos = new SourcePosition();
    start(formalPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new ConstFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new VarFormalParameter(iAST, tAST, formalPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        finish(formalPos);
        formalAST = new ProcFormalParameter(iAST, fpsAST, formalPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        accept(Token.LPAREN);
        FormalParameterSequence fpsAST = parseFormalParameterSequence();
        accept(Token.RPAREN);
        accept(Token.COLON);
        TypeDenoter tAST = parseTypeDenoter();
        finish(formalPos);
        formalAST = new FuncFormalParameter(iAST, fpsAST, tAST, formalPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a formal parameter",
        currentToken.spelling);
      break;

    }
    return formalAST;
  }


  ActualParameterSequence parseActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST;

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    if (currentToken.kind == Token.RPAREN) {
      finish(actualsPos);
      actualsAST = new EmptyActualParameterSequence(actualsPos);

    } else {
      actualsAST = parseProperActualParameterSequence();
    }
    return actualsAST;
  }

  ActualParameterSequence parseProperActualParameterSequence() throws SyntaxError {
    ActualParameterSequence actualsAST = null; // in case there's a syntactic error

    SourcePosition actualsPos = new SourcePosition();

    start(actualsPos);
    ActualParameter apAST = parseActualParameter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      ActualParameterSequence apsAST = parseProperActualParameterSequence();
      finish(actualsPos);
      actualsAST = new MultipleActualParameterSequence(apAST, apsAST,
        actualsPos);
    } else {
      finish(actualsPos);
      actualsAST = new SingleActualParameterSequence(apAST, actualsPos);
    }
    return actualsAST;
  }

  ActualParameter parseActualParameter() throws SyntaxError {
    ActualParameter actualAST = null; // in case there's a syntactic error

    SourcePosition actualPos = new SourcePosition();

    start(actualPos);

    switch (currentToken.kind) {

    case Token.IDENTIFIER:
    case Token.INTLITERAL:
    case Token.CHARLITERAL:
    case Token.OPERATOR:
    case Token.LET:
    case Token.IF:
    case Token.LPAREN:
    case Token.LBRACKET:
    case Token.LCURLY:
      {
        Expression eAST = parseExpression();
        finish(actualPos);
        actualAST = new ConstActualParameter(eAST, actualPos);
      }
      break;

    case Token.VAR:
      {
        acceptIt();
        Vname vAST = parseVname();
        finish(actualPos);
        actualAST = new VarActualParameter(vAST, actualPos);
      }
      break;

    case Token.PROC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new ProcActualParameter(iAST, actualPos);
      }
      break;

    case Token.FUNC:
      {
        acceptIt();
        Identifier iAST = parseIdentifier();
        finish(actualPos);
        actualAST = new FuncActualParameter(iAST, actualPos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start an actual parameter",
        currentToken.spelling);
      break;

    }
    return actualAST;
  }

///////////////////////////////////////////////////////////////////////////////
//
// TYPE-DENOTERS
//
///////////////////////////////////////////////////////////////////////////////

  TypeDenoter parseTypeDenoter() throws SyntaxError {
    TypeDenoter typeAST = null; // in case there's a syntactic error
    SourcePosition typePos = new SourcePosition();

    start(typePos);

    switch (currentToken.kind) {

    // @author        Joseph
    // @description   Cambio de parsing de type-denoter, de identifier a long identifier
    // @funcionalidad Cambio en las alternativas de type-denoter
    // @codigo        J.81
    case Token.IDENTIFIER:
      {
        LongIdentifier liAST = parseLongIdentifier();
        finish(typePos);
        typeAST = new SimpleTypeDenoter(liAST, typePos);
      }
      break;
    /*J.81
    case Token.IDENTIFIER:
      {
        Identifier iAST = parseIdentifier();
        finish(typePos);
        typeAST = new SimpleTypeDenoter(iAST, typePos);
      }
      break;
    */
    // END CAMBIO Joseph

    case Token.ARRAY:
      {
        acceptIt();
        IntegerLiteral ilAST = parseIntegerLiteral();
        accept(Token.OF);
        TypeDenoter tAST = parseTypeDenoter();
        finish(typePos);
        typeAST = new ArrayTypeDenoter(ilAST, tAST, typePos);
      }
      break;

    case Token.RECORD:
      {
        acceptIt();
        FieldTypeDenoter fAST = parseFieldTypeDenoter();
        accept(Token.END);
        finish(typePos);
        typeAST = new RecordTypeDenoter(fAST, typePos);
      }
      break;

    default:
      syntacticError("\"%\" cannot start a type denoter",
        currentToken.spelling);
      break;

    }
    return typeAST;
  }

  FieldTypeDenoter parseFieldTypeDenoter() throws SyntaxError {
    FieldTypeDenoter fieldAST = null; // in case there's a syntactic error

    SourcePosition fieldPos = new SourcePosition();

    start(fieldPos);
    Identifier iAST = parseIdentifier();
    accept(Token.COLON);
    TypeDenoter tAST = parseTypeDenoter();
    if (currentToken.kind == Token.COMMA) {
      acceptIt();
      FieldTypeDenoter fAST = parseFieldTypeDenoter();
      finish(fieldPos);
      fieldAST = new MultipleFieldTypeDenoter(iAST, tAST, fAST, fieldPos);
    } else {
      finish(fieldPos);
      fieldAST = new SingleFieldTypeDenoter(iAST, tAST, fieldPos);
    }
    return fieldAST;
  }
}
