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

import com.dp.patidatsamajdirectoryapp.R;
import com.dp.patidatsamajdirectoryapp.mainActivities.MainActivity;
import com.dp.patidatsamajdirectoryapp.utils.SharedPrefUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DirectoryStateList extends AppCompatActivity {


    Button back;
    AutoCompleteTextView search;
    ListView list;
    SharedPrefUtil mSharedPrefUtil;
    Set stateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        list = (ListView) findViewById(R.id.list);
        mSharedPrefUtil=new SharedPrefUtil(DirectoryStateList.this);
        stateList = new HashSet();
        search = (AutoCompleteTextView)findViewById(R.id.search);

        stateList = mSharedPrefUtil.retrieveStringSet("States",new HashSet());
        final ArrayList<String> statesInArray = new ArrayList<String>(stateList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.prototype_cell_list_generic, android.R.id.text1,statesInArray );
        list.setAdapter(adapter);

        ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(DirectoryStateList.this,
                android.R.layout.simple_dropdown_item_1line,statesInArray);
        search.setAdapter(searchAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(DirectoryStateList.this, DirectoryCityList.class);
                    intent.putExtra("STATE", statesInArray.get(i));
                    startActivity(intent);
            }
        });
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DirectoryStateList.this, MainActivity.class));
            }
        });

        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(DirectoryStateList.this, DirectoryCityList.class);
                intent.putExtra("STATE", (String)adapterView.getItemAtPosition(i));
                startActivity(intent);
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
//                Toast.makeText(DirectoryStateList.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    public void setDataOnSpinner(ArrayList<String> data, Spinner s) {
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data) {
//            @Override
//            public boolean isEnabled(int position) {
//                return position != 0;
//            }
//
//            @Override
//            public View getDropDownView(int position, View convertView,
//                                        ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                if (position == 0) {
//                    tv.setTextColor(Color.GRAY);
//                } else {
//                    tv.setTextColor(Color.BLACK);
//                }
//                return view;
//            }
//        };
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(dataAdapter);
//        ProgressDialogUtil.hideProgressDialog();
//    }

//    public void setDataOnSpinner(Set<String> data, Spinner s) {
//        ArrayList<String> dataInArrayList = new ArrayList<>(data);
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataInArrayList) {
//            @Override
//            public boolean isEnabled(int position) {
//                return position != 0;
//            }
//
//            @Override
//            public View getDropDownView(int position, View convertView,
//                                        ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                if (position == 0) {
//                    tv.setTextColor(Color.GRAY);
//                } else {
//                    tv.setTextColor(Color.BLACK);
//                }
//                return view;
//            }
//        };
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        s.setAdapter(dataAdapter);
//        ProgressDialogUtil.hideProgressDialog();
//    }
}



