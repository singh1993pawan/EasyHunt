package in.easyhunt;

import retrofit.Callback;
import retrofit.client.Response;

/**
 * Created by ebaraha 12 on 3/24/2018.
 */

public interface ForgotApi {


    @retrofit.http.FormUrlEncoded
    @retrofit.http.POST("/api_forgot_password.aspx")
    public void forgotpassword(
            @retrofit.http.Field("api_login_key") String api_login_key,
            @retrofit.http.Field("email_id") String email,
            retrofit.Callback<retrofit.client.Response> callback);

}
