package com.example.retrofit2example;

import com.google.gson.annotations.SerializedName;

public class Calendar {

    private String month;
    @SerializedName("src")
    private String src;

    public String getMonth() {
        return month;
    }

    public String getSrc() {
        return src;
    }
}
