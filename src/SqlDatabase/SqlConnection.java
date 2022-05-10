package SqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SqlConnection {
//
//    public static final String url = "jdbc:mysql://i54jns50s3z6gbjt.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/iy5jkxqnaep9jd04" + "?useSSL=false";
//    public static final String user = "e3uv1gsfsu6r8v6j";
//    public static final String pswd = "ud6g593tlbsh3knw";

//    public static final String url = "jdbc:sqlite:CalendarBDLocal.db";
    private Connection con = null;

    public void SqlConnection() {

    }

    public Connection getSqlConnection() {
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
//            con = DriverManager.getConnection(url, user, pswd);
            String url = "jdbc:sqlite:CalendarBDLocal.db";
            // creamos una conexión a la BD
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("NO SE HA PODIDO CONECTAR A LA BASE DE DATOS LOCAL");
        }
        return con;
    }
}
