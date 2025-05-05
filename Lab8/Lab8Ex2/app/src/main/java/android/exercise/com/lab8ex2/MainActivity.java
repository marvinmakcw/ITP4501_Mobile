package android.exercise.com.lab8ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    DrawableView drawView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new DrawableView(this);
        setContentView(drawView);
        drawView.requestFocus();
    }
}