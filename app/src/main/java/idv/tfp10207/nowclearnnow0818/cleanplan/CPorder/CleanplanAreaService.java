package idv.tfp10207.nowclearnnow0818.cleanplan.CPorder;


public class CleanplanAreaService {

    //參考 rc_itemcard_cleanplan01_area.xml 有的項目

    private int cp_iv_serviceID;
    private String cp_ratingservice;

    private String cp_serviceName;
    private String cp_good_project;
    private String toll_standard;
    private String finsh;

    public CleanplanAreaService(){
    }

    public CleanplanAreaService(int cp_iv_serviceID, String cp_ratingservice, String cp_serviceName, String cp_good_project, String toll_standard, String finsh) {
        this.cp_iv_serviceID = cp_iv_serviceID;
        this.cp_ratingservice = cp_ratingservice;
        this.cp_serviceName = cp_serviceName;
        this.cp_good_project = cp_good_project;
        this.toll_standard = toll_standard;
        this.finsh = finsh;
    }

    public int getCp_iv_serviceID() {
        return cp_iv_serviceID;
    }

    public void setCp_iv_serviceID(int cp_iv_serviceID) {
        this.cp_iv_serviceID = cp_iv_serviceID;
    }

    public String getCp_ratingservice() {
        return cp_ratingservice;
    }

    public void setCp_ratingservice(String cp_ratingservice) {
        this.cp_ratingservice = cp_ratingservice;
    }

    public String getCp_serviceName() {
        return cp_serviceName;
    }

    public void setCp_serviceName(String cp_serviceName) {
        this.cp_serviceName = cp_serviceName;
    }

    public String getCp_good_project() {
        return cp_good_project;
    }

    public void setCp_good_project(String cp_good_project) {
        this.cp_good_project = cp_good_project;
    }

    public String getToll_standard() {
        return toll_standard;
    }

    public void setToll_standard(String toll_standard) {
        this.toll_standard = toll_standard;
    }

    public String getFinsh() {
        return finsh;
    }

    public void setFinsh(String finsh) {
        this.finsh = finsh;
    }
}
