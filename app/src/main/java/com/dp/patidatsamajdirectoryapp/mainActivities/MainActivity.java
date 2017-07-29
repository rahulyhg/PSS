package com.dp.patidatsamajdirectoryapp.mainActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.adapters.MainGridAdapter;
import com.dp.patidatsamajdirectoryapp.network.DirectoryCalls;
import com.dp.patidatsamajdirectoryapp.pojo.directoryStateResponse.LastDatum;
import com.dp.patidatsamajdirectoryapp.pojo.directoryStateResponse.StateResponse;
import com.dp.patidatsamajdirectoryapp.utils.SharedPrefUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView mainGridRecycler;
    MainGridAdapter adapter;
    private List<LastDatum> stateData;
    private Set<String> stateList;
SharedPrefUtil mSharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGridRecycler = (RecyclerView)findViewById(R.id.main_grid);
        adapter = new MainGridAdapter(MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),4);
        mainGridRecycler.setLayoutManager(mLayoutManager);
        mainGridRecycler.setItemAnimator(new DefaultItemAnimator());
        mainGridRecycler.setAdapter(adapter);
        mSharedPrefUtil = new SharedPrefUtil(MainActivity.this);
        getStates();

    }



    void getStates(){
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("http://patidarsamajsangthan.com/")
                //  .client(Utils.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONObject data = new JSONObject();
        try {
            data.put("DataType", "STATE");
            data.put("DataValue", "");
            data.put("lastIndex","0");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        DirectoryCalls service = adapter.create(DirectoryCalls.class);
        Call<StateResponse> response =  service.getStates(data);

        response.enqueue(new Callback<StateResponse>() {
            @Override
            public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {
                if (response.isSuccessful()) {
                    stateData =   response.body().getData().getLastData();
                    stateList = new HashSet<String>();
                    for (int i = 0; i < stateData.size(); i++) {
                        stateList.add(stateData.get(i).getStateName());
                    }
                 mSharedPrefUtil.addStringSet("States",stateList);
                }
            }
            @Override
            public void onFailure(Call<StateResponse> call, Throwable t) {


            }
        });


    }
}
