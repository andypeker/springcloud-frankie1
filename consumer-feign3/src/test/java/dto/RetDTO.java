package dto;

/**
 * @author Frankie Yang on 2019-07-19.
 */
public class RetDTO {

    private Enum RetStatus;
    private String RetData;

    public static Enum SUCCESS;

    public Enum getRetStatus() {
        return RetStatus;
    }

    public void setRetData(String retData) {
        RetData = retData;
    }

    public void setRetStatus(Enum retStatus) {
        RetStatus = retStatus;
    }

    public String getRetData() {
        return RetData;
    }

}
