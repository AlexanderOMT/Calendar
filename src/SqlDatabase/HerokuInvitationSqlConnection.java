
package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.Invitation;
import model.User;


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
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
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
             System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
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
             System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
        }
        return invitacion;
    }
    // TODO
    public void selectCalendarId() {
        String sql = "SELECT * FROM invitation";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
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
            System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
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
            
    System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
        }
    }
    
    public void changeRol(int target_calendar_id,int origin_user_id,int target_user_id, String rol) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE invitation SET rol=? WHERE origin_user_id=? AND target_user_id=? AND target_calendar_id=?");
            ps.setString(1, rol);
            ps.setInt(2, origin_user_id);
            ps.setInt(3, target_user_id);
            ps.setInt(4, target_calendar_id);
            ps.executeUpdate();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
        }
    }
   public void changeRol2(int target_calendar_id,int target_user_id, String rol) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE invitation SET rol=? WHERE origin_user_id=? AND target_calendar_id=?");
            ps.setString(1, rol);
            ps.setInt(2, target_user_id);
            ps.setInt(3, target_calendar_id);
            ps.executeUpdate();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
        }
    }
    
    public void insertInvitation(int origin_user_id, int target_user_id, int calendar_id, String rol) throws SQLException {
        Connection conn = getSqlConnection();        
        
            PreparedStatement ps = conn.prepareStatement("INSERT INTO invitation(origin_user_id, target_user_id, target_calendar_id, reply ,rol) VALUES(?,?,?,?,?)");
            ps.setInt(1, origin_user_id);
            ps.setInt(2, target_user_id);
            ps.setInt(3, calendar_id);
            ps.setNull(4, java.sql.Types.TINYINT);
            ps.setString(5, rol);
            int res = ps.executeUpdate();
            
            conn.close();
            
        
    }
    public void insertInvitationAccepted(int origin_user_id, int target_user_id, int calendar_id, String rol) {
        try {
            Connection conn = getSqlConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO invitation(origin_user_id, target_user_id, target_calendar_id, reply ,rol) VALUES(?,?,?,?,?)");
            ps.setInt(1, origin_user_id);
            ps.setInt(2, target_user_id);
            ps.setInt(3, calendar_id);
            ps.setInt(4, 1);
            ps.setString(5, rol);
            int res = ps.executeUpdate();
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HerokuInvitationSqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    
    public void selectInvitationIdByOriginAndTarget(int origin_user_id, int target_user_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM invitation WHERE origin_user_id=? and target_user_id=?");
            ps.setInt(1, origin_user_id);
            ps.setInt(2, target_user_id);
            ResultSet rs = ps.executeQuery();
            conn.close();
            
        } catch (SQLException e) {
             System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
        }

    }        
    
    public void selectInvitationById(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM invitation WHERE invitation_id=?");
                       
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM invitation WHERE invitation_id=?");
            ps.setInt(1, id);
            int res = ps.executeUpdate();
                        
            
            conn.close();
            
        } catch (SQLException e) {
        }

    }    
    
    public void deleteInvitationByTargetUserIdAndCalendarId(int target_us_id, int cal_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM invitation WHERE target_user_id=? AND target_calendar_id=?");
            ps.setInt(1, target_us_id);
            ps.setInt(2, cal_id);
            int res = ps.executeUpdate();
                        
            
            conn.close();
            
        } catch (SQLException e) {
        }

    }  
    
    public ArrayList<Invitation> getInvitationsAccepted(Integer target_calendar_id) {
        Connection conn = getSqlConnection();     
        ArrayList<Invitation> invitations= new ArrayList<Invitation>();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM invitation WHERE target_calendar_id=? AND reply=1");
            ps.setInt(1, target_calendar_id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("adsfasdfa");
                Invitation invite= 
                            new Invitation(rs.getInt("invitation_id"), rs.getInt("origin_user_id"),
                                    rs.getInt("target_user_id"), rs.getInt("target_calendar_id"), rs.getString("reply"), rs.getString("rol"));    
                    invitations.add(invite);
            }
            conn.close();
            return invitations;
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
        }
        return invitations;
    }
    
    
     public Admin getAdminfromCalendar(int cal_id) throws SQLException {
        
        Admin u= new Admin();
        Connection conn = getSqlConnection();     
        ArrayList<Invitation> invitations= new ArrayList<Invitation>();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT * FROM calendar_permit WHERE calendar_id =? AND rol='Admin'");
            ps.setInt(1, cal_id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            System.out.println(
                    "Id usuario: " +
                    rs.getInt("user_id") + "\t" +
                    "Id calendario: " +
                    rs.getString("calendar_id") + "\t" +
                    "Rol: " +
                    rs.getString("rol")       
            );
                 u= new Admin( rs.getInt("user_id"),rs.getInt("calendar_id"), rs.getString("rol") );
                 conn.close();
                return u;
            
            }
        }catch (SQLException e) {
            System.out.println("Error al seleccionar el admin del grupo: " + e.getMessage());
        }
        return u;
        
            }
}
