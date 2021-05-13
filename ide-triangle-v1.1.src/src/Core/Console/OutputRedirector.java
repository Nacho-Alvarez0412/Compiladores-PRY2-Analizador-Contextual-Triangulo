/*
 * IDE-Triangle v1.0
 * OutputRedirector.java
 */

package Core.Console;

import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;

/**
 * This class is used to redirect the console output to a queue, firing an
 * event every time there is data to read.
 *
 * @author Luis Leopoldo Pérez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class OutputRedirector extends ByteArrayOutputStream {
      
    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    /**
     * Creates a new instance of RedirectedStream
     */
    public OutputRedirector() {
        System.setOut(new PrintStream(this));
    }
          
    /**
     * Redirects the console output to the queue and fires the event.
     * @param b Array of bytes to be written to the console.
     * @throws java.io.IOException 
     */
    public void write(byte b[]) throws IOException { 
        String a = new String(b);
        dataQueue.add(a);
        delegate.actionPerformed(null);
    }
       
    /**
     * Redirects the console output to the queue and fires the event.
     * @param b Array of bytes to be written to the console.
     * @param offset Starting position in the array.
     * @param length Number of bytes to read.
     */
    public void write(byte b[], int offset, int length) {
        String a = new String(b, offset, length);
        dataQueue.add(a);
        delegate.actionPerformed(null);
    }    
        
    /**
     * Polls the queue and returns the first element.
     * @return First element in the queue (String with the output contents).
     */
    public String readQueue() {
        String ret = "";
        if (peekQueue())
            ret = (String)dataQueue.poll();
        
        return(ret);
    }
      
    /**
     * Peeks at the queue, returns true if there's data to read.
     * @return True if there's data to read.
     */
    public boolean peekQueue() {
        return((dataQueue.size() > 0));
    }
      
    /**
     * Sets the event delegate. Sometimes it must be changed.
     * @param _delegate New event to be fired.
     */
    public void setDelegate(ActionListener _delegate) {
        delegate = _delegate;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    private ActionListener delegate;                    // Event to be triggered
    private LinkedList dataQueue = new LinkedList();    // Queue with the data ro read
    // </editor-fold>
}
