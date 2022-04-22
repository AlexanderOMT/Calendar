package design;

import SqlDatabase.HerokuTaskSqlConnection;
import SqlDatabase.HerokuUsersSqlConnection;
import static design.usuario.userSigned;
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
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
    public calendarView() {
        initComponents();
        initTags();
        aux = loadTaks();
        initTaks();
        // test();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        actualMonth = cl.get(Calendar.MONTH);
        actualYear = cl.get(Calendar.YEAR);
        updateTasks();
        userSignedUpmp=userSigned;
        close();
    }
    
    // Test para añadir tareas al calendario
    public void test() {
        CalendarTask c = new CalendarTask("prueba");
        Timestamp fecha2 = new Timestamp(1900-116, 5,3, 0, 0, 0, 0);
        // ArrayList<Tags> tags = new ArrayList<>();
        tag = Tags.BIRTHDAY;
        // tags.add(tag);
        Task t = new Task(1, "Esto es una prueba", "This is the description", fecha2, 3, tag.toString());
        c.addTask(t);
        t = new Task(2, "Esto es una prueba1", "This is the description", new Timestamp(2022, 3,23, 0, 0, 0, 0), 3, tag.toString());
        c.addTask(t);
        tag = Tags.SCHOOL;
        // tags.add(tag);
        t = new Task(3, "Esto es una prueba2", "This is the description",new Timestamp(2022, 3,23, 0, 0, 0, 0), 3, tag.toString());
        c.addTask(t);
        t = new Task(4, "Esto es una prueba3", "This is the description", new Timestamp(2022, 3,23, 0, 0, 0, 0), 3, tag.toString());
        c.addTask(t);
        tag = Tags.HOUSE;
        // tags.add(tag);
        t = new Task(5, "Esto es una prueba1", "This is the description", new Timestamp(2022, 3,24, 0, 0, 0, 0), 3, tag.toString());
        c.addTask(t);
        //List l = c.getTasks(date);
        //l = c.getTasks(date);
        this.actualCalendar = c;
    }
    
    // Método para formatear la tabla y actualizar las tareas
    public void updateTasks() {
        jTable1.setRowHeight(100);
        Object mes [][] = calculateDate(actualMonth, actualYear);
        for (Object[] me : mes) {
            for (int j = 0; j<mes[0].length; j++) {
                if (me[j] == null) {
                    me[j] = new String[]{""};
                } else {
                    int day = (Integer) me[j];
                    String date = "";
                    if (actualMonth <= 9) {
                        date = actualYear + "-0" + (actualMonth+1);
                    } else {
                        date = actualYear + "-" + (actualMonth+1);
                    }
                    if (day <= 9) {
                        date += "-0" + day;
                    } else {
                        date += "-" + day;
                    }
                    Timestamp d = new Timestamp(actualYear, actualMonth+1,day, 0, 0, 0, 0);
                    List<Task> tasks = actualCalendar.getTasks(d);
                    String[] content = new String[tasks.size()+1];
                    content[0] = Integer.toString(day);
                    for (int k = 1; k < content.length; k++) {
                        content[k] = tasks.get(k-1).getName();
                    }
                    me[j] = content;
                }
            }
        }
        jTable1.setCellSelectionEnabled(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            mes,
            new String [] {
                "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
            }));
       MultiLineTableCellRenderer renderer = new MultiLineTableCellRenderer();
       jTable1.setDefaultRenderer(String[].class, renderer);
       for (int i = 0; i <7; i++) {
           jTable1.getColumnModel().getColumn(i).setCellRenderer(renderer);
       }
    }
    
    // Método para calcular la fecha en la que se encuentra el usuario actualmente
    private Object[][] calculateDate (int month, int year) {
        String months[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Calendar c1 = new GregorianCalendar(year, month, 1);
        Object mesAux [][] = getMonth(c1.get(Calendar.DAY_OF_MONTH), c1.get(Calendar.DAY_OF_WEEK), 
                c1.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.actualMonth = month;
        this.actualYear = year;
        jLabel1.setText(months[month] + " " + year);
        return mesAux;
    }
    
    // Método para conseguir el mes entero de la fecha
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        addTaskInternal = new javax.swing.JInternalFrame();
        jLabel5 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        hourBox = new javax.swing.JComboBox<>();
        minBox = new javax.swing.JComboBox<>();
        jComboBoxTag = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

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

        jLabel2.setText("Tag:");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(addTaskInternalLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(hourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(minBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBoxTag, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2))
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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addGap(131, 131, 131))
                    .addGroup(addTaskInternalLayout.createSequentialGroup()
                        .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(hourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Previous");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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

        jButton3.setText("Add a title");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(254, 254, 254)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    // Botón de avanzar al siguiente mes
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if (this.actualMonth + 1 == 12) {
            this.actualMonth = 0;
            this.actualYear += 1;
        } else {
            this.actualMonth += 1;
        }
        updateTasks();
    }                                        

    // Botón de retroceder al anterior mes
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if (this.actualMonth - 1 == -1) {
            this.actualMonth = 11;
            this.actualYear -= 1;
        } else {
            this.actualMonth -= 1;
        }
        updateTasks();
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
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
                String month = "";
                if (this.actualMonth < 10) {
                    month = "0" + this.actualMonth;
                } else {
                    month = Integer.toString(this.actualMonth);
                }
                String day = "";
                if (Integer.parseInt(tasks[0]) < 10) {
                    day = "0" + tasks[0];
                } else {
                    day = tasks[0];
                }

                Timestamp fecha = new Timestamp(this.actualYear-1900, actualMonth,Integer.parseInt (day), Integer.valueOf(minBox.getSelectedItem().toString()), Integer.valueOf(minBox.getSelectedItem().toString()), 0, 0);
                // ArrayList<Tags> tags = new ArrayList<>();
                // tags.add(tag);
                Task t = new Task(1, nameField.getText(), descriptionField.getText(), fecha, 3, jComboBoxTag.getSelectedItem().toString());
                this.actualCalendar.addTask(t);
                
                updateTasks();
                
                Timestamp fecha2 = new Timestamp(this.actualYear-1900, Integer.parseInt(month),Integer.parseInt (day), Integer.parseInt((String) hourBox.getSelectedItem()), Integer.parseInt((String) minBox.getSelectedItem()), 0, 0);
                List l = actualCalendar.getTasks(fecha2);
                t = (Task)l.get(l.size()-1);
                nameField.setText("Add a title");
                descriptionField.setText("Add a description");
                hourBox.setSelectedIndex(0);
                minBox.setSelectedIndex(0);
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
        }
    }                                        

    private void minBoxActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void nameFieldFocusGained(java.awt.event.FocusEvent evt) {                                      
        if(nameField.getText().equals("Add a title")) nameField.setText("");
    }                                     

    private void nameFieldFocusLost(java.awt.event.FocusEvent evt) {                                    
        if(nameField.getText().equals("")) nameField.setText("Add a title");
    }                                   

    private void descriptionFieldFocusGained(java.awt.event.FocusEvent evt) {                                             
        if(descriptionField.getText().equals("Add a description")) descriptionField.setText("");
    }                                            

    private void descriptionFieldFocusLost(java.awt.event.FocusEvent evt) {                                           
        if(descriptionField.getText().equals("")) descriptionField.setText("Add a description");
    }                                          

    private void hourBoxActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(calendarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new calendarView().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JInternalFrame addTaskInternal;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JComboBox<String> hourBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxTag;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox<String> minBox;
    private javax.swing.JTextField nameField;
    // End of variables declaration                   

    private void initTags() {
        for (Tags myVar : Tags.values()) {
            jComboBoxTag.addItem(myVar.toString()); 
        }   
    }

    private void initTaks() {
        
    }

    private ArrayList<Integer> loadTaks() {
        conex_task = HerokuTaskSqlConnection.getInstance();
        ArrayList<Integer> taksCalendar=conex_task.selectAllTaksIdByIdCalendar(userSigned.getId());
        return taksCalendar;
    }
}
