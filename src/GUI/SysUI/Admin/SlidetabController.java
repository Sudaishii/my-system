/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class SlidetabController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void dashboard(MouseEvent event) {
    }
    
}
