package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Second_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);

        Bundle bundle = getIntent().getExtras();
        int adult = bundle.getInt("adult");
        int child = bundle.getInt("child");

        TextView answer = findViewById(R.id.answer);
        TextView adult1 = findViewById(R.id.adult1);
        TextView child1 = findViewById(R.id.child1);

        int adulttotal = adult * 400;
        adult1.setText("成人門票"+adult+"張 - 共$"+adulttotal);
        int childtotal = child * 200;
        child1.setText("小童門票"+child+"張 - 共$"+childtotal);
        int totalprice = adulttotal + childtotal;
        answer.setText("total:"+totalprice);


        Button btn_back = findViewById(R.id.button2);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}