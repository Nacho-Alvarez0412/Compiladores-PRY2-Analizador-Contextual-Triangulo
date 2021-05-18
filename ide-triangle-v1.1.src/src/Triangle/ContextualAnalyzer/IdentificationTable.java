/*
 * @(#)IdentificationTable.java                2.1 2003/10/07
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

import Triangle.AbstractSyntaxTrees.Declaration;
import javafx.util.Pair;

public final class IdentificationTable {

  private int level;
  private IdEntry latest;

  public IdentificationTable () {
    level = 0;
    latest = null;
  }
  // Opens a new level in the identification table, 1 higher than the
  // current topmost level.

  public void openScope () {

    level ++;
  }

  // Closes the topmost level in the identification table, discarding
  // all entries belonging to that level.

  public void closeScope () {

    IdEntry entry, local;

    // Presumably, idTable.level > 0.
    entry = this.latest;
    while (entry.level == this.level) {
      local = entry;
      entry = local.previous;
    }
    this.level--;
    this.latest = entry;
  }

  // Makes a new entry in the identification table for the given identifier
  // and attribute. The new entry belongs to the current level.
  // duplicated is set to to true iff there is already an entry for the
  // same identifier at the current level.

  
  
  // @author        Ignacio
  // @descripcion   Modificacion id 
  // @funcionalidad Parseo de nuevos ASTs
  // @codigo        I.1
  public void enter (String id, Declaration attr) {
    String[] realID = getRealID(id);
    IdEntry entry = this.latest;
    boolean present = false, searching = true;

    // Check for duplicate entry ...
    while (searching) {
      if (entry == null || entry.level < this.level)
        searching = false;
      else if (this.isEntryEquals(entry.id, realID)) {
        present = true;
        searching = false;
       } else
       entry = entry.previous;
    }

    attr.duplicated = present;
    // Add new entry ...
    entry = new IdEntry(realID, attr, this.level, this.latest);
      System.out.println("("+realID[0]+","+realID[1]+")");
    this.latest = entry;
  }
  
  
  
  /*
   I.1
  
  public void enter (String id, Declaration attr) {
    IdEntry entry = this.latest;
    boolean present = false, searching = true;

    // Check for duplicate entry ...
    while (searching) {
      if (entry == null || entry.level < this.level)
        searching = false;
      else if (entry.id.equals(id)) {
        present = true;
        searching = false;
       } else
       entry = entry.previous;
    }

    attr.duplicated = present;
    // Add new entry ...
    entry = new IdEntry(id, attr, this.level, this.latest);
    this.latest = entry;
  }
  */
  // End cambio

  // Finds an entry for the given identifier in the identification table,
  // if any. If there are several entries for that identifier, finds the
  // entry at the highest level, in accordance with the scope rules.
  // Returns null iff no entry is found.
  // otherwise returns the attribute field of the entry found.

  public Declaration retrieve (String id) {

    IdEntry entry;
    Declaration attr = null;
    boolean present = false, searching = true;

    entry = this.latest;
    while (searching) {
      if (entry == null)
        searching = false;
      else if (entry.id.equals(id)) {
        present = true;
        searching = false;
        attr = entry.attr;
      } else
        entry = entry.previous;
    }

    return attr;
  }
    
  // @author        Ignacio
  // @descripcion   Verificar si se repite un entry
  // @funcionalidad Parseo de nuevos ASTs
  // @codigo        I.2
    private boolean isEntryEquals( String[] entryID, String[] id) {
        return entryID[0].equals(id[0]) && entryID[1].equals(id[1]);
    }
    
  //End Cambio

    private String[] getRealID(String id) {
        if(this.level == 0)
            return new String[]{"Ambient",id};
        else
            return new String[]{"Scope "+this.level,id};
    }

}
