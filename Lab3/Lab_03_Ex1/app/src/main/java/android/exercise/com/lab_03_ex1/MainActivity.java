package android.exercise.com.lab_03_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvUserName;
    private EditText etUserName;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserName = findViewById(R.id.etUserName);
    }

    public void onClick(View v) {
        String userName = etUserName.getText().toString();
        Toast.makeText(getApplicationContext(), "Hello " + userName,
                Toast.LENGTH_SHORT).show();
    }

}