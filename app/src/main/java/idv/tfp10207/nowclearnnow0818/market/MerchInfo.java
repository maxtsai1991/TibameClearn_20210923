package idv.tfp10207.nowclearnnow0818.market;

public class MerchInfo {//implements Serializable {

    private int drawableID;
    private String merchName;
    private int merchPrice;
    private int merchNumber;
    private String merchBrand;
    private String merchContent;
    private String seller;
    private String memberId;

    public MerchInfo(){
    }

    public MerchInfo(int drawableID, String merchName, int merchPrice, int merchNumber, String merchBrand, String merchContent, String seller, String memberId) {
        this.drawableID = drawableID;
        this.merchName = merchName;
        this.merchPrice = merchPrice;
        this.merchNumber = merchNumber;
        this.merchBrand = merchBrand;
        this.merchContent = merchContent;
        this.seller = seller;
        this.memberId = memberId;
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

    public String getMerchBrand() {
        return merchBrand;
    }

    public void setMerchBrand(String merchBrand) {
        this.merchBrand = merchBrand;
    }

    public String getMerchContent() {
        return merchContent;
    }

    public void setMerchContent(String merchContent) {
        this.merchContent = merchContent;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
