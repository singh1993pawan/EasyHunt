package in.easyhunt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WebLogin extends Activity{

    private RecyclerView recyclerView;

    WebView myweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_login);

       /* myweb= (WebView) findViewById(R.id.myweb);
        myweb.setWebViewClient(new myWebClient());
        myweb.getSettings().setJavaScriptEnabled(true);
        myweb.loadUrl("http://demo.ebaraha.com/easyhunt/mobile_default.aspx");*/

        ImageView main1 = (ImageView) findViewById(R.id.main1);
        ImageView main2 = (ImageView) findViewById(R.id.main2);
        ImageView main3 = (ImageView) findViewById(R.id.main3);
        ImageView main4 = (ImageView) findViewById(R.id.main4);
        ImageView main5 = (ImageView) findViewById(R.id.main5);
        ImageView main6 = (ImageView) findViewById(R.id.main6);

        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WebLogin.this, "Comming Soon......", Toast.LENGTH_SHORT).show();
            }
        });

        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WebLogin.this, "Comming Soon......", Toast.LENGTH_SHORT).show();
            }
        });

        main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WebLogin.this, "Comming Soon......", Toast.LENGTH_SHORT).show();
            }
        });

        main4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WebLogin.this, "Comming Soon......", Toast.LENGTH_SHORT).show();
            }
        });

        main5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WebLogin.this, "Comming Soon......", Toast.LENGTH_SHORT).show();
            }
        });
        main6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(WebLogin.this);
                View mView = getLayoutInflater().inflate(R.layout.fornavigation,null);
                Button b1 = (Button) mView.findViewById(R.id.profile);
                Button b2 = (Button) mView.findViewById(R.id.cp);
                Button b3 = (Button) mView.findViewById(R.id.nc);
                Button b4 = (Button) mView.findViewById(R.id.cl);
                Button b5 = (Button) mView.findViewById(R.id.payout);
                Button b6 = (Button) mView.findViewById(R.id.plans);
                Button b7 = (Button) mView.findViewById(R.id.log);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(WebLogin.this, ProfileActivity.class);
                        startActivity(browserIntent);
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(WebLogin.this, ChangePassword.class);
                        startActivity(browserIntent);
                    }
                });

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent browserIntent = new Intent(WebLogin.this, NewCustomer.class);
                        startActivity(browserIntent);

                    }
                });

                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.easyhunt.in/register.htm"));
                        startActivity(browserIntent);
                    }
                });

                b5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.easyhunt.in/register.htm"));
                        startActivity(browserIntent);
                    }
                });

                b6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(WebLogin.this, LoginActivity.class);
                        startActivity(browserIntent);
                    }
                });

                b7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent browserIntent = new Intent(WebLogin.this, LoginActivity  .class);
                        startActivity(browserIntent);
                        finish();
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
                rv.setAdapter(new in.easyhunt.ShowCategoryAdapter(categoryName,img,WebLogin.this));

            }

            @Override

            public void onFailure(Call<in.easyhunt.AllCategory>call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(WebLogin.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
/*
    private class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
// TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }
*//* @Override
public boolean shouldOverrideUrlLoading(WebView view, String url) {
// TODO Auto-generated method stub

view.loadUrl(url);
return true;

}*//*
    }*/
    // To handle "Back" key press event for WebView to go back to previous screen.
  /*  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myweb.canGoBack()) {
            myweb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
*/

    public void onBackPressed()
    {

    }
}
