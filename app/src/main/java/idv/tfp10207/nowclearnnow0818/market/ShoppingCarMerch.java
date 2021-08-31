package idv.tfp10207.nowclearnnow0818.market;

import java.io.Serializable;

public class ShoppingCarMerch implements Serializable {
    private String memberId;
    private String seller;
    private int drawableID;
    private String merchName;
    private int merchPrice;
    private int merchNumber;
    private boolean firstMerchItem;
    //private boolean merchCheckStateAll;

    private boolean sellerCheckBox;
    private boolean merchCheckBox;

    public ShoppingCarMerch() {
    }

    public ShoppingCarMerch(String memberId, String seller, int drawableID, String merchName, int merchPrice, int merchNumber, boolean firstMerchItem, /*boolean merchCheckStateAll,*/ boolean sellerCheckBox, boolean merchCheckBox) {
        this.memberId = memberId;
        this.seller = seller;
        this.drawableID = drawableID;
        this.merchName = merchName;
        this.merchPrice = merchPrice;
        this.merchNumber = merchNumber;
        this.firstMerchItem = firstMerchItem;
        //this.merchCheckStateAll = merchCheckStateAll;
        this.sellerCheckBox = sellerCheckBox;
        this.merchCheckBox = merchCheckBox;
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

    public boolean getFirstMerchItem() {
        return firstMerchItem;
    }

    public void setFirstMerchItem(boolean firstMerchItem) {
        this.firstMerchItem = firstMerchItem;
    }

    /*public boolean getMerchCheckStateAll() {
        return merchCheckStateAll;
    }

    public void setMerchCheckStateAll(boolean merchCheckState) {
        this.merchCheckStateAll = merchCheckState;
    }*/

    public boolean getSellerCheckBox() {
        return sellerCheckBox;
    }

    public void setSellerCheckBox(boolean sellerCheckBos) {
        this.sellerCheckBox = sellerCheckBos;
    }

    public boolean getMerchCheckBox() {
        return merchCheckBox;
    }

    public void setMerchCheckBox(boolean merchCheckBox) {
        this.merchCheckBox = merchCheckBox;
    }
}
