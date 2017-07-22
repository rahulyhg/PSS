package com.dp.patidatsamajdirectoryapp.network;

import com.dp.patidatsamajdirectoryapp.pojo.login.Login;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ps11 on 03/07/17.
 */

public interface LoginCalls {

    @FormUrlEncoded
    @POST("/appAPI/login.php")
    Call<Login> login(@Field("Login") JSONObject details);


}
