
package in.easyhunt;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.easyhunt.NewCustomerData.DistrictList_;

public class DistrictList {

    @SerializedName("District List")
    @Expose
    private List<DistrictList_> districtList = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<DistrictList_> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictList_> districtList) {
        this.districtList = districtList;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
