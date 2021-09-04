package idv.tfp10207.nowclearnnow0818.mycommodity;

public class Produce {
    private Integer imageId;
    private String title;

    public Produce() {
    }

    public Produce(Integer imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
