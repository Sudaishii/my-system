/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import GUI.config.Session;
import GUI.config.config;
import GUI.config.dbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class SlidetabController implements Initializable {

    @FXML
    private Label greet;
    @FXML
    private Label name;
    @FXML
    private ImageView Profile;
    private static final double PROFILE_FIT_WIDTH = 204;
    private static final double PROFILE_FIT_HEIGHT = 168;
    private static final double IMAGE_SIZE = 50;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        Session ses = Session.getInstance();
        String uname = ses.getUsername(); 

   
        name.setText(uname);
        
    
        
        
     
        config conf = new config();
        
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
    
        
//        Profile.setImage(image);
//        Profile.setPreserveRatio(true);
//        Profile.setSmooth(true);
//
//        //Set a fixed size for the ImageView or it won't display correctly
//        double radius = 26;
//        Profile.setFitWidth(radius * 2);
//        Profile.setFitHeight(radius * 2);
//
//        // Create a circular clip for the ImageView
//        Circle circleClip = new Circle(radius, radius, radius);
//        Profile.setClip(circleClip);
//
//        // Ensure the ImageView's size is managed correctly.  Important!
//        Profile.setManaged(false); //  Important for layout in some cases
//        

//        double size = Math.min(52, 41);
//            Profile.setFitWidth(size);
//            Profile.setFitHeight(size);
//
//            Circle circleClip = new Circle(size / 2, size / 2, size / 2);
//            Profile.setClip(circleClip);
//
//            Profile.setImage(new Image(getClass().getResourceAsStream("/GUI/images/Emp/09c0eddb-1373-422b-b709-ef871a17eb5d.jpg")));
        
      
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        int hour = now.getHour();

     
        String greeting;
        if (hour >= 5 && hour < 12) {
            greeting = "Good Morning";
        } else if (hour >= 12 && hour < 18) {
            greeting = "Good Afternoon";
        } else {
            greeting = "Good Evening";
        }

        String combinedText = greeting ;

         
        greet.setText(combinedText);
        
        
    }    
//   public void loadPage(String targetFXML, Object controller) {
//    try {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(targetFXML));
//
//        if (controller != null) {
//            loader.setController(controller);
//        }
//
//        Parent newContent = loader.load();
//        newContent.setTranslateX(-250);  // Start aligned with the hidden sidebar
//
//        // Animate content sliding in
//        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.4), newContent);
//        slideIn.setToX(0);
//
//        // Clear old content, add new one, then animate
//        centerPane.getChildren().clear();
//        centerPane.getChildren().add(newContent);
//        
//        slideIn.play();
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
    
    public void setToCircle(double IMAGE_SIZE, ImageView image) {

    Circle circle = new Circle(IMAGE_SIZE / 2); 
    

    circle.centerXProperty().bind(image.fitWidthProperty().divide(2));
    circle.centerYProperty().bind(image.fitHeightProperty().divide(2));
    

    image.setClip(circle);
}
    
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
    
    @FXML
    private void logout(MouseEvent event) {
        
        

    
}
}