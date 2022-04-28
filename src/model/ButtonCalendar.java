/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author TESTER
 */
public class ButtonCalendar {

    private int id;
    private JButton boton;
    private JLabel titulo;
    private JButton eliminar;
    private int posicionCalendariox;
    private int posicionCalendarioy;
    private int position;

    public ButtonCalendar() {
    }
 
    public ButtonCalendar(int position, int id, JButton boton, JLabel titulo, JButton eliminar, int posicionCalendariox, int posicionCalendarioy) {
        this.position = position;
        this.id = id;
        this.boton = boton;
        this.titulo = titulo;
        this.eliminar = eliminar;
        this.posicionCalendariox = posicionCalendariox;
        this.posicionCalendarioy = posicionCalendarioy;       
        System.out.println("Crea objeto ButtonCalendar con parámetros");
    }

    public int getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public JButton getBoton() {
        return boton;
    }

    public JLabel getTitulo() {
        return titulo;
    }

    public JButton getEliminar() {
        return eliminar;
    }

    public int getPosicionCalendariox() {
        return posicionCalendariox;
    }

    public int getPosicionCalendarioy() {
        return posicionCalendarioy;
    }

    public JButton createButton(int posicionCalendarioxAux, int posicionCalendarioyAux){
        JButton botonAux = new JButton();
        botonAux.setSize(233, 169);
        botonAux.setLocation(posicionCalendarioxAux,posicionCalendarioyAux);
        return botonAux;
    }
    
    public JButton createButtonNotification(int posicionCalendarioxAux, int posicionCalendarioyAux){
        JButton botonAux = new JButton();
        botonAux.setSize(75,40);
        
        botonAux.setLocation(posicionCalendarioxAux,posicionCalendarioyAux);
        return botonAux;
    }
    public JScrollPane createTextFieldNotification(int posicionCalendarioxAux, int posicionCalendarioyAux, String texto){
        JTextField textoaux = new JTextField(texto);
        textoaux.setFont(new java.awt.Font("asd", 0, 15)); // NOI18N
        textoaux.setEditable(false);
        JScrollPane textoroll= new JScrollPane(textoaux);
        textoroll.setSize(535,50);
        textoroll.setLocation(posicionCalendarioxAux,posicionCalendarioyAux);
        return textoroll;
    }
    
    public JButton createButtonPrincipal(int posicionCalendarioxAux, int posicionCalendarioyAux){
        JButton botonAux = new JButton();
        botonAux.setSize(233, 169);
        botonAux.setLocation(posicionCalendarioxAux,posicionCalendarioyAux);
        botonAux.setFont(new java.awt.Font("Rockwell", 0, 20));
        botonAux.setText("+");
        return botonAux;
    }
    
    public JLabel createTitle(String calendarName, int posicionCalendarioxAux, int posicionCalendarioyAux){
        JLabel tituloAux = new JLabel();
        tituloAux.setSize(79, 25);
        tituloAux.setText(calendarName);
        tituloAux.setBorder(null);
        tituloAux.setLocation(posicionCalendarioxAux,posicionCalendarioyAux-39);
        return tituloAux;
    }
    
    public JButton createDelete(int posicionCalendarioxAux, int posicionCalendarioyAux){
        JButton eliminarAux = new JButton();
        eliminarAux.setIcon(new ImageIcon(getClass().getResource("/design/imagenes/basura.png"))); 
        eliminarAux.setSize(79, 25);
        eliminarAux.setBorder(null);
        eliminarAux.setBorderPainted(false);
        eliminarAux.setContentAreaFilled(false);
        eliminarAux.setLocation(posicionCalendarioxAux+160,posicionCalendarioyAux-39);
        return eliminarAux;
    }
    //ZONA INVITATION SHARE
    public JLabel createCorreoInvitationTitle(String correo, int posicionCalendarioxAux, int posicionCalendarioyAux){
        JLabel tituloAux = new JLabel();
        tituloAux.setSize(300, 25);
        tituloAux.setText(correo);
        tituloAux.setBorder(null);
        tituloAux.setLocation(posicionCalendarioxAux,posicionCalendarioyAux);
        return tituloAux;
    }
    public JButton createButtonInvitation(int posicionCalendarioxAux, int posicionCalendarioyAux){
        JButton botonAux = new JButton();
        botonAux.setSize(85, 25);
        
        botonAux.setLocation(posicionCalendarioxAux,posicionCalendarioyAux);
        return botonAux;
    }
    public JComboBox createJComboBox(int posicionCalendarioxAux, int posicionCalendarioyAux){
        javax.swing.JComboBox<String> botonAux=new javax.swing.JComboBox<>();
        botonAux.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Editor", "Lector"}));
        botonAux.setSize(85, 25);
        
        botonAux.setLocation(posicionCalendarioxAux,posicionCalendarioyAux);
        return botonAux;
    }

}