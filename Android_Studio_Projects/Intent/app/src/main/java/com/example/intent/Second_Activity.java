package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Second_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        int no2 = bundle.getInt("no2");
        double no = bundle.getDouble("no");
        Log.d("tag111", "no: " + no);
        Log.d("tag111", "no2: " + no2);
        Log.d("tag111", "name: " + name);

        Button btn_back = findViewById(R.id.button2);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}