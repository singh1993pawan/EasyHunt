package in.easyhunt;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ebaraha 12 on 3/24/2018.
 */

public interface LoginApi {


    @retrofit.http.FormUrlEncoded
    @retrofit.http.POST("/api_login.aspx")
    public void insertUser(
            @retrofit.http.Field("api_login_key") String api_login_key,
            @retrofit.http.Field("email_id") String email,
            @retrofit.http.Field("password") String password,
            retrofit.Callback<retrofit.client.Response> callback);
}
