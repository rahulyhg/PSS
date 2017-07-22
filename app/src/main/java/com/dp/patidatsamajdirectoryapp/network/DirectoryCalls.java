package com.dp.patidatsamajdirectoryapp.network;

import com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse.CityResponse;
import com.dp.patidatsamajdirectoryapp.pojo.directoryStateResponse.StateResponse;
import com.dp.patidatsamajdirectoryapp.pojo.directoryUserResponse.UserResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ps11 on 16/07/17.
 */

public interface DirectoryCalls {

    @FormUrlEncoded
    @POST("/appAPI/directory.php")
    Call<StateResponse> getStates(@Field("Directory")JSONObject data);

    @FormUrlEncoded
    @POST("/appAPI/directory.php")
    Call<CityResponse> getCities(@Field("Directory")JSONObject data);

    @FormUrlEncoded
    @POST("/appAPI/directory.php")
    Call<UserResponse> getUsers(@Field("Directory")JSONObject data);


}
