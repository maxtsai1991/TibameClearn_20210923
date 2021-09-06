package idv.tfp10207.nowclearnnow0818.member.bean;



public class User {
    private String id;
    private String name;
    private String mail;
    private String phone;
    private String address;
    private String imagePath;
    private String gender;


    public User() {

    }

    public User(String id, String name, String mail, String phone, String address, String gender) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.imagePath = imagePath;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
