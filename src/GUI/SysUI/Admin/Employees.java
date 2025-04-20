
package GUI.SysUI.Admin;

import java.util.Date;
import javafx.scene.control.CheckBox;


public class Employees {
    

    private int id;
    private String fname;
    private String lname;
    private String email;
    private Date hdate;
    private String contact;
    private String dept;
    private String pos;
    private CheckBox Box;
    
    public Employees (int id, String fname, String lname, String email, Date hdate, String contact, String dept, String pos) {
        
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.hdate = hdate;
        this.contact = contact;
        this.dept = dept;
        this.pos = pos;
        this.Box = new CheckBox();
        
       
    }
    
    public int getId() { return id; }
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getEmail() { return email; }
    public Date getHdate() { return hdate; }
    public String getContact() { return contact; }
    public String getDept() { return dept; }
    public String getPos() { return pos; }
    public String getFullName() {
    return fname + " " + lname;
    }
    public CheckBox getBox(){
        return Box;
    }
    
    public void setSelect (CheckBox Box){
        this.Box = Box;
    }
    
    
    
}
