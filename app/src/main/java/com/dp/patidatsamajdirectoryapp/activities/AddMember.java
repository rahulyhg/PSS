package com.dp.patidatsamajdirectoryapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.network.AddMemberCalls;
import com.dp.patidatsamajdirectoryapp.network.Utils;
import com.dp.patidatsamajdirectoryapp.pojo.register.RegisterResponse;

import org.json.JSONException;
import org.json.JSONObject;

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
    String selectedGender="M", selectedStatus="Single", selectedBloodGroup="O +ve", selectedState="Madhya Pradesh", selectedDistrict="dhar";



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


        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://patidarsamajsangthan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(Utils.getUnsafeOkHttpClient())
                .build();

        AddMemberCalls service = adapter.create(AddMemberCalls.class);


        JSONObject data = new JSONObject();

        data.put("name", name.getText().toString());
        data.put("lname", surName.getText().toString());
        data.put("uid", "1347");
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
                Toast.makeText(AddMember.this, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(AddMember.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();
            }
        });








    }
}
