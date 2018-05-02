package in.easyhunt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import static in.easyhunt.LoginActivity.session_email;
import static in.easyhunt.LoginActivity.session_key;
import static in.easyhunt.LoginActivity.session_password;


/**
 * Created by Admin on 22-03-2018.
 */


public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 25000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences shared = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        String key = (shared.getString(session_key,""));
        String email = (shared.getString(session_email,""));
        String password = (shared.getString(session_password,""));




        if (key.isEmpty() && email.isEmpty() && password.isEmpty())
        {
            Toast.makeText(this, "New User", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent i = new Intent(SplashScreen.this, WebLogin.class);
            startActivity(i);
        }




        VideoView mVideoView2 = (VideoView)findViewById(R.id.videoView1);
        String uriPath2 = "android.resource://in.easyhunt/"+R.raw.easyhut;
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();
        mVideoView2.start();

        Button skip = findViewById(R.id.skip);



        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences shared = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
                String key = (shared.getString(session_key,""));
                String email = (shared.getString(session_email,""));
                String password = (shared.getString(session_password,""));

                if (key.isEmpty() && email.isEmpty() && password.isEmpty())
                {

                    Intent i = new Intent(SplashScreen.this, SelectLanguage.class);
                    startActivity(i);


                }
                else
                {
                    Intent i = new Intent(SplashScreen.this, WebLogin.class);
                    startActivity(i);
                }

            }
        });

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                SharedPreferences shared = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
                String key = (shared.getString(session_key,""));
                String email = (shared.getString(session_email,""));
                String password = (shared.getString(session_password,""));

                if (key.isEmpty() && email.isEmpty() && password.isEmpty())
                {

                    Intent i = new Intent(SplashScreen.this, SelectLanguage.class);
                    startActivity(i);


                }
                else
                {

                    Intent i = new Intent(SplashScreen.this, WebLogin.class);
                    startActivity(i);
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

