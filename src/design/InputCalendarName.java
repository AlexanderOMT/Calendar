/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design;

import SqlDatabase.HerokuCalendarPermitSqlConnection;
import SqlDatabase.HerokuCalendarSqlConnection;
import SqlDatabase.HerokuUsersSqlConnection;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.*;

/**
 *
 * @author Leyre
 */
public class InputCalendarName extends javax.swing.JDialog {

    /**
     * Creates new form InputCalendarName
     */
    public InputCalendarName(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public InputCalendarName(){
        initComponents();
        Color color = new Color(255,255,255);
        this.getContentPane().setBackground(color);
        setModal(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendarName = new javax.swing.JTextField();
        AddCalendar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        AddCalendar.setBackground(new java.awt.Color(102, 204, 255));
        AddCalendar.setText("Add");
        AddCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCalendarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel1.setText("Enter the name of your calendar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(calendarName, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calendarName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCalendarActionPerformed
        // TODO add your handling code here:
        
        String CalendarName = calendarName.getText();
        calendar calendar = new calendar(CalendarName);
        
        HerokuUsersSqlConnection conex = HerokuUsersSqlConnection.getInstance();
        HerokuCalendarPermitSqlConnection conex_cal_per = HerokuCalendarPermitSqlConnection.getInstance();
        HerokuCalendarSqlConnection conex_cal = HerokuCalendarSqlConnection.getInstance();
        conex.insertCalendar(calendar.getName());
        /*metodo obtener id del calendario recientemente creado*/
        /*prueba con un usuario creado en la base de datos.
        IMPORTANTE: implantar uso de sesiones a traves de cada ventana que se cree*/
        int id_cal_recien_creado=conex_cal.selectUltimateCalendar();
        conex_cal_per.insertCalendarPermit(11, id_cal_recien_creado, 1, "Admin");
        System.out.println("ultimo caelndario"+id_cal_recien_creado);
        /*
        Controlar que no este vacio
        */
           this.setVisible(false);
        
    }//GEN-LAST:event_AddCalendarActionPerformed

    
    
    public String getCalendarName(){
        return calendarName.getText();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputCalendarName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputCalendarName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputCalendarName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputCalendarName.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputCalendarName dialog = new InputCalendarName(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCalendar;
    private javax.swing.JTextField calendarName;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
