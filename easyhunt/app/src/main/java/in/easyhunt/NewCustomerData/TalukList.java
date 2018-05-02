
package in.easyhunt;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.easyhunt.NewCustomerData.TalukList_;

public class TalukList {

    @SerializedName("Taluk List")
    @Expose
    private List<TalukList_> talukList = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<TalukList_> getTalukList() {
        return talukList;
    }

    public void setTalukList(List<TalukList_> talukList) {
        this.talukList = talukList;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
