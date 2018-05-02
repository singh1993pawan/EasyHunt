package in.easyhunt;

import retrofit.Callback;
import retrofit.client.Response;

/**
 * Created by ebaraha 12 on 3/24/2018.
 */

public interface ChangePasswordApi {


    @retrofit.http.FormUrlEncoded
    @retrofit.http.POST("/api_change_password.aspx")
    public void updatePassword(
            @retrofit.http.Field("api_login_key") String api_login_key,
            @retrofit.http.Field("old_password") String old_password,
            @retrofit.http.Field("new_password") String new_password,
            @retrofit.http.Field("confirm_password") String confirm_password,
            @retrofit.http.Field("member_id") String member_id,
            Callback<Response> callback);

}


