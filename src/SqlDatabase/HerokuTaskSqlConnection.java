package SqlDatabase;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
// import model.Tags;
import model.Task;

public class HerokuTaskSqlConnection  extends SqlConnection {
    
    private static HerokuTaskSqlConnection instance;
    
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
            while (rs.next()) {
            System.out.println(rs.getInt("task_id") + "\t" +
                        rs.getString("name") + "\t"
            );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla TASK: " + e.getMessage());
        }
    }
    
    public void insertTask(String name, Timestamp date, String tag, int priority, String description) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO task(name, date, tag, priority, description) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setTimestamp(2, date); 
            ps.setString(3, tag); 
            ps.setInt(4, priority);
            ps.setString(5, description);            
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Tarea insertado correctamente");
                System.out.println("Tarea insertado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Tarea insertado incorrectamente");
                System.out.println("Tarea insertado incorrectamente");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla TASK: " + e.getMessage());
            System.out.println("Error al insertar en la tabla TASK: " + e.getMessage());
        }
    }
    
    public void insertTaskByTask(Task task) {
        Connection conn = getSqlConnection();  
        // System.out.println("Entra en insertar BD");
        // Object[] objArray = task.getTag().toArray();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO task(name, date, tag, priority, description) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, task.getName());
            ps.setTimestamp(2, (Timestamp) task.getDate()); 
            ps.setString(3, task.getTag()); 
            ps.setInt(4, task.getPrior());
            ps.setString(5, task.getDesc());
            
            // ps.execute();  
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Tarea insertado correctamente");
                System.out.println("Tarea insertado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Tarea insertado incorrectamente");
                System.out.println("Tarea insertado incorrectamente");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al insertar en la tabla TASK: " + e.getMessage());
            System.out.println("Error al insertar en la tabla TASK: " + e.getMessage());
        }
    }
    
    public Task selectTaskById(int task_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM task WHERE task_id=" + Integer.toString(task_id));
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(rs.getInt("task_id") + "\t" +
                rs.getString("name") + "\t"
                );
                return new Task(
                        rs.getInt("task_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("date"),
                        rs.getInt("priority"),
                        rs.getString("tag")
                );
            } else {
                //JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese id");
                System.out.println("No existe ningún usuario con ese id");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla TASK: " + e.getMessage());
        }
        return null;

    }   
    
 
    public void deleteTaskById(int id) {
        
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM task WHERE task_id=?");
            ps.setInt(1, id);
            
            int res = ps.executeUpdate();
            
            if(res > 0){
                //JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                System.out.println("Tarea eliminado correctamente");
            }else{
                //JOptionPane.showMessageDialog(null, "Usuario eliminado incorrectamente");
                System.out.println("Tarea eliminado incorrectamente");
            }

            conn.close();
            
        }catch (SQLException e) {
                System.out.println("Error al eliminar por id en la tabla TASK: " + e.getMessage());
            }
    }
}
