/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Minerva
 */
public class InvitationElement {
    private JButton botonAccept;
    private JTextField texto;
    private JScrollPane textoroll;
    private JButton botonDecline;
    private Invitation invite;

    public InvitationElement(int id, JButton botonAccept, JTextField texto, JScrollPane textoroll, JButton botonDecline, Invitation invite) {
        this.botonAccept = botonAccept;
        this.texto = texto;
        this.textoroll = textoroll;
        this.botonDecline = botonDecline;
        this.invite=invite;
    }
    
    
    
    public JButton createButtonNotification(int posicionCalendarioxAux, int posicionCalendarioyAux){
        JButton botonAux = new JButton();
        botonAux.setSize(40,40);
        botonAux.setLocation(posicionCalendarioxAux,posicionCalendarioyAux);
        return botonAux;
    }
    public JScrollPane createTextFieldNotification(int posicionCalendarioxAux, int posicionCalendarioyAux, String texto){
        JTextField textoaux = new JTextField(texto);
        JScrollPane textoroll= new JScrollPane(textoaux);
        textoroll.setSize(370,50);
        textoroll.setLocation(posicionCalendarioxAux,posicionCalendarioyAux);
        return textoroll;
    }

    public JButton getBotonAccept() {
        return botonAccept;
    }

    public void setBotonAccept(JButton botonAccept) {
        this.botonAccept = botonAccept;
    }

    public JTextField getTexto() {
        return texto;
    }

    public void setTexto(JTextField texto) {
        this.texto = texto;
    }

    public JScrollPane getTextoroll() {
        return textoroll;
    }

    public void setTextoroll(JScrollPane textoroll) {
        this.textoroll = textoroll;
    }

    public JButton getBotonDecline() {
        return botonDecline;
    }

    public void setBotonDecline(JButton botonDecline) {
        this.botonDecline = botonDecline;
    }

    public Invitation getInvite() {
        return invite;
    }

    public void setInvite(Invitation invite) {
        this.invite = invite;
    }
}
