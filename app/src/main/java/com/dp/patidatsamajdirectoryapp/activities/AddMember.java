package com.dp.patidatsamajdirectoryapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.mainActivities.MainActivity;
import com.dp.patidatsamajdirectoryapp.network.AddMemberCalls;
import com.dp.patidatsamajdirectoryapp.network.DirectoryCalls;
import com.dp.patidatsamajdirectoryapp.network.Utils;
import com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse.CityResponse;
import com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse.LastDatum;
import com.dp.patidatsamajdirectoryapp.pojo.register.RegisterResponse;
import com.dp.patidatsamajdirectoryapp.utils.ProgressDialogUtil;
import com.dp.patidatsamajdirectoryapp.utils.SharedPrefUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddMember extends AppCompatActivity {

    Button back;
    EditText name,surName,fatherName,gotra,tehsil,city,primaryAddress,secondaryAddress,occupation,mobile,email,whatsapp,landline,password, boys,girls,dob,doa;
    Spinner bloodGroup,state,district;
    RadioGroup gender,marital;
    Button submit;
    ArrayList<String> bloodGroups, cities;
    String selectedGender="M", selectedStatus="Single", selectedBloodGroup="O +ve", selectedState="Madhya Pradesh", selectedDistrict="dhar";
    SharedPrefUtil sharedPrefUtil;
    private Set<String> stateList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);


        name = (EditText)findViewById(R.id.name);
        surName = (EditText)findViewById(R.id.surname);
        fatherName = (EditText)findViewById(R.id.father_name);
        gotra = (EditText)findViewById(R.id.gotra);
        tehsil = (EditText)findViewById(R.id.tehsil);
        city = (EditText)findViewById(R.id.city);
        primaryAddress = (EditText)findViewById(R.id.primaryAddress);
        secondaryAddress = (EditText)findViewById(R.id.secondaryAddress);
        occupation = (EditText)findViewById(R.id.occupation);
        mobile = (EditText)findViewById(R.id.mobile);
        boys = (EditText)findViewById(R.id.boys);
        girls = (EditText)findViewById(R.id.girls);
        doa = (EditText)findViewById(R.id.doa);
        dob = (EditText)findViewById(R.id.date_of_birth);
        email = (EditText)findViewById(R.id.email);
        whatsapp = (EditText)findViewById(R.id.whatsapp);
        landline = (EditText)findViewById(R.id.landline);
        password = (EditText)findViewById(R.id.password);

        bloodGroup = (Spinner)findViewById(R.id.blood_group);
        state = (Spinner)findViewById(R.id.state);
        district = (Spinner)findViewById(R.id.district);

        gender = (RadioGroup)findViewById(R.id.gender);
        marital = (RadioGroup)findViewById(R.id.marital_status);

        submit = (Button)findViewById(R.id.submit);
        back=(Button)findViewById(R.id.back);
        sharedPrefUtil = new SharedPrefUtil(this);


        bloodGroups = new ArrayList<String>();
        bloodGroups.add("A +ve");
        bloodGroups.add("A -ve");
        bloodGroups.add("B +ve");
        bloodGroups.add("B-+ve");
        bloodGroups.add("AB +ve");
        bloodGroups.add("AB -ve");
        bloodGroups.add("O +ve");
        bloodGroups.add("O -ve");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bloodGroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup.setAdapter(adapter);

        stateList = sharedPrefUtil.retrieveStringSet("States",new HashSet());
        final ArrayList<String> statesInArray = new ArrayList<String>(stateList);
        ArrayAdapter<String> statesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, statesInArray);
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(statesAdapter);

        bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               selectedBloodGroup=(String)adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedState = (String)adapterView.getSelectedItem();
                    getCities((String)adapterView.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDistrict = (String)adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddMember.this, MainActivity.class));

            }
        });

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(AddMember.this, "Called", Toast.LENGTH_SHORT).show();
                    registerMember();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    public void registerMember() throws JSONException {

        ProgressDialogUtil.showProgressDialog(AddMember.this,"Adding Member...");

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://patidarsamajsangthan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(Utils.getUnsafeOkHttpClient())
                .build();

        AddMemberCalls service = adapter.create(AddMemberCalls.class);


        JSONObject data = new JSONObject();

        data.put("name", name.getText().toString());
        data.put("lname", surName.getText().toString());
        data.put("uid", sharedPrefUtil.retrieveString("uid","1347"));
        data.put("fname",fatherName.getText().toString());
        data.put("gotra",gotra.getText().toString());
        data.put("tehsil",tehsil.getText().toString());
        data.put("district",selectedDistrict);
        data.put("city",city.getText().toString());
        data.put("primaryaddress",primaryAddress.getText().toString());
        data.put("secondaryaddress",secondaryAddress.getText().toString());
        data.put("occupation",occupation.getText().toString());
        data.put("mobile",mobile.getText().toString());
        data.put("landline",landline.getText().toString());
        data.put("whatsapp",whatsapp.getText().toString());
        data.put("email",email.getText().toString());
        data.put("password",password.getText().toString());
        data.put("state", selectedState);
        data.put("bloodgroup", selectedBloodGroup);
        data.put("boys",boys.getText().toString());
        data.put("girls",girls.getText().toString());
        data.put("marital_statuse",selectedStatus);
        data.put("gender",selectedGender);
        data.put("cast",surName.getText().toString());
        data.put("dob",dob.getText().toString());
        data.put("doa",doa.getText().toString());

        Call<RegisterResponse> response = service.registerUser(data);


        response.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                ProgressDialogUtil.hideProgressDialog();
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMember.this);
                if(response.body().getResponseCode()==200) {

                    alertDialogBuilder.setMessage(response.body().getResponseMessage());
                    alertDialogBuilder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    startActivity(new Intent(AddMember.this, MainActivity.class));
                                }
                            });
                    alertDialogBuilder.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    startActivity(new Intent(AddMember.this, MainActivity.class));

                                }
                            });
                    alertDialogBuilder.show();

                }
                else{
                    alertDialogBuilder.setMessage(response.body().getResponseMessage());
                    alertDialogBuilder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();

                                }
                            });
                    alertDialogBuilder.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    alertDialogBuilder.show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                ProgressDialogUtil.hideProgressDialog();
                Toast.makeText(AddMember.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getCities(String stateName) {
        ProgressDialogUtil.showProgressDialog(AddMember.this, "Getting Cities...");

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://patidarsamajsangthan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(Utils.getUnsafeOkHttpClient())
                .build();

        JSONObject data = new JSONObject();
        try {
            data.put("DataType", "CITY");
            data.put("DataValue", stateName);
            data.put("lastIndex","0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DirectoryCalls service = adapter.create(DirectoryCalls.class);
        Call<CityResponse> response =  service.getCities(data);

        response.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                if (response.isSuccessful()) {
                    List<LastDatum> cityData =   response.body().getData().getLastData();
                    cities = new ArrayList<String>();
                    for (int i = 0; i < cityData.size(); i++) {
                        cities.add(cityData.get(i).getCity());
                    }
                    ProgressDialogUtil.hideProgressDialog();
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddMember.this,
                            android.R.layout.simple_spinner_item, cities);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    district.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                ProgressDialogUtil.hideProgressDialog();
                Toast.makeText(AddMember.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
