package com.dp.patidatsamajdirectoryapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.network.DirectoryCalls;
import com.dp.patidatsamajdirectoryapp.network.Utils;
import com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse.CityResponse;
import com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse.LastDatum;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DirectoryCityList extends AppCompatActivity {
ListView list;
    List<LastDatum> cityData;
    ArrayList<String> cityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_city_list);
        String stateName = getIntent().getStringExtra("STATE");
        list = (ListView) findViewById(R.id.list);

            getCities(stateName);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DirectoryCityList.this, DirectoryList.class);
                intent.putExtra("CITY", cityList.get(i));
                startActivity(intent);
            }
        });

    }

    private void getCities(String stateName) {

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
                    cityData =   response.body().getData().getLastData();
                    cityList = new ArrayList<String>();
                    cityList.add("Cities");
                    for (int i = 0; i < cityData.size(); i++) {
                        cityList.add(cityData.get(i).getCity());
                    }
                    if(cityList.size()>0)
                    {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DirectoryCityList.this,
                                android.R.layout.simple_list_item_1, android.R.id.text1,cityList );
                        list.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
             //   ProgressDialogUtil.hideProgressDialog();
                Toast.makeText(DirectoryCityList.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
