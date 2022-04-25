/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package design;

import SqlDatabase.HerokuTaskSqlConnection;
import SqlDatabase.HerokuUsersSqlConnection;
import static design.usuario.userSigned;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.plaf.ColorUIResource;
import model.CalendarTask;
import model.Task;
import model.User;
import model.Tags;

public final class calendarView extends javax.swing.JFrame {

    private int actualMonth;
    private int actualYear;
    private CalendarTask actualCalendar;
    private User userSignedUpmp;
    Calendar cl = new GregorianCalendar();
    Tags tag; 
    ArrayList<Integer> aux;
    HerokuTaskSqlConnection conex_task;
    
    // Constructor
    public calendarView(CalendarTask actualCalendar) {
        this.actualCalendar = actualCalendar;
        initComponents();
        initTags();
        //aux = loadTaks();
        initTaks();
        //test();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        Color color = new Color(255,255,255);
        this.getContentPane().setBackground(color);
        actualMonth = cl.get(Calendar.MONTH);
        actualYear = cl.get(Calendar.YEAR);
        updateTasks();
        doubleClickDay();
        userSignedUpmp=userSigned;
        close();
    }
    
    
    // Method to access with double click to the day view
    public void doubleClickDay () {
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    String[] tasks = (String[])jTable1.getValueAt
                        (jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                    int day = Integer.parseInt(tasks[0]);
                    Timestamp date = new Timestamp (actualYear-1900, actualMonth+1, day, 0, 0, 0, 0);
                    dayView dia = new dayView(actualCalendar, date);
                    dia.setVisible(true);
                    setVisible(false);
                }
            }
        });
    }
    
    // Test para añadir tareas al calendario
    public void test() {
        CalendarTask c = new CalendarTask("prueba");
        Timestamp fecha2 = new Timestamp(2022-1900, 4,3, 0, 0, 0, 0);
        // ArrayList<Tags> tags = new ArrayList<>();
        tag = Tags.BIRTHDAY;
        // tags.add(tag);
        Task t = new Task(1, "Esto es una prueba", "This is the description", fecha2, 3, tag.toString());
        c.addTask(t);
        t = new Task(2, "Esto es una prueba1", "This is the description", new Timestamp(2022-1900, 4, 23, 0, 0, 0, 0), 3, tag.toString());
        c.addTask(t);
        tag = Tags.SCHOOL;
        // tags.add(tag);
        t = new Task(3, "Esto es una prueba2", "This is the description", new Timestamp(2022-1900, 4, 23, 0, 0, 0, 0), 3, tag.toString());
        c.addTask(t);
        t = new Task(4, "Esto es una prueba3", "This is the description", new Timestamp(2022-1900, 4, 23, 0, 0, 0, 0), 3, tag.toString());
        c.addTask(t);
        tag = Tags.HOUSE;
        // tags.add(tag);
        t = new Task(5, "Esto es una prueba1", "This is the description", new Timestamp(2022-1900, 4, 24, 0, 0, 0, 0), 3, tag.toString());
        c.addTask(t);
        //List l = c.getTasks(date);
        //l = c.getTasks(date);
        this.actualCalendar = c;
    }
    
    // Method to modify the table and update the tasks
    public void updateTasks() {
        jTable1.setRowHeight(100);
        Object mes [][] = calculateDate(this.actualMonth, this.actualYear);
        for (Object[] me : mes) {
            for (int j = 0; j<mes[0].length; j++) {
                if (me[j] == null) {
                    me[j] = new String[]{""};
                } else {
                    int day = (Integer) me[j];
                    Timestamp d = new Timestamp(this.actualYear-1900, 
                            this.actualMonth+1,day, 0, 0, 0, 0);
                    List<Task> tasks = this.actualCalendar.getTasks(d);
                    String[] content = new String[1];
                    if (tasks == null) {
                        content[0] = Integer.toString(day);
                    } else {
                        content = new String[tasks.size()+1];
                        content[0] = Integer.toString(day);
                        for (int k = 1; k < content.length; k++) {
                            content[k] = tasks.get(k-1).getName();
                        }
                    }
                    me[j] = content;
                }
            }
        }
        jTable1.setCellSelectionEnabled(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            mes,
            new String [] {
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
            }));
       MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
       jTable1.setDefaultRenderer(String[].class, renderer);
       for (int i = 0; i <7; i++) {
           jTable1.getColumnModel().getColumn(i).setCellRenderer(renderer);
       }
    }
    
    // Method to calculate the actual date
    private Object[][] calculateDate (int month, int year) {
        String months[] = {"January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"};
        Calendar c1 = new GregorianCalendar(year, month, 1);
        Object mesAux [][] = getMonth(c1.get(Calendar.DAY_OF_MONTH), c1.get(Calendar.DAY_OF_WEEK), 
                c1.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.actualMonth = month;
        this.actualYear = year;
        jLabel1.setText(months[month] + " " + year);
        return mesAux;
    }
    
    // Method to get the month of the date
    private Object [][] getMonth(int dayM, int dayOfWeek, int maxDayOfMonth) {
        Object mes[][] = {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            };
        dayOfWeek = dayOfWeek-2;
        if (dayOfWeek == -1) {
            dayOfWeek = 6;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = dayOfWeek; j < 7; j++) {
                if (dayM > maxDayOfMonth) {
                    break;
                }
                mes[i][j] = dayM;
                dayM++;
            }
            dayOfWeek = 0;
        }
        return mes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addTaskInternal = new javax.swing.JInternalFrame();
        jLabel5 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        hourBox = new javax.swing.JComboBox<>();
        minBox = new javax.swing.JComboBox<>();
        jComboBoxTag = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        addTaskInternal.setBackground(new java.awt.Color(255, 255, 255));
        addTaskInternal.setBorder(null);
        addTaskInternal.setVisible(true);

        jLabel5.setText("Time");

        nameField.setText("Add a title");
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

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        descriptionField.setText("Add a description");
        descriptionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descriptionFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionFieldFocusLost(evt);
            }
        });
        jScrollPane3.setViewportView(descriptionField);

        hourBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        hourBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourBoxActionPerformed(evt);
            }
        });

        minBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" }));
        minBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minBoxActionPerformed(evt);
            }
        });

        jLabel3.setText("Tag:");

        javax.swing.GroupLayout addTaskInternalLayout = new javax.swing.GroupLayout(addTaskInternal.getContentPane());
        addTaskInternal.getContentPane().setLayout(addTaskInternalLayout);
        addTaskInternalLayout.setHorizontalGroup(
            addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTaskInternalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addTaskInternalLayout.createSequentialGroup()
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addTaskInternalLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(addTaskInternalLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(hourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(minBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBoxTag, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3))
                        .addGap(175, 175, 175))))
        );
        addTaskInternalLayout.setVerticalGroup(
            addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTaskInternalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addTaskInternalLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addGap(131, 131, 131))
                    .addGroup(addTaskInternalLayout.createSequentialGroup()
                        .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(hourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Previous");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Filters:");

        jButton3.setText("Week");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Day");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Add a task");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE))
                .addGap(59, 59, 59)
                .addComponent(jButton5)
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(649, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (this.actualMonth - 1 == -1) {
            this.actualMonth = 11;
            this.actualYear -= 1;
        } else {
            this.actualMonth -= 1;
        }
        updateTasks();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Botón de avanzar al siguiente mes
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.actualMonth + 1 == 12) {
            this.actualMonth = 0;
            this.actualYear += 1;
        } else {
            this.actualMonth += 1;
        }
        updateTasks();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// TODO add your handling code here:
        if (jTable1.getSelectedRow() == -1 || jTable1.getSelectedColumn() == -1) {
            JOptionPane.showConfirmDialog(null,"Select a date.","", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
            return;
        }
        int option = JOptionPane.showConfirmDialog(null,addTaskInternal,"", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null);
         
        switch (option) {
            case JOptionPane.OK_OPTION:
                String[] tasks = (String[]) jTable1.getValueAt
                        (jTable1.getSelectedRow(), jTable1.getSelectedColumn());
                /*
                Radio button o checkbox para las tags, y las que esten seleccionadas, 
                insertarlas en el array (último atributo de la clase Task
                */
                Timestamp fecha = new Timestamp(this.actualYear-1900, this.actualMonth+1,Integer.parseInt (tasks[0]), Integer.valueOf(minBox.getSelectedItem().toString()), Integer.valueOf(minBox.getSelectedItem().toString()), 0, 0);
                // ArrayList<Tags> tags = new ArrayList<>();
                // tags.add(tag);
                Task t = new Task(1, nameField.getText(), descriptionField.getText(), fecha, 3, jComboBoxTag.getSelectedItem().toString());
                this.actualCalendar.addTask(t);
                
                updateTasks();
                
                //Timestamp fecha2 = new Timestamp(this.actualYear-1900, Integer.parseInt(month),Integer.parseInt (day), Integer.parseInt((String) hourBox.getSelectedItem()), Integer.parseInt((String) minBox.getSelectedItem()), 0, 0);
                //List l = actualCalendar.getTasks(fecha2);
                //t = (Task)l.get(l.size()-1);
                nameField.setText("Add a title");
                descriptionField.setText("Add a description");
                hourBox.setSelectedIndex(0);
                minBox.setSelectedIndex(0);
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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

    private void minBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minBoxActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        weekView week = new weekView(this.actualMonth,this.actualYear,this.actualCalendar);
        week.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dayView week = new dayView(this.actualCalendar, new Timestamp(this.actualYear-1900,this.actualMonth+1,1,0,0,0,0));
        System.out.println(this.actualMonth+1);
        week.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    public void close(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
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
    
    private void initTags() {
        for (Tags myVar : Tags.values()) {
            jComboBoxTag.addItem(myVar.toString()); 
        }   
    }

    private void initTaks() {
        Color color = new Color(255,255,255);
        this.getContentPane().setBackground(color);
        
    }

    /*private ArrayList<Integer> loadTaks() {
        conex_task = HerokuTaskSqlConnection.getInstance();
        ArrayList<Integer> taksCalendar=conex_task.selectAllTaksIdByIdCalendar(userSigned.getId());
        return taksCalendar;
    }*/
    
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
            java.util.logging.Logger.getLogger(calendarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(calendarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(calendarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(calendarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new calendarView(new CalendarTask()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame addTaskInternal;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JComboBox<String> hourBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBoxTag;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox<String> minBox;
    private javax.swing.JTextField nameField;
    // End of variables declaration//GEN-END:variables
}
