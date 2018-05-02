package in.easyhunt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String ROOT_URL = "http://demo.ebaraha.com/easyhunt/";

    String api_login_key = "12345";
    EditText email,password;
    ProgressDialog pd;
    Button forgot,login;



    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String session_key = "key";
    public static final String session_email = "email";
    public static final String session_password = "password";
    public static final String session_member = "member";

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.sign_in);
        forgot = (Button) findViewById(R.id.forgot);





        login.setOnClickListener(this);


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.forgotpassword,null);

                final EditText email_for_forgot = (EditText) mView.findViewById(R.id.email);
                Button b1 = (Button) mView.findViewById(R.id.forgotpwd);


                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        forgotpassword(email_for_forgot.getText().toString());
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });




    }

    //Overriding onclick method
    @Override
    public void onClick(View v) {
        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Loading...");

        //Calling insertUser on button click
        insertUser();
    }


    private void insertUser(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        in.easyhunt.LoginApi api = adapter.create(in.easyhunt.LoginApi.class);

        //Defining the method insertuser of our interface
        api.insertUser(

                //Passing the values by getting it from editTexts
                api_login_key,
                email.getText().toString(),
                password.getText().toString(),

                //Creating an anonymous callback
                new retrofit.Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response result, retrofit.client.Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object

                        String member = null;
                        String message = null;
                        int val = 0;

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

                           val = obj.getInt("success");

                            JSONArray jsonArray = obj.optJSONArray("Login");

                            for(int i=0;i<jsonArray.length();i++)

                            {
                                JSONObject objj=jsonArray.getJSONObject(i);
                                member=objj.getString("member_id");
                                message=objj.getString("Message");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (val==0)
                        {
                            Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();

                        }
                        else{

                            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                            String k = "12345";
                            String n  = email.getText().toString();
                            String ph  = password.getText().toString();
                            String mem = member;


                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString(session_key, k);
                            editor.putString(session_email, n);
                            editor.putString(session_password, ph);
                            editor.putString(session_member, mem);

                            editor.commit();
                            
                            Intent intent = new Intent(LoginActivity.this,WebLogin.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(LoginActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void forgotpassword(String forgotEmail){

        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        in.easyhunt.ForgotApi api = adapter.create(in.easyhunt.ForgotApi.class);

        //Defining the method insertuser of our interface
        api.forgotpassword(

                //Passing the values by getting it from editTexts
                api_login_key,
                forgotEmail,


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


                            JSONArray jsonArray = obj.optJSONArray("Forgot Password");

                            for(int i=0;i<jsonArray.length();i++)

                            {
                                JSONObject objj=jsonArray.getJSONObject(i);
                                member=objj.getString("member_id");
                                message=objj.getString("Message");
                            }

                            if (val==0)
                            {
                                Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_LONG).show();

                            }
                            else {

                                Toast.makeText(LoginActivity.this, "Password send successfull to your email" , Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(LoginActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );

    }



}
