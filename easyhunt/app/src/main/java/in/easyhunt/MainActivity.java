package in.easyhunt;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ImageView lg = (ImageView) findViewById(R.id.log);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.loginregis,null);
                Button b1 = (Button) mView.findViewById(R.id.login);
                Button b2 = (Button) mView.findViewById(R.id.regis);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(browserIntent);
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                        startActivity(browserIntent);
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });


        in.easyhunt.AllCategoryApi apiService =
                in.easyhunt.ApiClient.getClient().create( in.easyhunt.AllCategoryApi.class);

        Call<in.easyhunt.AllCategory> call = apiService.getTopRatedMovies();
        call.enqueue(new Callback<in.easyhunt.AllCategory>() {
            @Override
            public void onResponse(Call<in.easyhunt.AllCategory>call, Response<in.easyhunt.AllCategory> response) {
                in.easyhunt.AllCategory name = response.body();

                List<in.easyhunt.MostPopularServicesList> arrayLists = name.getMostPopularServicesList();

                int len = arrayLists.size();
                String[] categoryName = new String[len];
                String[] img = new String[len];

                for (int i =0; i<arrayLists.size();i++)
                {
                    categoryName[i]= arrayLists.get(i).getCategoryName().toString();
                    img[i] = arrayLists.get(i).getCategoryImage().toString();

                }
                RecyclerView rv = (RecyclerView) findViewById(R.id.allcategory);
                rv.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                rv.setLayoutManager(layoutManager);
               // rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rv.setAdapter(new in.easyhunt.ShowCategoryAdapter(categoryName,img,MainActivity.this));

            }

            @Override

            public void onFailure(Call<in.easyhunt.AllCategory>call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onBackPressed()
    {

    }
}
