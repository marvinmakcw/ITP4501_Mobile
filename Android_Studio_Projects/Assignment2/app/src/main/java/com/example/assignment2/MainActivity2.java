package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {
    ViewPager vp;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();
        int location = bundle.getInt("location");

        //隱藏動作欄
        getSupportActionBar().hide();

        TextView weather = findViewById(R.id.weather);
        TextView weather1 = findViewById(R.id.weather1);
        ImageView home = findViewById(R.id.imageViewHome);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

        switch (location){
            case 0:
                weather.setText("香港天氣");
                myAdapter = new MyAdapter(this);
                myAdapter.location = getIntent().getIntExtra("location",0);
                vp = (ViewPager)findViewById(R.id.ViewPager2);
                vp.setAdapter(myAdapter);
                weather1.setText("天氣概況:\n" +
                        "一股強風程度的偏東氣流會在未來一兩日影響廣東沿岸。覆蓋華南的雲層預料會在下週中期逐漸轉薄，下週後期該區日間天氣炎熱。另外，有熱帶氣旋會在未來數天橫過菲律賓以東海域。");
                break;
            case 1:
                weather.setText("台北天氣");
                myAdapter = new MyAdapter(this);
                myAdapter.location = getIntent().getIntExtra("location",1);
                vp = (ViewPager)findViewById(R.id.ViewPager2);
                vp.setAdapter(myAdapter);
                weather1.setText("天氣概況:\n" +
                        "局部多雲，可能出現大量灰塵和輕微薄霧。受東北風影響，天氣稍轉涼，清晨溫度會降低，未來數天會有雨。");
                break;
            case 2:
                weather.setText("東京天氣");
                myAdapter = new MyAdapter(this);
                myAdapter.location = getIntent().getIntExtra("location",2);
                vp = (ViewPager)findViewById(R.id.ViewPager2);
                vp.setAdapter(myAdapter);
                weather1.setText("天氣概況:\n" +
                        "多雲有短暫時間降雨，下星期部分天晴轉間歇性多雲。局部多雲，吹東北風 ");
                break;
        }
    }
}