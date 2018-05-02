
package in.easyhunt;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class CityList {

    @SerializedName("City List")
    @Expose
    private List<in.easyhunt.CityList_> cityList = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<in.easyhunt.CityList_> getCityList() {
        return cityList;
    }

    public void setCityList(List<in.easyhunt.CityList_> cityList) {
        this.cityList = cityList;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
