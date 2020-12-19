package com.example.retrofit2example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CalendarApi {

    @GET("calendar")
    Call<List<Calendar>> getCalendar();
}
