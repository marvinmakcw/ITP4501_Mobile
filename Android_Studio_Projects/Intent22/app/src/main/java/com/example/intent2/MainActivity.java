package com.example.intent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnphone = findViewById(R.id.btnphone);
        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri number = Uri.parse("tel:22652164");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });

        TextView txtPhone = findViewById(R.id.txtphone);
        txtPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:2265 2164");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }
        });
        Button btnmap = findViewById(R.id.btnmap);
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Map point based on address
                //Uri location = Uri.parse("geo:0,0?q=2 on shing street");
                // Or map point based on latitude/longitude
                Uri location = Uri.parse("geo:0,0?q=2 on shing street");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        Button btnwebsite = findViewById(R.id.btnwebsite);
        btnwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri website = Uri.parse("http://www.google.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, website);
                startActivity(webIntent);
            }
        });
        Button btnemail = findViewById(R.id.btnemail);
        btnemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                String[] CC = {"s2081984@gmail.com"};
                emailIntent.setData(Uri.parse("mailto: s2081984@student.hkct.edu.hk"));
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "意見反映");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "陳大文服務很好");
                startActivity(emailIntent);
            }
        });
    }
}