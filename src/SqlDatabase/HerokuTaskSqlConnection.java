package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HerokuTaskSqlConnection  extends SqlConnection {
    
    private static HerokuTaskSqlConnection instance;
    
    private HerokuTaskSqlConnection(){}
    
    public static synchronized HerokuTaskSqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuTaskSqlConnection();
            instance.getSqlConnection();
        }
        return instance;
    }
    
    public void selectAllTasks() {
        String sql = "SELECT * FROM task";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
        } catch (SQLException e) {
        }
    }
    
    public void insertTask(String name) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO task(name) VALUES(?)");
            ps.setString(1, name);
            int res = ps.executeUpdate();
            
            conn.close();
            
        } catch (SQLException e) {
        }
    }
    
    public void selectTaskById(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM task WHERE task_id=" + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            
        } catch (SQLException e) {
        }

    }            
 
    public void deleteTaskById(int id) {
        
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM task WHERE task_id=?");
            ps.setInt(1, id);
            
            int res = ps.executeUpdate();
            
            conn.close();
            
        }catch (SQLException e) {
            }
    }
}
