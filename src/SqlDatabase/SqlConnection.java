package SqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class SqlConnection {

    public static final String url = "jdbc:mysql://i54jns50s3z6gbjt.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/iy5jkxqnaep9jd04" + "?useSSL=false";
    public static final String user = "e3uv1gsfsu6r8v6j";
    public static final String pswd = "ud6g593tlbsh3knw";
    private Connection con = null;
    
    public void SqlConnection (){

    }

    public Connection getSqlConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pswd);
        } catch (SQLException | ClassNotFoundException e) {
        }
        return con;
    }
}