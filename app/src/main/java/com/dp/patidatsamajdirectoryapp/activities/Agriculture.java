package com.dp.patidatsamajdirectoryapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.adapters.AgricultureAdapter;
import com.dp.patidatsamajdirectoryapp.network.AgricultureCalls;
import com.dp.patidatsamajdirectoryapp.pojo.agricultureResponse.AgricultureResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Agriculture extends AppCompatActivity {
RecyclerView myGrid;
    AgricultureAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture);
        myGrid = (RecyclerView)findViewById(R.id.grid);
        setAgricultureData();
    }


    void setAgricultureData(){


        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://patidarsamajsangthan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AgricultureCalls service = adapter.create(AgricultureCalls.class);

        Call<AgricultureResponse> response =  service.getData();

        response.enqueue(new Callback<AgricultureResponse>() {
            @Override
            public void onResponse(Call<AgricultureResponse> call, Response<AgricultureResponse> response) {
                if (response.isSuccessful()) {
                    myAdapter = new AgricultureAdapter(response.body().getData(),Agriculture.this);
                    Log.d("Response", "onResponse: "+response.body().getData().size());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Agriculture.this);
                    myGrid.setLayoutManager(layoutManager);
                    myGrid.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<AgricultureResponse> call, Throwable t) {
                Toast.makeText(Agriculture.this, "Internet Connection Error", Toast.LENGTH_SHORT).show();
            }
        });






    }
}
