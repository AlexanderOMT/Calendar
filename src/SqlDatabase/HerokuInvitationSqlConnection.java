
package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HerokuInvitationSqlConnection extends SqlConnection { 
    
    private static HerokuInvitationSqlConnection instance;
        
    private HerokuInvitationSqlConnection(){}
    
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
        } catch (SQLException e) {
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
