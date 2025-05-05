package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnjump = (Button)findViewById(R.id.button);
        btnjump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText adultNum = (EditText)findViewById(R.id.adultNum);
                String adultnumtext = adultNum.getText().toString();
                int adultNum1 = Integer.parseInt(adultnumtext);

                EditText childNum = (EditText)findViewById(R.id.childNum);
                String childnumtext = childNum.getText().toString();
                int childNum1 = Integer.parseInt(childnumtext);



                Intent intent = new Intent(MainActivity.this,Second_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("adult", adultNum1);
                bundle.putInt("child", childNum1);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}