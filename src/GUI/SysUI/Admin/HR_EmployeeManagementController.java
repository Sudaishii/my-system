/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class HR_EmployeeManagementController implements Initializable {

    @FXML
    private ComboBox<String> deptcombo1;
    
    private HashMap<String, List<String>> departmentPositions = new HashMap<>();
    
    @FXML
    private ComboBox<String> sexcombo1;
    @FXML
    private ComboBox<String> poscombo1;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         
        departmentPositions.put("Human Resources", Arrays.asList("Recruiter", "HR Manager", "Benefits Specialist"));
        departmentPositions.put("Engineering", Arrays.asList("Software Engineer", "QA Tester", "Project Manager"));
        departmentPositions.put("Marketing", Arrays.asList("Marketing Specialist", "Social Media Manager", "Content Writer"));

        
        ObservableList<String> departments = FXCollections.observableArrayList();
        departments.add("Choose Department"); 
        departments.addAll(departmentPositions.keySet()); 
        deptcombo1.setItems(departments);
        deptcombo1.setValue("Choose Department"); 

       
        ObservableList<String> sexOptions = FXCollections.observableArrayList();
        sexOptions.add("Choose Sex"); 
        sexOptions.addAll(Arrays.asList("Male", "Female")); 
        sexcombo1.setItems(sexOptions);
        sexcombo1.setValue("Choose Sex"); 

        
        ObservableList<String> positionOptions = FXCollections.observableArrayList();
        positionOptions.add("Choose Position"); 
        poscombo1.setItems(positionOptions);
        poscombo1.setValue("Choose Position"); 

       
        deptcombo1.setOnAction(event -> {
            String selectedDepartment = deptcombo1.getValue();
            if (!"Choose Department".equals(selectedDepartment)) { 
                List<String> positions = departmentPositions.getOrDefault(selectedDepartment, Arrays.asList());

               
                ObservableList<String> positionOptionsWithPrompt = FXCollections.observableArrayList();
                positionOptionsWithPrompt.add("Choose Position");
                positionOptionsWithPrompt.addAll(positions);

               
                poscombo1.setItems(positionOptionsWithPrompt);
                poscombo1.setValue("Choose Position"); 
            } else {
             
                ObservableList<String> positionOptionsWithPrompt = FXCollections.observableArrayList();
                positionOptionsWithPrompt.add("Choose Position"); 
                poscombo1.setItems(positionOptionsWithPrompt);
                poscombo1.setValue("Choose Position");
            }
        });
    }
    
        
        
    
}
