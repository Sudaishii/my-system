/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.LogIn;

import GUI.config.Session;
import GUI.config.config;
import GUI.config.dbConnect;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class LoginController implements Initializable {

    @FXML
    private Button bntlog;

    dbConnect db = new dbConnect();    
    config con = new config();
    Session ses = Session.getInstance(); 
    
    @FXML
    private Pane rootPane;
    @FXML
    private Pane navHolder;
    @FXML
    private Label Title1;
    @FXML
    private Button btnreg1;
    @FXML
    private TextField UNField1;
    @FXML
    private PasswordField PassField1;
    @FXML
    private Label forgotPasswordLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> rootPane.requestFocus()); 
    }    

    @FXML
    private void regBtn(MouseEvent event) throws IOException {
         Parent registrationPane = FXMLLoader.load(getClass().getResource("/GUI/SysUI/Registration/registration.fxml"));

  
    Scene scene = btnreg1.getScene(); 
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

   @FXML
private void LogIntButton(MouseEvent event) throws Exception {
    config conf = new config();   

    String username = UNField1.getText().trim();
    String password = PassField1.getText().trim();
    String hashedPassword = con.hashPassword(password);
    
    System.out.println("Username Value: " + username);

   if (username.isEmpty() || password.isEmpty()) {
        conf.logAction(username, "Login Attempt", "Failed - Empty fields"); 
        System.out.println("dasdasas");
        con.showAlert(Alert.AlertType.ERROR, "Validation Error", "Username and password cannot be empty."); 
        
        
        
        return;
    }

    try {
        Map<String, String> userInfo = authenticateUserWithStatus(username);

        if (userInfo == null || userInfo.isEmpty()) {
            con.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            con.logAction(username, "Login Failed", "Invalid username or password.");
            
            return;
        }

        String role = userInfo.get("role");
        String status = userInfo.get("status");
        String storedHashedPassword = userInfo.get("hashed_password");

        if (role == null || role.isEmpty()) {
            con.showAlert(Alert.AlertType.WARNING, "Role Not Assigned",
                    "Your role is not properly assigned. Please contact customer service for role activation.");
            con.logAction(username, "Login Failed", "Role not assigned.");
            return;
        }

        if ("Newly Registered".equalsIgnoreCase(status)) {
            con.showAlert(Alert.AlertType.WARNING, "Account Inactive",
                    "Your account is still marked as 'Newly Registered'. Please contact customer service for activation.");
            con.logAction(username, "Login Failed", "Account marked as 'Newly Registered'.");
            return;
        }

        if (storedHashedPassword == null || !hashedPassword.equals(storedHashedPassword)) {
            con.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            con.logAction(username, "Login Failed", "Password mismatch.");
            return;
        }

        if ("Active".equalsIgnoreCase(status)) {
            Map<String, String> rolePaths = new HashMap<>();
            rolePaths.put("HR_Admin", "/GUI/SysUI/Admin/HR_Admin.fxml");
            rolePaths.put("Super_Admin", "/GUI/SysUI/SuperAdmin/Super_Admin.fxml");
            rolePaths.put("Employee", "/GUI/SysUI/Employees/Employee.fxml");
            rolePaths.put("Staff", "/GUI/SysUI/Staff/Staff.fxml");

            if (rolePaths.containsKey(role)) {
                Session.getInstance().createSession(1, username);
                con.showAlert(Alert.AlertType.INFORMATION, "Login Successful!",
                        "Welcome Back! You are logged in as an/a " + role + ". Redirecting to your dashboard.");
                con.logAction(username, "Login Successful", "Logged in as " + role);
                con.switchScene(getClass(), event, rolePaths.get(role));
            } else {
                con.showAlert(Alert.AlertType.WARNING, "Access Denied",
                        "Your role does not have permission to access this system.");
                con.logAction(username, "Login Failed", "Access denied due to role restriction.");
            }
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred during login. Please try again later.");
        con.logAction(username, "Database Error", ex.getMessage());
    } catch (IOException ex) {
        ex.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load the dashboard. Please try again later.");
        con.logAction(username, "Navigation Error", ex.getMessage());
    } catch (NoSuchAlgorithmException e) {
        con.showAlert(Alert.AlertType.ERROR, "Hashing Error", "Could not hash the password.");
        e.printStackTrace();
        con.logAction(username, "Hashing Error", e.getMessage());
    }
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
    
    @FXML
    private void handleForgotPassword(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("forgot_password_real.fxml"));
    Stage stage = (Stage) forgotPasswordLabel.getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.centerOnScreen(); 

    }
}
