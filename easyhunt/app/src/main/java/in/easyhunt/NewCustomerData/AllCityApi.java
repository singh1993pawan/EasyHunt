package in.easyhunt;

import in.easyhunt.CityList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 22-03-2018.
 */

public interface AllCityApi {


    @GET("easyhunt/api_city_list.aspx?api_login_key=12345&taluk_id=1")
    Call<CityList> getCity();
}
