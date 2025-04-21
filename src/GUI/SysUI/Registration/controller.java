package GUI.SysUI.Registration;

import GUI.config.config;
import GUI.config.dbConnect;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class controller {

    @FXML
    private AnchorPane APaneReg;
    @FXML
    private Button btnSign, LogIn;
    @FXML
    private TextField  emailF, usernameF;
    @FXML
    private PasswordField passwordF;

    dbConnect db = new dbConnect();
    config con = new config();
    @FXML
    private Label Title1;

    public void initialize() {
        // Initialization logic if needed
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Scene scene = ((Node) event.getSource()).getScene();
        Pane rootPane = (Pane) scene.getRoot();
        Parent registrationPane = (Parent) rootPane.getChildren().get(rootPane.getChildren().size() - 1);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(registrationPane.translateXProperty(), scene.getWidth(), Interpolator.EASE_OUT);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(e -> rootPane.getChildren().remove(registrationPane));
        timeline.play();
    }
    
     
    @FXML
    private void RegisterClick(javafx.scene.input.MouseEvent event) throws NoSuchAlgorithmException, IOException {
    String username = usernameF.getText().trim();
    String email = emailF.getText().trim();
    String password = passwordF.getText().trim();

    if (!validateInputs(email, username, password)) {
        return;
    }

    List<String> errors = new ArrayList<>();

    try {
        if (isDuplicate("user_email", email)) {
            errors.add("This email is already registered.");
        }

        if (isDuplicate("user_name", username)) {
            errors.add("This username is already taken.");
        }

        if (!errors.isEmpty()) {
            con.showAlert(Alert.AlertType.ERROR, "Registration Error", String.join("\n", errors));
            return;
        }

        String hashedPassword = con.hashPassword(password);

        String sql = "INSERT INTO users (user_email, user_name, user_pass, status, role_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setString(2, username);
            pst.setString(3, hashedPassword);
            pst.setString(4, "Newly Registered");
            pst.setInt(5, 3); // 3 = Employee role_id

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                con.showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "You have successfully registered!");
                clearFields();
                logout(new ActionEvent());
            } else {
                con.showAlert(Alert.AlertType.ERROR, "Registration Failed", "No rows were inserted. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            con.showAlert(Alert.AlertType.ERROR, "Registration Failed", "An error occurred while registering.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while checking for duplicates.");
    }
}
    
     @FXML
    private void logout(ActionEvent event) {
   
        try {
            Stage stage = (Stage) APaneReg.getScene().getWindow(); 
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/SysUI/LogIn/login.fxml"));
            Scene scene = new Scene(root, 899, 547);

            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading login page: " + e.getMessage());
            e.printStackTrace();
        }
    

    }
    private boolean validateInputs(String email, String username, String password) {
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            con.showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
            return false;
        }

        if (password.length() < 8) {
            con.showAlert(Alert.AlertType.ERROR, "Validation Error", "Password must be at least 8 characters long.");
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            con.showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a valid email address.");
            return false;
        }

        return true;
    }

    private boolean isDuplicate(String column, String value) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE " + column + " = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, value);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        }
    }

    private void clearFields() {
        emailF.clear();
        passwordF.clear();
        usernameF.clear();
    }
}
