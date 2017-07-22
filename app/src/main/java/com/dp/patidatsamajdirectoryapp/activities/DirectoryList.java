package com.dp.patidatsamajdirectoryapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.adapters.DirectoryListAdapter;
import com.dp.patidatsamajdirectoryapp.network.DirectoryCalls;
import com.dp.patidatsamajdirectoryapp.pojo.directoryUserResponse.UserResponse;
import com.dp.patidatsamajdirectoryapp.utils.ProgressDialogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DirectoryList extends AppCompatActivity {

    RecyclerView list;
    DirectoryListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_list);

        list= (RecyclerView)findViewById(R.id.grid);

        String city = getIntent().getStringExtra("CITY");
        ProgressDialogUtil.showProgressDialog(this,"Getting Users...");
        getUsers(city);
        Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
    }

    private void getUsers(String city) {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://patidarsamajsangthan.com/")
                //  .client(Utils.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObject data = new JSONObject();
        try {
            data.put("DataType", "USER");
            data.put("DataValue", city);
            data.put("lastIndex","10");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DirectoryCalls service = adapter.create(DirectoryCalls.class);
        Call<UserResponse> response =  service.getUsers(data);

        response.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                   listAdapter = new DirectoryListAdapter(response.body().getData().getLastData(),DirectoryList.this);
                    Log.d("Response", "onResponse: "+response.body().getData().getLastData().size());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DirectoryList.this);
                    list.setLayoutManager(layoutManager);
                    list.setAdapter(listAdapter);
                    ProgressDialogUtil.hideProgressDialog();
                }
                else{
                    Toast.makeText(DirectoryList.this, "Error Occured", Toast.LENGTH_SHORT).show();
                    ProgressDialogUtil.hideProgressDialog();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                ProgressDialogUtil.hideProgressDialog();
                Toast.makeText(DirectoryList.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
