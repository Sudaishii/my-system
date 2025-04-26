/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.LogIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class login extends Application {
    
        public static void main(String[] args) {
            
            launch(args); 
    }
        
    

    @Override
    public void start(Stage stage) throws Exception {
             
       Parent root = FXMLLoader.load(getClass().getResource("/GUI/SysUI/LogIn/login.fxml"));

        Scene scene = new Scene (root); 
       
       String css = this.getClass().getResource("application.css").toExternalForm();
       scene.getStylesheets().add(css);
       
       stage.getIcons().add(new Image("/GUI/images/money.png"));
       stage.setTitle("PayFuse");
       

       stage.setResizable(false);
       stage.setScene(scene);
       stage.sizeToScene();
       stage.show();
    
    }
    
    
    
}
