package com.example.retrofit2example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private Button nextbutton,prevbutton,descbutton;
    private String[] calendar_urls = new String[]{
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/01.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/02.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/03.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/04.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/05.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/06.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/07.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/08.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/09.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/10.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/11.jpg",
            "https://kalakunjmandir.com/wp-content/uploads/2020/12/cal/12.jpg",
    };
    ViewPagerAdapter viewPagerAdapter;
    private Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true,new HingeAnimation());
        ViewPagerAdapter adapter = new ViewPagerAdapter(this,calendar_urls);
        viewPager.setAdapter(adapter);

        nextbutton =findViewById(R.id.nextbutton);
        prevbutton =findViewById(R.id.prevbutton);
        descbutton =findViewById(R.id.descbutton);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
        prevbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.232.95.6/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Call<List<Calendar>>[] call = new Call[]{null};
        CalendarApi calendarApi = retrofit.create(CalendarApi.class);
        descbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (viewPager.getCurrentItem()){
                    case (0):
                        id = 12;
                        break;
                    default:
                        id = viewPager.getCurrentItem();
                        break;
                }
                call[0] = calendarApi.getCalendarData(id);
                call[0].enqueue(new Callback<List<Calendar>>() {
                    @Override
                    public void onResponse(Call<List<Calendar>> call, Response<List<Calendar>> response) {
                        if (!response.isSuccessful()) {
                            Log.d("Code = ",String.valueOf(response.code()));
                            Toast.makeText(getApplicationContext(),"Code =" + String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<Calendar> calendars = response.body();
                        for (Calendar calendar:calendars){
                            Toast.makeText(getApplicationContext(),String.valueOf(calendar.getData()),Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Calendar>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),String.valueOf(t.getMessage()),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}