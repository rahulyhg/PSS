package com.dp.patidatsamajdirectoryapp.network;

import com.dp.patidatsamajdirectoryapp.pojo.agricultureResponse.AgricultureResponse;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ps11 on 22/07/17.
 */

public interface AgricultureCalls {

    @POST("/appAPI/agricultureList.php")
    Call<AgricultureResponse> getData();

}
