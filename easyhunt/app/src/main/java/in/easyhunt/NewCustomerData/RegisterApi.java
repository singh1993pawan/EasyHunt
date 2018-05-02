package in.easyhunt;

import retrofit.Callback;
import retrofit.client.Response;

/**
 * Created by ebaraha 12 on 3/24/2018.
 */

public interface RegisterApi {


    @retrofit.http.FormUrlEncoded
    @retrofit.http.POST("/api_register_customer_by_executive.aspx")
    public void registerCustomer(
            @retrofit.http.Field("api_login_key") String api_login_key,
            @retrofit.http.Field("member_id") String member_id,
            @retrofit.http.Field("first_name") String first_name,
            @retrofit.http.Field("last_name") String last_name,
            @retrofit.http.Field("middle_name") String middle_name,
            @retrofit.http.Field("email_id") String email_id,
            @retrofit.http.Field("mobile_no") String mobile_no,
            @retrofit.http.Field("alt_name") String alt_name,
            @retrofit.http.Field("alt_mobile") String alt_mobile,
            @retrofit.http.Field("password") String password,
            @retrofit.http.Field("confirm_password") String confirm_password,
            @retrofit.http.Field("notification_id_android") String notification_id_android,
            @retrofit.http.Field("device_id_android") String device_id_android,
            @retrofit.http.Field("notification_id_ios") String notification_id_ios,
            @retrofit.http.Field("device_id_ios") String device_id_ios,
            @retrofit.http.Field("reg_at") String reg_at,
            @retrofit.http.Field("district_id") String district_id,
            @retrofit.http.Field("taluk_id") String taluk_id,
            @retrofit.http.Field("hubli_id") String hubli_id,

            Callback<Response> callback);

}
