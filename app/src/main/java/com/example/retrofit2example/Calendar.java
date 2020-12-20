package com.example.retrofit2example;

import com.google.gson.annotations.SerializedName;

public class Calendar {
    @SerializedName("dic")
    private String data;

    public String getData() {
        return data;
    }
}
