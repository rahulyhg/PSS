package com.dp.patidatsamajdirectoryapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.network.DirectoryCalls;
import com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse.CityResponse;
import com.dp.patidatsamajdirectoryapp.pojo.directoryStateResponse.LastDatum;
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

public class Directory extends AppCompatActivity {


List<LastDatum> stateData;
    List<com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse.LastDatum> cityData;
    ArrayList<String> cityList;
    Spinner state,city;
    Button search;
    String selectedCity;
        SharedPrefUtil mSharedPrefUtil;
    Set stateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        state = (Spinner) findViewById(R.id.state);
        city = (Spinner) findViewById(R.id.city);
        search = (Button) findViewById(R.id.search);
        mSharedPrefUtil=new SharedPrefUtil(Directory.this);
        ProgressDialogUtil.showProgressDialog(this,"Getting States");
        stateList = new HashSet();
        stateList = mSharedPrefUtil.retrieveStringSet("States",new HashSet());
        setDataOnSpinner(stateList,state);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Directory.this, DirectoryList.class);
                i.putExtra("CITY", selectedCity);
                startActivity(i);

            }
        });



       // getStates();

       state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               ArrayList<String> statesInArrayList = new ArrayList(stateList);
               String stateName = statesInArrayList.get(i);

               if (!stateName.equals("States")) {
                   getCities(stateName);
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            selectedCity = cityList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void getCities(String stateName) {

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://patidarsamajsangthan.com/")
                //  .client(Utils.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
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
                    setDataOnSpinner(cityList,city);
                }
            }
            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                ProgressDialogUtil.hideProgressDialog();
                Toast.makeText(Directory.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

//    public void getStates() {
//
//        Retrofit adapter = new Retrofit.Builder()
//                .baseUrl("http://patidarsamajsangthan.com/")
//              //  .client(Utils.getUnsafeOkHttpClient())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        JSONObject data = new JSONObject();
//        try {
//            data.put("DataType", "STATE");
//            data.put("DataValue", "");
//            data.put("lastIndex","0");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        DirectoryCalls service = adapter.create(DirectoryCalls.class);
//        Call<StateResponse> response =  service.getStates(data);
//
//        response.enqueue(new Callback<StateResponse>() {
//            @Override
//            public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {
//                if (response.isSuccessful()) {
//                stateData =   response.body().getData().getLastData();
//                    stateList = new ArrayList<String>();
//                    stateList.add("States");
//                    for (int i = 0; i < stateData.size(); i++) {
//                        stateList.add(stateData.get(i).getStateName());
//                    }
//                    //setDataOnSpinner(stateList,state);
//                }
//            }
//            @Override
//            public void onFailure(Call<StateResponse> call, Throwable t) {
//                    ProgressDialogUtil.hideProgressDialog();
//                Toast.makeText(Directory.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void setDataOnSpinner(ArrayList<String> data, Spinner s) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(dataAdapter);
        ProgressDialogUtil.hideProgressDialog();
    }

    public void setDataOnSpinner(Set<String> data, Spinner s) {
        ArrayList<String> dataInArrayList = new ArrayList<>(data);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataInArrayList) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(dataAdapter);
        ProgressDialogUtil.hideProgressDialog();
    }
}



