/*
 * IDE-Triangle v1.0
 * FileFrame.java
 */

package GUI;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Element;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * File Frame. Contains the source edition text box, console, ASTs and 
 * everything else in an edition window.
 * 
 * @author Luis Leopoldo Pérez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class FileFrame extends javax.swing.JInternalFrame {
    
    // <editor-fold defaultstate="collapsed" desc=" Methods ">
      
    /**
     * Creates a new instance of FileFrame.
     * @param delegateKey Event to fire when a key is pressed in the editor window.
     * @param delegateFrame Event to fire when the frame is closed or focused.
     * @param delegateEnter Event to fire when the "Enter Input" button is pressed.
     */
    public FileFrame(KeyAdapter delegateKey, MouseListener delegateMouse, InternalFrameListener delegateFrame, ActionListener delegateEnter) {
        initComponents();
        previouslySaved = false;        
        sourcePane.addKeyListener(delegateKey);
        addInternalFrameListener(delegateFrame);
        enterButton.addActionListener(delegateEnter);
        sourcePane.addMouseListener(delegateMouse);
        previouslyModified = false;        
    }
        
    /**
     * Returns the text in the editor window.
     * @return Text in the editor window.
     */
    public String getSourcePaneText() {
        return(sourcePane.getText());
    }
       
    /**
     * Sets the text in the editor box.
     * @param text String with new text to be set.
     */
    public void setSourcePaneText(String text) {
        sourcePane.setText(text);
    }
  
    /**
     * Returns the previouslySaved value.
     * @return True if the file has been previously saved.
     */
    public boolean getPreviouslySaved() {
        return(previouslySaved);
    }
      
    /**
     * Sets the file as previously saved.
     * @param _previouslySaved New previouslySaved value.
     */
    public void setPreviouslySaved(boolean _previouslySaved) {
        previouslySaved = _previouslySaved;
    }
      
    /**
     * Sets the file prvious size value.
     * @param _previousSize New value to be set.
     */
    public void setPreviousSize(int _previousSize) {
        previousSize = _previousSize;
    }
    /**
     * Sets the file prvious Text value.
     * @param _previousText New value to be set.
     */
    public void setPreviousText(String _previousText) {
        previousText = _previousText;
    }
    
    /**
     * Sets the file as previously modified.
     * @param _previouslyModified New value to be set.
     */
    public void setPreviouslyModified(boolean _previouslyModified) {
        previouslyModified = _previouslyModified;
    }
      
    /**
     * Determines if the file has changed since the las time it was saved.
     * @return True if the file has changed.
     */
    public boolean hasChanged() {
        if (previousSize != sourcePane.getText().length() || !previousText.equals(sourcePane.getText())) {
            if (!previouslyModified)
                previouslyModified = true;
        }        
        return(previouslyModified);
    }
       
    /**
     * Returns the selected text from the editor text box.
     * @return Selected string.
     */
    public String getSelectedText() {
        String className = tabbedPane.getSelectedComponent().getName();
        String ret = "";
        
        if (className.compareTo("sourceScroll") == 0)
            ret = sourcePane.getSelectedText();
        
        if (className.compareTo("consolePanel") == 0)
            ret = consolePane.getSelectedText();
        
        if (className.compareTo("tamScroll") == 0)
            ret = tamPane.getSelectedText();
        
        return(ret);
    }
      
    /**
     * Enables/Disables the console input fields.
     * @param enabled New value to be set.
     */
    public void setInputEnabled(boolean enabled) {
        inputField.setEnabled(enabled);
        enterButton.setEnabled(enabled);
    }
          
    /**
     * Pastes text into the editor text box.
     * @param text String with the text to be pasted.
     */
    public void pasteText(String text) {        
        if ((tabbedPane.getSelectedComponent().getName().compareTo("sourceScroll")) == 0) {
            int caretPosition = sourcePane.getCaretPosition();
            sourcePane.setText(sourcePane.getText().substring(0, sourcePane.getSelectionStart()) + text + sourcePane.getText().substring(sourcePane.getSelectionEnd(), sourcePane.getText().length()));
            sourcePane.setCaretPosition(caretPosition);
        }
    }
    
    /**
     * Cuts the selected text from the editor text box.
     */
    public void cutText() {
        if ((tabbedPane.getSelectedComponent().getName().compareTo("sourceScroll")) == 0) {
            int caretPosition = sourcePane.getCaretPosition();
            sourcePane.setText(sourcePane.getText().substring(0, sourcePane.getSelectionStart()) + sourcePane.getText().substring(sourcePane.getSelectionEnd(), sourcePane.getText().length()));
            sourcePane.setCaretPosition(caretPosition);
        }
    }
        
    /** 
     * Clears the Console text box.
     */
    public void clearConsole() {
        consolePane.setText("");
    }
    
    /**
     * Writes in the Console text box.
     * @param text Text to be written.
     */
    public void writeToConsole(String text) {
        consolePane.setText(consolePane.getText() + text);
    }
    
    /**
     * Clears the TAM Code text box.   
     */
    public void clearTAMCode() {
        tamPane.setText("");
    }
    
    /**
     * Writes in the TAM Code text box.
     * @param text Text to be written.
     */
    public void writeToTAMCode(String text) {
        tamPane.setText(tamPane.getText() + text);
    }
      
    /**
     * Sets the focus on the Console Panel.
     */
    public void selectConsole() {
        tabbedPane.setSelectedComponent(consolePanel);
    }
    
    /**
     * Gets the text in the input field.
     * @return Text in the input field.
     */
    public String getInputFieldText() {
        return(inputField.getText());
    }
    
    /** 
     * Clears the text in the input field.
     */
    public void clearInputField() {
        inputField.setText("");
    }
    
    /**
     * Highlights the entire line where an error was reported.
     */
    public void highlightError(int line) {
        sourcePane.requestFocus();
        Element elem = sourcePane.getDocument().getDefaultRootElement();
        int start = elem.getElement(line - 1).getStartOffset();
        int end = elem.getElement(line - 1).getEndOffset() - 1;
        sourcePane.select(start, end);
        UpdateRowColNumbers();
    }
    
    
    /**
     * Updates the Row and Column Number bar on the bottom of the Source pane.
     */
    public void UpdateRowColNumbers() {     
        Element elem = sourcePane.getDocument().getDefaultRootElement();
        
        int row = elem.getElementIndex(sourcePane.getCaretPosition());
        int col = sourcePane.getCaretPosition() - elem.getElement(row).getStartOffset();
        rowPane.setText("Line: " + ++row + ", Position: " + ++col + "");        
    }
    
    
    /** 
     * Removes the content of the AST Pane.       
     */
    public void clearTree() {
        if (astTree != null) {
            astScroll.remove(astTree);
            astScroll.setViewportView(null);
        }
    }
    
    /**
     * Sets the Abstract Syntax Tree to draw
     * @param tree Root node of the AST to be drawn.
     */
    public void setTree(DefaultMutableTreeNode tree) {
        astTree = new JTree(tree);
        for (int i=0;i<astTree.getRowCount();i++)
            astTree.expandRow(i);
        
        astTree.setSize(astScroll.getSize());
        astScroll.add(astTree);
        astScroll.setViewportView(astTree);
    }
    
    /**
     * Removes the content of the Table Details Pane.
     */
    public void clearTable() {
        if (idTable != null) {
            tableScroll.remove(idTable);
            tableScroll.setViewportView(null);
        }
    }
    
    /**
     * Sets the Identifier Table Details to show.
     * @param table Table model.
     */
    public void setTable(DefaultTableModel table) {
        idTable = new JTable(table);
        idTable.setEnabled(false);
        tableScroll.add(idTable);
        tableScroll.setViewportView(idTable);
    }
    // </editor-fold>
                 
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        tabbedPane = new javax.swing.JTabbedPane();
        sourcePanel = new javax.swing.JPanel();
        sourceScroll = new javax.swing.JScrollPane();
        sourcePane = new javax.swing.JEditorPane();
        rowPane = new javax.swing.JTextPane();
        consolePanel = new javax.swing.JPanel();
        consoleScroll = new javax.swing.JScrollPane();
        consolePane = new javax.swing.JEditorPane();
        inputPanel = new javax.swing.JPanel();
        inputField = new javax.swing.JTextField();
        enterButton = new javax.swing.JButton();
        astScroll = new javax.swing.JScrollPane();
        tamScroll = new javax.swing.JScrollPane();
        tamPane = new javax.swing.JEditorPane();
        tableScroll = new javax.swing.JScrollPane();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setDoubleBuffered(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/iconFrame.gif")));
        sourcePanel.setLayout(new javax.swing.BoxLayout(sourcePanel, javax.swing.BoxLayout.Y_AXIS));

        sourceScroll.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sourceScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sourceScroll.setName("sourceScroll");
        sourcePane.setFont(new java.awt.Font("Courier New", 0, 12));
        sourceScroll.setViewportView(sourcePane);

        sourcePanel.add(sourceScroll);

        rowPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rowPane.setEditable(false);
        rowPane.setFocusable(false);
        rowPane.setMaximumSize(new java.awt.Dimension(2147483647, 20));
        sourcePanel.add(rowPane);

        tabbedPane.addTab("Source Code", sourcePanel);

        consolePanel.setLayout(new javax.swing.BoxLayout(consolePanel, javax.swing.BoxLayout.Y_AXIS));

        consolePanel.setName("consolePanel");
        consoleScroll.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        consoleScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        consolePane.setEditable(false);
        consolePane.setFont(new java.awt.Font("Courier New", 0, 12));
        consoleScroll.setViewportView(consolePane);

        consolePanel.add(consoleScroll);

        inputPanel.setLayout(new javax.swing.BoxLayout(inputPanel, javax.swing.BoxLayout.X_AXIS));

        inputPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inputField.setEnabled(false);
        inputField.setMaximumSize(new java.awt.Dimension(2147483647, 20));
        inputField.setMinimumSize(new java.awt.Dimension(11, 20));
        inputField.setPreferredSize(new java.awt.Dimension(23, 20));
        inputPanel.add(inputField);

        enterButton.setText("Enter Input");
        enterButton.setEnabled(false);
        inputPanel.add(enterButton);

        consolePanel.add(inputPanel);

        tabbedPane.addTab("Console", consolePanel);

        astScroll.setBorder(null);
        tabbedPane.addTab("Abstract Syntax Trees", astScroll);

        tamScroll.setBorder(null);
        tamScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tamScroll.setName("tamScroll");
        tamPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tamPane.setEditable(false);
        tamPane.setFont(new java.awt.Font("Courier New", 0, 12));
        tamScroll.setViewportView(tamPane);

        tabbedPane.addTab("TAM Code", tamScroll);

        tableScroll.setBorder(null);
        tabbedPane.addTab("Table Details", tableScroll);

        getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
      
    // <editor-fold defaultstate="collapsed" desc=" GUI Variables ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane astScroll;
    private javax.swing.JEditorPane consolePane;
    private javax.swing.JPanel consolePanel;
    private javax.swing.JScrollPane consoleScroll;
    private javax.swing.JButton enterButton;
    private javax.swing.JTextField inputField;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JTextPane rowPane;
    private javax.swing.JEditorPane sourcePane;
    private javax.swing.JPanel sourcePanel;
    private javax.swing.JScrollPane sourceScroll;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JEditorPane tamPane;
    private javax.swing.JScrollPane tamScroll;
    // End of variables declaration//GEN-END:variables
    private JTree astTree;
    private JTable idTable;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Non-GUI Variables ">
    // [ Non-GUI Variables ]
    private boolean previouslySaved;        // Defines if the file has been previously saved
    private boolean previouslyModified;     // Defines if the file has been recently modified
    private int previousSize;               // The size of the file before being modified.    
    private String previousText;               // The text of the file before being modified.    
    // [ End of Non-GUI Variables ]
    // </editor-fold>    
    
}
