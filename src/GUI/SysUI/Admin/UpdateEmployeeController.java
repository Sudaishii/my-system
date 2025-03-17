/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class UpdateEmployeeController implements Initializable {

    @FXML
    private TextField addF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField contactF;
    @FXML
    private ComboBox<String> deptF;
    @FXML
    private ComboBox<String> posF;
    @FXML
    private Button submit;
    @FXML
    private AnchorPane overlayPane;
    @FXML
    private Pane rootPane;
    @FXML
    private TextField fname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void setOverlayPane(AnchorPane overlayPane) {
        this.overlayPane = overlayPane;
    }

    public void setRootPane(Pane rootPane) {
        this.rootPane = rootPane;
    }
    
    public void setEmployeeData(Employees employee) {
    String fullName = employee.getFname() + " " + employee.getLname();
    fname.setText(fullName);
    fname.setEditable(false);

    addF.setText(employee.getContact());  
    emailF.setText(employee.getEmail());
    contactF.setText(employee.getContact());
    deptF.setValue(employee.getDept());
    posF.setValue(employee.getPos());
}

    
    @FXML
    private void closeBtn(MouseEvent event) {
    }

    @FXML
    private void AddEmployee(MouseEvent event) {
    }
    
}
