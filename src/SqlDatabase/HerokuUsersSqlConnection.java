package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.User;

public class HerokuUsersSqlConnection extends SqlConnection {
        
    private static HerokuUsersSqlConnection instance;
    private String emailUser;
    
    private User user;
    
    PreparedStatement ps;
    ResultSet rs;
    
    private HerokuUsersSqlConnection(){}
    
    public static synchronized HerokuUsersSqlConnection getInstance(){
        if(instance == null){
            instance = new HerokuUsersSqlConnection();
            instance.getSqlConnection();
        }
        return instance;
    }

    public void selectAllUsers() {
        String sql = "SELECT * FROM user";
        try (Connection conn = this.getSqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
            System.out.println(rs.getInt("user_id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("password") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("login") 
            );
            }
        } catch (SQLException e) {
            System.out.println("Error al seleccionar todo en la tabla user: " + e.getMessage());
        }
    }

    public void selectUserById(int id) {
        Connection conn = getSqlConnection();
        
        try{
            ps = conn.prepareStatement("SELECT * FROM user WHERE user_id=" + Integer.toString(id));
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(rs.getInt("user_id") + "\t" +
                rs.getString("name") + "\t" +
                rs.getString("password") + "\t" +
                rs.getString("email") + "\t" +
                rs.getString("login") 
            );
            } else {
                JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese id");
                System.out.println("No existe ningún usuario con ese id");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla user: " + e.getMessage());
        }
    }    
    
    public int getUserIdByEmail(String email) throws SQLException {
        Connection conn = getSqlConnection();
        System.out.println("Usuario:" );
        try{
            ps = conn.prepareStatement("SELECT * FROM user WHERE email=?");
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                System.out.println("Usuario:" + rs.getInt("user_id"));
                return rs.getInt("user_id");
            } else {
                System.out.println("No existe ningún usuario con ese email");
                return -1;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla user: " + e.getMessage());
        }
        return -1;
    }   
    /*este metodo en realidad no es un select, es una comprobación*/
    public boolean selectUserByEmail(String email) throws SQLException {
        Connection conn = getSqlConnection();
        
        try{
            ps = conn.prepareStatement("SELECT * FROM user WHERE email=?");
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Si existe ningún usuario con ese id");
                System.out.println("Si existe usuario con ese id");
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "No existe ningún usuario con ese id");
                System.out.println("No existe ningún usuario con ese id");
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al seleccionar por id  en la tabla user: " + e.getMessage());
        }
        return false;
    }
    
    public void deleteUserById(int id) {
        
        Connection conn = getSqlConnection();
        
        try{
            ps = conn.prepareStatement("DELETE FROM user WHERE user_id=?");
            ps.setInt(1, id);
            
            int res = ps.executeUpdate();
            
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                System.out.println("Usuario eliminado correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Usuario eliminado incorrectamente");
                System.out.println("Usuario eliminado incorrectamente");
            }
            
            conn.close();
            
        }catch (SQLException e) {
                System.out.println("Error al eliminar por id en la tabla user: " + e.getMessage());
            }
    }
     /*
    public JSONObject getCalendarById(int id) throws ParseException {
        String sql = "SELECT calendar FROM USERS WHERE id=" + Integer.toString(id);
        JSONObject jsonObjectCalendar = null;
        String stringCalendar = null;
        try (Connection conn = this.getConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if(rs.next()){stringCalendar = rs.getString("calendar");}

            jsonObjectCalendar = (JSONObject) new JSONParser().parse(stringCalendar);
                        
        } catch (SQLException e) {
                System.out.println("Error al obtener calendario por id en la tabla USERS: " + e.getMessage());
            }
        
        return jsonObjectCalendar;
    }

*/
    
    public void insertUser(String name, String pswd, String email, boolean login) {
        Connection conn = getSqlConnection();        
        try{
            ps = conn.prepareStatement("INSERT INTO user(name, password, email, login) VALUES(?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, pswd);
            ps.setString(3, email);
            ps.setBoolean(4, login);
            // ps.execute();  
            int res = ps.executeUpdate();
            /*
            Sirve para guardar el email como variable local para identificar el usuario en toda la sesión
            */
            emailUser = email;
            
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Usuario insertado correctamente");
                System.out.println("Usuario insertado correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Usuario insertado incorrectamente");
                System.out.println("Usuario insertado incorrectamente");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar en la tabla user: " + e.getMessage());
            System.out.println("Error al insertar en la tabla user: " + e.getMessage());
        }
    }
    
    public boolean login(User user) throws SQLException {
        
        Connection conn = getSqlConnection();
        try{
            ps = conn.prepareStatement("SELECT user_id, name, email, password, login FROM user WHERE email=?");
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();
            if (rs.next()) {
                if(user.getPwd().equals(rs.getString(4))){                     
                    if (!rs.getBoolean(5)) {
                        ps = conn.prepareStatement("UPDATE user SET login=? WHERE email=?");
                        ps.setBoolean(1, true);
                        ps.setString(2, user.getEmail());
                        ps.execute();
                        
                        user.setId(rs.getInt(1));
                        user.setName(rs.getString(2));
                        user.setEmail(rs.getString(3));
                        user.setPwd(rs.getString(4));
                        user.setLogin(rs.getBoolean(5));
                                                
                        emailUser = user.getEmail();
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "Ya hay un usuario logeado con esta cuenta");
                        System.out.println("Ya hay un usuario logeado con esta cuenta"); 
                    }
                }
            }
        }catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla user: " + e.getMessage());
        }
        return false;
    }

    public boolean signOut() throws SQLException {
        Connection conn = getSqlConnection();
        try{
            ps = conn.prepareStatement("SELECT user_id, name, email, password, login FROM user WHERE email=?");
            ps.setString(1, emailUser);
            rs = ps.executeQuery();
            System.out.println("1");
            if (rs.next()) {
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("password"));
                System.out.println("2");
                     
                System.out.println("3");                    
                ps = conn.prepareStatement("UPDATE user SET login=? WHERE email=?");
                System.out.println("4");
                ps.setBoolean(1, false);
                ps.setString(2, emailUser);
                ps.execute();
                System.out.println("5");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USERS: " + e.getMessage());
        }
        return false;
    }
    
    
    public boolean signOut2(User userSignedIn) throws SQLException {
        Connection conn = getSqlConnection();
        try{
            ps = conn.prepareStatement("SELECT user_id, name, email, password, login FROM user WHERE email=?");
            ps.setString(1, userSignedIn.getEmail());
            rs = ps.executeQuery();
            System.out.println("1");
            if (rs.next()) {
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("password"));
                System.out.println("2");
                     
                System.out.println("3");                    
                ps = conn.prepareStatement("UPDATE user SET login=? WHERE email=?");
                System.out.println("4");
                ps.setBoolean(1, false);
                ps.setString(2, userSignedIn.getEmail());
                ps.execute();
                System.out.println("5");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al autentificar en la tabla USERS: " + e.getMessage());
        }
        return false;
    }
    /*este método no pertenece a esta clase no?
    public void insertCalendar(String name) {
        Connection conn = getSqlConnection();        
        try{
            ps = conn.prepareStatement("INSERT INTO calendar(name) VALUES(?)");
            ps.setString(1, name);
            // ps.execute();  
            int res = ps.executeUpdate();
            /*
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Calendario insertado correctamente");
                System.out.println("Calendario insertado correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Usuario insertado incorrectamente");
                System.out.println("Calendario insertado incorrectamente");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar en la tabla calendar: " + e.getMessage());
            System.out.println("Error al insertar en la tabla calendar: " + e.getMessage());
        }
    }*/
}
