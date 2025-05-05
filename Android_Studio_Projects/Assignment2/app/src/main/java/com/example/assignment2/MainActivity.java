package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ImageViewHongKong = findViewById(R.id.imageViewHongKong);
        ImageView ImageViewTaipei = findViewById(R.id.imageViewTaipei);
        ImageView ImageViewTokyo = findViewById(R.id.imageViewTokyo);

        ImageViewHongKong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("location",0);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast toast = Toast.makeText(MainActivity.this,"香港天氣", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0, 0);
                toast.show();
            }
        });

        ImageViewTaipei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("location",1);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast toast = Toast.makeText(MainActivity.this,"台北天氣", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0, 0);
                toast.show();
            }
        });

        ImageViewTokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("location",2);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast toast = Toast.makeText(MainActivity.this,"東京天氣", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0, 0);
                toast.show();
            }
        });
    }
}