
package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Invitation;


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
            
         
            
            conn.close();
            
        } catch (SQLException e) {
            
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
                
                Invitation invite= 
                        new Invitation(rs.getInt("invitation_id"), rs.getInt("origin_user_id"),
                                rs.getInt("target_user_id"), rs.getInt("target_calendar_id"), rs.getString("reply"), rs.getString("rol"));    
                invitacion.add(invite);
                }
            }
            
            conn.close();
            
            
        } catch (SQLException e) {
            
        }
        return invitacion;
    }
    // TODO
    public void selectCalendarId() {
        String sql = "SELECT * FROM invitation";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
        } catch (SQLException e) {
            
        }
    }
    
    
    public void selectAllByCalendarId(Integer target_calendar_id) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM invitation WHERE target_calendar_id=?");
            ps.setInt(1, target_calendar_id);

            ResultSet rs = ps.executeQuery();
            
          
            
            conn.close();
            
        } catch (SQLException e) {
            
        }
    }
    
    
    public void replyInvitation(int invitation_id, int reply) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE invitation SET reply=? WHERE invitation_id=?");
            ps.setInt(1, reply);
            ps.setInt(2, invitation_id);
    
            
            this.selectReplyByInvitationId(invitation_id);
            
            ps.executeUpdate();
            
            
            this.selectReplyByInvitationId(invitation_id);
            
            
            conn.close();
            
        } catch (SQLException e) {
            
        }
    }
    
    public void insertInvitation(int origin_user_id, int target_user_id) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO invitation(origin_user_id, target_user_id) VALUES(?,?)");
            ps.setInt(1, origin_user_id);
            ps.setInt(2, target_user_id);
            int res = ps.executeUpdate();
            
            conn.close();
            
        } catch (SQLException e) {
        }
    }
    
    
    public void selectInvitationIdByOriginAndTarget(int origin_user_id, int target_user_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM invitation WHERE origin_user_id=? and target_user_id=?");
            ps.setInt(1, origin_user_id);
            ps.setInt(2, target_user_id);
            ResultSet rs = ps.executeQuery();
            
            
        } catch (SQLException e) {
        }

    }        
    
    public void selectInvitationById(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM invitation WHERE invitation_id=?");
                       
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            
        } catch (SQLException e) {
        }

    }    

    public void selectReplyByInvitationId(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT reply FROM invitation WHERE invitation_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            conn.close();
            
        } catch (SQLException e) {
        }

    }
    
    public void deleteInvitationById(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM calendar WHERE invitation_id=?");
            ps.setInt(1, id);
            int res = ps.executeUpdate();
                        
            
            conn.close();
            
        } catch (SQLException e) {
        }

    }    
    
}
