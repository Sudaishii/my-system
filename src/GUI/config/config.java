package GUI.config;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Rasheed
 */
public class config {

    public void showAlert(Alert.AlertType alertType, String title, String message) {

       

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);


        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/GUI/SysUI/Registration/registration.css").toExternalForm());
        dialogPane.getStyleClass().add("alert");


        Stage alertStage = (Stage) dialogPane.getScene().getWindow();


        alertStage.setResizable(false);


        alertStage.initStyle(StageStyle.UTILITY);


        alertStage.initModality(Modality.APPLICATION_MODAL);


        alertStage.setOnShown(event -> {

            alertStage.setX(alertStage.getX());
            alertStage.setY(alertStage.getY());
        });


        alert.showAndWait();

    }

    public void switchScene(Class getClass, Event evt, String targetFXML) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(targetFXML));
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }


    public void fadeIn(Node node) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(100));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public void fadeOut(Node node) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(100));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    public void setSelected(String imageLocation, Label label, Line line, ImageView icon) {
        Image newImage = new Image(imageLocation);
        label.setTextFill(Color.web("#2f9efe"));
        line.setOpacity(1);
        icon.setImage(newImage);
    }

    public void setUnselected(String imageLocation, Label label, Line line, ImageView icon) {
        Image newImage = new Image(imageLocation);
        label.setTextFill(Color.web("#a5b4d9"));
        line.setOpacity(0);
        icon.setImage(newImage);
    }
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        String encoded = Base64.getEncoder().encodeToString(hashBytes);
        return encoded;
    }  
    
    public boolean addRecord(String sql, Object... values) {
    try (Connection conn = new dbConnect().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) values[i]);
            } else if (values[i] instanceof Double) {
                pstmt.setDouble(i + 1, (Double) values[i]); 
            } else if (values[i] instanceof Float) {
                pstmt.setFloat(i + 1, (Float) values[i]); 
            } else if (values[i] instanceof Long) {
                pstmt.setLong(i + 1, (Long) values[i]); 
            } else if (values[i] instanceof Boolean) {
                pstmt.setBoolean(i + 1, (Boolean) values[i]); 
            } else if (values[i] instanceof java.util.Date) {
                pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); 
            } else if (values[i] instanceof java.sql.Date) {
                pstmt.setDate(i + 1, (java.sql.Date) values[i]);
            } else if (values[i] instanceof java.sql.Timestamp) {
                pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]);
            } else {
                pstmt.setString(i + 1, values[i].toString()); 
            }
        }

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;  // Return true if the insertion was successful

    } catch (SQLException e) {
        System.out.println("Error adding record: " + e.getMessage());
        return false;  // Return false if there's an error
    }
}
//     public void addRecord(String sql, Object... values) {
//         
//         
//         
//          try (Connection conn = new dbConnect().getConnection();
//         PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//  
//        for (int i = 0; i < values.length; i++) {
//            if (values[i] instanceof Integer) {
//                pstmt.setInt(i + 1, (Integer) values[i]);
//            } else if (values[i] instanceof Double) {
//                pstmt.setDouble(i + 1, (Double) values[i]); 
//            } else if (values[i] instanceof Float) {
//                pstmt.setFloat(i + 1, (Float) values[i]); 
//            } else if (values[i] instanceof Long) {
//                pstmt.setLong(i + 1, (Long) values[i]); 
//            } else if (values[i] instanceof Boolean) {
//                pstmt.setBoolean(i + 1, (Boolean) values[i]); 
//                pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); 
//            } else if (values[i] instanceof java.sql.Date) {
//                pstmt.setDate(i + 1, (java.sql.Date) values[i]);
//            } else if (values[i] instanceof java.sql.Timestamp) {
//                pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]);
//            } else {
//                pstmt.setString(i + 1, values[i].toString()); 
//            }
//        }
//
//        pstmt.executeUpdate();
//        System.out.println("Record added successfully!");
//    } catch (SQLException e) {
//        System.out.println("Error adding record: " + e.getMessage());
//    }
//}   
     
      dbConnect db = new dbConnect();
     
    public void showAlert(String warning, String selection_Error, String please_select_an_employee_before_updating) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    
    public void logAction(String username, String action, String details) {
    System.out.println("Attempting to log action...");
    System.out.println("Username: " + username);
    System.out.println("Action: " + action);
    System.out.println("Details: " + details);

    String sql = "INSERT INTO system_logs (username, action, details) VALUES (?, ?, ?)";
    try (Connection connect = db.getConnection();
         PreparedStatement pstmt = connect.prepareStatement(sql)) {
        
        pstmt.setString(1, username);
        pstmt.setString(2, action);
        pstmt.setString(3, details);

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