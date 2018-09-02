package jdbc;

import java.sql.*;
import java.util.Properties;

public class DBHelper {
    public static final String driver;
    public static final String url;
    public static final String username;
    public static String password;
    private static Properties pr = new Properties();
    static {
        try {
            pr.load(DBHelper.class.getClassLoader().getResourceAsStream("db.properties"));
            driver = pr.getProperty("driver");
            url = pr.getProperty("url");
            username = pr.getProperty("username");
            //password = pr.getProperty("password");
            Class.forName(driver);
        } catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public Connection getConnection() throws SQLException{
            return DriverManager.getConnection(url, username, password);
    }

    public void free (ResultSet rs, PreparedStatement pstmt, Connection conn){
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
