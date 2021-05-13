/*
 * IDE-Triangle v1.0
 * AboutDialog.java
 */

package GUI;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;


/**
 * About Dialog.
 *
 * @author Luis Leopoldo Pérez <luiperpe@ns.isi.ulatina.ac.cr>
 */
public class AboutDialog extends javax.swing.JDialog {
    
    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    /** 
     * Creates new form AboutDialog. 
     */
    public AboutDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        imagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponents(g);
                g.drawImage(new ImageIcon(getClass().getResource("Icons/About.gif")).getImage(), 0, 0, this);
            }
        };
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(imagePanel, java.awt.BorderLayout.CENTER);
        setSize(496, 220);
        setBackground(new Color(255, 255, 255));
        setResizable(false);
        setTitle("About...");
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        pack();
    }
    // </editor-fold>//GEN-END:initComponents
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    private javax.swing.JPanel imagePanel; // Panel with the image to be shown.
    // </editor-fold>
}
