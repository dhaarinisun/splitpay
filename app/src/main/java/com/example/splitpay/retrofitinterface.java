package com.example.splitpay;

import java.util.HashMap;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface retrofitinterface {
    @POST("/login")
    Call<LoginResult>executeLogin(@Body HashMap<String,String>map);

    @POST("/signup")
    Call<Void>executeSignup(@Body HashMap<String,String>map);

    //post request ,call loginResult . then we are passing a hashmap with name and email.

}
