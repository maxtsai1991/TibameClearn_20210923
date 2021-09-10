package idv.tfp10207.nowclearnnow0818.market;

import java.io.Serializable;

public class OrderNumber implements Serializable {
    private String address;
    private String orderNumber;
    private String merchTotalMoney;

    public OrderNumber() {
    }

    public OrderNumber(String address, String orderNumber, String merchTotalMoney) {
        this.address = address;
        this.orderNumber = orderNumber;
        this.merchTotalMoney = merchTotalMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getMerchTotalMoney() {
        return merchTotalMoney;
    }

    public void setMerchTotalMoney(String merchTotalMoney) {
        this.merchTotalMoney = merchTotalMoney;
    }
}
