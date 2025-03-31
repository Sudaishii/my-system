/*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package GUI.SysUI.Admin;

 import GUI.config.config;
 import GUI.config.dbConnect;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.IOException;
 import java.net.URL;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.text.SimpleDateFormat;
import java.util.Date;
 import java.util.ResourceBundle;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import javafx.animation.FadeTransition;
 import javafx.animation.KeyFrame;
 import javafx.animation.Timeline;
 import javafx.application.Platform;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.Initializable;
 import javafx.scene.control.Button;
 import javafx.scene.control.Label;
 import javafx.scene.control.ProgressBar;
 import javafx.scene.image.ImageView;
 import javafx.scene.input.MouseEvent;
 import javafx.scene.layout.AnchorPane;
 import javafx.scene.layout.Pane;
 import javafx.stage.FileChooser;
 import javafx.util.Duration;
 import javafx.animation.KeyValue;
 import javafx.scene.control.Alert;

 /**
  * FXML Controller class
  *
  * @author Administrator
  */
 public class ImportCSVController implements Initializable {

     @FXML
     private Pane rootPane;
     private config con;
     private File selectedFile;
     private FileChooser fileChooser;
     @FXML
     private Button browseFileButton11;
     @FXML
     private AnchorPane overlayPane;
     @FXML
     private Label progressPercentage;
     @FXML
     private Label TimeImporting;
     @FXML
     private Label fileName;
     @FXML
     private ProgressBar bar;
     @FXML
     private ImageView cancelBtn;
     private dbConnect db = new dbConnect();
     private ExecutorService executor = Executors.newSingleThreadExecutor();
     private int totalRecordsToProcess = 0;
     private int recordsProcessed = 0;
     @FXML
     private Label summary;
     @FXML
     private Label totalRecords;
     @FXML
     private Label SuccessImports;
     @FXML
     private Label DuplicateRecords;
     @FXML
     private Label skipped;
    @FXML
    private Pane SummaryCont;

     @Override
     public void initialize(URL url, ResourceBundle rb) {
         if (overlayPane != null) {
             overlayPane.setOnMouseClicked(e -> {
                 if (e.getPickResult().getIntersectedNode() == overlayPane) {
                     closeOverlay();
                 }
             });
         }
         con = new config();
         SummaryCont.setVisible(false);
         summary.setVisible(false);
         totalRecords.setVisible(false);
         SuccessImports.setVisible(false);
         DuplicateRecords.setVisible(false);
         skipped.setVisible(false);
         bar.setVisible(false);
         fileName.setVisible(false);
         progressPercentage.setVisible(false);
         TimeImporting.setVisible(false);
         cancelBtn.setVisible(false);

         fileChooser = new FileChooser();
         fileChooser.getExtensionFilters().add(
                 new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv")
         );

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

     @FXML
     private void closeBtn(MouseEvent event) {
         closeOverlay();
     }

     @FXML
     private void chooseCSV(MouseEvent event) {

         selectedFile = fileChooser.showOpenDialog(null);

         if (selectedFile != null) {
             fileName.setText(selectedFile.getName());
             fileName.setVisible(true);
             cancelBtn.setVisible(true);

             bar.setVisible(false);
             progressPercentage.setVisible(false);
             TimeImporting.setVisible(false);
         }

     }
     
         

     private String formatDate(String csvDate) throws Exception {
         SimpleDateFormat csvFormat = new SimpleDateFormat("MM/dd/yyyy");
         SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
         return dbFormat.format(csvFormat.parse(csvDate));
     }

     private void importDataFromCSV(File selectedFile) {

     String selectQuery = "SELECT COUNT(*) FROM dailytimerecords WHERE employee_id = ? AND entry_date = ?";
     String insertQuery = "INSERT INTO dailytimerecords (employee_id, entry_date, time_in, time_out, month, hours_worked, overtime_hrs, absent) "
             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

     int successfulImports = 0;
     int duplicateRecords = 0;
     int invalidRecords = 0;
     recordsProcessed = 0;
     final int batchSize = 1; 
     int recordsInBatch = 0;

     try (Connection conn = db.getConnection()) {
         conn.setAutoCommit(false);
         try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
              PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
              BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {

             String line;
             br.readLine(); // Skip header row (if it exists)

             while ((line = br.readLine()) != null) {
                 recordsProcessed++;
                 String[] record = line.split(",");

                 if (record.length != 8) {
                     invalidRecords++;
                     continue;
                 }

                 try {
                     int empId = Integer.parseInt(record[0].trim());
                     String entryDate = formatDate(record[1].trim());
                     String timeIn = record[2].trim();
                     String timeOut = record[3].trim();
                     String month = record[4].trim();
                     double hoursWorked = Double.parseDouble(record[5].trim());
                     int overtimeHrs = Integer.parseInt(record[6].trim());
                     String absent = record[7].trim();

                     selectStmt.setInt(1, empId);
                     selectStmt.setString(2, entryDate);

                     try (ResultSet rs = selectStmt.executeQuery()) {
                         rs.next();
                         if (rs.getInt(1) > 0) {
                             duplicateRecords++;
                             continue;
                         }
                     }

                     insertStmt.setInt(1, empId);
                     insertStmt.setString(2, entryDate);
                     insertStmt.setString(3, timeIn);
                     insertStmt.setString(4, timeOut);
                     insertStmt.setString(5, month);
                     insertStmt.setDouble(6, hoursWorked);
                     insertStmt.setInt(7, overtimeHrs);
                     insertStmt.setString(8, absent);

                     insertStmt.executeUpdate();
                     successfulImports++;

                     recordsInBatch++;
                     if (recordsInBatch >= batchSize || recordsProcessed == totalRecordsToProcess) {
                         final double currentProgress = (double) recordsProcessed / totalRecordsToProcess;
                         Platform.runLater(() -> {
                             bar.setProgress(currentProgress);
                             progressPercentage.setText((int) (currentProgress * 100) + "%");
                         });
                         recordsInBatch = 0;
                     }

                 } catch (NumberFormatException e) {
                     invalidRecords++;
                     System.err.println("Invalid data format in record: " + line + " - " + e.getMessage());
                 } catch (Exception e) {
                     invalidRecords++;
                     System.err.println("Error processing record: " + line + " - " + e.getMessage());
                 }
             }

             conn.commit();
             final int finalSuccessfulImports = successfulImports;
             final int finalDuplicateRecords = duplicateRecords;
             final int finalInvalidRecords = invalidRecords;
             Platform.runLater(() -> {
                 summary.setText("Summary of file: " + selectedFile.getName());
                 totalRecords.setText("Total records processed: " + totalRecordsToProcess);
                 SuccessImports.setText("Successful imports: " + finalSuccessfulImports);
                 DuplicateRecords.setText("Duplicate records skipped: " + finalDuplicateRecords);
                 skipped.setText("Invalid records skipped: " + finalInvalidRecords);
                 summary.setVisible(true);
                 totalRecords.setVisible(true);
                 SuccessImports.setVisible(true);
                 DuplicateRecords.setVisible(true);
                 skipped.setVisible(true);
              
                 SummaryCont.setVisible(true);
                 FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), SummaryCont);
                 fadeIn.setFromValue(0.0);
                 fadeIn.setToValue(1.0);
                 fadeIn.play();
                 System.out.println("\nData import completed successfully!");
                 System.out.println("Summary for file: " + selectedFile.getName());
                 System.out.println("\tTotal records processed: " + totalRecordsToProcess);
                 System.out.println("\tSuccessful imports: " + finalSuccessfulImports);
                 System.out.println("\tDuplicate records skipped: " + finalDuplicateRecords);
                 System.out.println("\tInvalid records skipped: " + finalInvalidRecords);
                 progressPercentage.setText("100%");
                 bar.setProgress(1.0);
                 TimeImporting.setVisible(true);
                 TimeImporting.setText("Completed");
             });

         } catch (IOException e) {
             conn.rollback();
             Platform.runLater(() -> {
                 System.err.println("Error reading CSV file: " + selectedFile.getAbsolutePath() + " - " + e.getMessage());
             });
         } catch (SQLException e) {
             conn.rollback();
             Platform.runLater(() -> {
                 System.err.println("Error during database operations (transaction rolled back): " + e.getMessage());
             });
         }
     } catch (SQLException e) {
         Platform.runLater(() -> {
             System.err.println("Database connection error: " + e.getMessage());
         });
     }
 }

     @FXML
     private void browseFileButtonAction(MouseEvent event) {
         selectedFile = fileChooser.showOpenDialog(null);


         if (selectedFile != null) {
             fileName.setText(selectedFile.getName());
             fileName.setVisible(true);
             cancelBtn.setVisible(true);
             bar.setVisible(true);
             progressPercentage.setVisible(true);
             TimeImporting.setVisible(true);
             progressPercentage.setText("0%");
             bar.setProgress(0.0);
         }

     }

     private int countTotalRecords(File file) {
         int count = 0;
         try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
             reader.readLine(); // Skip header
             while (reader.readLine() != null) {
                 count++;
             }
         } catch (IOException e) {
             System.err.println("Error counting records: " + e.getMessage());
         }
         return count;
     }

     @FXML
     private void importCSVAction(ActionEvent event) {

         if (selectedFile == null) {
             con.showAlert(Alert.AlertType.ERROR, "Error", "Select a CSV File First!");
         }

         if (selectedFile != null) {
             bar.setVisible(true);
             progressPercentage.setVisible(true);
             TimeImporting.setVisible(true);
             progressPercentage.setText("0%");
             bar.setProgress(0.0);

             summary.setVisible(false);
             totalRecords.setVisible(false);
             SuccessImports.setVisible(false);
             DuplicateRecords.setVisible(false);
             skipped.setVisible(false);

             totalRecordsToProcess = countTotalRecords(selectedFile);
             executor.submit(() -> importDataFromCSV(selectedFile));
         } else {

         }
     }

     private void cancelImport(MouseEvent event) {
         executor.shutdownNow();
         bar.setVisible(false);
         progressPercentage.setVisible(false);
         TimeImporting.setVisible(false);
         executor = Executors.newSingleThreadExecutor(); // Create a new executor
     }

     @FXML
     private void cancel(MouseEvent event) {
          SummaryCont.setVisible(false);
         summary.setVisible(false);
         totalRecords.setVisible(false);
         SuccessImports.setVisible(false);
         DuplicateRecords.setVisible(false);
         skipped.setVisible(false);
         bar.setVisible(false);
         fileName.setVisible(false);
         progressPercentage.setVisible(false);
         TimeImporting.setVisible(false);
         cancelBtn.setVisible(false);
         selectedFile = null;
     }

 }