    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package GUI.SysUI.SuperAdmin;

    import GUI.config.dbConnect;
    import java.net.URL;
    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.time.format.DateTimeFormatter;
    import java.util.ResourceBundle;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.TextArea;
    import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

    /**
     * FXML Controller class
     *
     * @author Administrator
     */
    public class SystemController implements Initializable {

        @FXML
        private TextArea LogsArea;


        @Override
        public void initialize(URL url, ResourceBundle rb) {
            loadLogs();
        }    
    

        private void loadLogs() {
        LogsArea.clear(); 

    String sql = "SELECT log_id, username, action, details, timestamp FROM system_logs ORDER BY timestamp DESC";
    dbConnect dbConnect = new dbConnect();

    try (Connection conn = dbConnect.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss");

        StringBuilder logBuilder = new StringBuilder();

        while (rs.next()) {
            String action = rs.getString("action").toLowerCase();
            String colorTag;

            // Text-based color indicators
            if (action.contains("success")) {
                colorTag = "[✔> SUCCESS]"; 
            } else if (action.contains("error") || action.contains("failed")) {
                colorTag = "[❌ ERROR]";   
            } else if (action.contains("info")) {
                colorTag = "[ℹ️ INFO]";    
            } else {
                colorTag = "[⚫ GENERAL]"; 
            }

          
            String logEntry = String.format(
                "[ID: %-4d] [%-15s] [User: %-10s] %-12s %s",
                rs.getInt("log_id"),
                rs.getTimestamp("timestamp").toLocalDateTime().format(formatter),
                rs.getString("username"),
                colorTag,
                rs.getString("details")
            );

            logBuilder.append(logEntry).append("\n");
        }

        LogsArea.setText(logBuilder.toString());

    } catch (SQLException e) {
        LogsArea.setText("Error loading logs: " + e.getMessage());
    }
}



    }
