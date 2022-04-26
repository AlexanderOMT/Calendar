
package SqlDatabase;

import calendar.Calendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.CalendarTask;
import model.Invitation;
import model.Task;


public class HerokuInvitationSqlConnection extends SqlConnection { 
    
    private static HerokuInvitationSqlConnection instance;
        
    public HerokuInvitationSqlConnection(){}
    
    public static synchronized HerokuInvitationSqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuInvitationSqlConnection();
            instance.getSqlConnection();
        }
        return instance;
    }   
    
    public void selectAllInvitations() {
        String sql = "SELECT * FROM invitation";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(
                        "Id invitación: " +    rs.getInt("invitation_id") + "\t" +
                        "Id usuario origen: " +    rs.getString("origin_user_id") + "\t" +
                        "Id usuario destino: " +    rs.getString("target_user_id") + "\t" +
                        "Id calendario: " +    rs.getString("target_calendar_id") + "\t" +        
                        "Respuesta: " +  rs.getString("reply")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla CALENDAR: " + e.getMessage());
        }
    }
    
    public void selectAllInvitationIdByUserId(Integer user_id) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM invitation WHERE ("
                    + "origin_user_id=? OR target_user_id=?"
                    + ")");
            ps.setInt(1, user_id);
            ps.setInt(2, user_id);

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                System.out.println(
                        "Id invitación: " +    rs.getInt("invitation_id") + "\t" +
                        "Id usuario origen: " +    rs.getString("origin_user_id") + "\t" +
                        "Id usuario destino: " +    rs.getString("target_user_id") + "\t" +
                        "Id calendario: " +    rs.getString("target_calendar_id") + "\t" +        
                        "Respuesta: " +  rs.getString("reply")
                );
            }
                        
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla CALENDAR: " + e.getMessage());
            System.out.println("Error al responder en la tabla INVITATION: " + e.getMessage());
        }
    }
    
    public ArrayList<Invitation> getAllInvitationIdByUserId(Integer user_id) {
        Connection conn = getSqlConnection();
        ArrayList<Invitation> invitacion=new ArrayList<Invitation>();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM invitation WHERE ("
                    + "target_user_id=?"
                    + ")");
            ps.setInt(1, user_id);

            ResultSet rs = ps.executeQuery();
             
            while (rs.next()) {
                if(rs.getString("reply")== null ){
                System.out.println(
                        "Id invitación: " +    rs.getInt("invitation_id") + "\t" +
                        "Id usuario origen: " +    rs.getString("origin_user_id") + "\t" +
                        "Id usuario destino: " +    rs.getString("target_user_id") + "\t" +
                        "Id calendario: " +    rs.getString("target_calendar_id") + "\t" +  
                        "Rol: " +    rs.getString("rol") + "\t" +   
                        "Respuesta: " +  rs.getString("reply")
                );
                Invitation invite= 
                        new Invitation(rs.getInt("invitation_id"), rs.getInt("origin_user_id"),
                                rs.getInt("target_user_id"), 
                                rs.getInt("target_calendar_id"), rs.getString("reply"), rs.getString("rol"));    
                invitacion.add(invite);
                }
            }
                        
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla CALENDAR: " + e.getMessage());
            System.out.println("Error al responder en la tabla INVITATION: " + e.getMessage());
        }
        return invitacion;
    }
    // TODO
    public void selectCalendarId() {
        String sql = "SELECT * FROM invitation";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(
                        "Id invitación: " +    rs.getInt("invitation_id") + "\t" +
                        "Id usuario origen: " +    rs.getString("origin_user_id") + "\t" +
                        "Id usuario destino: " +    rs.getString("target_user_id") + "\t" +
                        "Id calendario: " +    rs.getString("target_calendar_id") + "\t" +  
                        "Rol: " +    rs.getString("rol") + "\t" +   
                        "Respuesta: " +  rs.getString("reply")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla CALENDAR: " + e.getMessage());
        }
    }
    
    
    public void selectAllByCalendarId(Integer target_calendar_id) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM invitation WHERE target_calendar_id=?");
            ps.setInt(1, target_calendar_id);

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                System.out.println(
                        "Id invitación: " +    rs.getInt("invitation_id") + "\t" +
                        "Id usuario origen: " +    rs.getString("origin_user_id") + "\t" +
                        "Id usuario destino: " +    rs.getString("target_user_id") + "\t" +
                        "Id calendario: " +    rs.getString("target_calendar_id") + "\t" +        
                        "Respuesta: " +  rs.getString("reply")
                );
            }
                        
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla CALENDAR: " + e.getMessage());
            System.out.println("Error al responder en la tabla INVITATION: " + e.getMessage());
        }
    }
    
    
    public void replyInvitation(int invitation_id, int reply) {
        Connection conn = getSqlConnection(); 

        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE invitation SET reply=? WHERE invitation_id=?");
            ps.setInt(1, reply);
            ps.setInt(2, invitation_id);
    
            System.out.println(" -> Respuesta antes de responder: ");
            this.selectReplyByInvitationId(invitation_id);
            
            ps.executeUpdate();
            
            System.out.println(" -> Respuesta Después de responder: ");
            this.selectReplyByInvitationId(invitation_id);
            
            
            System.out.println(" Nota: Invitación eliminada de la BD");
            this.deleteInvitationById(invitation_id);
            
            if(reply == 1){
                HerokuCalendarPermitSqlConnection calendarPermit = HerokuCalendarPermitSqlConnection.getInstance();
                HerokuCalendarSqlConnection calendar = HerokuCalendarSqlConnection.getInstance();

                Invitation inv = this.selectInvitationById(invitation_id);
                CalendarTask cal = calendar.selectCalendarById(inv.getCalendar_id());

                // TODO recuperar task de BD
                
                List<Task> allTasks =  cal.getAllTasks();
                        
                allTasks.forEach((task) -> {
                    calendarPermit.insertCalendarPermit(inv.getOrigin_user(),
                            inv.getCalendar_id(),
                            task.getId(),
                            inv.getRol());
                });
      
            }
            
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla CALENDAR: " + e.getMessage());
            System.out.println("Error al responder en la tabla INVITATION: " + e.getMessage());
        }
    }
    
    public void insertInvitation(int origin_user_id, int target_user_id, int target_calendar_id, String rol) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO invitation(origin_user_id,"
                    + "target_user_id, target_calendar_id, rol) VALUES(?,?,?,?)"
            );
            ps.setInt(1, origin_user_id);
            ps.setInt(2, target_user_id);
            ps.setInt(3, target_calendar_id);
            ps.setString(4, rol);
            // ps.execute();  
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Calendario insertado correctamente");
                System.out.println("Invitación insertado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Calendario insertado incorrectamente");
                System.out.println("Invitación insertado incorrectamente");
            }
                        
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla CALENDAR: " + e.getMessage());
            System.out.println("Error al insertar en la tabla INVITATION: " + e.getMessage());
        }
    }
    
    
    public void selectInvitationIdByOriginAndTarget(int origin_user_id, int target_user_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM invitation WHERE origin_user_id=? and target_user_id=?");
            ps.setInt(1, origin_user_id);
            ps.setInt(2, target_user_id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                System.out.println(
                        "Id de la invitación: " + rs.getInt("invitation_id") + "\t" +
                        "Id usuario origen: " + rs.getInt("origin_user_id") + "\t" +
                        "Id usuario destino: " + rs.getInt("target_user_id") + "\t" +
                        "Id calendario: " +    rs.getString("target_calendar_id") + "\t" + 
                        "Respuesta: " + rs.getString("reply") 
                );
            }
            
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar id por usuario id origen y objetivo en la tabla INVITATION: " + e.getMessage());
        }

    }        
    
    public Invitation selectInvitationById(int invitation_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM invitation WHERE invitation_id=?");
                       
            ps.setInt(1, invitation_id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(
                        "Id de la invitación: " + rs.getInt("invitation_id") + "\t" +
                        "Id usuario origen: " + rs.getInt("origin_user_id") + "\t" +
                        "Id usuario destino: " + rs.getInt("target_user_id") + "\t" +
                        "Id calendario: " +    rs.getString("target_calendar_id") + "\t" + 
                        "Rol: " + rs.getString("rol") + "\t" + 
                        "Respuesta: " + rs.getString("reply") 
                );
                return new Invitation(rs.getInt("invitation_id"), 
                        rs.getInt("origin_user_id"), 
                        rs.getInt("target_user_id"), rs.getInt("target_calendar_id"),  
                        rs.getString("reply"), rs.getString("rol"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id en la tabla INVITATION: " + e.getMessage());
        }
        return null;

    }    

    public void selectReplyByInvitationId(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT reply FROM invitation WHERE invitation_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                System.out.println(
                        "Respuesta: " + rs.getString("reply") 
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar Respuesta por id de invitación en la tabla INVITATION: " + e.getMessage());
        }

    }
    
    private void deleteInvitationById(int invitation_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM invitation WHERE invitation_id=?");
            ps.setInt(1, invitation_id);
            int res = ps.executeUpdate();
                        
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                System.out.println("Invitación eliminado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Usuario eliminado incorrectamente");
                System.out.println("Invitación eliminado incorrectamente");
            }
                        
        } catch (SQLException e) {
            System.out.println("Error al eliminar por id en la tabla INVITATION: " + e.getMessage() + "(Valor del id: "+ invitation_id +" )");
        }

    }    
    
}
