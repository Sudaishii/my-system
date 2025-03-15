package GUI.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnect {

    private static final String URL = "jdbc:mysql://localhost:3306/sys_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public int insertData(String sql) {
        int result = 0;
        try (Connection connect = getConnection(); // Get a new connection
             PreparedStatement pst = connect.prepareStatement(sql)) {
            pst.executeUpdate();
            System.out.println("Inserted Successfully!");
            result = 1;
        } catch (SQLException ex) {
            System.out.println("Connection Error: " + ex);
        }
        return result;
    }

    //Function to retrieve data
    public ResultSet getData(String sql) throws SQLException {
        Connection connect = getConnection(); // Get a new connection
        Statement stmt = null;
        ResultSet rst = null;
        try {
            stmt = connect.createStatement();
            rst = stmt.executeQuery(sql);
            return rst; // Be very careful returning ResultSet here!
            // Consider processing the ResultSet and returning a List instead.
        } catch (SQLException ex) {
            // It's important to close the connection even if an error occurs
            if (connect != null) {
                connect.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            throw ex; // Re-throw the exception to be handled by the caller
        } finally {
            // The calling method will need to handle closing the ResultSet and Connection
            // if you return the ResultSet directly. This is generally not recommended.
        }
    }
}