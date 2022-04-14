/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package design;

import SqlDatabase.HerokuCalendarPermitSqlConnection;
import SqlDatabase.HerokuCalendarSqlConnection;
import SqlDatabase.HerokuTaskSqlConnection;
import SqlDatabase.HerokuUsersSqlConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import model.ButtonCalendar;
import model.User;
import model.Calendars;

/**
 *
 * @author Leyre
 */
public class MainPage extends javax.swing.JFrame implements usuario{

    int posicionCalendariox = 6;
    int posicionCalendarioy = 39;
    HerokuCalendarSqlConnection conex_cal;
    HerokuUsersSqlConnection conex_us;
    HerokuCalendarPermitSqlConnection conex_cal_per;
    Calendars calendars = new Calendars();
    ArrayList<Integer> aux;
    int id = 0;
    /**
     * Creates new form MainPage
     */
    
    private User userSignedUpmp;
    public MainPage() throws SQLException {
        initComponents();
        Color color =new Color(86,47,65);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        conex_us = HerokuUsersSqlConnection.getInstance();
        conex_cal = HerokuCalendarSqlConnection.getInstance();
        System.out.println("el usuario se llama " +userSigned.getEmail());
        aux = loadCalendars();
        initCalendars();
        userSignedUpmp=userSigned;
        close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        singout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        configuration = new javax.swing.JButton();
        alert = new javax.swing.JButton();
        users1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);

        jPanel2.setBackground(new java.awt.Color(235, 216, 189));

        singout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/imagenes/singOut.png"))); // NOI18N
        singout.setBorderPainted(false);
        singout.setContentAreaFilled(false);
        singout.setFocusPainted(false);
        singout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singoutActionPerformed(evt);
            }
        });

        description.setEditable(false);
        description.setBackground(new java.awt.Color(235, 216, 189));
        description.setBorder(null);
        description.setForeground(new java.awt.Color(235, 216, 189));
        description.setCaretColor(new java.awt.Color(235, 216, 189));
        description.setDisabledTextColor(new java.awt.Color(235, 216, 189));
        description.setFocusable(false);
        description.setRequestFocusEnabled(false);
        description.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(description);

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        jLabel1.setText("Description");

        jTextPane2.setEditable(false);
        jTextPane2.setBackground(new java.awt.Color(235, 216, 189));
        jTextPane2.setBorder(null);
        jTextPane2.setForeground(new java.awt.Color(235, 216, 189));
        jTextPane2.setCaretColor(new java.awt.Color(235, 216, 189));
        jTextPane2.setDisabledTextColor(new java.awt.Color(235, 216, 189));
        jTextPane2.setFocusable(false);
        jTextPane2.setRequestFocusEnabled(false);
        jTextPane2.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(jTextPane2);

        configuration.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        configuration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/imagenes/configuration.png"))); // NOI18N
        configuration.setText("Configuration");
        configuration.setBorderPainted(false);
        configuration.setContentAreaFilled(false);
        configuration.setFocusable(false);
        configuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configurationActionPerformed(evt);
            }
        });

        alert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/imagenes/alert.png"))); // NOI18N
        alert.setBorderPainted(false);
        alert.setContentAreaFilled(false);
        alert.setFocusable(false);
        alert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alertActionPerformed(evt);
            }
        });

        users1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/imagenes/user.png"))); // NOI18N
        users1.setBorderPainted(false);
        users1.setContentAreaFilled(false);
        users1.setFocusPainted(false);
        users1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                users1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(configuration, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(singout, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(186, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(users1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(251, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(singout)
                .addGap(50, 50, 50)
                .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 347, Short.MAX_VALUE)
                .addComponent(configuration, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(133, 133, 133)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(610, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(users1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(687, Short.MAX_VALUE)))
        );

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        jLabel2.setText("My Calendars");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1262, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(1017, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        


    private void initCalendars(){
        conex_cal_per = HerokuCalendarPermitSqlConnection.getInstance();
        conex_cal = HerokuCalendarSqlConnection.getInstance();
        
        ButtonCalendar bc = new ButtonCalendar();
        JButton jButton2 = bc.createButtonPrincipal(posicionCalendariox, posicionCalendarioy);
        jPanel4.add(jButton2);
        
        jButton2.addActionListener((java.awt.event.ActionEvent e) -> {

            InputCalendarName inputCalendarName = new InputCalendarName();
            inputCalendarName.userSignedIn=userSigned;
            inputCalendarName.setVisible(true);


            String calendarName = inputCalendarName.getCalendarName();
            if(!calendarName.equals("")){
                aux.add(inputCalendarName.getCalendarId());
                añadirCalendario(calendarName);
            }
        });
        
        for (int x=0; x<aux.size(); x++){
            String calendar_name=conex_cal.getCalendarNameById(aux.get(x));
            if(!(calendar_name == null)){
                añadirCalendario(calendar_name);
            }else{
                System.out.println("no existe un calendario con ese id");
            }
        }
         
    }
    
    private ArrayList<Integer> loadCalendars(){
        conex_cal_per = HerokuCalendarPermitSqlConnection.getInstance();
        ArrayList<Integer> calendarsUser=conex_cal_per.selectAllCalendarsIdByIdUser(userSigned.getId());
        return calendarsUser;
    }                                           

    private void singoutActionPerformed(java.awt.event.ActionEvent evt) {                                        
        conex_us = HerokuUsersSqlConnection.getInstance();
        
        try {
            if(conex_us.signOut2(this.userSignedUpmp)){
                System.out.println("El signOut se ha realizado de forma correcta");
                this.setVisible(false);
                System.exit(0);
            }else {
                JOptionPane.showMessageDialog(null, "El signOut ha fallado");
                System.out.println("El signOut ha fallado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                       

    
    private void configurationActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        Configuration configuration = new Configuration();
        configuration.setVisible(true);
        
    }                                             

    private void alertActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
        Notification notification = new Notification();
        notification.setVisible(true);
        
    }                                     

    private void users1ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                                  
    
    public void close(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                confirmarSalida();
            }
        });
    }
    
    public void confirmarSalida() {
        HerokuUsersSqlConnection conex = HerokuUsersSqlConnection.getInstance();
        try {
            if(conex.signOut2(userSignedUpmp)){
                System.out.println("El signOut se ha realizado de forma correcta");
                this.setVisible(false);
                System.exit(0);
            }else {
                JOptionPane.showMessageDialog(null, "El signOut ha fallado");
                System.out.println("El signOut ha fallado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void añadirCalendario(String calendarName){
        /*JButton boton1 = new JButton();
        boton1.setSize(233, 169);
        JLabel titulo = new JLabel();
        titulo.setSize(79, 25);
        titulo.setText(calendarName);
        titulo.setBorder(null);
        JButton eliminar = new JButton();
        eliminar.setIcon(new ImageIcon(getClass().getResource("/design/imagenes/basura.png"))); 
        eliminar.setSize(79, 25);
        eliminar.setBorder(null);
        eliminar.setBorderPainted(false);
        eliminar.setContentAreaFilled(false);
        
        jPanel4.add(boton1);
        jPanel4.add(titulo);
        jPanel4.add(eliminar);*/
        
        ButtonCalendar calendar_aux = new ButtonCalendar();
     
        JButton boton1 = calendar_aux.createButton(posicionCalendariox, posicionCalendarioy);
        JLabel titulo = calendar_aux.createTitle(calendarName, posicionCalendariox, posicionCalendarioy);
        JButton eliminar = calendar_aux.createDelete(posicionCalendariox, posicionCalendarioy);
        
        jPanel4.add(boton1);
        jPanel4.add(titulo);
        jPanel4.add(eliminar);
        
        if(id == aux.size()){
            id--;
        }
        
        ButtonCalendar calendar = new ButtonCalendar(id, aux.get(id), boton1, titulo, eliminar, posicionCalendariox, posicionCalendarioy);
        
        id++;
        
        calendars.addCalendar(calendar);
        
        if(posicionCalendariox > 1235){
            posicionCalendariox=6;
            posicionCalendarioy+=210;
        }else{
            posicionCalendariox+=247;
        }
        
        boton1.setLocation(posicionCalendariox,posicionCalendarioy);
        titulo.setLocation(posicionCalendariox,posicionCalendarioy-39);
        eliminar.setLocation(posicionCalendariox+160,posicionCalendarioy-39);
        
        boton1.addActionListener((java.awt.event.ActionEvent e) -> {
            System.out.println("Se ha abierto el calendario " + calendarName);
            calendarView cv = new calendarView();
            cv.setVisible(true);
        });
        
        eliminar.addActionListener((java.awt.event.ActionEvent e) -> {
            /*if(posicionCalendariox == 6 && posicionCalendarioy == 210){
                posicionCalendariox -= 247;
            } else if(posicionCalendariox != 6){
                posicionCalendariox -= 247;
            } else {
                posicionCalendariox = 1241;
                posicionCalendarioy -= 210;
            }*/

            boton1.setVisible(false);
            titulo.setVisible(false);
            eliminar.setVisible(false);
        
            ArrayList<ButtonCalendar> a = calendars.getCalendars();
            int x = 0;
            for(int i = 0; i < aux.size(); i++){
                if(a.get(i).getTitulo().getText().equals(titulo.getText())){
                   x = a.get(i).getId();
                }
            }
            
            conex_cal.deleteCalendarById(x);
            aux.remove(aux.indexOf(x));
         
            id--;
            
            jPanel4.removeAll();
            jPanel4.updateUI();
            jPanel4.repaint();
            
            posicionCalendariox = 6;
            posicionCalendarioy = 39;

            initCalendars();
        });
        
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
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainPage().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton alert;
    private javax.swing.JButton configuration;
    private javax.swing.JTextPane description;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JButton singout;
    private javax.swing.JButton users1;
    // End of variables declaration                   
}
