/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import GUI.config.Session;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class SlidetabController implements Initializable {

    @FXML
    private Label greet;
    @FXML
    private Label name;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Session ses = Session.getInstance();
        String uname = Session.getInstance().getUname();
        uname = ses.getUname();
        name.setText(uname);
        
        
        
      
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Singapore")); // GMT+8 Timezone
        int hour = now.getHour();

     
        String greeting;
        if (hour >= 5 && hour < 12) {
            greeting = "Good Morning";
        } else if (hour >= 12 && hour < 18) {
            greeting = "Good Afternoon";
        } else {
            greeting = "Good Evening";
        }

        String combinedText = greeting + " " + uname;
         
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

    @FXML
    private void logout(MouseEvent event) {
        
        

    
}
}