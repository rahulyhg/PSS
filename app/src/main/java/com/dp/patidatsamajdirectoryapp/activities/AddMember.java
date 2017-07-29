package com.dp.patidatsamajdirectoryapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.dp.patidatsamajdirectoryapp.R;

public class AddMember extends AppCompatActivity {

    Button back;
    EditText name,surName,fatherName,gotra,tehsil,city,primaryAddress,
            secondaryAddress,occupation,mobile,email,whatsapp,landline,password;
    Spinner bloodGroup,state,district;
    RadioGroup gender,marital;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);


    }
}
