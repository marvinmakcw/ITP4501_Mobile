package android.exercise.com.lab5ex2;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        startTime = System.currentTimeMillis();
        TextView tvName = findViewById(R.id.TVname);
        TextView tvTrial = findViewById(R.id.TVtrial);
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvTrial.setText(intent.getStringExtra("trial"));
    }

    public void onFinish(View view) {
        finish();
    }

    @Override
    public void finish() {
        long finishTime = System.currentTimeMillis();
        int elapsedTime = (int) (finishTime - startTime) / 1000;
        Intent result = new Intent();
        result.putExtra("playTime", elapsedTime + "");
        setResult(RESULT_OK, result);
        super.finish();
    }
}