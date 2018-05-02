package in.easyhunt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import in.easyhunt.NewCustomerData.DistrictList_;
import in.easyhunt.NewCustomerData.TalukList_;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.easyhunt.LoginActivity.session_email;
import static in.easyhunt.LoginActivity.session_key;
import static in.easyhunt.LoginActivity.session_member;
import static in.easyhunt.LoginActivity.session_password;

public class NewCustomer extends AppCompatActivity {

    Spinner selectdistrict;
    Spinner Selecttaluk;
    Spinner Selectcity;

    String keyvalue,memberid;

    String dist_id,taluk_id,city_id;

    HashMap<Integer,String> dist = new HashMap<Integer, String>();
    HashMap<Integer,String> talk = new HashMap<Integer, String>();
    HashMap<Integer,String> cit = new HashMap<Integer, String>();



    ArrayAdapter<String> adapter_for_district,adapter_for_district1;
    ArrayAdapter<String> adapter_for_taluk;
    ArrayAdapter<String> adapter_for_city;

    EditText fname,mname,lname,emailids,mobilenumbers,alternateperson,alternatecontact,password,confpassword;
    Button register;

    public static final String ROOT_URL = "http://demo.ebaraha.com/easyhunt/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        fname = (EditText) findViewById(R.id.fname);
        mname = (EditText) findViewById(R.id.mname);
        lname = (EditText) findViewById(R.id.lname);
        emailids = (EditText) findViewById(R.id.emailids);
        mobilenumbers = (EditText) findViewById(R.id.mobilenumber);
        alternateperson = (EditText) findViewById(R.id.alternateperson);
        alternatecontact = (EditText) findViewById(R.id.alternatemobile);
        password = (EditText) findViewById(R.id.password1);
        confpassword = (EditText) findViewById(R.id.password2);

        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerCustomer(fname.getText().toString(),mname.getText().toString(),lname.getText().toString(),emailids.getText().toString(),mobilenumbers.getText().toString(),alternateperson.getText().toString(),alternatecontact.getText().toString(),password.getText().toString(),confpassword.getText().toString());

            }
        });


        // ------------------------------------------------This code for District--------------------------------------------------

        selectdistrict = (Spinner) findViewById(R.id.selectdistrict);



        in.easyhunt.AllDistrictApi apiService =
                in.easyhunt.ApiClient.getClient().create( in.easyhunt.AllDistrictApi.class);

        Call<in.easyhunt.DistrictList> call = apiService.getDistrict();
        call.enqueue(new Callback<in.easyhunt.DistrictList>() {
            @Override
            public void onResponse(Call<in.easyhunt.DistrictList>call, Response<in.easyhunt.DistrictList> response) {
                in.easyhunt.DistrictList name = response.body();

                List<DistrictList_> val = name.getDistrictList();


                //List<in.easyhunt.MostPopularServicesList> arrayLists = name.getDistrictList();

                int len = val.size();
                String[] ditictid = new String[len];
                String[] ditictname = new String[len];




                for (int i =0; i<len;i++)
                {

                    ditictid[i]= val.get(i).getDistrictID().toString();

                    ditictname[i]= val.get(i).getDistrictName().toString();

                    dist.put(i,ditictname[i]);
                    dist.put(i,ditictid[i]);

                }

                adapter_for_district = new ArrayAdapter<String>(NewCustomer.this, android.R.layout.simple_spinner_item, ditictname);
               // adapter_for_district1 = new ArrayAdapter<String>(NewCustomer.this, android.R.layout.simple_spinner_item, ditictid);

                adapter_for_district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                selectdistrict.setAdapter( adapter_for_district);


            }

            @Override

            public void onFailure(Call<in.easyhunt.DistrictList>call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(NewCustomer.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        selectdistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
               // dist_id = adapter.getItemAtPosition(position).toString();

                dist_id = dist.get(position);
         //       Toast.makeText(NewCustomer.this, ""+d, Toast.LENGTH_SHORT).show();
                // Showing selected spinner item

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

       //--------------------------------------------District Code End Here---------------------------------------------------


        //---------------------------------------Taluk Code start here----------------------------------------------------------
          Selecttaluk = (Spinner) findViewById(R.id.selecttaluka);



        in.easyhunt.AllTalukApi apiServices =
                in.easyhunt.ApiClient.getClient().create( in.easyhunt.AllTalukApi.class);

        Call<in.easyhunt.TalukList> calls = apiServices.getTaluk();
        calls.enqueue(new Callback<in.easyhunt.TalukList>() {
            @Override
            public void onResponse(Call<in.easyhunt.TalukList>call, Response<in.easyhunt.TalukList> response) {
                in.easyhunt.TalukList name = response.body();

                List<TalukList_> val = name.getTalukList();


                //List<in.easyhunt.MostPopularServicesList> arrayLists = name.getDistrictList();

                int len = val.size();
                String[] talukid = new String[len];
                String[] talukname = new String[len];

                for (int i =0; i<len;i++)
                {
                    talukid[i]= val.get(i).getTalukID().toString();

                    talukname[i]= val.get(i).getTalukName().toString();

                    talk.put(i,talukname[i]);
                    talk.put(i,talukid[i]);

                }

                adapter_for_taluk = new ArrayAdapter<String>(NewCustomer.this, android.R.layout.simple_spinner_item, talukname);

                adapter_for_taluk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Selecttaluk.setAdapter( adapter_for_taluk);


            }

            @Override

            public void onFailure(Call<in.easyhunt.TalukList>call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(NewCustomer.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Selecttaluk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item

                taluk_id = talk.get(position);
                 //= adapter.getItemAtPosition(position).toString();
                // Showing selected spinner item

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

         //-------------------------------------------------------taluk code end here--------------------------------


        //----------------------------------------------------------Hobli Code Start here---------------------------------




        Selectcity = (Spinner) findViewById(R.id.selecthobli);



        in.easyhunt.AllCityApi apiServicess =
                in.easyhunt.ApiClient.getClient().create( in.easyhunt.AllCityApi.class);

        Call<in.easyhunt.CityList> callss = apiServicess.getCity();
        callss.enqueue(new Callback<in.easyhunt.CityList>() {
            @Override
            public void onResponse(Call<in.easyhunt.CityList>call, Response<in.easyhunt.CityList> response) {
                in.easyhunt.CityList name = response.body();

                List<in.easyhunt.CityList_> val = name.getCityList();


                //List<in.easyhunt.MostPopularServicesList> arrayLists = name.getDistrictList();

                int len = val.size();
                String[] cityid = new String[len];
                String[] cityname = new String[len];

                for (int i =0; i<len;i++)
                {
                    cityid[i]= val.get(i).getHubliId().toString();

                    cityname[i]= val.get(i).getHubliName().toString();

                    cit.put(i,cityname[i]);
                    cit.put(i,cityid[i]);
                }

                adapter_for_city = new ArrayAdapter<String>(NewCustomer.this, android.R.layout.simple_spinner_item, cityname);

                adapter_for_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Selectcity.setAdapter( adapter_for_city);


            }

            @Override

            public void onFailure(Call<in.easyhunt.CityList>call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(NewCustomer.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Selectcity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                city_id = cit.get(position);
                // Showing selected spinner item
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //----------------------------------------------------------Hoblil code End Here-p-----------------------------------


        SharedPreferences shared = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        keyvalue = (shared.getString(session_key,""));
        memberid = (shared.getString(session_member,""));



    }


    public void  registerCustomer(String fsname, String msname, String lsname, String semail, String mobilenumber, String alternateperson, String alternatecontact, String pass, String confpass)
    {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        in.easyhunt.RegisterApi api = adapter.create(in.easyhunt.RegisterApi.class);

        //Defining the method insertuser of our interface
        api.registerCustomer
                (

                //Passing the values by getting it from editTexts//
                keyvalue,
                memberid,
                fsname,
                lsname,
                msname,
                semail,
                mobilenumber,
                alternateperson,
                alternatecontact,
                pass,
                confpass,
                "",
                "123459",
                "",
                "er55453",
                "A",
                dist_id,
                taluk_id,city_id,

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


                            JSONArray jsonArray = obj.optJSONArray("Register Customer");

                            for (int i = 0; i < jsonArray.length(); i++)

                            {
                                JSONObject objj = jsonArray.getJSONObject(i);
                                message = objj.getString("Message");
                            }

                            if (val == 0) {
                                Toast.makeText(NewCustomer.this, "" + message, Toast.LENGTH_LONG).show();

                            } else {

                                Toast.makeText(NewCustomer.this, "Your are registered successfull in this app", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(NewCustomer.this,WebLogin.class);
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
                        Toast.makeText(NewCustomer.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

    }


}
