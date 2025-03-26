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
            // TODO
        }    
        private void loadLogs() {
            StringBuilder logBuilder = new StringBuilder();
            String sql = "SELECT log_id, username, action, details, timestamp FROM system_logs ORDER BY timestamp DESC";

            dbConnect dbConnect = new dbConnect();

            try (Connection conn = dbConnect.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                while (rs.next()) {
                    String logEntry = String.format("[%s] [ID: %d] [User: %s] [%s] %s%n",
                            rs.getTimestamp("timestamp").toLocalDateTime().format(formatter),
                            rs.getInt("log_id"),
                            rs.getString("username"),
                            rs.getString("action"),
                            rs.getString("details")
                    );
                    logBuilder.append(logEntry);
                }

                LogsArea.setText(logBuilder.toString());
            } catch (SQLException e) {
                LogsArea.setText("Error loading logs: " + e.getMessage());
            }
}
    }
