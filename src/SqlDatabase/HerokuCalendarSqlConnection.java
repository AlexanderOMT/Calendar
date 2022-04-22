
package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class HerokuCalendarSqlConnection extends SqlConnection {
 
    private static HerokuCalendarSqlConnection instance;
        
    private HerokuCalendarSqlConnection(){}
    
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


            conn.close();
            
        } catch (SQLException e) {

        }
        
    }
    
    public void selectAllCalendars() {
        String sql = "SELECT * FROM calendar";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
        } catch (SQLException e) {
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
            
            conn.close();
            
        } catch (SQLException e) {
        }
    }
    
    public void selectCalendarById(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM calendar WHERE calendar_id=" + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            
            
        } catch (SQLException e) {
        }

    }            
 
    public void deleteCalendarById(int id) {
        
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM calendar WHERE calendar_id=?");
            ps.setInt(1, id);
            
            int res = ps.executeUpdate();
            
            conn.close();
            
        }catch (SQLException e) {
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
                calendario=rs.getInt("calendar_id");
            
            }
        } catch (SQLException e) {
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
                calendar_name=rs.getString("name");
            } else {
            }
            
        } catch (SQLException e) {
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
                calendar_id=rs.getInt("calendar_id");
            } else {
            }
            
        } catch (SQLException e) {
        }
        return calendar_id;
    }    

}
