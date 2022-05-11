package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
Nombre de los roles de los usuarios en la bd:
-administrador : "Admin"
-editor : "Editor"
-lector : "Lector" 
*/
public class HerokuCalendarPermitSqlConnection extends SqlConnection {
    
    private static HerokuCalendarPermitSqlConnection instance;
    
    public HerokuCalendarPermitSqlConnection(){}
    
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
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo por id de usuatio en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }
    }
    
    public int selectIdCalendarsPermitsByIdUserAndCalendarName(int user_id, String calendarName) {
        
        String sql ="SELECT DISTINCT calendar_permit.calendar_id FROM calendar_permit inner JOIN calendar \n " +
                " ON calendar_permit.calendar_id = calendar.calendar_id \n " + 
                " WHERE calendar.name =" + calendarName + " AND calendar_permit.user_id =" + Integer.toString(user_id) + ";" ; 
        int id = 0;
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            System.out.println("Entra en el try y no en el while");
            
            while (rs.next()) {
                System.out.println(
                        "Id USUARIO: " +
                        rs.getInt("user_id") + "\t" +
                        "Id CALENDARIO: " +
                        rs.getString("calendar_id")    
                );
                id = rs.getInt("calendar_id");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("ERROR : : : Error al seleccionar todo por id de usuatio en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }
        return id;
    }
    public int getCalendarIdBySpecialId(String special_id){
        Connection conn = getSqlConnection();

        try{
            PreparedStatement ps = conn.prepareStatement("SELECT calendar_id FROM calendar where special_id=?;");
            ps.setString(1, special_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(
                        "ID DEL EL CALENDARIOOO " +
                        rs.getString("calendar_id")
                );
                int id=rs.getInt("calendar_id");
                conn.close();
            return id;
            }

        } catch (SQLException e) {
            System.out.println("Error al seleccionar id de calendario por nombre"
                    + " de calendario y nombre de propietario"
                    + " en la tabla CALENDAR: " + e.getMessage());
        }
        return -1;
    }
    
        /*metodo que se utiliza para la obtencion de una lista que contiene los id de calendarios de un usuario concreto*/
    public ArrayList<Integer> selectAllCalendarsIdByIdUser(int user_id) {
        ArrayList<Integer> calendario = new ArrayList<>();
        try{
            Connection conn = getSqlConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT calendar_id FROM calendar_permit inner JOIN user \n" +
                        "ON calendar_permit.user_id = user.user_id \n" +
                        "where user.user_id =" + Integer.toString(user_id) + ";");
            
            ResultSet rs = ps.executeQuery();
            
             while (rs.next()) {
                System.out.println(
                        "Id calendario: " +
                        rs.getString("calendar_id")    
                );
                calendario.add(rs.getInt("calendar_id"));
            }
             conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla CALENDAR: " + e.getMessage());
        }
        return calendario;
    }
    
    public boolean selectAllCalendarsIdByUserEmail(String user_email, int cal_id) {
        ArrayList<Integer> calendario = new ArrayList<>();
        try{
            Connection conn = getSqlConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT calendar_id FROM calendar_permit inner JOIN user \n" +
                        "ON calendar_permit.user_id = user.user_id \n" +
                        "where user.email =" + user_email + ";");
            
            ResultSet rs = ps.executeQuery();
            
             while (rs.next()) {
                System.out.println(
                        "Id calendario: " +
                        rs.getString("calendar_id")    
                );
                calendario.add(rs.getInt("calendar_id"));
            }
             conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla CALENDAR: " + e.getMessage());
        }
        
        for (int x=0; x<calendario.size(); x++){
            if (calendario.get(x) == cal_id){
                return true;
            }
        }
        return false;
    }
    
        
    public ArrayList<Integer> selectAllTaskIdByIdCalendar(int calendar_id) {
        Connection conn = getSqlConnection();
        ArrayList<Integer> tareas = new ArrayList<>();
        

        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT task_id FROM calendar_permit WHERE calendar_id = ?;");
            ps.setInt(1, calendar_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                /*System.out.println(
                        "ID DEL EL TAREAAA " +
                        rs.getString("task_id")
                );*/
                tareas.add(rs.getInt("task_id"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL SELECCIONAR LAS TAREAS DE UN CALENDARIO EN CONCRETO" + e.getMessage());
        }
        return tareas;
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
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla CALENDAR_PERMIT: " + e.getMessage());
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

            conn.close();
            
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
    }
    
    public void insertCalendarPermitTaskNull(Integer user_id, Integer calendar_id, String rol) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO calendar_permit(user_id, calendar_id, task_id, rol) VALUES(?,?,?,?)");
            ps.setInt(1, user_id);
            ps.setInt(2, calendar_id);
            ps.setInt(3, 2);
            ps.setString(4, rol);
            // ps.execute();  
            int res = ps.executeUpdate();
            
           
            
            conn.close();
            
        } catch (SQLException e) {
            System.out.println( "NO SE HA CREADO LA CONEXION ENTRE USER Y EL NEW CALENDAR POR: "+e.getMessage());
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
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar tarea(s) por id del calendario en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }

    } 

    
    public void selectCalendarByTaskId(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT calendar_permit.calendar_id FROM calendar_permit inner JOIN calendar\n" +
"            ON calendar_permit.task_id = calendar.calendar_id \n" +
"            where calendar.calendar_id =  " + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                System.out.println(
                        "Id de tarea: " +
                        Integer.toString(id) + "\t" +
                        "id de calendario: " +
                        rs.getString("calendar_id") + "\t"
            );
            }   
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar calendario(s) por id de la tarea en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }

    } 
        
    // return: lista de ids de usuarios que pertenece al calendario
    public Map<Integer, String> selectUsersPermitsByCalendarId(int id) {
        Connection conn = getSqlConnection();
        Map<Integer, String> user_id_rol = new HashMap<>();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT calendar_permit.user_id, calendar_permit.task_id, calendar_permit.rol FROM calendar_permit inner JOIN calendar\n" +
                "ON calendar_permit.calendar_id = calendar.calendar_id \n" +
                "where calendar_permit.calendar_id = " + Integer.toString(id)+";");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                System.out.println(
                        "Id de calendario: " + 
                        Integer.toString(id) + "\t" +        
                        "Id de usuario: " +
                        rs.getInt("user_id") + "\t" +
                        "Id de tarea: " +
                        rs.getInt("task_id") + "\t" +
                        "Rol: " +
                        rs.getString("rol") + "\t"
                );
                user_id_rol.put(rs.getInt("user_id"), rs.getString("rol"));
            }
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar permisos de usuario por id del calendario en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }
        return user_id_rol;
    }    


    
    public void selectCalendarPermitByCalendarId(int id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM calendar_permit WHERE calendar_id=" + Integer.toString(id));
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                System.out.println(
                        "Id de usuario: " +
                        rs.getInt("user_id") + "\t" +
                        "Id de calendario: " +
                        rs.getInt("calendar_id") + "\t" +
                        "Rol: " +
                        rs.getString("rol") + "\t"
            );
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar permiso del calendario por id del calendario en la tabla CALENDAR_PERMIT: " + e.getMessage());
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
                System.out.println("Error al eliminar por id en la tabla CALENDAR_PERMIT: " + e.getMessage());
            }
    }
    public void deleteOnlyCalendarPermitfromOneUser( int id_user, int id_cal) {
        
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM calendar_permit WHERE calendar_id=? AND user_id=?");
            ps.setInt(1, id_cal);
            ps.setInt(2, id_user);
            int res = ps.executeUpdate();
            conn.close();
            
        }catch (SQLException e) {
                System.out.println("Error al eliminar por id en la tabla CALENDAR_PERMIT: " + e.getMessage());
            }
    }
    
    public void selectCalendarIdByUserId(int u_id) {
        Connection conn = getSqlConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT calendar_id FROM calendar_permit WHERE user_id=?");
            ps.setInt(1, u_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 System.out.println(
                        "Id de usuario: " +
                        rs.getInt("user_id") + "\t" +
                        "Id de calendario: " +
                        rs.getInt("calendar_id"));
            }

            conn.close();
            
        }catch (SQLException e) {
                System.out.println("Error al seleccionar por id en la tabla CALENDAR_PERMIT: " + e.getMessage());
            }
        
    }
    public boolean selectUserfromSameCalendar(int cal_id) {
        Connection conn = getSqlConnection();
        //System.out.println("CAL ID:" +cal_id);
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT user_id FROM calendar_permit WHERE calendar_id=?");
            ps.setInt(1, cal_id);
            
            ResultSet rs = ps.executeQuery();
            int cont=0;
            while(rs.next()){
                 /*System.out.println(
                        "Id de usuario: " +
                        rs.getInt("user_id") + "\t" +
                        "Id de calendario: " +
                        rs.getInt("calendar_id"));*/
                 cont++;
            }
            conn.close();
            if(cont==1){
                return false;
            }
            
            
        }catch (SQLException e) {
                System.out.println("Errorakñlsjdñkjñ en la tabla CALENDAR_PERMIT: " + e.getMessage());
            }
        return true;
    }
    
    public void changeRol(int user_id,int calendar_id, String rol) {
        Connection conn = getSqlConnection();        
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE calendar_permit SET rol=? WHERE user_id=? AND calendar_id=?");
            ps.setString(1, rol);
            ps.setInt(2, user_id);
            ps.setInt(3, calendar_id);
            ps.executeUpdate();
            conn.close();
            System.out.println("se ha cambiado correctamente el rol");
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla INVITATION: " + e.getMessage());
        }
    }
    
    public String selectRolfromUser(int us_id, int cal_id) {
        Connection conn = getSqlConnection();
        String rol=new String();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT rol FROM calendar_permit WHERE user_id=? AND calendar_id=?");
            ps.setInt(1, us_id);
            ps.setInt(2, cal_id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                  rol=rs.getString("rol");
            }
            conn.close();
            return rol;            
        }catch (SQLException e) {
                System.out.println("Error al seleccionar el rol en la tabla CALENDAR_PERMIT: " + e.getMessage());
        }
        return rol;
    }
}
