/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design;

import SqlDatabase.HerokuCalendarPermitSqlConnection;
import SqlDatabase.HerokuTaskSqlConnection;
import static design.usuario.userSigned;
import java.awt.Color;
import java.sql.Timestamp;
import java.util.Map;
import javax.swing.JTable;
import model.CalendarTask;
import model.Tags;
import model.Task;
import model.User;

/**
 *
 * @author TESTER
 */
public class addTaskInternal extends javax.swing.JDialog {

    /**
     * Creates new form NewJDialog
     */
    private User userSignedUpmp;
    private JTable jTable1;
    private Timestamp fecha;
    private CalendarTask actualCalendar;
    private calendarView calendar;
    private dayView dateList;
    public addTaskInternal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setModal(true);
        this.setLocationRelativeTo(null);
        initComponents();
    }
    
    public addTaskInternal() {
        initComponents();
        userSignedUpmp=userSigned;
        setModal(true);
        this.setLocationRelativeTo(null);
        changeColor();
    }
    
    public addTaskInternal(JTable jTable1, Timestamp fecha, CalendarTask actualCalendar, calendarView calendar) {
        initComponents();
        userSignedUpmp=userSigned;
        this.jTable1 = jTable1;
        this.fecha = fecha;
        this.actualCalendar = actualCalendar;
        this.calendar=calendar;
        setModal(true);
        this.setLocationRelativeTo(null);
        initTags();
        changeColor();
    }
    
    public addTaskInternal(Timestamp fecha, CalendarTask actualCalendar, dayView dateList) {
        initComponents();
        userSignedUpmp=userSigned;
        this.fecha = fecha;
        this.actualCalendar = actualCalendar;
        this.dateList=dateList;
        setModal(true);
        this.setLocationRelativeTo(null);
        initTags();
        changeColor();
    }

    public void changeColor(){
        if(userSignedUpmp.getModo() == 1){
            this.setBackground(Color.decode("#000000"));
            
            nameField.setForeground(Color.decode("#FFFFFF"));
            nameField.setBackground(Color.decode("#000000"));
            descriptionField.setForeground(Color.decode("#FFFFFF"));
            descriptionField.setBackground(Color.decode("#000000"));
            jLabel5.setForeground(Color.decode("#FFFFFF"));
            jLabel3.setForeground(Color.decode("#FFFFFF"));
            
            hourBox.setBackground(Color.decode("#000000"));
            hourBox.setForeground(Color.decode("#FFFFFF"));
            minBox.setBackground(Color.decode("#000000"));
            minBox.setForeground(Color.decode("#FFFFFF"));
            jComboBoxTag.setBackground(Color.decode("#000000"));
            jComboBoxTag.setForeground(Color.decode("#FFFFFF"));
            
            jButton1.setBackground(Color.decode("#859EBC"));
            jButton2.setBackground(Color.decode("#859EBC"));
        }else{
            this.setBackground( Color.decode("#FFFFFF"));
            
            nameField.setForeground(Color.decode("#000000"));
            nameField.setBackground(Color.decode("#FFFFFF"));
            descriptionField.setForeground(Color.decode("#000000"));
            descriptionField.setBackground(Color.decode("#FFFFFF"));
            jLabel5.setForeground(Color.decode("#000000"));
            jLabel3.setForeground(Color.decode("#000000"));
            
            hourBox.setBackground(Color.decode("#FFFFFF"));
            hourBox.setForeground(Color.decode("#000000"));
            minBox.setBackground(Color.decode("#FFFFFF"));
            minBox.setForeground(Color.decode("#000000"));
            jComboBoxTag.setBackground(Color.decode("#FFFFFF"));
            jComboBoxTag.setForeground(Color.decode("#000000"));
            
            jButton1.setBackground(Color.decode("#F0F0F0"));
            jButton2.setBackground(Color.decode("#F0F0F0"));
        }       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        minBox = new javax.swing.JComboBox<>();
        jComboBoxTag = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        hourBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        minBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" }));
        minBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minBoxActionPerformed(evt);
            }
        });

        jComboBoxTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTagActionPerformed(evt);
            }
        });

        jLabel3.setText("Tag:");

        nameField.setText("Add a title");
        nameField.setBorder(null);
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameFieldFocusLost(evt);
            }
        });
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jScrollPane3.setBorder(null);

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        descriptionField.setText("Add a description");
        descriptionField.setBorder(null);
        descriptionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descriptionFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionFieldFocusLost(evt);
            }
        });
        jScrollPane3.setViewportView(descriptionField);

        jLabel5.setText("Time");

        hourBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        hourBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourBoxActionPerformed(evt);
            }
        });

        jButton1.setText("Accept");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(hourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(minBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxTag, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(hourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void minBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minBoxActionPerformed

    private void jComboBoxTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTagActionPerformed

    private void nameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFieldFocusGained
        // TODO add your handling code here:
        if(nameField.getText().equals("Add a title")) nameField.setText("");
    }//GEN-LAST:event_nameFieldFocusGained

    private void nameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFieldFocusLost
        // TODO add your handling code here:
        if(nameField.getText().equals("")) nameField.setText("Add a title");
    }//GEN-LAST:event_nameFieldFocusLost

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void descriptionFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionFieldFocusGained
        // TODO add your handling code here:
        if(descriptionField.getText().equals("Add a description")) descriptionField.setText("");
    }//GEN-LAST:event_descriptionFieldFocusGained

    private void descriptionFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionFieldFocusLost
        // TODO add your handling code here:
        if(descriptionField.getText().equals("")) descriptionField.setText("Add a description");
    }//GEN-LAST:event_descriptionFieldFocusLost

    private void hourBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hourBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hourBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        HerokuTaskSqlConnection con = HerokuTaskSqlConnection.getInstance();
        HerokuCalendarPermitSqlConnection con1 = HerokuCalendarPermitSqlConnection.getInstance();
        this.fecha.setHours(Integer.valueOf(hourBox.getSelectedItem().toString()));
        this.fecha.setMinutes(Integer.valueOf(minBox.getSelectedItem().toString()));
        Task t = new Task(nameField.getText(), descriptionField.getText(), 
                        this.fecha, 3, jComboBoxTag.getSelectedItem().toString());
        this.actualCalendar.addTask(t);
        int idTask = con.insertTaskByTask(t);
        String rol = con1.selectRolfromUser(userSignedUpmp.getId(), this.actualCalendar.getId());
        con1.insertCalendarPermit(userSignedUpmp.getId(), this.actualCalendar.getId(), idTask, rol);
        Map<Integer, String> user_id_rol = con1.selectUsersPermitsByCalendarId(this.actualCalendar.getId());
        user_id_rol.keySet().forEach((id) -> {
            con1.insertCalendarPermit(id, this.actualCalendar.getId(), idTask, user_id_rol.get(id));
        });
        if (this.calendar != null) {
            calendar.loadTask();
            calendar.updateTasks();
        }
        if (this.dateList != null) {
            dateList.loadTask();
            dateList.updateDate();
        }
        nameField.setText("Add a title");
        descriptionField.setText("Add a description");
        hourBox.setSelectedIndex(0);
        minBox.setSelectedIndex(0);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void initTags() {
        for (Tags myVar : Tags.values()) {
            jComboBoxTag.addItem(myVar.toString()); 
        }   
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
            java.util.logging.Logger.getLogger(addTaskInternal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addTaskInternal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addTaskInternal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addTaskInternal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                addTaskInternal dialog = new addTaskInternal(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JComboBox<String> hourBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxTag;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> minBox;
    private javax.swing.JTextField nameField;
    // End of variables declaration//GEN-END:variables
}
