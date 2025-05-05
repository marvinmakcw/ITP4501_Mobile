package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnjump = (Button)findViewById(R.id.button);
        btnjump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Second_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("no", 9.9);
                bundle.putInt("no2", 2);
                bundle.putString("name", "Marvin");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}