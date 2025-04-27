/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class SliderController implements Initializable {

    private ImageView menumage;
    @FXML
    private JFXHamburger burger;
    @FXML
    private JFXDrawersStack drawer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    private void Slider(MouseEvent event) throws IOException {
        Parent registrationPane = FXMLLoader.load(getClass().getResource("/GUI/SysUI/Admin/slidetab.fxml"));

  
    Scene scene = menumage.getScene(); 
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
    
}
