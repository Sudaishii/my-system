    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package GUI.SysUI.univ;

    import GUI.config.Session;
    import GUI.config.config;
    import GUI.config.dbConnect;
    import java.net.URL;
import java.security.NoSuchAlgorithmException;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ResourceBundle;
import javafx.application.Platform;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Label;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

    /**
     * FXML Controller class
     *
     * @author Administrator
     */
    public class ProfileController implements Initializable {
        
      
        @FXML
        private Label ProfName;
        @FXML
        private Label emailLabel;
        @FXML
        private TextField eField;
        @FXML
        private Pane odlPass;
        @FXML
        private PasswordField newPass;
        @FXML
        private PasswordField confPass;
        @FXML
        private PasswordField oldPass;
        @FXML
        private ImageView Profile;
        private static final double PROFILE_FIT_WIDTH = 137;
        private static final double PROFILE_FIT_HEIGHT = 114;
        private static final double IMAGE_SIZE = 100;

      
        @Override
        public void initialize(URL url, ResourceBundle rb) {

           Session ses = Session.getInstance();
           String uname = ses.getUsername(); 
           ProfName.setText(uname);

            fetchUserDetails(uname);
            
            final config conf = new config();
            
            if (hasEmpId(uname)) {
                    Platform.runLater(() -> {
                        conf.loadProfilePicture(uname, Profile, IMAGE_SIZE);  
                    });
                } else {

                Image defaultImage = new Image("/GUI/images/default.png", false);
                Profile.setImage(defaultImage);
                Profile.setFitWidth(IMAGE_SIZE);
                Profile.setFitHeight(IMAGE_SIZE);
                Profile.setPreserveRatio(true);
                Profile.setSmooth(true);
                Profile.setCache(true);

                setToCircle(IMAGE_SIZE, Profile); 
            }

        }    

        config con = new config();
        dbConnect db = new dbConnect();
        
           public boolean hasEmpId(String username) {

            dbConnect db = new dbConnect();    

            String query = "SELECT emp_id FROM users WHERE user_name = ?";
            try (PreparedStatement stmt = db.getConnection().prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    return result.getInt("emp_id") != 0; 
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }
            return false; 
        }

        private void fetchUserDetails(String username) {
        String query = "SELECT user_email FROM users WHERE user_name = ?"; 
        dbConnect dbConnection = new dbConnect(); 

        try (Connection connection = dbConnection.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("user_email"); 
                eField.setText(email); 
                emailLabel.setText(email);
            } else {

                con.showAlert(Alert.AlertType.ERROR, "Error", "Could not retrieve user details.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            con.showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch user details.");
        }
    }

    public void setToCircle(double IMAGE_SIZE, ImageView image) {

    Circle circle = new Circle(IMAGE_SIZE / 2); 
    

    circle.centerXProperty().bind(image.fitWidthProperty().divide(2));
    circle.centerYProperty().bind(image.fitHeightProperty().divide(2));
    

    image.setClip(circle);
}
        
        @FXML
        private void PassChange(MouseEvent event) throws NoSuchAlgorithmException {

            String oldPassword = oldPass.getText().trim();
            String newPassword = newPass.getText().trim();
            String confirmPassword = confPass.getText().trim();

            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                con.showAlert(Alert.AlertType.WARNING, "Warning", "All fields are required.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                con.showAlert(Alert.AlertType.WARNING, "Warning", "New passwords do not match.");
                return;
            }

            if (newPassword.length() < 8) {
                con.showAlert(Alert.AlertType.WARNING, "Weak Password", "New password must be at least 8 characters long.");
                return;
            }

            
            if (oldPassword.equals(newPassword)) {
                con.showAlert(Alert.AlertType.WARNING, "Warning", "New password cannot be the same as the old password.");
                return;
            }

            Session ses = Session.getInstance();
            String username = ses.getUsername();

            if (validateOldPassword(username, oldPassword)) {
                updatePassword(username, newPassword);
               
                con.showAlert(Alert.AlertType.INFORMATION, "Success", "Password updated successfully.");
               
                oldPass.clear();
                newPass.clear();
                confPass.clear();
            } else {
               
               
               
            }
        }

          private boolean validateOldPassword(String username, String oldPassword) throws NoSuchAlgorithmException {
             String query = "SELECT user_pass FROM users WHERE user_name = ?";

                    try (Connection connection = db.getConnection();
                         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                        preparedStatement.setString(1, username);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            String storedPassword = resultSet.getString("user_pass");


                            if (!con.hashPassword(oldPassword).equals(storedPassword)) {
                                con.showAlert(Alert.AlertType.ERROR, "Error", "Incorrect old password.");
                                return false;
                            }
                            return true;
                        } else {
                            con.showAlert(Alert.AlertType.ERROR, "Error", "User not found.");
                            return false;
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        con.showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to verify password.");
                        return false;
                    }
                } 
          
          
           private void updatePassword(String username, String newPassword) throws NoSuchAlgorithmException {
            String query = "UPDATE users SET user_pass = ? WHERE user_name = ?";

            try (Connection connection = db.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, con.hashPassword(newPassword)); 
                preparedStatement.setString(2, username);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    con.showAlert(Alert.AlertType.INFORMATION, "Success", "Password successfully changed.");
                    clearFields();
                } else {
                    con.showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                con.showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update password.");
            }
        }
    
          
          
          private void clearFields() {
            oldPass.clear();
            newPass.clear();
            confPass.clear();
        }
          
          

    }
