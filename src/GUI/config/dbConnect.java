package GUI.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

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

    
    public ResultSet getData(String sql) throws SQLException {
        Connection connect = getConnection(); 
        Statement stmt = null;
        ResultSet rst = null;
        try {
            stmt = connect.createStatement();
            rst = stmt.executeQuery(sql);
            return rst; 
           
        } catch (SQLException ex) {
            
            if (connect != null) {
                connect.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            throw ex; 
        } finally {
            
        }
    }
    
    public void logAction(String username, String action, String details) {
        System.out.println("das");
    String sql = "INSERT INTO logs (username, action, details, timestamp) VALUES (?, ?, ?, ?)";
    try (Connection connect = getConnection();
         PreparedStatement pstmt = connect.prepareStatement(sql)) {
        
        pstmt.setString(1, username);
        pstmt.setString(2, action);
        pstmt.setString(3, details);
        pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Log entry successfully created.");
        } else {
            System.out.println("Failed to insert log entry.");
        }
    } catch (SQLException e) {
        System.err.println("Error while logging action: " + e.getMessage());
    }
}
}