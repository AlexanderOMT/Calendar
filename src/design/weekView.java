/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package design;

import static design.usuario.userSigned;
import java.awt.Color;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import model.CalendarTask;
import model.Tags;
import model.Task;
import model.User;

public class weekView extends javax.swing.JFrame {

    private int actualMonth;
    private int actualYear;
    private int prevFirst;
    private int prevLast;
    private int prevChangeDay;
    private boolean prevChange = false;
    private int nextFirst;
    private int nextLast;
    private int nextChangeDay;
    private boolean nextChange = false;
    private User userSignedUpmp;
    private CalendarTask actualCalendar;
    private Tags tag;
    private int idCalendar;
    
    public weekView(int month, int year, CalendarTask actualCalendar) {
        this.idCalendar = idCalendar;
        this.actualCalendar = actualCalendar;
        this.actualMonth = month;
        this.actualYear = year;
        initComponents();
        
        userSignedUpmp=userSigned;
        changeColor();
        
        //test();
        calculateDate();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        /* Color color = new Color(255,255,255);
        this.getContentPane().setBackground(color); */
    }
    
    public void changeColor(){
        if(userSignedUpmp.getModo() == 1){
            this.getContentPane().setBackground(Color.decode("#000000"));
            jLabelDay1.setForeground(Color.decode("#FFFFFF"));
            jLabelDay2.setForeground(Color.decode("#FFFFFF"));
            jLabelDay3.setForeground(Color.decode("#FFFFFF"));
            jLabelDay4.setForeground(Color.decode("#FFFFFF"));
            jLabelDay5.setForeground(Color.decode("#FFFFFF"));
            jLabelDay6.setForeground(Color.decode("#FFFFFF"));
            jLabelDay7.setForeground(Color.decode("#FFFFFF"));
            jLabelMY.setForeground(Color.decode("#FFFFFF"));

            jButton1.setBackground(Color.decode("#859EBC"));
            jButton2.setBackground(Color.decode("#859EBC"));
            jButton3.setBackground(Color.decode("#859EBC"));
            
            jList1.setBackground(Color.decode("#F0F0F0"));
            jList2.setBackground(Color.decode("#F0F0F0"));
            jList3.setBackground(Color.decode("#F0F0F0"));
            jList4.setBackground(Color.decode("#F0F0F0"));
            jList5.setBackground(Color.decode("#F0F0F0"));
            jList6.setBackground(Color.decode("#F0F0F0"));
            jList7.setBackground(Color.decode("#F0F0F0"));
        }else{
            this.getContentPane().setBackground(Color.decode("#FFFFFF"));
            jLabelDay1.setForeground(Color.decode("#000000"));
            jLabelDay2.setForeground(Color.decode("#000000"));
            jLabelDay3.setForeground(Color.decode("#000000"));
            jLabelDay4.setForeground(Color.decode("#000000"));
            jLabelDay5.setForeground(Color.decode("#000000"));
            jLabelDay6.setForeground(Color.decode("#000000"));
            jLabelDay7.setForeground(Color.decode("#000000"));
            jLabelMY.setForeground(Color.decode("#000000"));

            jButton1.setBackground(Color.decode("#F0F0F0"));
            jButton2.setBackground(Color.decode("#F0F0F0"));
            jButton3.setBackground(Color.decode("#F0F0F0"));
            
            jList1.setBackground(Color.decode("#F0F0F0"));
            jList2.setBackground(Color.decode("#F0F0F0"));
            jList3.setBackground(Color.decode("#F0F0F0"));
            jList4.setBackground(Color.decode("#F0F0F0"));
            jList5.setBackground(Color.decode("#F0F0F0"));
            jList6.setBackground(Color.decode("#F0F0F0"));
            jList7.setBackground(Color.decode("#F0F0F0"));
        }
    }    

    
    // Method to calculate dates of week and show
    public void calculateDate() {
        String months[] = {"January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"};
        jLabelMY.setText(months[this.actualMonth] + " " + this.actualYear);
        Calendar c1 = new GregorianCalendar(actualYear, actualMonth, 1);
        Calendar c2 = new GregorianCalendar(actualYear, actualMonth-1, 1);
        int dayOfWeek = c1.get(Calendar.DAY_OF_WEEK)-2;
        if (dayOfWeek == -1) {
            dayOfWeek = 6;
        }
        int firstDayOfWeek = c2.getActualMaximum(Calendar.DAY_OF_MONTH) - dayOfWeek + 1;
        int dayChangeMonth = c2.getActualMaximum(Calendar.DAY_OF_MONTH);
        int lastDayOfWeek = 7 - dayOfWeek;
        calculateWeek(firstDayOfWeek,lastDayOfWeek,dayChangeMonth);
    }
    
    // Method to calculate the date of the week, dayChangeMonth value = -1 when no change of month
    public void calculateWeek (int firstDayOfWeek, int lastDayOfWeek, int dayChangeMonth) {
        String[] days = new String[]{"Monday", "Tuesday", "Wednesday", 
                            "Thursday", "Friday", "Saturday", "Sunday"};
        JLabel[] labels = new JLabel[]{jLabelDay1,jLabelDay2,jLabelDay3,
                            jLabelDay4,jLabelDay5,jLabelDay6,jLabelDay7};
        JList[] lists = new JList[]{jList1,jList2,jList3,jList4,jList5,
                            jList6,jList7};
        this.nextFirst = lastDayOfWeek + 1;
        this.nextLast = this.nextFirst + 6;
        this.nextChangeDay = -1;
        Calendar c1 = new GregorianCalendar(actualYear, actualMonth, 1);
        if (this.nextLast > c1.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            this.nextLast = this.nextLast - c1.getActualMaximum(Calendar.DAY_OF_MONTH);
            this.nextChangeDay = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
            this.nextChange = true;
        }
        
        this.prevLast = firstDayOfWeek - 1;
        this.prevFirst = this.prevLast - 6;
        this.prevChangeDay = -1;
        if (dayChangeMonth != -1) {
            this.prevChange = true;
        }
        if (prevFirst <= 0) {
            Calendar c2 = new GregorianCalendar(actualYear, actualMonth-1, 1);
            this.prevFirst = c2.getActualMaximum(Calendar.DAY_OF_MONTH) + this.prevFirst;
            this.prevChangeDay = c2.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if (dayChangeMonth != -1) {
            dayChangeMonth = dayChangeMonth-firstDayOfWeek+1;
            for (int i = 0; i < dayChangeMonth; i++) {
                labels[i].setText(days[i] + " " + firstDayOfWeek);
                Timestamp t = new Timestamp(this.actualYear-1900, this.actualMonth, 
                        firstDayOfWeek, 0, 0, 0, 0);
                if (firstDayOfWeek == 31) {
                    t = new Timestamp(this.actualYear-1900, this.actualMonth, 
                        0, 0, 0, 0, 0);
                }
                updateList(t,lists[i]);
                firstDayOfWeek++;
            }
            firstDayOfWeek = 1;
            for (int i = dayChangeMonth; i < dayChangeMonth+lastDayOfWeek; i++) {
                labels[i].setText(days[i] + " " + firstDayOfWeek);
                Timestamp t = new Timestamp(this.actualYear-1900, this.actualMonth, 
                        firstDayOfWeek, 0, 0, 0, 0);
                updateList(t,lists[i]);
                firstDayOfWeek++;
            }
        } else {
            for (int i = 0; i < 7; i++) {
                labels[i].setText(days[i] + " " + firstDayOfWeek);
                Timestamp t = new Timestamp(this.actualYear-1900, this.actualMonth, 
                        firstDayOfWeek, 0, 0, 0, 0);
                if (firstDayOfWeek == 31) {
                    t = new Timestamp(this.actualYear-1900, this.actualMonth, 
                        0, 0, 0, 0, 0);
                }
                updateList(t,lists[i]);
                firstDayOfWeek++;
            }
        }
    }
    
    public void updateList(Timestamp date, JList j) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        j.setModel(modelo);
        List<Task> dayTasks = this.actualCalendar.getTasks(date);
        if (dayTasks != null) {
            Iterator i = dayTasks.iterator();
            while (i.hasNext()) {
                Task t = (Task)i.next();
                modelo.addElement(t.getName());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList6 = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList7 = new javax.swing.JList<>();
        jLabelDay1 = new javax.swing.JLabel();
        jLabelDay2 = new javax.swing.JLabel();
        jLabelDay3 = new javax.swing.JLabel();
        jLabelDay4 = new javax.swing.JLabel();
        jLabelDay5 = new javax.swing.JLabel();
        jLabelDay6 = new javax.swing.JLabel();
        jLabelDay7 = new javax.swing.JLabel();
        jLabelMY = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);

        jScrollPane2.setViewportView(jList2);

        jScrollPane3.setViewportView(jList3);

        jScrollPane4.setViewportView(jList4);

        jScrollPane5.setViewportView(jList5);

        jScrollPane6.setViewportView(jList6);

        jScrollPane7.setViewportView(jList7);

        jLabelDay1.setText("Lunes");

        jLabelDay2.setText("Martes");

        jLabelDay3.setText("Miércoles");

        jLabelDay4.setText("Jueves");

        jLabelDay5.setText("Viernes");

        jLabelDay6.setText("Sábado");

        jLabelDay7.setText("Domingo");

        jLabelMY.setText("Mes + Año");

        jButton1.setText("Prev");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Back");
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
                        .addComponent(jLabelDay1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelDay2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelDay3, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMY, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelDay4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelDay5, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDay6, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelDay7, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addGap(79, 79, 79))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addGap(83, 83, 83))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMY)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDay2)
                    .addComponent(jLabelDay3)
                    .addComponent(jLabelDay4)
                    .addComponent(jLabelDay5)
                    .addComponent(jLabelDay6)
                    .addComponent(jLabelDay7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    // Button go to the previous week
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if (this.prevChange == true) {
            this.actualMonth--;
            if (this.actualMonth == -1) {
                this.actualMonth = 11;
                this.actualYear--;
            }
            this.prevChange = false;
        }
        if (this.nextChange == true) {
            this.nextChange = false;
        }
        calculateWeek(this.prevFirst, this.prevLast, this.prevChangeDay);
        String months[] = {"January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"};
        jLabelMY.setText(months[this.actualMonth] + " " + this.actualYear);
    }                                        

    // Button go to the next week
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if (this.nextChange == true) {
            this.actualMonth++;
            if (this.actualMonth == 12) {
                this.actualMonth = 0;
                this.actualYear++;
            }
            this.nextChange = false;
        }
        if (this.prevChange == true) {
            this.prevChange = false;
        }
        calculateWeek(this.nextFirst, this.nextLast, this.nextChangeDay);
        String months[] = {"January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"};
        jLabelMY.setText(months[this.actualMonth] + " " + this.actualYear);
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        calendarView cal = new calendarView(this.actualCalendar);
        cal.setVisible(true);
        setVisible(false);
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
            java.util.logging.Logger.getLogger(weekView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(weekView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(weekView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(weekView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new weekView(3,2022,new CalendarTask()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabelDay1;
    private javax.swing.JLabel jLabelDay2;
    private javax.swing.JLabel jLabelDay3;
    private javax.swing.JLabel jLabelDay4;
    private javax.swing.JLabel jLabelDay5;
    private javax.swing.JLabel jLabelDay6;
    private javax.swing.JLabel jLabelDay7;
    private javax.swing.JLabel jLabelMY;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JList<String> jList4;
    private javax.swing.JList<String> jList5;
    private javax.swing.JList<String> jList6;
    private javax.swing.JList<String> jList7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    // End of variables declaration                   
}
