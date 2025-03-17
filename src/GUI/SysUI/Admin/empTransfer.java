
package GUI.SysUI.Admin;

public class empTransfer {
    private String emp_add; 
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String contact;
    private String dept;
    private String pos;


 public empTransfer(int id, String fname, String lname, String emp_add, String email, String contact, String dept, String pos) {

        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.emp_add = emp_add;
        this.email = email;
        this.contact = contact;
        this.dept = dept;
        this.pos = pos;

    }
 
    public int getId() { return id; }
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
    public String getDept() { return dept; }
    public String getPos() { return pos; }
    public String getEmpAdd() { return emp_add;}

}