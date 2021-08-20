package idv.tfp10207.nowclearnnow0818.market;

public class MerchInfo {//implements Serializable {

    private int drawableID;
    private String merchName;
    private String merchPrice;

    public MerchInfo(){
    }

    public MerchInfo(int drawableID, String merchName, String merchPrice) {
        this.drawableID = drawableID;
        this.merchName = merchName;
        this.merchPrice = merchPrice;
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

    public String getMerchPrice() {
        return merchPrice;
    }

    public void setMerchPrice(String merchPrice) {
        this.merchPrice = merchPrice;
    }
}
