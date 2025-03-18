
package GUI.SysUI.LogIn;

import GUI.config.Session;
import GUI.config.config;
import GUI.config.dbConnect;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;




public class controller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button btnreg;
    @FXML
    private AnchorPane Apane1;
    @FXML
    private Label welcome;
    @FXML
    private TextField UNField;
    @FXML
    private PasswordField PassField;
    @FXML
    private Label Title;
    @FXML
    private Button btnlog;
    @FXML
    private ImageView image;
    @FXML
    private Rectangle reclog;
  
     
    
    dbConnect db = new dbConnect();
    @FXML
    private Pane rootPane;
    
   
   public void initialize(URL url, ResourceBundle rb) {
        
      Platform.runLater(() -> rootPane.requestFocus()); 
        
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
    Parent registrationPane = FXMLLoader.load(getClass().getResource("/GUI/SysUI/Registration/registration.fxml"));

  
    Scene scene = btnreg.getScene(); 
    Pane rootPane = (Pane) scene.getRoot();

   
    registrationPane.translateXProperty().set(scene.getWidth()); 

    rootPane.getChildren().add(registrationPane);

 
    Timeline timeline = new Timeline();

 
    double registrationPaneWidth = registrationPane.prefWidth(-1); 
    double finalTranslateX = scene.getWidth() - registrationPaneWidth; 

  
    KeyValue keyValue = new KeyValue(registrationPane.translateXProperty(), finalTranslateX, Interpolator.EASE_IN);
    KeyFrame keyFrame = new KeyFrame(Duration.millis(600), keyValue);

    timeline.getKeyFrames().add(keyFrame);

   
    timeline.play();

  
    timeline.setOnFinished(e -> {
      
    });
}
    config con = new config();
    Session ses = Session.getInstance();
    
    
      public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        String encoded = Base64.getEncoder().encodeToString(hashBytes);
        return encoded;
    }  
    
      
 
      
@FXML
private void LogInButton(ActionEvent event) throws Exception {

    String username = UNField.getText().trim();
    String password = PassField.getText().trim();
    String hashedPassword = hashPassword(password);

    if (username.isEmpty() || password.isEmpty()) {
        con.showAlert(Alert.AlertType.ERROR, "Validation Error", "Username and password cannot be empty.");
        return;
    }

    try {
        
        Map<String, String> userInfo = authenticateUserWithStatus(username);

        // Check if the credentials (username and password) are valid first
        if (userInfo == null || !hashedPassword.equals(userInfo.get("hashed_password"))) {
            con.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            return;
        }

        // After successful credentials check, retrieve role and status
        String role = userInfo.get("role");
        String status = userInfo.get("status");

        // If role or status is missing or invalid, show a specific message
        if (role == null || role.isEmpty()) {
            con.showAlert(Alert.AlertType.WARNING, "Role Not Assigned", 
                    "Your role is not properly assigned. Please contact customer service for role activation.");
            return;
        }

        if ("Newly Registered".equalsIgnoreCase(status)) {
            con.showAlert(Alert.AlertType.WARNING, "Account Inactive",
                    "Your account is still marked as 'Newly Registered'. Please contact customer service for activation.");
            return;
        }

        // If the account is active and role is valid
        if ("Active".equalsIgnoreCase(status)) {
            Map<String, String> rolePaths = new HashMap<>();
            rolePaths.put("HR_Admin", "/GUI/SysUI/Admin/HR_Admin.fxml");
            rolePaths.put("Super_Admin", "/GUI/SysUI/SuperAdmin/Super_Admin.fxml");

            if (rolePaths.containsKey(role)) {
                Session.getInstance().createSession(1, username);
                con.showAlert(Alert.AlertType.INFORMATION, "Login Successful!",
                        "Welcome Back! You are logged in as an " + role + ". Redirecting to your dashboard.");
                con.switchScene(getClass(), event, rolePaths.get(role));
            } else {
                con.showAlert(Alert.AlertType.WARNING, "Access Denied",
                        "Your role does not have permission to access this system.");
            }
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred during login. Please try again later.");
    } catch (IOException ex) {
        ex.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the dashboard. Please try again later.");
    } catch (NoSuchAlgorithmException e) {
        con.showAlert(Alert.AlertType.ERROR, "Hashing Error", "Could not hash the password.");
        e.printStackTrace();
    }
}





   public String authenticateUser(String username, String password) throws SQLException {
       String sql = "SELECT u.user_id, u.user_name, r.role_name FROM users u INNER JOIN roles r ON u.role_id = r.role_id WHERE u.user_name = ? AND u.user_pass = ?"; 
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String Username = rs.getString("user_name");
                    String role = rs.getString("role_name"); 
                
                    
                    ses.createSession(userId, Username);

                    return role; 
                }
            }
        }
        return null;
    }


private Map<String, String> authenticateUserWithStatus(String username) throws SQLException {
    Map<String, String> userInfo = new HashMap<>();

    String query = "SELECT r.role_name, u.status, u.user_pass " +
                   "FROM users u " +
                   "JOIN roles r ON u.role_id = r.role_id " +
                   "WHERE u.user_name = ?";

    try (Connection con = new dbConnect().getConnection();
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setString(1, username);

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                
                userInfo.put("role", rs.getString("role_name") != null ? rs.getString("role_name") : "Unknown");
                userInfo.put("status", rs.getString("status") != null ? rs.getString("status") : "Inactive");
                userInfo.put("hashed_password", rs.getString("user_pass") != null ? rs.getString("user_pass") : "");
            }
        }
    }

    return userInfo.isEmpty() ? null : userInfo;
}





//    // Method to center the stage on the screen
//    public void setCenterAlignment(Stage stage) {
//        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//        double centerX = (screenBounds.getWidth() - stage.getWidth()) / 2;
//        double centerY = (screenBounds.getHeight() - stage.getHeight()) / 2;
//        stage.setX(centerX);
//        stage.setY(centerY);
//    }
   
}
    
    