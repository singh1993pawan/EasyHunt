
package in.easyhunt.NewCustomerData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TalukList_ {

    @SerializedName("Taluk_ID")
    @Expose
    private String talukID;
    @SerializedName("Taluk_Name")
    @Expose
    private String talukName;

    public String getTalukID() {
        return talukID;
    }

    public void setTalukID(String talukID) {
        this.talukID = talukID;
    }

    public String getTalukName() {
        return talukName;
    }

    public void setTalukName(String talukName) {
        this.talukName = talukName;
    }

}
