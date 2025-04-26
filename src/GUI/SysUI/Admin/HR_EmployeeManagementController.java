package GUI.SysUI.Admin;


import GUI.SysUI.SuperAdmin.UserAddController;
import GUI.config.config;
import GUI.config.dbConnect;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    @FXML
    private TextField Tfname;
    @FXML
    private TextField Tmname;
    @FXML
    private TextField Tlname;
    @FXML
    private TextField Tadd;
    @FXML
    private TextField Temail;
    @FXML
    private TextField Tage;
    @FXML
    private TextField Tcontact;
    @FXML
    private DatePicker Tdate;
    @FXML
    private Button submit;
    private String photoFilePath;
    private ObservableList<Employees> empList;
    ObservableList<Employees> searchby = FXCollections.observableArrayList();
    @FXML
    private TableView<Employees> EmployeeView;
    
    
    private dbConnect db = new dbConnect();
    
    @FXML
    private StackedBarChart<String , Number> barChart;
    
    @FXML
    private TableColumn<Employees, String> idC;
    @FXML
    private TableColumn<Employees, String> fnameC;
    @FXML
    private TableColumn<Employees, String> lnameC;
    @FXML
    private TableColumn<Employees, String> emailC;
    @FXML
    private TableColumn<Employees, Date> hdateC;
    @FXML
    private TableColumn<Employees, String> contactC;
    @FXML
    private TableColumn<Employees, String> deptC;
    @FXML
    private TableColumn<Employees, String> postC;
    @FXML
    private Button update;
    @FXML
    private ImageView employeePhoto;
    @FXML
    private TextField FilterField;
   
//    empTransfer trans = new empTransfer

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         
        
        empList = FXCollections.observableArrayList();
        
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));       
        fnameC.setCellValueFactory(new PropertyValueFactory<>("fname"));  
        lnameC.setCellValueFactory(new PropertyValueFactory<>("lname"));   
        emailC.setCellValueFactory(new PropertyValueFactory<>("email")); 
        hdateC.setCellValueFactory(new PropertyValueFactory<>("hdate")); 
        contactC.setCellValueFactory(new PropertyValueFactory<>("contact")); 
        deptC.setCellValueFactory(new PropertyValueFactory<>("dept")); 
        postC.setCellValueFactory(new PropertyValueFactory<>("pos")); 
        
        departmentPositions.put("Human Resources", Arrays.asList("General Manager", "HR Manager", "Finance Manager", "Finance Clerk"));
        departmentPositions.put("Front Office", Arrays.asList("Front Office Manager",  "Receptionist", "Porter", "Reservation Clerk"));
        departmentPositions.put("House Keeping", Arrays.asList("Executive Housekeeper", "Housekeeping Supervisor", "Room Attendant", "Public Area Cleaner"));
        departmentPositions.put("Maintenance", Arrays.asList("Chief Engineer", "Maintenance Supervisor", "Maintenance Technician", "Groundskeeper"));
        departmentPositions.put("IT", Arrays.asList("IT Manager", "IT Support Specialist", "Network Administrator", "System Administrator"));

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
        
        
         EmployeeView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int selectedId = newSelection.getId();
                System.out.println("Selected Employee ID: " + selectedId);
            }
        });
           
        loadDataFromDatabase();
        
        employeePhoto.setImage(new Image(getClass().getResourceAsStream("/GUI/images/Emp/default.png")));
        
        

    }
    
    config conf = new config();
    
    
   @FXML
   private void AddEmployee(MouseEvent event) {
    config conf = new config();

    String fname = Tfname.getText().trim();
    String mname = Tmname.getText().trim();
    String lname = Tlname.getText().trim();
    String address = Tadd.getText().trim();
    String email = Temail.getText().trim();
    String age = Tage.getText().trim();
    String sex = sexcombo1.getValue() != null ? sexcombo1.getValue().trim() : "";
    String dept = deptcombo1.getValue() != null ? deptcombo1.getValue().trim() : "";
    String pos = poscombo1.getValue() != null ? poscombo1.getValue().trim() : "";
    String contact = Tcontact.getText().trim();
    LocalDate date = Tdate.getValue();

    
    if (fname.isEmpty() || lname.isEmpty() || address.isEmpty() || email.isEmpty() ||
        age.isEmpty() || sex.isEmpty() || dept.isEmpty() || pos.isEmpty() || contact.isEmpty() || date == null) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
        return;
    }

    if (!age.matches("\\d+") || Integer.parseInt(age) < 18) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Age must be a valid number and at least 18.");
        return;
    }

    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Invalid email format.");
        return;
    }

    if (!contact.matches("\\d{10,11}")) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Contact number must be 10-11 digits.");
        return;
    }

    if (date.isAfter(LocalDate.now())) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Hiring date cannot be in the future.");
        return;
    }

    if (isDuplicateEmployee(email, contact)) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Employee with this email or contact number already exists.");
        return;
    }

    if (photoFilePath == null || photoFilePath.isEmpty()) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Please select a profile photo.");
        return;
    }

    
    int rateId;
    try (Connection conn = new dbConnect().getConnection()) {
        rateId = getRateIdByPosition(conn, pos);
    } catch (SQLException e) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Database connection error: " + e.getMessage());
        return;
    }

    if (rateId == -1) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Invalid position selected.");
        return;
    }

    String sql = "INSERT INTO employee (emp_fname, emp_middle, emp_lname, emp_age, emp_sex, emp_add, emp_email, emp_contact, emp_hdate, emp_dept, emp_position, filePath, rates_id) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        String destinationPath = "src/GUI/images/Emp/" + new File(photoFilePath).getName();
        Files.copy(Paths.get(photoFilePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);

        if (conf.addRecord(sql, fname, mname, lname, age, sex, address, email, contact, date.toString(), dept, pos, destinationPath, rateId)) {
            conf.showAlert(Alert.AlertType.INFORMATION, "Successful", "Employee added successfully!");
            loadDataFromDatabase();
            clearFields();
        }
    } catch (IOException e) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Failed to save photo.");
    }
}  
    
    
   private int getRateIdByPosition(Connection conn, String position) {
    int rateId = -1; 
    String query = "SELECT rates_id FROM rates WHERE position = ?"; 

    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, position); 
        ResultSet rs = pstmt.executeQuery(); 

        if (rs.next()) {
            rateId = rs.getInt("rates_id"); 
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }

    return rateId; // Return the found rates_id or -1 if not found
}
    
    
    
private void setEmployeePhoto(Image image) {
    double imgWidth = image.getWidth();
    double imgHeight = image.getHeight();
    double viewportSize = Math.min(imgWidth, imgHeight);

    Rectangle2D viewport = new Rectangle2D(
        (imgWidth - viewportSize) / 2,
        (imgHeight - viewportSize) / 2,
        viewportSize,
        viewportSize
    );

    employeePhoto.setImage(image);
    employeePhoto.setViewport(viewport);
    employeePhoto.setFitWidth(178);
    employeePhoto.setFitHeight(166);
    employeePhoto.setPreserveRatio(false);
    employeePhoto.setSmooth(true);
    employeePhoto.setCache(true);
}
    
    
//    @FXML
//    private void AddEmployee(MouseEvent event) {
//    config conf = new config();
//
//    String fname = Tfname.getText().trim();
//    String mname = Tmname.getText().trim();
//    String lname = Tlname.getText().trim();
//    String address = Tadd.getText().trim();
//    String email = Temail.getText().trim();
//    String age = Tage.getText().trim();
//    String sex = sexcombo1.getValue() != null ? sexcombo1.getValue().trim() : "";
//    String dept = deptcombo1.getValue() != null ? deptcombo1.getValue().trim() : "";
//    String pos = poscombo1.getValue() != null ? poscombo1.getValue().trim() : "";
//    String contact = Tcontact.getText().trim();
//    LocalDate date = Tdate.getValue();
//
//    if (fname.isEmpty() || lname.isEmpty() || address.isEmpty() || email.isEmpty() ||
//        age.isEmpty() || sex.isEmpty() || dept.isEmpty() || pos.isEmpty() || contact.isEmpty() || date == null) {
//        conf.showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
//        return;
//    }
//
//    if (!age.matches("\\d+") || Integer.parseInt(age) < 18) {
//        conf.showAlert(Alert.AlertType.ERROR, "Error", "Age must be a valid number and at least 18.");
//        return;
//    }
//
//    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
//        conf.showAlert(Alert.AlertType.ERROR, "Error", "Invalid email format.");
//        return;
//    }
//
//    if (!contact.matches("\\d{10,11}")) {
//        conf.showAlert(Alert.AlertType.ERROR, "Error", "Contact number must be 10-11 digits.");
//        return;
//    }
//
//    if (date.isAfter(LocalDate.now())) {
//        conf.showAlert(Alert.AlertType.ERROR, "Error", "Hiring date cannot be in the future.");
//        return;
//    }
//
//    if (isDuplicateEmployee(email, contact)) {
//        conf.showAlert(Alert.AlertType.ERROR, "Error", "Employee with this email or contact number already exists.");
//        return;
//    }
//
//  
//    if (photoFilePath == null || photoFilePath.isEmpty()) {
//        conf.showAlert(Alert.AlertType.ERROR, "Error", "Please select a profile photo.");
//        return;
//    }
//
//   
//    String destinationPath = "src/GUI/images/Emp/" + new File(photoFilePath).getName();
//    try {
//        Files.copy(Paths.get(photoFilePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
//    } catch (IOException e) {
//        conf.showAlert(Alert.AlertType.ERROR, "Error", "Failed to save photo.");
//        return;
//    }
//
//   
//    String sql = "INSERT INTO employee (emp_fname, emp_middle, emp_lname, emp_age, emp_sex, emp_add, emp_email, emp_contact, emp_hdate, emp_dept, emp_position, filePath) "
//               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//    conf.addRecord(sql, fname, mname, lname, age, sex, address, email, contact, date.toString(), dept, pos, destinationPath);
//    conf.showAlert(Alert.AlertType.INFORMATION, "Successful", "Employee added successfully!");
//    loadDataFromDatabase();
//    clearFields();
//}

    private boolean isDuplicateEmployee(String email, String contact) {
    String query = "SELECT COUNT(*) FROM employee WHERE emp_email = ? OR emp_contact = ?";
    
    try (Connection connect = new dbConnect().getConnection();
         PreparedStatement pst = connect.prepareStatement(query)) {
        
        pst.setString(1, email);
        pst.setString(2, contact);

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;
}
     private void loadDataFromDatabase() {
         if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }
         
         empList.clear();
         
        String query = "SELECT emp_id, emp_fname, emp_lname, emp_email, emp_hdate, emp_contact, emp_dept, emp_position FROM employee";
        try {
            
            System.out.println("Executing Query: " + query);
            ResultSet rs = db.getData(query);
            
            if (rs == null) {
                System.out.println("ResultSet is null");
                return;
            }

            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String fname = rs.getString("emp_fname");
                String lname = rs.getString("emp_lname");
                String email = rs.getString("emp_email");
                Date hdate = rs.getDate("emp_hdate");
                String contact = rs.getString("emp_contact");
                String dept = rs.getString("emp_dept");
                String position = rs.getString("emp_position");
                

                empList.add(new Employees(id, fname, lname, email, hdate, contact, dept, position));
            }
            

            EmployeeView.setItems(empList);
            
            FilteredList<Employees> filteredData = new FilteredList<>(empList, b -> true);

          
            FilterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(employee -> {
                
                    if (newValue == null || newValue.trim().isEmpty()) {
                        return true;
                    }

                  
                    String lowerCaseFilter = newValue.toLowerCase();

                   
                    if (String.valueOf(employee.getId()).contains(lowerCaseFilter) ||  
                    employee.getFullName().toLowerCase().contains(lowerCaseFilter) || 
                    employee.getDept().toLowerCase().contains(lowerCaseFilter) || 
                    employee.getPos().toLowerCase().contains(lowerCaseFilter) || 
                    (employee.getHdate() != null && employee.getHdate().toString().toLowerCase().contains(lowerCaseFilter))) {
                    return true;
                }
                    return false; 
                });
            });
            
            EmployeeView.setItems(filteredData);
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
    }
     
     
     private void clearFields() {
         
            Tfname.clear();
            Tmname.clear();
            Tlname.clear();
            Tadd.clear();
            Temail.clear();
            Tage.clear();
            Tcontact.clear();
            Tdate.setValue(null);

            employeePhoto.setImage(new Image(getClass().getResourceAsStream("/GUI/images/Emp/default.png")));
            sexcombo1.setValue("Choose Sex");
            deptcombo1.setValue("Choose Department");
            poscombo1.setValue("Choose Position");
            employeePhoto.setImage(null);
}

    @FXML
    private void refreshButton(MouseEvent event) {
        loadDataFromDatabase();
    }

    
    @FXML
    private void choosePhoto(MouseEvent event) {
    
    config con = new config();
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Employee Photo");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
    );

    File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

    if (selectedFile != null) {
       
        photoFilePath = selectedFile.getAbsolutePath();

        
        Image image = new Image(new File(photoFilePath).toURI().toString());
        setEmployeePhoto(image);  
    }
    
}


    
//   @FXML
//    private void choosePhoto(MouseEvent event) {
//    FileChooser fileChooser = new FileChooser();
//    fileChooser.setTitle("Choose Employee Photo");
//
//  
//    fileChooser.getExtensionFilters().addAll(
//        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
//    );
//
//    File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
//
//    if (selectedFile != null) {
//        try {
//            String destinationFolder = "src/GUI/images/Emp/";
//            File destDir = new File(destinationFolder);
//
//            if (!destDir.exists()) {
//                destDir.mkdirs();
//            }
//
//            File destinationFile = new File(destDir, selectedFile.getName());
//            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//            Image image = new Image(destinationFile.toURI().toString());
//
//          
//            double imgWidth = image.getWidth();
//            double imgHeight = image.getHeight();
//            double viewportSize = Math.min(imgWidth, imgHeight); 
//
//            Rectangle2D viewport = new Rectangle2D(
//                (imgWidth - viewportSize) / 2, 
//                (imgHeight - viewportSize) / 2, 
//                viewportSize, 
//                viewportSize
//            );
//
//            employeePhoto.setImage(image);
//            employeePhoto.setViewport(viewport);  
//            employeePhoto.setFitWidth(178);       
//            employeePhoto.setFitHeight(166);      
//            employeePhoto.setPreserveRatio(false); 
//            employeePhoto.setSmooth(true);        
//            employeePhoto.setCache(true);
//
//            // Save path for database storage
//            photoFilePath = destinationFile.getAbsolutePath();
//        } catch (IOException e) {
//            new config().showAlert(Alert.AlertType.ERROR, "Error", "Failed to save photo.");
//        }
//    }
//}



    
    public class Employee {
       private String employeeId;

       public String getEmployeeId() {
           return employeeId;
       }
   }
    
    config con = new config();
   
    public void loadPage (String targetFXML) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(targetFXML));
        
    }
   
    @FXML
    private void updateBtn(MouseEvent event) throws IOException {
    
            
            Employees selectedRow = EmployeeView.getSelectionModel().getSelectedItem();
   
            if (selectedRow == null) {
               conf.showAlert(Alert.AlertType.ERROR, "Error Selection!", "You must select an Employee!");
               return; 
            }

            int empId = selectedRow.getId();
  
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SysUI/Admin/EmployeeUpdate.fxml"));
            Parent addUserContent = loader.load(); 

            
            UpdateEmployeeController controller = loader.getController();

            
            Scene scene = update.getScene();
            Pane rootPane = (Pane) scene.getRoot();

            AnchorPane overlayPane = new AnchorPane(addUserContent); 
            overlayPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

      
            overlayPane.prefWidthProperty().bind(scene.widthProperty());
            overlayPane.prefHeightProperty().bind(scene.heightProperty());

            controller.setEmployeeId(empId);
            System.out.println(empId);
            controller.setOverlayPane(overlayPane); 
            controller.setRootPane(rootPane);

   
            rootPane.getChildren().add(overlayPane);

     
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), overlayPane); 
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

            
            overlayPane.setOnMouseClicked(e -> {
                if (e.getTarget() == overlayPane) {
                    FadeTransition fadeOut = new FadeTransition(Duration.millis(300), overlayPane);
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setOnFinished(ev -> rootPane.getChildren().remove(overlayPane));
                    fadeOut.play();
                }
            });
}

        private void closeOverlay(Pane rootPane, Parent updateContent) {
            if (updateContent != null && rootPane != null) {
                FadeTransition fadeOut = new FadeTransition(Duration.millis(300), updateContent);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);

                fadeOut.setOnFinished(ev -> {
                    rootPane.getChildren().remove(updateContent); 
                    rootPane.setMouseTransparent(false); 
                });

                fadeOut.play();
            }
        }

    

     
    







        private Employees getEmployeeById(int employeeId) {
            for (Employees emp : empList) { // Assuming employeeList contains full details
                if (emp.getId() == employeeId) {
                    return emp; 
                }
            }
            return null; 
        }
    
    
    
}