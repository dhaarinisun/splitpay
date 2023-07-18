package com.example.splitpay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class page extends MainActivity{

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    private String url = "";
    Retrofit retrofity = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

HashMap<String,Integer> pending = new HashMap<>();

    ListView list=fi





        }
