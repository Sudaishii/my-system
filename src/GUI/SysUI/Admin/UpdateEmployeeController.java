package GUI.SysUI.Admin;

import GUI.config.config;
import GUI.config.dbConnect;
import java.io.File;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class UpdateEmployeeController implements Initializable {

    private HashMap<String, List<String>> departmentPositions = new HashMap<>();
    @FXML private AnchorPane overlayPane;
    @FXML private Pane rootPane;
    @FXML private TextField fname;
    @FXML private TextField addF;
    @FXML private TextField emailF;
    @FXML private TextField contactF;
    @FXML private ComboBox<String> deptF;
    @FXML private ComboBox<String> posF;
    @FXML private Button submit;
    @FXML private Label idLabel;
    private String currentAdd;
    private String currentEmail;
    private String currentContact;
    private String currentDept;
    private String currentPos;
    private File selectedImageFile;
    private String currentPhotoPath;
    private boolean imageChanged = false;

    private int employeeId;
    config con = new config();
    dbConnect db = new dbConnect();
    @FXML
    private ImageView employeePhoto;
    @FXML
    private ImageView cloudIcon;
    private String photoFilePath;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (overlayPane != null) {
            overlayPane.setOnMouseClicked(e -> {
                if (e.getPickResult().getIntersectedNode() == overlayPane) {
                    closeOverlay();
                }
            });
        }

        departmentPositions.put("Human Resources", Arrays.asList("General Manager", "HR Manager", "Finance Manager", "Finance Clerk"));
        departmentPositions.put("Front Office", Arrays.asList("Front Office Manager", "Receptionist", "Porter", "Reservation Clerk"));
        departmentPositions.put("House Keeping", Arrays.asList("Executive Housekeeper", "Housekeeping Supervisor", "Room Attendant", "Public Area Cleaner"));
        departmentPositions.put("Maintenance", Arrays.asList("Chief Engineer", "Maintenance Supervisor", "Maintenance Technician", "Groundskeeper"));
        departmentPositions.put("IT", Arrays.asList("IT Manager", "IT Support Specialist", "Network Administrator", "System Administrator"));

        ObservableList<String> departments = FXCollections.observableArrayList("Choose Department");
        departments.addAll(departmentPositions.keySet());
        deptF.setItems(departments);
        deptF.setValue("Choose Department");

        ObservableList<String> positionOptions = FXCollections.observableArrayList("Choose Position");
        posF.setItems(positionOptions);
        posF.setValue("Choose Position");

        deptF.setOnAction(event -> {
        String selectedDepartment = deptF.getValue();
        ObservableList<String> positionOptionsWithPrompt = FXCollections.observableArrayList("Choose Position");

            if (!"Choose Department".equals(selectedDepartment)) {
                positionOptionsWithPrompt.addAll(departmentPositions.getOrDefault(selectedDepartment, Collections.emptyList()));
            }

            posF.setItems(positionOptionsWithPrompt);
            posF.setValue("Choose Position");
        });
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

    public void setEmployeeId(int employeeId) {

        this.employeeId = employeeId;
        System.out.println("Employee ID received in UpdateEmployeeController: " + employeeId);
        loadEmployeeData();
        idLabel.setText("Employee: " + this.employeeId);

    }

    private void loadEmployeeData() {
        if (employeeId > 0) {
            try (Connection connection = db.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "SELECT emp_fname, emp_lname, emp_add, emp_email, emp_contact, emp_dept, emp_position, filePath " +
                         "FROM employee WHERE emp_id = ?")) {

            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                fname.setText(String.format("%s %s",
                        Optional.ofNullable(resultSet.getString("emp_fname")).orElse(""),
                        Optional.ofNullable(resultSet.getString("emp_lname")).orElse("")
                ).trim());

                currentAdd = resultSet.getString("emp_add");
                currentEmail = resultSet.getString("emp_email");
                currentContact = resultSet.getString("emp_contact");
                currentDept = resultSet.getString("emp_dept");
                currentPos = resultSet.getString("emp_position");
                currentPhotoPath = resultSet.getString("filePath");

                addF.setText(currentAdd);
                emailF.setText(currentEmail);
                contactF.setText(currentContact);
                deptF.setValue(currentDept);
                posF.setValue(currentPos);

                if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                    File imageFile = new File(currentPhotoPath);
                    if (imageFile.exists()) {
                        try {
                            Image image = new Image(imageFile.toURI().toString());
                            setEmployeePhoto(image);

                        } catch (Exception e) {
                            System.err.println("Error loading image from path: " + currentPhotoPath);
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Image file not found at path: " + currentPhotoPath);
                        employeePhoto.setImage(new Image(getClass().getResourceAsStream("/GUI/images/Emp/default.png")));
                    }
                } else {
                    employeePhoto.setImage(new Image(getClass().getResourceAsStream("/GUI/images/Emp/default.png")));
                }
                imageChanged = false; // Reset the flag when loading data

            } else {
                con.showAlert(Alert.AlertType.ERROR, "Error", "Employee with ID " + employeeId + " not found.");
                closeOverlay();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            con.showAlert(Alert.AlertType.ERROR, "Database Error", "Error fetching employee data.");
            closeOverlay();
        }
    }
    }

    @FXML
    private void closeBtn(MouseEvent event) {
        closeOverlay();
    }

    @FXML
    private void AddEmployee(MouseEvent event) {

    String newAdd = addF.getText().trim();
    String newEmail = emailF.getText().trim();
    String newContact = contactF.getText().trim();
    String newDept = deptF.getValue();
    String newPos = posF.getValue();

    if (newAdd.isEmpty() || newEmail.isEmpty() || newContact.isEmpty() ||
        "Choose Department".equals(newDept) || "Choose Position".equals(newPos)) {
        con.showAlert(Alert.AlertType.WARNING, "Warning", "Please fill out all fields.");
        return;
    }

    if (!imageChanged && newAdd.equals(currentAdd) && newEmail.equals(currentEmail) &&
        newContact.equals(currentContact) && newDept.equals(currentDept) &&
        newPos.equals(currentPos)) {
        con.showAlert(Alert.AlertType.INFORMATION, "Information", "No changes detected.");
        return;
    }

    if (!newEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        con.showAlert(Alert.AlertType.ERROR, "Error", "Invalid email format.");
        return;
    }

    if (!newContact.matches("\\d{10,11}")) {
        con.showAlert(Alert.AlertType.ERROR, "Error", "Contact number must be 10-11 digits.");
        return;
    }

    String sql = "UPDATE employee SET emp_add = ?, emp_email = ?, emp_contact = ?, " +
                 "emp_dept = ?, emp_position = ?";
    if (imageChanged && photoFilePath != null) {
        sql += ", filePath = ?";
    }
    sql += " WHERE emp_id = ?";

    try (Connection connection = db.getConnection();
         PreparedStatement updateStmt = connection.prepareStatement(sql)) {

        updateStmt.setString(1, newAdd);
        updateStmt.setString(2, newEmail);
        updateStmt.setString(3, newContact);
        updateStmt.setString(4, newDept);
        updateStmt.setString(5, newPos);

        int parameterIndex = 6;
        if (imageChanged && photoFilePath != null) {
            updateStmt.setString(parameterIndex++, photoFilePath);
        }
        updateStmt.setInt(parameterIndex, employeeId);

        int rowsAffected = updateStmt.executeUpdate();
        if (rowsAffected > 0) {
            con.showAlert(Alert.AlertType.INFORMATION, "Success", "Employee details updated successfully.");
            closeOverlay();
        } else {
            con.showAlert(Alert.AlertType.ERROR, "Error", "Failed to update employee details.");
        }
        imageChanged = false; // Reset the flag
    } catch (SQLException e) {
        e.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Database Error", "Error updating employee data.");
    }
    }

    @FXML
    private void updateImage(MouseEvent event) {
        config con = new config();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Employee Photo");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {

            photoFilePath = selectedFile.getAbsolutePath();
            imageChanged = true;

            Image image = new Image(new File(photoFilePath).toURI().toString());
            setEmployeePhoto(image);
        }
    }

    @FXML
    private void setDefaultImage(MouseEvent event) {
        employeePhoto.setImage(new Image(getClass().getResourceAsStream("/GUI/images/Emp/default.png")));
            photoFilePath = null;
        imageChanged = true;
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
        employeePhoto.setFitWidth(207);
        employeePhoto.setFitHeight(166);
        employeePhoto.setPreserveRatio(false);
        employeePhoto.setSmooth(true);
        employeePhoto.setCache(true);
    }

}