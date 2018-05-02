package in.easyhunt;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 22-03-2018.
 */

public interface AllTalukApi {


    @GET("easyhunt/api_taluk_list.aspx?api_login_key=12345&member_id=1&district_id=1")
    Call<in.easyhunt.TalukList> getTaluk();
}
