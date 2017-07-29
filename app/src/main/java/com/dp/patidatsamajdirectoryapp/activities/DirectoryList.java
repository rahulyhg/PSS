package com.dp.patidatsamajdirectoryapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.adapters.DirectoryListAdapter;
import com.dp.patidatsamajdirectoryapp.network.DirectoryCalls;
import com.dp.patidatsamajdirectoryapp.pojo.directoryUserResponse.LastDatum;
import com.dp.patidatsamajdirectoryapp.pojo.directoryUserResponse.UserResponse;
import com.dp.patidatsamajdirectoryapp.utils.ProgressDialogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DirectoryList extends AppCompatActivity {
    Button back;
    RecyclerView list;
    DirectoryListAdapter listAdapter;
    AutoCompleteTextView search;
    String city,state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_list);
        state = getIntent().getStringExtra("STATE");
        list= (RecyclerView)findViewById(R.id.grid);
        search = (AutoCompleteTextView)findViewById(R.id.search);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DirectoryList.this, DirectoryCityList.class);
                i.putExtra("STATE",state);
                startActivity(i);
            }
        });
        city = getIntent().getStringExtra("CITY");


        ProgressDialogUtil.showProgressDialog(this,"Getting Users...");
        getUsers(city);



//        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//
////                Toast.makeText(DirectoryCityList.this, (String)adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(DirectoryList.this, DirectoryList.class);
//                intent.putExtra("CITY", (String)adapterView.getItemAtPosition(i));
//                startActivity(intent);
//            }
//        });
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

                ArrayList<String> usersList = new ArrayList<String>();

                if (response.isSuccessful()) {
                   listAdapter = new DirectoryListAdapter(response.body().getData().getLastData(),DirectoryList.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DirectoryList.this);
                    list.setLayoutManager(layoutManager);
                    for (LastDatum name :
                            response.body().getData().getLastData()) {
                        String s = name.getName()+" "+name.getLastName();
                        usersList.add(s);

                    }
                    ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(DirectoryList.this,
                            android.R.layout.simple_dropdown_item_1line,usersList);
                    search.setAdapter(searchAdapter);
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
