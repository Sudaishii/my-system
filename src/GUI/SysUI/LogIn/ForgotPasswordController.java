package GUI.SysUI.LogIn;

import GUI.config.ResetSession;
import GUI.config.config;
import GUI.config.dbConnect;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class ForgotPasswordController {
    @FXML private TextField emailField;
    @FXML private Rectangle sendOTP;
    @FXML private TextField OTPField;
    @FXML private Button verifyOtp;
    @FXML private Label yourtp;
    @FXML private Button sendOtpButton;
    @FXML private Button backToLoginButton;

    private String currentOtp;
    private int userId;
    private Connection connection;
    private dbConnect db;

    public void initialize() {
        try {
            db = new dbConnect();
            connection = db.getConnection();
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to connect to database: " + e.getMessage());
        }
    }

    @FXML
    private void handleSendOtp() {
        if (connection == null) {
            showAlert("Error", "Database connection is not available. Please try again later.");
            return;
        }

        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            showAlert("Error", "Please enter your email address");
            return;
        }
        
        try {
            String query = "SELECT user_id FROM users WHERE user_email = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                userId = rs.getInt("user_id");
                ResetSession.getInstance().createSession(email);
                Random random = new Random();
                currentOtp = String.format("%06d", random.nextInt(1000000));
                String insertQuery = "INSERT INTO password_reset_tokens (user_id, token, expiry_time) VALUES (?, ?, ?)";
                PreparedStatement insertPst = connection.prepareStatement(insertQuery);
                insertPst.setInt(1, userId);
                insertPst.setString(2, currentOtp);
                insertPst.setString(3, LocalDateTime.now().plusMinutes(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                insertPst.executeUpdate();
                sendOtpEmail(email, currentOtp);
                OTPField.setDisable(false);
                verifyOtp.setDisable(false);
                yourtp.setDisable(false);
                emailField.setEditable(false);
                showAlert("Success", "OTP has been sent to your email");
            } else {
                showAlert("Error", "Email not found in our system");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to process your request: " + e.getMessage());
        }
    }

    @FXML
    private void handleVerifyOtp() {
        if (connection == null) {
            showAlert("Error", "Database connection is not available. Please try again later.");
            return;
        }
        String enteredOtp = OTPField.getText().trim();
        if (enteredOtp.isEmpty()) {
            showAlert("Error", "Please enter the OTP");
            return;
        }
        if (!enteredOtp.matches("\\d{6}")) {
            showAlert("Error", "OTP must be 6 digits");
            return;
        }
        try {
            String query = "SELECT token, expiry_time FROM password_reset_tokens WHERE user_id = ? AND is_used = 0 ORDER BY created_at DESC LIMIT 1";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String storedOtp = rs.getString("token");
                LocalDateTime expiryTime = LocalDateTime.parse(rs.getString("expiry_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));

                if (enteredOtp.equals(storedOtp)) {
                    if (LocalDateTime.now().isBefore(expiryTime)) {
                        String updateQuery = "UPDATE password_reset_tokens SET is_used = 1 WHERE user_id = ? AND token = ?";
                        PreparedStatement updatePst = connection.prepareStatement(updateQuery);
                        updatePst.setInt(1, userId);
                        updatePst.setString(2, storedOtp);
                        updatePst.executeUpdate();
                        showAlert("Success", "OTP verified. You may now reset your password");
                        try {
                        Parent root = FXMLLoader.load(getClass().getResource("Reset_Password.fxml"));
                        Stage stage = (Stage) emailField.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.centerOnScreen(); 
                    } catch (IOException e) {
                        showAlert("Error", "Failed to load login screen: " + e.getMessage());
                    }
                        
                    } else {
                        showAlert("Error", "OTP has expired");
                    }
                } else {
                    showAlert("Error", "Invalid OTP");
                }
            } else {
                showAlert("Error", "No valid OTP found");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to verify OTP: " + e.getMessage());
        }
    }

    @FXML
    private void handleBackToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen(); 
        } catch (IOException e) {
            showAlert("Error", "Failed to load login screen: " + e.getMessage());
        }
    }

    private void sendOtpEmail(String email, String otp) {
        final String username = "amphoreus.hotel.ph@gmail.com"; 
        final String password = "fpxo skcu zpxq afez"; 
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "PayFuse"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Password Reset OTP");
            String content = "Your OTP for password reset is: " + otp + "\n\n" +
                           "This OTP will expire in 10 minutes.\n\n" +
                           "If you didn't request this, please ignore this email.\n\n" +
                           "Thanks,\nPayFuse Team";
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            showAlert("Error", "Failed to send OTP: " + e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 