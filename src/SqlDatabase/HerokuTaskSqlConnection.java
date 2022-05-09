package SqlDatabase;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
// import model.Tags;
import model.Task;

public class HerokuTaskSqlConnection  extends SqlConnection {
    
    private static HerokuTaskSqlConnection instance;
    PreparedStatement ps;
    ResultSet rs;
    
    public HerokuTaskSqlConnection(){}
    
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
    
    public ArrayList<String> selectSpecialIdTasks(String name) {
         Connection conn = getSqlConnection();
         ArrayList<String> specialId = new ArrayList<>();
        try{
            ps = conn.prepareStatement("SELECT special_id FROM task WHERE name=?");
            ps.setString(1, name);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {

                //conn.close();
                specialId.add(rs.getString("special_id"));
            } 
            
        } catch (SQLException e) {
        }
        //conn.close();
        return specialId;
        
        
    }
    
    public int selectIdBySpecialIdTasks(String special_Id) {
        
        
         int task_id = 0;
        try{
            Connection conn = getSqlConnection();
            System.out.println("Entra en el try de permit");
            ps = conn.prepareStatement("SELECT DISTINCT task_id FROM task WHERE special_id=?");
            ps.setString(1, special_Id);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {

                //conn.close();
                task_id = rs.getInt("task_id");
                conn.close();
            } 
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //conn.close();
        return task_id;
        
        
         
        
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
    
    
    public int insertTaskByTask(Task task) {
        Connection conn = getSqlConnection();  
        int result=-1;
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO task(name, date, tag, priority, description) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, task.getName());
            ps.setTimestamp(2, (Timestamp) task.getDate()); 
            ps.setString(3, task.getTag()); 
            ps.setInt(4, task.getPrior());
            ps.setString(5, task.getDesc());
            
            // ps.execute();  
            ps.executeUpdate();
            
            ps = conn.prepareStatement("SELECT DISTINCT last_insert_id() AS last_id FROM task;");
            
            ResultSet res = ps.executeQuery();
            
            
            if(res.next()) result = res.getInt("last_id");
            
            conn.close();
            return result;
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla TASK: " + e.getMessage());
            System.out.println("Error al insertar en la tabla TASK: " + e.getMessage());
        }
        return result;
    }
    
    public void selectTaskById(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM task WHERE task_id=" + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            conn.close();
            
        } catch (SQLException e) {
        }

    }     
    
    public Task getTaskById(int id) {
        Connection conn = getSqlConnection();
        Task task = new Task();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM task WHERE task_id=" + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                task.setId(rs.getInt("task_id"));
                task.setName(rs.getString("name"));
                task.setDate(rs.getTimestamp("date"));
                task.setTag(rs.getString("tag"));
                task.setPrior(rs.getInt("priority"));
                task.setDesc(rs.getString("description"));
            }
            conn.close();
            
        } catch (SQLException e) {
        }
        return task;

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
