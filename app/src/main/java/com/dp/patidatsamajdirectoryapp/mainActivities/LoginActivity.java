package com.dp.patidatsamajdirectoryapp.mainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.network.LoginCalls;
import com.dp.patidatsamajdirectoryapp.pojo.login.Login;
import com.dp.patidatsamajdirectoryapp.utils.ProgressDialogUtil;
import com.dp.patidatsamajdirectoryapp.utils.SharedPrefUtil;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button login,register;
    TextView forgotPassword;
    RelativeLayout loginLayout;
    SharedPrefUtil sharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPrefUtil= new SharedPrefUtil(LoginActivity.this);

        if (!sharedPrefUtil.retrieveString("uid","").equals("")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        email = (EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        register=(Button) findViewById(R.id.register);
        forgotPassword=(TextView) findViewById(R.id.forgot_password);
        loginLayout=(RelativeLayout)findViewById(R.id.layout_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProgressDialogUtil.showProgressDialog(LoginActivity.this,"Logging In");


                Retrofit adapter = new Retrofit.Builder()
                        .baseUrl("http://patidarsamajsangthan.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        //.client(Utils.getUnsafeOkHttpClient())
                        .build();

                LoginCalls service = adapter.create(LoginCalls.class);

                JSONObject data = new JSONObject();

                try {
                    data.put("loginid",email.getText().toString());
                    data.put("password",password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Call<Login> response =  service.login(data);


                response.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if (response.isSuccessful()) {
                        if(response.body()!=null)
                            if(response.body().getResponseCode()==200){
                                sharedPrefUtil.addString("mobile", response.body().getData().getPersonalInfo().getMobile());
                                sharedPrefUtil.addString("uid", response.body().getData().getPersonalInfo().getId());
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                ProgressDialogUtil.hideProgressDialog();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {

                           // Log.d("Failure", call.execute().body().toString());
                            Log.d("Error", t.getLocalizedMessage());
                        ProgressDialogUtil.hideProgressDialog();
                        Snackbar.make(loginLayout, "Username/Password Incorrect",Snackbar.LENGTH_LONG).show();
                    }
                });





            }
        });



    }

}
