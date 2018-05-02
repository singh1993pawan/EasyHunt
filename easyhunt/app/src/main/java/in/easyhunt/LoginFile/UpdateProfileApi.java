package in.easyhunt;

import retrofit.Callback;
import retrofit.client.Response;

/**
 * Created by ebaraha 12 on 3/24/2018.
 */

public interface UpdateProfileApi {


    @retrofit.http.FormUrlEncoded
    @retrofit.http.POST("/api_profile.aspx")
    public void updateProfile(
            @retrofit.http.Field("api_login_key") String api_login_key,
            @retrofit.http.Field("first_name") String first_name,
            @retrofit.http.Field("last_name") String last_name,
            @retrofit.http.Field("email_id") String email_id,
            @retrofit.http.Field("mobile_no") String mobile_no,
            @retrofit.http.Field("member_id") String member_id,
            Callback<Response> callback);

}


