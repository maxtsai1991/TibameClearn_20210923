package idv.tfp10207.nowclearnnow0818.market;

import java.io.Serializable;

public class OrderUpdateMerchNumber implements Serializable {

    private int drawableID;
    private int buyMerchNumber;
//    private int merchNumber;
//    private int merchPrice;
//    private int merchMoney;

    public OrderUpdateMerchNumber() {
    }

    public OrderUpdateMerchNumber(int drawableID, int buyMerchNumber/*, int merchNumber, int merchPrice, int merchMoney*/) {
        this.drawableID = drawableID;
        this.buyMerchNumber = buyMerchNumber;
//        this.merchNumber = merchNumber;
//        this.merchPrice = merchPrice;
//        this.merchMoney = merchMoney;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }

    public int getBuyMerchNumber() {
        return buyMerchNumber;
    }

    public void setBuyMerchNumber(int buyMerchNumber) {
        this.buyMerchNumber = buyMerchNumber;
    }

//    public int getMerchNumber() {
//        return merchNumber;
//    }
//
//    public void setMerchNumber(int merchNumber) {
//        this.merchNumber = merchNumber;
//    }
//
//    public int getMerchPrice() {
//        return merchPrice;
//    }
//
//    public void setMerchPrice(int merchPrice) {
//        this.merchPrice = merchPrice;
//    }
//
//    public int getMerchMoney() {
//        return merchMoney;
//    }
//
//    public void setMerchMoney(int merchMoney) {
//        this.merchMoney = merchMoney;
//    }
}
