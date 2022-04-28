package SqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;

public class HerokuUsersSqlConnection extends SqlConnection {
        
    private static HerokuUsersSqlConnection instance;
    private String emailUser;
    
    private User user;
    
    PreparedStatement ps;
    ResultSet rs;
    
    public HerokuUsersSqlConnection(){}
    
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
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void selectUserById(int id) {
        Connection conn = getSqlConnection();
        
        try{
            ps = conn.prepareStatement("SELECT * FROM user WHERE user_id=" + Integer.toString(id));
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
        }catch (SQLException e) {
            System.err.println(e);
        } finally{
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
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
                int aux=rs.getInt("user_id");
                return aux;
            } else {
                return -1;
            }
            
        } catch (SQLException e) {
            System.err.println(e);
            return -1;
        } finally{
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    } 
    
    public String getEmailByUserId(int id) throws SQLException {
        Connection conn = getSqlConnection();
        try{
            ps = conn.prepareStatement("SELECT * FROM user WHERE user_id=?");
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String aux= rs.getString("email");
                return aux;
            } else {
                return null;
            }
            
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally{
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    } 
    
    /*este metodo en realidad no es un select, es una comprobación*/
    public boolean selectUserByEmail(String email) throws SQLException {
        Connection conn = getSqlConnection();
        
        try{
            ps = conn.prepareStatement("SELECT * FROM user WHERE email=?");
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println(e);
        } finally{
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }
    
    public void deleteUserById(int id) {
        
        Connection conn = getSqlConnection();
        
        try{
            ps = conn.prepareStatement("DELETE FROM user WHERE user_id=?");
            ps.setInt(1, id);
            ps.execute();
                        
        }catch (SQLException e) {
            System.err.println(e);
        } finally{
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
    }

    
    public void insertUser(String name, String pswd, String email, boolean login) {
        Connection conn = getSqlConnection();        
        try{
            ps = conn.prepareStatement("INSERT INTO user(name, password, email, login) VALUES(?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, pswd);
            ps.setString(3, email);
            ps.setBoolean(4, login);
            ps.execute();
            /*
            Sirve para guardar el email como variable local para identificar el usuario en toda la sesión
            */
            emailUser = email;

        } catch (SQLException e) {
            System.err.println(e);
        } finally{
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean updateUser(User user){
        
        Connection conn = getSqlConnection();
                
        try{
            ps = conn.prepareStatement("UPDATE user SET name=?, description=? where email=?");
            System.out.println("Nombre del usuario al modificar = " + user.getName());
            ps.setString(1, user.getName());
            System.out.println("Descripción del usuario al modificar = " + user.getDescription());
            ps.setString(2, user.getDescription());
            ps.setString(3, user.getEmail());
            ps.execute();
            return true;
        }catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }        
    }
    
    public boolean login(User user) throws SQLException {
        
        Connection conn = getSqlConnection();
        try{
            ps = conn.prepareStatement("SELECT user_id, name, email, password, login, description FROM user WHERE email=?");
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
                        user.setDescription(rs.getString(6));
                                                
                        emailUser = user.getEmail();
                        conn.close();
                        return true;
                    }else{
                    }
                }
            }
            
        }catch (SQLException e) {
            System.err.println(e);
        } finally{
            try{
                conn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    public boolean signOut() throws SQLException {
        Connection conn = getSqlConnection();
        try{
            ps = conn.prepareStatement("SELECT user_id, name, email, password, login FROM user WHERE email=?");
            ps.setString(1, emailUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE user SET login=? WHERE email=?");
                ps.setBoolean(1, false);
                ps.setString(2, emailUser);
                ps.execute();
                conn.close();
                return true;
            }
        } catch (SQLException e) {
        }
        conn.close();
        return false;
    }
    
    
    public boolean signOut2(User userSignedIn) throws SQLException {
        Connection conn = getSqlConnection();
        try{
            ps = conn.prepareStatement("SELECT user_id, name, email, password, login FROM user WHERE email=?");
            ps.setString(1, userSignedIn.getEmail());
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE user SET login=? WHERE email=?");
                ps.setBoolean(1, false);
                ps.setString(2, userSignedIn.getEmail());
                ps.execute();
                conn.close();
                return true;
            }
        } catch (SQLException e) {
        }
        conn.close();
        return false;
    }
}
