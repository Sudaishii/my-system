package GUI.SysUI.Staff;

import GUI.config.dbConnect;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThisIsForPDF {

    private static final Color PRIMARY_COLOR = new DeviceRgb(25, 54, 83);
    private static final Color SECONDARY_COLOR = new DeviceRgb(244, 246, 248);
    private static final Color ACCENT_COLOR = new DeviceRgb(52, 152, 219);
    private static final Color TEXT_COLOR = new DeviceRgb(44, 62, 80);

    public static boolean generateEmployeeReport(int empId, String month, int year) {
        try {
            String userHome = System.getProperty("user.home");
            Path documentsPath = Paths.get(userHome, "Documents");
            String fileName = "Payslip_" + empId + "_" + month + "_" + year + ".pdf";
            String filePath = documentsPath.resolve(fileName).toString();

            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);

            String fontPath = "resources/fonts/NotoSans-Regular.ttf";
            PdfFont font = PdfFontFactory.createFont(fontPath, "Identity-H", true);
            document.setFont(font);

            Table mainTable = new Table(1).useAllAvailableWidth();

            Table headerTable = new Table(new float[]{2, 1}).useAllAvailableWidth();

            Cell companyCell = new Cell();
            companyCell.add(new Paragraph("AMPHOREUS HOTEL").setFontSize(18).setBold().setFontColor(PRIMARY_COLOR))
                    .add(new Paragraph("Employee Payslip").setFontSize(10).setFontColor(TEXT_COLOR));
            companyCell.setBorder(Border.NO_BORDER);
            companyCell.setPadding(10);

            Cell periodCell = new Cell();
            periodCell.add(new Paragraph(month + " " + year).setFontSize(12).setBold())
                    .add(new Paragraph("CONFIDENTIAL").setFontSize(8).setFontColor(ACCENT_COLOR));
            periodCell.setTextAlignment(TextAlignment.RIGHT);
            periodCell.setBorder(Border.NO_BORDER);
            periodCell.setPadding(10);

            headerTable.addCell(companyCell);
            headerTable.addCell(periodCell);
            headerTable.setBackgroundColor(SECONDARY_COLOR);

            mainTable.addCell(new Cell().add(headerTable).setBorder(Border.NO_BORDER));

            try (Connection conn = new dbConnect().getConnection()) {
                String empQuery = "SELECT * FROM employee WHERE emp_id = ?";
                PreparedStatement empStmt = conn.prepareStatement(empQuery);
                empStmt.setInt(1, empId);
                ResultSet empRs = empStmt.executeQuery();

                if (empRs.next()) {
                    Table empInfo = new Table(new float[]{1, 1, 1, 1}).useAllAvailableWidth();
                    empInfo.setMarginTop(20).setMarginBottom(20);

                    String fullName = empRs.getString("emp_fname") + " " +
                            (empRs.getString("emp_middle") != null ? empRs.getString("emp_middle") + " " : "") +
                            empRs.getString("emp_lname");

                    addInfoCell(empInfo, "Employee Name", fullName);
                    addInfoCell(empInfo, "Employee ID", String.valueOf(empId));
                    addInfoCell(empInfo, "Department", empRs.getString("emp_dept"));
                    addInfoCell(empInfo, "Position", empRs.getString("emp_position"));

                    mainTable.addCell(new Cell().add(empInfo).setBorder(Border.NO_BORDER));

                    String reportQuery = "SELECT * FROM reports WHERE emp_id = ? AND month = ? AND year = ?";
                    PreparedStatement reportStmt = conn.prepareStatement(reportQuery);
                    reportStmt.setInt(1, empId);
                    reportStmt.setString(2, month);
                    reportStmt.setInt(3, year);
                    ResultSet reportRs = reportStmt.executeQuery();

                    if (reportRs.next()) {
                        int totalHours = reportRs.getInt("total_hours");
                        int overtimeHours = reportRs.getInt("total_overtime");
                        BigDecimal overtime = reportRs.getBigDecimal("overtime_pay");

                        String rateQuery = "SELECT r.hourly_rate FROM rates r INNER JOIN employee e ON e.rates_id = r.rates_id WHERE e.emp_id = ?";
                        PreparedStatement rateStmt = conn.prepareStatement(rateQuery);
                        rateStmt.setInt(1, empId);
                        ResultSet rateRs = rateStmt.executeQuery();

                        BigDecimal ratePerHour = BigDecimal.ZERO;
                        if (rateRs.next()) {
                            ratePerHour = rateRs.getBigDecimal("hourly_rate");
                        } else {
                            showAlert(AlertType.ERROR, "Error", "Rate not found for employee ID: " + empId);
                            return false;
                        }

                        BigDecimal basic = ratePerHour.multiply(new BigDecimal(totalHours));

                        Table payDetails = new Table(new float[]{2, 1, 1}).useAllAvailableWidth();

                        addHeaderCell(payDetails, "Description");
                        addHeaderCell(payDetails, "Earnings");
                        addHeaderCell(payDetails, "Deductions");

                        addDetailRow(payDetails, "Regular Hours", totalHours + " hrs", "");
                        addDetailRow(payDetails, "Rate per Hour", formatCurrency(ratePerHour), "");
                        addDetailRow(payDetails, "Basic Salary", formatCurrency(basic), "");
                        addDetailRow(payDetails, "Overtime Hours", overtimeHours + " hrs", "");
                        addDetailRow(payDetails, "Overtime Pay", formatCurrency(overtime), "");

                        payDetails.addCell(new Cell(1, 3).add(new Paragraph("")).setBorder(Border.NO_BORDER)); // Spacer

                        addDetailRow(payDetails, "SSS", "", formatCurrency(reportRs.getBigDecimal("sss")));
                        addDetailRow(payDetails, "PhilHealth", "", formatCurrency(reportRs.getBigDecimal("phil_health")));
                        addDetailRow(payDetails, "Pag-IBIG", "", formatCurrency(reportRs.getBigDecimal("pag_ibig")));

                        BigDecimal totalEarnings = basic.add(overtime);
                        BigDecimal totalDeductions = reportRs.getBigDecimal("t_deductions");

                        payDetails.addCell(new Cell().add(new Paragraph("TOTAL").setBold()).setBackgroundColor(SECONDARY_COLOR));
                        payDetails.addCell(new Cell().add(new Paragraph(formatCurrency(totalEarnings)).setBold()).setBackgroundColor(SECONDARY_COLOR));
                        payDetails.addCell(new Cell().add(new Paragraph(formatCurrency(totalDeductions)).setBold()).setBackgroundColor(SECONDARY_COLOR));

                        mainTable.addCell(new Cell().add(payDetails).setBorder(Border.NO_BORDER));

                        // Net Pay
                        Table netPayTable = new Table(new float[]{1, 1}).useAllAvailableWidth();
                        netPayTable.setMarginTop(20);

                        Cell netLabel = new Cell()
                                .add(new Paragraph("NET PAY").setFontSize(14).setBold())
                                .setBackgroundColor(PRIMARY_COLOR)
                                .setFontColor(SECONDARY_COLOR)
                                .setPadding(10);
                        Cell netValue = new Cell()
                                .add(new Paragraph(formatCurrency(reportRs.getBigDecimal("net_pay")))
                                .setFontSize(14).setBold())
                                .setBackgroundColor(SECONDARY_COLOR)
                                .setPadding(10);

                        netPayTable.addCell(netLabel);
                        netPayTable.addCell(netValue);

                        mainTable.addCell(new Cell().add(netPayTable).setBorder(Border.NO_BORDER));

                        // Footer
                        Table footerTable = new Table(2).useAllAvailableWidth();
                        footerTable.setMarginTop(20);

                        Date generatedDate = reportRs.getDate("date_generated");
                        String formattedDate = new SimpleDateFormat("MMMM dd, yyyy").format(generatedDate);

                        footerTable.addCell(new Cell().add(new Paragraph("Date Generated: " + formattedDate)
                                .setFontSize(10).setFontColor(TEXT_COLOR)).setBorder(Border.NO_BORDER));
                        footerTable.addCell(new Cell().add(new Paragraph("This is a computer-generated document. No signature is required.")
                                .setFontSize(8).setFontColor(TEXT_COLOR).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER));

                        mainTable.addCell(new Cell().add(footerTable).setBorder(Border.NO_BORDER));
                    }
                }
                document.add(mainTable);

            } catch (SQLException e) {
                showAlert(AlertType.ERROR, "Database Error", e.getMessage());
                return false;
            }

            document.close();
            showAlert(AlertType.INFORMATION, "Success", "Payslip generated in Documents folder:\n" + fileName);
            return true;

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", e.getMessage());
            return false;
        }
    }

    private static void addHeaderCell(Table table, String text) {
        table.addCell(new Cell().add(new Paragraph(text).setBold())
                .setBackgroundColor(PRIMARY_COLOR)
                .setFontColor(SECONDARY_COLOR)
                .setPadding(8));
    }

    private static void addDetailRow(Table table, String description, String earnings, String deductions) {
        table.addCell(new Cell().add(new Paragraph(description)).setPadding(4));
        table.addCell(new Cell().add(new Paragraph(earnings)).setPadding(4).setTextAlignment(TextAlignment.RIGHT));
        table.addCell(new Cell().add(new Paragraph(deductions)).setPadding(4).setTextAlignment(TextAlignment.RIGHT));
    }

    private static void addInfoCell(Table table, String label, String value) {
        table.addCell(new Cell(1, 2).add(new Paragraph(label + ": " + value).setFontColor(TEXT_COLOR))
                .setBorder(Border.NO_BORDER).setPadding(3));
    }

    private static String formatCurrency(BigDecimal amount) {
        if (amount == null) return "₱0.00";
        return "₱" + String.format("%,.2f", amount.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private static void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
