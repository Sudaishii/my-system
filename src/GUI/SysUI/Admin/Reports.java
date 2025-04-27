package GUI.SysUI.Admin;

import java.time.LocalDate;


public class Reports {
    private int reportId;
    private int empId;
    private String month;
    private int year;
    private int totalHours;
    private int totalOvertime;
    private double grossSalary;
    private double sss;
    private double philHealth;
    private double pagIbig;
    private double totalDeductions;
    private double overtimePay;
    private double netPay;
    private String status;
    private LocalDate dateGenerated;


    public Reports(int reportId, int empId, String month, int year, int totalHours, int totalOvertime,
                   double grossSalary, double sss, double philHealth, double pagIbig, double totalDeductions,
                   double overtimePay, double netPay, String status, LocalDate dateGenerated) {
        this.reportId = reportId;
        this.empId = empId;
        this.month = month;
        this.year = year;
        this.totalHours = totalHours;
        this.totalOvertime = totalOvertime;
        this.grossSalary = grossSalary;
        this.sss = sss;
        this.philHealth = philHealth;
        this.pagIbig = pagIbig;
        this.totalDeductions = totalDeductions;
        this.overtimePay = overtimePay;
        this.netPay = netPay;
        this.status = status;
        this.dateGenerated = dateGenerated;
    }


    public int getReportId() { return reportId; }
    public int getEmpId() { return empId; }
    public String getMonth() { return month; }
    public int getYear() { return year; }
    public int getTotalHours() { return totalHours; }
    public int getTotalOvertime() { return totalOvertime; }
    public double getGrossSalary() { return grossSalary; }
    public double getSss() { return sss; }
    public double getPhilHealth() { return philHealth; }
    public double getPagIbig() { return pagIbig; }
    public double getTotalDeductions() { return totalDeductions; }
    public double getOvertimePay() { return overtimePay; }
    public double getNetPay() { return netPay; }
    public String getStatus() { return status; }
    public LocalDate getDateGenerated() { return dateGenerated; }
}
