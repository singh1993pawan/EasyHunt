package in.easyhunt;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 22-03-2018.
 */

public interface AllDistrictApi {


    @GET("easyhunt/api_district_list.aspx?api_login_key=12345&member_id=1")
    Call<in.easyhunt.DistrictList> getDistrict();
}
