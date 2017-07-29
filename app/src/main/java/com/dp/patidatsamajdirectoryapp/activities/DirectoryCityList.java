package com.dp.patidatsamajdirectoryapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.network.DirectoryCalls;
import com.dp.patidatsamajdirectoryapp.network.Utils;
import com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse.CityResponse;
import com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse.LastDatum;
import com.dp.patidatsamajdirectoryapp.utils.ProgressDialogUtil;

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
    Button back;
    List<LastDatum> cityData;
    ArrayList<String> cityList;
    AutoCompleteTextView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_city_list);
        final String stateName = getIntent().getStringExtra("STATE");
        list = (ListView) findViewById(R.id.list);
        search = (AutoCompleteTextView)findViewById(R.id.search);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DirectoryCityList.this, DirectoryStateList.class));
            }
        });

            getCities(stateName);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DirectoryCityList.this, DirectoryList.class);
                intent.putExtra("CITY", cityList.get(i));
                intent.putExtra("STATE", stateName);
                startActivity(intent);
            }
        });


        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


//                Toast.makeText(DirectoryCityList.this, (String)adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DirectoryCityList.this, DirectoryList.class);
                intent.putExtra("CITY", (String)adapterView.getItemAtPosition(i));
                startActivity(intent);
            }
        });
    }

    private void getCities(String stateName) {
ProgressDialogUtil.showProgressDialog(DirectoryCityList.this, "Getting Cities...");

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
                    for (int i = 0; i < cityData.size(); i++) {
                        cityList.add(cityData.get(i).getCity());
                    }
                    if(cityList.size()>0)
                    {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DirectoryCityList.this,
                                R.layout.prototype_cell_list_generic, android.R.id.text1,cityList );
                        ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(DirectoryCityList.this,
                                android.R.layout.simple_dropdown_item_1line,cityList);
                        search.setAdapter(searchAdapter);
                        list.setAdapter(adapter);
                        ProgressDialogUtil.hideProgressDialog();
                    }
                }
            }
            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                ProgressDialogUtil.hideProgressDialog();
                Toast.makeText(DirectoryCityList.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
