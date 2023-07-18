package com.example.splitpay;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface apiInterface {
    @POST("/listview")
    Call<LoginResult> excutelogin(@Body HashMap<String,Integer> map);



}
