package model;

/**
 *
 * @author Selene
 */

import java.util.ArrayList;

public class Calendars {
    ArrayList<ButtonCalendar> calendars;

    public Calendars() {
        calendars = new ArrayList<>();
    }
    
    public ArrayList<ButtonCalendar> getCalendars() {
        return calendars;
    }
    
    public void addCalendar(ButtonCalendar calendar){
        calendars.add(calendar);
    }
    
    public void remove(ButtonCalendar posicion){
        calendars.remove(posicion);
    }
    
    public void clearCalendar(){
        calendars.clear();
    }
    
    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < calendars.size(); i++){
            result += "\nid: " + calendars.get(i).getId() + "\n";
            result += "Título: " + calendars.get(i).getTitulo().getText() + "\n";
            result += "posición x: " + calendars.get(i).getPosicionCalendariox() + "\n";
            result += "posocion y: " + calendars.get(i).getPosicionCalendarioy();
        }
        return result;
    }
    
}
