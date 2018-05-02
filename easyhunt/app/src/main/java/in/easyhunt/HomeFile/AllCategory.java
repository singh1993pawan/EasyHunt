
package in.easyhunt;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCategory {

    @SerializedName("Most Popular Services List")
    @Expose
    private List<in.easyhunt.MostPopularServicesList> mostPopularServicesList = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<in.easyhunt.MostPopularServicesList> getMostPopularServicesList() {
        return mostPopularServicesList;
    }

    public void setMostPopularServicesList(List<in.easyhunt.MostPopularServicesList> mostPopularServicesList) {
        this.mostPopularServicesList = mostPopularServicesList;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
