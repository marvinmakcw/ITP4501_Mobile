package android.exercise.com.simpleadder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleAdderActivity extends AppCompatActivity {

    private EditText etValue1;
    private EditText etValue2;
    private TextView tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adder);

        etValue1 = findViewById(R.id.etValue1);
        etValue2 = findViewById(R.id.etValue2);
        tvAnswer = findViewById(R.id.tvAnswer);
    }

    public void onClick(View view) {
        double value1 = Double.parseDouble(etValue1.getText().toString());
        double value2 = Double.parseDouble(etValue2.getText().toString());

        tvAnswer.setText(value1 + " + " + value2 + " = " + (value1 + value2));
    }

}