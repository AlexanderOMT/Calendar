/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

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
}