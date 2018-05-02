
package in.easyhunt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityList_ {

    @SerializedName("hubli_id")
    @Expose
    private String hubliId;
    @SerializedName("hubli_name")
    @Expose
    private String hubliName;

    public String getHubliId() {
        return hubliId;
    }

    public void setHubliId(String hubliId) {
        this.hubliId = hubliId;
    }

    public String getHubliName() {
        return hubliName;
    }

    public void setHubliName(String hubliName) {
        this.hubliName = hubliName;
    }

}
