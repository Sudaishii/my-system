/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import java.util.Date;

public class DTRModel {

    private int recordId;
    private int empId;
    private Date entryDate;
    private String time_in;
    private String time_out;
    private String month;
    private String hours_worked;
    private String overtime_hours;
    private String absent;

    public DTRModel(int recordId, int empId, Date entryDate, String time_in, String time_out, String month, String hours_worked, String overtime_hours, String absent){

        this.recordId = recordId;
        this.empId = empId;
        this.entryDate = entryDate;
        this.time_in = time_in;
        this.time_out = time_out;
        this.month = month;
        this.hours_worked = hours_worked;
        this.overtime_hours  = overtime_hours;
        this.absent = absent;

    }
    public int getRecordId() { return recordId; }
    public int getEmpId() { return empId; }
    public Date getEntryDate() { return entryDate; }
    public String getTimeIn() { return time_in; }
    public String getTimeOut() { return time_out; }
    public String getMonth() { return month; }
    public String getHoursWorked() { return hours_worked; }
    public String getOvertimeHours() { return overtime_hours; }
    public String getAbsent() { return absent; }
}