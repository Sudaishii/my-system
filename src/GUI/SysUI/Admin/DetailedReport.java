package GUI.SysUI.Admin;

import java.time.LocalDate;

public class DetailedReport extends Reports {
    private String fname;
    private String lname;
    private String department;
    private String position;

    public DetailedReport(int reportId, int empId, String month, int year, int totalHours, int totalOvertime,
                          double grossSalary, double sss, double philHealth, double pagIbig, double totalDeductions,
                          double overtimePay, double netPay, String status, LocalDate dateGenerated,
                          String fname, String lname, String department, String position) {

        super(reportId, empId, month, year, totalHours, totalOvertime, grossSalary, sss, philHealth,
              pagIbig, totalDeductions, overtimePay, netPay, status, dateGenerated);

        this.fname = fname;
        this.lname = lname;
        this.department = department;
        this.position = position;
    }

    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
}
