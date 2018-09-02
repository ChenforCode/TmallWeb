package beans;

public class User {
    private String username;
    private String password;
    private String telphone;
    private String address;
    private String head;


    public User(String username, String password, String telphone, String address, String head) {
        this.username = username;
        this.password = password;
        this.telphone = telphone;
        this.address = address;
        this.head = head;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
