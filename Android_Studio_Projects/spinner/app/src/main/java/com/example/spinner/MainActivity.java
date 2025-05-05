package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnconfirm = findViewById(R.id.button);
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView t = (TextView) findViewById(R.id.textView);
                Spinner s = (Spinner) findViewById(R.id.spinner);
                int i = s.getSelectedItemPosition();
                String[] itemname = getResources().getStringArray(R.array.theme_park);
                t.setText("您選擇的景點是：" + itemname[i]);

                ImageView img = (ImageView) findViewById(R.id.imageView);


                switch (i) {
                    case 0:
                        img.setImageResource(R.drawable.op);
                        break;
                    case 1:
                        img.setImageResource(R.drawable.disney);
                        break;
                    case 2:
                        img.setImageResource(R.drawable.lfc);
                        break;
                    case 3:
                        img.setImageResource(R.drawable.altontowers);
                        break;
                    case 4:
                        img.setImageResource(R.drawable.universal);
                        break;
                }
            }
        });
    }
}