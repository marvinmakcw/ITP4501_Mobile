package android.exercise.com.lab5ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 3434;
    private int trial = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressWarnings("deprecation")
    public void clickHandler(View view){
        EditText etName = findViewById(R.id.ETname);

        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("name", etName.getText().toString());
        ++trial;
        i.putExtra("trial", "" + trial);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("playTime")) {
                TextView tv = findViewById(R.id.TVmsg);
                tv.setText("You have played " + data.getExtras().getString("playTime") + "second(s)");
            }
        }
    }
}