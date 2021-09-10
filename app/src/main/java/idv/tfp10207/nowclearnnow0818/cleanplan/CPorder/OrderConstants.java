package idv.tfp10207.nowclearnnow0818.cleanplan.CPorder;

import java.io.Serializable;

public class OrderConstants implements Serializable {

    // 訂單編號
    public static String cpordernumber;
    //    CP01_預訂服務日期
    public static String onedate;
    public static String manydate;
    public static String manydate2;

    public static String manydate3;
    //    CP01_性別
    public static String gender;

    //    CP01_選擇家事者  抓ID
    public static String cparea;
    public static String cpservice;

    //    CP02_預估清潔人數
    public static String peoplenumber;
    public static String ping;

    //    CP02_選擇清潔時間
    public static String time;

    //    CP02_備註
    public static String remark;
    //    CP02_服務對象資訊
    public static String servicename;
    public static String servicephone;
    public static String serviceemail;
    public static String serviceaddress;
    //    CP02_付款金額
    public static String cppaymoney;
    //    CP03_付款方式
    public static String cppay;
    //    CP03_付款人資訊
    public static String payname;
    public static String payphone;
    public static String payemail;
    public static String payaddress;

    // 訂單狀態
    public static String cporderstate;

    //    CP02_上傳照片
    public static String picture;

    public OrderConstants() {
    }

    public OrderConstants(String cpordernumber, String onedate, String manydate, String manydate2, String manydate3, String gender, String cparea,
                          String cpservice, String peoplenumber, String ping, String time,String remark, String servicename, String servicephone, String serviceemail, String serviceaddress,
                          String cppaymoney, String cppay, String payname, String payphone, String payemail, String payaddress, String cporderstate,String picture) {

        this(cpordernumber, onedate, manydate, manydate2, manydate3, gender, cparea, cpservice, peoplenumber, ping, time, remark, servicename, servicephone, serviceemail,
                serviceaddress, cppaymoney, cppay, payname, payphone, payemail, payemail, payaddress, cporderstate,null);
    }

    public OrderConstants(String cpordernumber, String onedate, String manydate, String manydate2, String manydate3, String gender, String cparea, String cpservice, String peoplenumber, String ping, String time, String remark, String servicename, String servicephone, String serviceemail, String serviceaddress, String cppaymoney, String cppay, String payname, String payphone, String payemail, String payemail1, String payaddress, String cporderstate, String picture) {
        OrderConstants.cpordernumber = cpordernumber;
        OrderConstants.onedate = onedate;
        OrderConstants.manydate = manydate;
        OrderConstants.manydate2 = manydate2;
        OrderConstants.manydate3 = manydate3;
        OrderConstants.gender = gender;
        OrderConstants.cparea = cparea;
        OrderConstants.cpservice = cpservice;
        OrderConstants.peoplenumber = peoplenumber;
        OrderConstants.ping = ping;
        OrderConstants.time = time;
        OrderConstants.picture = picture;
        OrderConstants.remark = remark;
        OrderConstants.servicename = servicename;
        OrderConstants.servicephone = servicephone;
        OrderConstants.serviceemail = serviceemail;
        OrderConstants.serviceaddress = serviceaddress;
        OrderConstants.cppay = cppay;
        OrderConstants.payname = payname;
        OrderConstants.payphone = payphone;
        OrderConstants.payemail = payemail;
        OrderConstants.payaddress = payaddress;
        OrderConstants.cporderstate = cporderstate;
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

    public String getManydate2() {
        return manydate2;
    }

    public void setManydate2(String manydate2) {
        this.manydate2 = manydate2;
    }

    public String getManydate3() {
        return manydate3;
    }

    public void setManydate3(String manydate3) {
        this.manydate3 = manydate3;
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

    public String getPeoplenumber() {
        return peoplenumber;
    }

    public void setPeoplenumber(String peoplenumber) {
        this.peoplenumber = peoplenumber;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public static String getCppaymoney() {
        return cppaymoney;
    }

    public static void setCppaymoney(String cppaymoney) {
        OrderConstants.cppaymoney = cppaymoney;
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

}

