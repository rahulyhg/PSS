package com.dp.patidatsamajdirectoryapp.network;

import com.dp.patidatsamajdirectoryapp.pojo.register.RegisterResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ps11 on 30/07/17.
 */

public interface AddMemberCalls {
    @FormUrlEncoded
    @POST("/appAPI/registration.php")
    Call<RegisterResponse> registerUser(@Field("Registration")JSONObject data);
}
