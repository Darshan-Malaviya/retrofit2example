package com.example.retrofit2example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textv);

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
                    textView.setText("Code :" + response.code());
                }
                List<Calendar> months = response.body();
                for (Calendar month:months){
                    String content = "";
                    content += "month = " + month.getMonth() + "\n";
                    content += "src = " + month.getSrc() + "\n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Calendar>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }
}