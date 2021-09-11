package idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve;

import java.io.Serializable;

public class Cpreserveorder implements Serializable {

    // 訂單編號
    public String cpordernumber;

    //    CP01_預訂服務日期
    private   String onedate;
    private   String manydate;
    //    CP01_性別
    private   String gender;

    //    CP01_選擇家事者  抓ID
    private   String cparea;
    private   String cpservice;
    public  int cpservicemoney;

    //    CP02_預估清潔人數
    private   int peoplenumber;
    private   int ping;

    //    CP02_選擇清潔時間
    private   String time;

    //    CP02_備註
    private   String remark;
    //    CP02_服務對象資訊
    private   String servicename;
    private   String servicephone;
    private   String serviceemail;
    private String serviceaddress;
    //    CP02_付款金額
    private   String cppaymoney;
    //    CP03_付款方式
    private   String cppay;
    //    CP03_付款人資訊
    private   String payname;
    private   String payphone;
    private   String payemail;
    private   String payaddress;

    // 訂單狀態
    public  String cporderstate;

    //    CP02_上傳照片
    private  String picture;

    public  Cpreserveorder(){
    }

    public Cpreserveorder(String cpordernumber, String onedate, String manydate, String manydate2, String manydate3, String gender, String cparea,
                          String cpservice, int cpservicemoney, int peoplenumber, int ping, String time, String remark, String servicename, String servicephone, String serviceemail, String serviceaddress,
                          String cppaymoney, String cppay, String payname, String payphone, String payemail, String payaddress, String cporderstate) {

        this(cpordernumber, onedate, manydate, manydate2, manydate3, gender, cparea, cpservice, cpservicemoney, peoplenumber, ping,
                time, remark, servicename, servicephone, serviceemail, serviceaddress, cppaymoney, cppay, payname, payphone, payemail, payemail, payaddress, cporderstate,  null);
    }

    public Cpreserveorder(String cpordernumber1, String onedate1, String cpordernumber, String onedate, String manydate, String gender, String cparea, String cpservice, int cpservicemoney, int peoplenumber, int ping, String s, String time, String remark, String servicename, String servicephone, String serviceemail, String serviceaddress, String cppaymoney, String cppay, String payname, String payphone, String payemail, String payaddress, String cporderstate, String picture) {
        this.cpordernumber = cpordernumber;
        this.onedate = onedate;
        this.manydate = manydate;
        this.gender = gender;
        this.cparea = cparea;
        this.cpservice = cpservice;
        this.cpservicemoney = cpservicemoney;
        this.peoplenumber = peoplenumber;
        this.ping = ping;
        this.time = time;
        this.remark = remark;
        this.servicename = servicename;
        this.servicephone = servicephone;
        this.serviceemail = serviceemail;
        this.serviceaddress = serviceaddress;
        this.cppaymoney = cppaymoney;
        this.cppay = cppay;
        this.payname = payname;
        this.payphone = payphone;
        this.payemail = payemail;
        this.payaddress = payaddress;
        this.cporderstate = cporderstate;
        this.picture = picture;
    }


    public String getCpordernumber() {
        return cpordernumber;
    }

    public void setCpordernumber(String cpordernumber) {
        this.cpordernumber = cpordernumber;
    }

    public String getOnedate() {
        return onedate;
    }

    public void setOnedate(String onedate) {
        this.onedate = onedate;
    }

    public String getManydate() {
        return manydate;
    }

    public void setManydate(String manydate) {
        this.manydate = manydate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCparea() {
        return cparea;
    }

    public void setCparea(String cparea) {
        this.cparea = cparea;
    }

    public String getCpservice() {
        return cpservice;
    }

    public void setCpservice(String cpservice) {
        this.cpservice = cpservice;
    }

    public int getCpservicemoney() {
        return cpservicemoney;
    }

    public void setCpservicemoney(int cpservicemoney) {
        this.cpservicemoney = cpservicemoney;
    }

    public int getPeoplenumber() {
        return peoplenumber;
    }

    public void setPeoplenumber(int peoplenumber) {
        this.peoplenumber = peoplenumber;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServicephone() {
        return servicephone;
    }

    public void setServicephone(String servicephone) {
        this.servicephone = servicephone;
    }

    public String getServiceemail() {
        return serviceemail;
    }

    public void setServiceemail(String serviceemail) {
        this.serviceemail = serviceemail;
    }

    public String getServiceaddress() {
        return serviceaddress;
    }

    public void setServiceaddress(String serviceaddress) {
        this.serviceaddress = serviceaddress;
    }

    public String getCppaymoney() {
        return cppaymoney;
    }

    public void setCppaymoney(String cppaymoney) {
        this.cppaymoney = cppaymoney;
    }

    public String getCppay() {
        return cppay;
    }

    public void setCppay(String cppay) {
        this.cppay = cppay;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public String getPayphone() {
        return payphone;
    }

    public void setPayphone(String payphone) {
        this.payphone = payphone;
    }

    public String getPayemail() {
        return payemail;
    }

    public void setPayemail(String payemail) {
        this.payemail = payemail;
    }

    public String getPayaddress() {
        return payaddress;
    }

    public void setPayaddress(String payaddress) {
        this.payaddress = payaddress;
    }

    public String getCporderstate() {
        return cporderstate;
    }

    public void setCporderstate(String cporderstate) {
        this.cporderstate = cporderstate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }








}
