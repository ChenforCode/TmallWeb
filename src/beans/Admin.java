package beans;

public class Admin {
    public String adminname;
    public String adimpassword;

    public Admin() {
    }

    public Admin(String adminname, String adimpassword) {
        this.adminname = adminname;
        this.adimpassword = adimpassword;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {

        this.adminname = adminname;
    }

    public String getAdimpassword() {

        return adimpassword;
    }

    public void setAdimpassword(String adimpassword) {
        this.adimpassword = adimpassword;
    }
}
