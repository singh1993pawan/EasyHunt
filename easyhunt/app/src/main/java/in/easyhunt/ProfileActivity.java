package in.easyhunt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.RestAdapter;
import retrofit.RetrofitError;

import static in.easyhunt.LoginActivity.session_email;
import static in.easyhunt.LoginActivity.session_key;
import static in.easyhunt.LoginActivity.session_member;
import static in.easyhunt.LoginActivity.session_password;

public class ProfileActivity extends AppCompatActivity {

    EditText fname,lname,email,mobile;
    Button profilesubmit;

    String key,mem;
    public static final String ROOT_URL = "http://demo.ebaraha.com/easyhunt/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.emailid);
        mobile = (EditText) findViewById(R.id.mobilenumber);

        profilesubmit = (Button) findViewById(R.id.profilesubmit);

        SharedPreferences shared = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        key = (shared.getString(session_key,""));
        mem = (shared.getString(session_member,""));


        profilesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),mobile.getText().toString());
            }
        });
    }

    public void updateProfile(String fsname, String lsname, String emailid, String mobilenumber)
    {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        in.easyhunt.UpdateProfileApi api = adapter.create(in.easyhunt.UpdateProfileApi.class);

        //Defining the method insertuser of our interface
        api.updateProfile(

                //Passing the values by getting it from editTexts//
                key,
                fsname,
                lsname,
                emailid,
                mobilenumber,
                mem,


                //Creating an anonymous callback
                new retrofit.Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response result, retrofit.client.Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object

                        String member = null;
                        String message = null;

                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            // get JSONObject from JSON file
                            JSONObject obj = new JSONObject(output);

                            int val = obj.getInt("success");


                            JSONArray jsonArray = obj.optJSONArray("Profile");

                            for (int i = 0; i < jsonArray.length(); i++)

                            {
                                JSONObject objj = jsonArray.getJSONObject(i);
                                member = objj.getString("member_id");
                                message = objj.getString("Message");
                            }

                            if (val == 0) {
                                Toast.makeText(ProfileActivity.this, "" + message, Toast.LENGTH_LONG).show();

                            } else {

                                Toast.makeText(ProfileActivity.this, "Your Profile has been changed successfully", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(ProfileActivity.this,WebLogin.class);
                                startActivity(intent);

                                //Add new password in Session also//
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(ProfileActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}
