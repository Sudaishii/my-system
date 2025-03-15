/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Employees {
    
    private int id;
    private String fname;
    private String lname;
    private String email;
    private Date hdate;
    private String contact;
    private String dept;
    private String pos;
    
    public Employees (int id, String fname, String lname, String email, Date hdate, String contact, String dept, String pos ) {
        
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.hdate = hdate;
        this.contact = contact;
        this.dept = dept;
        this.pos = pos;
       
    }
    
    public int getId() { return id; }
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getEmail() { return email; }
    public Date getHdate() { return hdate; }
    public String getContact() { return contact; }
    public String getDept() { return dept; }
    public String getPos() { return pos; }
    
}
