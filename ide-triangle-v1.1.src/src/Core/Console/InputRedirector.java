/*
 * IDE-Triangle v1.0
 * InputRedirector.java
 */

package Core.Console;

import java.awt.event.ActionListener;
import java.io.InputStream;

/**
 * This class is used to redirect the console input. 
 * When the console expects for an input, an event is fired.
 *
 * @author Luis Leopoldo Pérez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class InputRedirector extends InputStream {
    
    // <editor-fold defaultstate="collapsed" desc=" Methods ">        
    /**
     * Creates a new instance of InputRedirector.
     * @param _delegate Event that runs every time the console is expecting for an input.
     */
    public InputRedirector(ActionListener _delegate) {
        delegate = _delegate;
        System.setIn(this);
    }
      
    /**
     * Adds user input to the console then calls a notify(), so the read() method
     * can continue.
     * @param _input Console input contents.
     */
    public synchronized void addInput (String _input) {
        input += _input;
        notify();
    }
    
    /**
     * Reads the input from the console, firing the event and waiting for a signal.
     * @return First character in the input queue.
     */
    public synchronized int read() {
        while (count == input.length()) { 
            input = "";
            count = 0;
            try {
                delegate.actionPerformed(null);
                wait();
            } catch(InterruptedException e) { }
        }
        
        char c = input.charAt(count);
        count++;
        return c;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">    

    private ActionListener delegate;    // Gets triggered when the console is expecting for an input.    
    private String input = "";          // The console input.
    private int count = 0;              // The actual byte count.
    // </editor-fold>
}
