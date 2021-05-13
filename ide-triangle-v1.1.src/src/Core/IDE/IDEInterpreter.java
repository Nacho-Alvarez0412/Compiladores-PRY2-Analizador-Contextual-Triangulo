/*
 * IDE-Triangle v1.0
 * IDEInterpreter.java
 */

package Core.IDE;
import java.awt.event.ActionListener;
// THis is a comment

/**
 * Just another small class to call the Triangle interpreter.
 *
 * @author Luis Leopoldo Pérez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class IDEInterpreter {
    
    // <editor-fold defaultstate="collapsed" desc=" Methods ">    
    
    /**
     * Creates a new instance of IDEInterpreter.
     * @param _delegate Event to be fired when the thread stops running.
     */
    public IDEInterpreter(ActionListener _delegate) {
        delegate = _delegate;
    }
    
    /**
     * Runs the Interpreter static main method as a separate thread.
     * @param fileName Path to the TAM Object File.
     */       
    public synchronized void Run(final String fileName) {
        new Thread(new Runnable() {
            public void run() {
                TAM.Interpreter.main(new String[] {fileName});
                delegate.actionPerformed(null);
            }
        }).start();
    }      
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    private ActionListener delegate;    // Gets triggered when the Interpreter stops.
    // </editor-fold>
}
