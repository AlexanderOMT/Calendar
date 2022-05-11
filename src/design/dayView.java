package design;

import SqlDatabase.HerokuCalendarPermitSqlConnection;
import SqlDatabase.HerokuCalendarSqlConnection;
import SqlDatabase.HerokuTaskSqlConnection;
import static design.usuario.userSigned;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import model.CalendarTask;
import model.Tags;
import model.Task;
import model.User;

public class dayView extends javax.swing.JFrame {

    private CalendarTask actualCalendar;
    private Timestamp date;
    private HerokuTaskSqlConnection conex_task;
    private HerokuCalendarPermitSqlConnection conex_calendarPermit;
    Tags tag;
    private User userSignedUpmp;

    public dayView(CalendarTask actualCalendar, Timestamp date) {
        this.actualCalendar = actualCalendar;
        this.date = date;
        initComponents();
        userSignedUpmp = userSigned;
        changeColor();

        initTags();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        updateDate();
        doubleClickTask();
        ifRol();
    }

    public void ifRol() {
        if (userSigned.getRol().equals("Lector")) {
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jButton6.setVisible(false);
        }
    }

    // Method to access with double click to the day view
    public void doubleClickTask() {
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JList list = (JList) me.getSource();
                Point p = me.getPoint();
                if (me.getClickCount() == 2) {
                    List<Task> dayTasks = actualCalendar.getTasks(date);
                    Task t = dayTasks.get(jList1.getSelectedIndex());
                    taskView taskView = new taskView(t);
                    taskView.setVisible(true);
                }
            }
        });
    }

    public void changeColor() {
        if (userSignedUpmp.getModo() == 1) {
            this.getContentPane().setBackground(Color.decode("#000000"));
            dayLabel.setForeground(Color.decode("#FFFFFF"));

            jButton4.setBackground(Color.decode("#859EBC"));
            jButton5.setBackground(Color.decode("#859EBC"));

            jButton1.setBackground(Color.decode("#859EBC"));
            jButton2.setBackground(Color.decode("#859EBC"));
            jButton3.setBackground(Color.decode("#859EBC"));
            jButton6.setBackground(Color.decode("#859EBC"));

            jList1.setBackground(Color.decode("#F0F0F0"));
        } else {
            this.getContentPane().setBackground(Color.decode("#FFFFFF"));
            dayLabel.setForeground(Color.decode("#000000"));

            jButton4.setBackground(Color.decode("#F0F0F0"));
            jButton5.setBackground(Color.decode("#F0F0F0"));

            jButton1.setBackground(Color.decode("#F0F0F0"));
            jButton2.setBackground(Color.decode("#F0F0F0"));
            jButton3.setBackground(Color.decode("#F0F0F0"));
            jButton6.setBackground(Color.decode("#F0F0F0"));

            jList1.setBackground(Color.decode("#F0F0F0"));
        }
    }

    public void updateDate() {
        String months[] = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
        dayLabel.setText(date.getDate() + "-" + months[date.getMonth()] + "-" + (date.getYear() + 1900));
        DefaultListModel<String> modelo = new DefaultListModel<>();
        jList1.setModel(modelo);
        List<Task> dayTasks = actualCalendar.getTasks(this.date);
        if (dayTasks != null) {
            Iterator i = dayTasks.iterator();
            while (i.hasNext()) {
                Task t = (Task) i.next();
                modelo.addElement(t.getName());
            }
        }
    }

    private void initTags() {
        for (Tags myVar : Tags.values()) {
            jComboBoxTag.addItem(myVar.toString());
            jComboBoxTag1.addItem(myVar.toString());
        }
    }

    public void loadTask() {
        conex_calendarPermit = HerokuCalendarPermitSqlConnection.getInstance();
        HerokuCalendarSqlConnection conex_cal = HerokuCalendarSqlConnection.getInstance();
        conex_task = HerokuTaskSqlConnection.getInstance();
        actualCalendar.getId();
        System.out.println(actualCalendar.getId());

        ArrayList<Integer> calendarsTask = conex_calendarPermit.selectAllTaskIdByIdCalendar(actualCalendar.getId());
        CalendarTask c = new CalendarTask();

        for (int i = 0; i < calendarsTask.size(); i++) {
            Task t = conex_task.getTaskById(calendarsTask.get(i));
            c.addTask(t);
        }
        c.setId(this.actualCalendar.getId());
        c.setName(actualCalendar.getName());
        this.actualCalendar = c;
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
        jLabel6 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        hourBox = new javax.swing.JComboBox<>();
        minBox = new javax.swing.JComboBox<>();
        jComboBoxTag = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        modifyTaskInternal = new javax.swing.JInternalFrame();
        jLabel7 = new javax.swing.JLabel();
        nameField1 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        descriptionField1 = new javax.swing.JTextArea();
        hourBox1 = new javax.swing.JComboBox<>();
        minBox1 = new javax.swing.JComboBox<>();
        jComboBoxTag1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        dayLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        addTaskInternal.setBackground(new java.awt.Color(255, 255, 255));
        addTaskInternal.setBorder(null);
        addTaskInternal.setVisible(true);

        jLabel6.setText("Time");

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
        jScrollPane4.setViewportView(descriptionField);

        hourBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
        hourBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourBoxActionPerformed(evt);
            }
        });

        minBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"}));
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
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                                .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(addTaskInternalLayout.createSequentialGroup()
                                                                        .addComponent(jLabel6)
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
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                                .addGap(131, 131, 131))
                                        .addGroup(addTaskInternalLayout.createSequentialGroup()
                                                .addGroup(addTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(hourBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(minBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        modifyTaskInternal.setBackground(new java.awt.Color(255, 255, 255));
        modifyTaskInternal.setBorder(null);
        modifyTaskInternal.setVisible(true);

        jLabel7.setText("Time");

        nameField1.setText("Add a title");
        nameField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameField1FocusGained(evt);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                nameField1FocusLost(evt);
            }
        });
        nameField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameField1ActionPerformed(evt);
            }
        });

        descriptionField1.setColumns(20);
        descriptionField1.setRows(5);
        descriptionField1.setText("Add a description");
        descriptionField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descriptionField1FocusGained(evt);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionField1FocusLost(evt);
            }
        });
        jScrollPane5.setViewportView(descriptionField1);

        hourBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
        hourBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hourBox1ActionPerformed(evt);
            }
        });

        minBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"}));
        minBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tag:");

        javax.swing.GroupLayout modifyTaskInternalLayout = new javax.swing.GroupLayout(modifyTaskInternal.getContentPane());
        modifyTaskInternal.getContentPane().setLayout(modifyTaskInternalLayout);
        modifyTaskInternalLayout.setHorizontalGroup(
                modifyTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(modifyTaskInternalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(modifyTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(modifyTaskInternalLayout.createSequentialGroup()
                                                .addComponent(nameField1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(modifyTaskInternalLayout.createSequentialGroup()
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                                .addGroup(modifyTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(modifyTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(modifyTaskInternalLayout.createSequentialGroup()
                                                                        .addComponent(jLabel7)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(hourBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(minBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(jComboBoxTag1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(jLabel4))
                                                .addGap(175, 175, 175))))
        );
        modifyTaskInternalLayout.setVerticalGroup(
                modifyTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(modifyTaskInternalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(nameField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(modifyTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(modifyTaskInternalLayout.createSequentialGroup()
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                                .addGap(131, 131, 131))
                                        .addGroup(modifyTaskInternalLayout.createSequentialGroup()
                                                .addGroup(modifyTaskInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(hourBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(minBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxTag1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dayLabel.setText("Day");

        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modify");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Prev");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Next");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(191, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(134, 134, 134)
                                                .addComponent(jButton5)))
                                .addGap(18, 135, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(201, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jButton5)
                                                .addComponent(dayLabel)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6)
                                .addGap(11, 11, 11)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    //Back
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        calendarView cal = new calendarView(this.actualCalendar);
        cal.setVisible(true);
        setVisible(false);
    }

    //Modify
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jList1.getSelectedIndex() == -1) {
            selectTask selectTask = new selectTask();
            selectTask.setVisible(true);
        } else {
            List<Task> dayTasks = actualCalendar.getTasks(this.date);
            Task t = dayTasks.get(jList1.getSelectedIndex());
            modifyTaskInternal modifyTaskInternal = new modifyTaskInternal(t, this.date, this.actualCalendar, this);
            modifyTaskInternal.setVisible(true);
        }
    }

    //Delete
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jList1.getSelectedIndex() == -1) {
            JOptionPane.showConfirmDialog(null, "Select a task.", "", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        } else {
            ListModel model = jList1.getModel();
            List<Task> dayTasks = actualCalendar.getTasks(this.date);
            Task t = dayTasks.get(jList1.getSelectedIndex());
            HerokuTaskSqlConnection taskConn = HerokuTaskSqlConnection.getInstance();
            taskConn.deleteTaskById(t.getId());
            loadTask();
            updateDate();
            JOptionPane.showConfirmDialog(null, "Eliminado!", "", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        }
    }

    // Next day
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        Calendar c2 = new GregorianCalendar(this.date.getYear() + 1900, this.date.getMonth(), 1);
        if (this.date.getDate() + 1 == c2.getActualMaximum(Calendar.DAY_OF_MONTH) + 1) {
            if (this.date.getMonth() + 1 == 12) {
                Calendar c1 = new GregorianCalendar(this.date.getYear() - 1 + 1900, 11, 1);
                this.date = new Timestamp(this.date.getYear() + 1, 0, 1, 0, 0, 0, 0);
            } else {
                Calendar c1 = new GregorianCalendar(this.date.getYear(), this.date.getMonth() + 1, 1);
                this.date = new Timestamp(this.date.getYear(), this.date.getMonth() + 1, 1, 0, 0, 0, 0);
            }
        } else {
            this.date = new Timestamp(this.date.getYear(), this.date.getMonth(), this.date.getDate() + 1, 0, 0, 0, 0);
        }
        updateDate();
    }

    // Prev day
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.date.getDate() - 1 == 0) {
            if (this.date.getMonth() - 1 == -1) {
                Calendar c1 = new GregorianCalendar(this.date.getYear() - 1 + 1900, 11, 1);
                this.date = new Timestamp(this.date.getYear() - 1, 11, c1.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
            } else {
                Calendar c1 = new GregorianCalendar(this.date.getYear(), this.date.getMonth() - 1, 1);
                this.date = new Timestamp(this.date.getYear(), this.date.getMonth() - 1, c1.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
            }
        } else {
            this.date = new Timestamp(this.date.getYear(), this.date.getMonth(), this.date.getDate() - 1, 0, 0, 0, 0);
        }
        updateDate();
    }

    // Add task
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        addTaskInternal addTaskInternal = new addTaskInternal(this.date, this.actualCalendar, this);
        addTaskInternal.setVisible(true);
    }

    private void nameFieldFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (nameField.getText().equals("Add a title")) {
            nameField.setText("");
        }
    }

    private void nameFieldFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (nameField.getText().equals("")) {
            nameField.setText("Add a title");
        }
    }

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void descriptionFieldFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (descriptionField.getText().equals("Add a description")) {
            descriptionField.setText("");
        }
    }

    private void descriptionFieldFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        if (descriptionField.getText().equals("")) {
            descriptionField.setText("Add a description");
        }
    }

    private void hourBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void minBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void nameField1FocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void nameField1FocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void nameField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void descriptionField1FocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void descriptionField1FocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void hourBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void minBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(dayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dayView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dayView(new CalendarTask(), new Timestamp(2022 - 1900, 3, 1, 0, 0, 0, 0)).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JInternalFrame addTaskInternal;
    private javax.swing.JLabel dayLabel;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JTextArea descriptionField1;
    private javax.swing.JComboBox<String> hourBox;
    private javax.swing.JComboBox<String> hourBox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBoxTag;
    private javax.swing.JComboBox<String> jComboBoxTag1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JComboBox<String> minBox;
    private javax.swing.JComboBox<String> minBox1;
    private javax.swing.JInternalFrame modifyTaskInternal;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField nameField1;
    // End of variables declaration                   
}
