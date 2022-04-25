
package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CalendarTask;

public class HerokuCalendarSqlConnection extends SqlConnection {
 
    private static HerokuCalendarSqlConnection instance;
        
    public HerokuCalendarSqlConnection(){}
    
    public static synchronized HerokuCalendarSqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuCalendarSqlConnection();
            instance.getSqlConnection();
        }
        return instance;
    }   
    
    
    public void selectCalendarIdByNameAndSpecialId(String name, String special_id){
        Connection conn = getSqlConnection();

        try{
            PreparedStatement ps = conn.prepareStatement("SELECT calendar_id FROM calendar where name=? and special_id=?;");
            ps.setString(1, name);
            ps.setString(2, special_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            System.out.println(
                    "Id Calendario: " +
                    rs.getString("calendar_id")
            );
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al seleccionar id de calendario por nombre"
                    + " de calendario y por id especial"
                    + " en la tabla CALENDAR: " + e.getMessage());
        }

    }
    
    public boolean selectCalendarIdBySpecialId(String special_id){
        Connection conn = getSqlConnection();

        try{
            PreparedStatement ps = conn.prepareStatement("SELECT calendar_id FROM calendar where special_id=?;");
            ps.setString(1, special_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            System.out.println(
                    "Id Calendario: " +
                    rs.getString("calendar_id")
            );
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error al seleccionar id de calendario por nombre"
                    + " de calendario y nombre de propietario"
                    + " en la tabla CALENDAR: " + e.getMessage());
        }
        return false;
    }
   
    public void selectAllCalendars() {
        String sql = "SELECT * FROM calendar";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(rs.getInt("calendar_id") + "\t" +
                            rs.getString("name") + "\t" +
                            rs.getString("special_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla CALENDAR: " + e.getMessage());
        }
    }

   
    public void insertCalendar(String name, String new_id_email) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO calendar(name, special_id) VALUES(?,?)");
            ps.setString(1, name);
            ps.setString(2, new_id_email);
            // ps.execute();  
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Calendario insertado correctamente");
                System.out.println("Calendario insertado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Calendario insertado incorrectamente");
                System.out.println("Calendario insertado incorrectamente");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla CALENDAR: " + e.getMessage());
            System.out.println("Error al insertar en la tabla CALENDAR: " + e.getMessage());
        }
    }
    
    public CalendarTask selectCalendarById(int calendar_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM calendar WHERE calendar_id=" + Integer.toString(calendar_id));
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(rs.getInt("calendar_id") + "\t" +
                rs.getString("name") + "\t"
                );
                return new CalendarTask(rs.getString("name"), rs.getInt("calendar_id"));
            } 
            
            else {
                //JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese id");
                System.out.println("No existe ningún usuario con ese id");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla CALENDAR: " + e.getMessage());
        }
        return null;

    }            
 
    public void deleteCalendarById(int id) {
        
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM calendar WHERE calendar_id=?");
            ps.setInt(1, id);
            
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                System.out.println("Calendario eliminado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Usuario eliminado incorrectamente");
                System.out.println("Calendario eliminado incorrectamente");
            }
            
            conn.close();
            
        }catch (SQLException e) {
                System.out.println("Error al eliminar por id en la tabla CALENDAR: " + e.getMessage());
            }
    }
    
    /*este metodo sirve para una prueba, no debe ser necesario para el desarrollo correcto del programa*/
    public int selectUltimateCalendar() {
        String sql = "SELECT * FROM CALENDAR";
        int calendario=-1;
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();            
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(rs.getInt("calendar_id") + "\t" +
                        rs.getString("name") + "\t"
            );
            calendario=rs.getInt("calendar_id");
            
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla CALENDAR: " + e.getMessage());
        }
        return calendario;
    }
    /*metodo que devuelve el nombre del calendario pasado por parametro en forma de id*/
     public String getCalendarNameById(int id) {
        Connection conn = getSqlConnection();
        String calendar_name="";
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM calendar WHERE calendar_id=" + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(rs.getInt("calendar_id") + "\t" +
                                            rs.getString("name") + "\t"
                                            );
                calendar_name=rs.getString("name");
            } else {
                //JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese id");
                System.out.println("No existe ningún usuario con ese id");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla CALENDAR: " + e.getMessage());
            
        }
        
        return calendar_name;
    }    
     
     /*metodo que devuelve un calendario creado por cierto usuario, conociendo el usuario y el nombre del calendario*/
     public int getCalendar(String email_id) {
        Connection conn = getSqlConnection();
        int calendar_id=-1;
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM calendar WHERE special_id=?");
            ps.setString(1, email_id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(rs.getInt("calendar_id") + "\t" +
                                            rs.getString("name") + "\t"
                                            );
                calendar_id=rs.getInt("calendar_id");
            } else {
                //JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese id");
                System.out.println("No existe ningún calendario con ese id");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla CALENDAR: " + e.getMessage());
        }
        return calendar_id;
    }    

}
