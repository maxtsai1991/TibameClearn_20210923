package idv.tfp10207.nowclearnnow0818.market;

import java.io.Serializable;

public class ShoppingCarMerch implements Serializable {
    private String memberId;
    private String seller;
    private int drawableID;
    private String merchName;
    private int merchPrice;
    private int merchNumber;

    public ShoppingCarMerch() {
    }

    public ShoppingCarMerch(String memberId, String seller, int drawableID, String merchName, int merchPrice, int merchNumber) {
        this.memberId = memberId;
        this.seller = seller;
        this.drawableID = drawableID;
        this.merchName = merchName;
        this.merchPrice = merchPrice;
        this.merchNumber = merchNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public int getMerchPrice() {
        return merchPrice;
    }

    public void setMerchPrice(int merchPrice) {
        this.merchPrice = merchPrice;
    }

    public int getMerchNumber() {
        return merchNumber;
    }

    public void setMerchNumber(int merchNumber) {
        this.merchNumber = merchNumber;
    }
}
