/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class LogDAO {
     public void insertLog(int userId, String actionType, String description) {
        String sql = "INSERT INTO system_logs (user_id, action_type, description) VALUES (?, ?, ?)";
        
        dbConnect dbConnect = new dbConnect();
        
        try (Connection conn = dbConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, actionType);
            pstmt.setString(3, description);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error inserting log: " + e.getMessage());
        }
    }
}
