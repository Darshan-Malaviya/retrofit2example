package com.example.retrofit2example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public String[] calendar_array = new String[] {};
    private List calendarList = new ArrayList<>();
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true,new HingeAnimation());
        /*ViewPagerAdapter adapter = new ViewPagerAdapter(this,calendarList);
        viewPager.setAdapter(adapter);*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.232.95.6/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CalendarApi calendarApi = retrofit.create(CalendarApi.class);

        Call<List<Calendar>> call = calendarApi.getCalendar();
        call.enqueue(new Callback<List<Calendar>>() {
            @Override
            public void onResponse(Call<List<Calendar>> call, Response<List<Calendar>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,String.valueOf(response.code()),Toast.LENGTH_LONG).show();
                    Log.d("Image",String.valueOf(response.code()));
                    return;
                }

                List<Calendar> months = response.body();
                for (Calendar month:months){
                    calendarList.add(month.getSrc());
                    Log.d("Image",month.getSrc());
                }
                viewPagerAdapter=new ViewPagerAdapter(getApplicationContext(),calendarList);
                viewPager.setAdapter(viewPagerAdapter);
            }

            @Override
            public void onFailure(Call<List<Calendar>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error",t.getMessage());
            }
        });

    }
}