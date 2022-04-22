package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class HerokuCalendarPermitSqlConnection extends SqlConnection {
    
    private static HerokuCalendarPermitSqlConnection instance;
    
    private HerokuCalendarPermitSqlConnection(){}
    
    public static synchronized HerokuCalendarPermitSqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuCalendarPermitSqlConnection();
            instance.getSqlConnection();
        }
        return instance;
    }    
    
    public void selectAllCalendarsPermitsByIdUser(int user_id) {
        String sql = "SELECT DISTINCT * FROM calendar_permit inner JOIN user \n" +
                        "ON calendar_permit.user_id = user.user_id \n" +
                        "where user.user_id =" + Integer.toString(user_id) + ";";
        
        
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(
                    "Id usuario: " +
                    rs.getInt("user_id") + "\t" +
                    "Id calendario: " +
                    rs.getString("calendar_id") + "\t" +
                    "Id tarea: " +
                    rs.getString("task_id") + "\t" +
                    "Rol: " +
                    rs.getString("rol")       
            );
            }
        } catch (SQLException e) {
        }
    }
        /*metodo que se utiliza para la obtencion de una lista que contiene los id de calendarios de un usuario concreto*/
    public ArrayList<Integer> selectAllCalendarsIdByIdUser(int user_id) {
        String sql = "SELECT DISTINCT * FROM calendar_permit inner JOIN user \n" +
                        "ON calendar_permit.user_id = user.user_id \n" +
                        "where user.user_id =" + Integer.toString(user_id) + ";";
        
        ArrayList<Integer> calendario = new ArrayList<>();
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(
                        "Id usuario: " +
                        rs.getInt("user_id") + "\t" +
                        "Id calendario: " +
                        rs.getString("calendar_id")    
                );
                calendario.add(rs.getInt("calendar_id"));
            }
        } catch (SQLException e) {
        }
        return calendario;
    }
    
    public void selectAllCalendarsPermits() {
        String sql = "SELECT * FROM calendar_permit";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(
                    rs.getInt("user_id") + "\t" +
                    rs.getString("calendar_id") + "\t" +
                    rs.getString("task_id") + "\t" +
                    rs.getString("rol")       
            );
            }
        } catch (SQLException e) {
        }
    }
    
    public void insertCalendarPermit(Integer user_id, Integer calendar_id, Integer task_id, String rol) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO calendar_permit(user_id, calendar_id, task_id, rol) VALUES(?,?,?,?)");
            ps.setInt(1, user_id);
            ps.setInt(2, calendar_id);
            ps.setInt(3, task_id);
            ps.setString(4, rol);
            // ps.execute();  
            int res = ps.executeUpdate();
            
            
            
            //conn.close();
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }
    }
    
    public void insertCalendarPermitTaskNull(Integer user_id, Integer calendar_id, String rol) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO calendar_permit(user_id, calendar_id, task_id, rol) VALUES(?,?,?,?)");
            ps.setInt(1, user_id);
            ps.setInt(2, calendar_id);
            ps.setInt(3, 1);
            ps.setString(4, rol);
            // ps.execute();  
            int res = ps.executeUpdate();
            
            
            
            //conn.close();
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla CALENDAR_PERMIT: " + e.getMessage());
            System.out.println("Error al insertar en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }
    }

    
    public void selectTaskByCalendarId(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT calendar_permit.task_id FROM calendar_permit inner JOIN task\n" +
            "ON calendar_permit.calendar_id = task.task_id \n" +
            "where task.task_id = " + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                System.out.println(
                        "Id de calendario: " +
                        Integer.toString(id) + "\t" +
                        "id de tarea: " +
                        rs.getString("task_id") + "\t"
            );
            }   
        } catch (SQLException e) {
        }

    } 

    
    public void selectCalendarByTaskId(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT calendar_permit.calendar_id FROM calendar_permit inner JOIN calendar\n" +
"            ON calendar_permit.task_id = calendar.calendar_id \n" +
"            where calendar.calendar_id =  " + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            
             
        } catch (SQLException e) {
        }

    } 
        
    public void selectUsersPermitsByCalendarId(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT calendar_permit.user_id, calendar_permit.task_id, calendar_permit.rol FROM calendar_permit inner JOIN calendar\n" +
                "ON calendar_permit.calendar_id = calendar.calendar_id \n" +
                "where calendar_permit.calendar_id = " + Integer.toString(id)+";");
            
            ResultSet rs = ps.executeQuery();
            
       
            
        } catch (SQLException e) {
        }

    }    


    
    public void selectCalendarPermitByCalendarId(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM calendar_permit WHERE calendar_id=" + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();

            
        } catch (SQLException e) {
        }

    }            
 
    public void deleteCalendarPermitById(int id) {
        
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM calendar_permit WHERE calendar_id=?");
            ps.setInt(1, id);
            
            int res = ps.executeUpdate();
            


            conn.close();
            
        }catch (SQLException e) {
        }
    }
    
    public void selectCalendarIdByUserId(int u_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT calendar_id FROM calendar_permit WHERE user_id=?");
            ps.setInt(1, u_id);
            ResultSet rs = ps.executeQuery();

            conn.close();
            
        }catch (SQLException e) {
        }
        
    }
}
