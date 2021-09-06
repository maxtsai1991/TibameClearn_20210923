package idv.tfp10207.nowclearnnow0818.market;

public class Member_05 {
    private String memberId;
    private String memberName;
    private String phone;
    private String email;
    private String password;
    private String address;
    private int hworkAuthority;
    private int gender;

    public Member_05() {
    }

    public Member_05(String memberId, String memberName, String phone, String email, String password, String address, int hworkAuthority, int gender) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.hworkAuthority = hworkAuthority;
        this.gender = gender;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHworkAuthority() {
        return hworkAuthority;
    }

    public void setHworkAuthority(int hworkAuthority) {
        this.hworkAuthority = hworkAuthority;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
