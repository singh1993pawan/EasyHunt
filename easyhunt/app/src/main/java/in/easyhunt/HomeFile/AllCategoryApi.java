package in.easyhunt;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 22-03-2018.
 */

public interface AllCategoryApi {

    @GET("easyhunt/api_homepage_list_most_popular.aspx?api_login_key=12345&language_id=0")
    Call<in.easyhunt.AllCategory> getTopRatedMovies();
}
