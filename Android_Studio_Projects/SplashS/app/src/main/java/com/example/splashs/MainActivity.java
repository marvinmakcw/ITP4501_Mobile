package com.example.splashs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        String text = "I want green and red text.\nyellow background";
        SpannableString ss = new SpannableString(text);

        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.GREEN);
        BackgroundColorSpan bcsYellow = new BackgroundColorSpan(Color.YELLOW);

        ss.setSpan(fcsGreen, 7, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsRed, 17, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(bcsYellow, 27, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(2f), 7, 12,0);
        textView.setText(ss);

    }
}