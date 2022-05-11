package SqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SqlConnection {
//
//    public static final String url = "jdbc:mysql://i54jns50s3z6gbjt.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/iy5jkxqnaep9jd04" + "?useSSL=false";
//    public static final String user = "e3uv1gsfsu6r8v6j";
//    public static final String pswd = "ud6g593tlbsh3knw";
public static final String url = "jdbc:mysql:BDLocal";
    public static final String user = "root";
    public static final String pswd = "minervafff";
//    public static final String url = "jdbc:sqlite:CalendarBDLocal.db";
    private Connection conn = null;

    public void SqlConnection() {

    }

    public Connection getSqlConnection()  {
//        try {
////            Class.forName("com.mysql.jdbc.Driver");
////            con = DriverManager.getConnection(url, user, pswd);
//            String url = "jdbc:sqlite:CalendarBDLocal.db";
//            // creamos una conexión a la BD
//            con = DriverManager.getConnection(url);
//        } catch (SQLException | ClassNotFoundException e) {
//        }

        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/iy5jkxqnaep9jd04?useSSL=false","root","minervafff");
            Statement stmt=conn.createStatement();  
            ResultSet rs=stmt.executeQuery("show databases;");
            if( conn != null){
              System.out.println("Connected");  

            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
   
    }
    
}
