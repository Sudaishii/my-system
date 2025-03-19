package GUI.SysUI.Admin;

import GUI.config.config;
import GUI.config.dbConnect;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;

public class UpdateEmployeeController implements Initializable {

    @FXML
    private AnchorPane overlayPane;
    @FXML
    private Pane rootPane;
    @FXML
    private TextField fname;
    @FXML
    private TextField addF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField contactF;
    @FXML
    private ComboBox<?> deptF;
    @FXML
    private ComboBox<?> posF;
    @FXML
    private Button submit;
    private int employeeId;
    @FXML
    private ComboBox<?> empBox;

    config con = new config();
    dbConnect db = new dbConnect();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }


    public void setOverlayPane(AnchorPane overlayPane) {
        this.overlayPane = overlayPane;
    }


    public void setRootPane(Pane rootPane) {
        this.rootPane = rootPane;
    }

    
    private void closeOverlay() {
        if (overlayPane != null && rootPane != null) {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), overlayPane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

           
            fadeOut.setOnFinished(ev -> {
                rootPane.getChildren().remove(overlayPane); 
                rootPane.setMouseTransparent(false);
            });

            fadeOut.play();
        }
    }

    private void closeOverlay(Pane rootPane, Parent overlayPane) {
    if (overlayPane != null && rootPane != null) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), overlayPane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        fadeOut.setOnFinished(ev -> {
            rootPane.getChildren().remove(overlayPane); 
            rootPane.setMouseTransparent(false); 
        });

        fadeOut.play();
    }
}
    
    
   

   
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
        System.out.println("Employee ID received in UpdateEmployeeController: " + employeeId);
    }

   
    @FXML
    private void closeBtn(MouseEvent event) {
        closeOverlay(rootPane, overlayPane);
    }

    
    @FXML
    private void AddEmployee(MouseEvent event) {
        System.out.println("Submit button clicked for employee update.");
      
    }

    
    
}
