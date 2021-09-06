package idv.tfp10207.nowclearnnow0818.market;

import java.util.List;

public class OrderNumber {
    //private List<ShoppingCarMerch> orderMerch;
    private String address;
    private String orderNumber;

    public OrderNumber() {
    }

    public OrderNumber(String address, String orderNumber) {
        this.address = address;
        this.orderNumber = orderNumber;
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
}
