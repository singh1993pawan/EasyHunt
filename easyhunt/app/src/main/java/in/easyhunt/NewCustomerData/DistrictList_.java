
package in.easyhunt.NewCustomerData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictList_ {

    @SerializedName("District_ID")
    @Expose
    private String districtID;
    @SerializedName("District_Name")
    @Expose
    private String districtName;

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

}
