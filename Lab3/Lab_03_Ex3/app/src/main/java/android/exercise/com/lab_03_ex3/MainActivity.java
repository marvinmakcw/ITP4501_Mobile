package android.exercise.com.lab_03_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber1, etNumber2;
    private RadioButton add, minus, times, divided;
    private Button compute;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumber1 = findViewById(R.id.editText);
        etNumber2 = findViewById(R.id.editText2);
        add = findViewById(R.id.radioButton);
        minus = findViewById(R.id.radioButton2);
        times = findViewById(R.id.radioButton3);
        divided = findViewById(R.id.radioButton4);
        compute = findViewById(R.id.button);
        answer = findViewById(R.id.textView5);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etnum1 = etNumber1.getText().toString();
                double num1 = Double.parseDouble(etnum1);

                String etnum2 = etNumber2.getText().toString();
                double num2 = Double.parseDouble(etnum2);

                double ans;
                if(add.isChecked()){
                    ans = num1 + num2;
                }else if(minus.isChecked()){
                    ans = num1 - num2;
                }else if(times.isChecked()){
                    ans = num1 * num2;
                }else {
                    ans = num1 / num2;
                }
                answer.setText("Answer = " +Double.toString(ans));
            }
        });
    }
}