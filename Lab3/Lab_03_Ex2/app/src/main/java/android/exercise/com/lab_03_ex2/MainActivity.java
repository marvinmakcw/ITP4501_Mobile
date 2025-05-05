package android.exercise.com.lab_03_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbLatte, rbMocha, rbCappuccino;
    private CheckBox cbCream, cbSugar;
    private Button btPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cbCream = findViewById(R.id.cbCream);
        cbSugar = findViewById(R.id.cbSugar);
        rbLatte = findViewById(R.id.rbLatte);
        rbMocha = findViewById(R.id.rbMocha);
        rbCappuccino = findViewById(R.id.rbCappuccino);
        btPay = findViewById(R.id.btPay);
        btPay.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                String msg = "";
                if(rbLatte.isChecked()){
                    msg = "Latte Coffee";
                }else if (rbMocha.isChecked()){
                    msg = "Mocha Coffee";
                }else{
                    msg = "Cappuccino Coffee";
                }
                if(cbCream.isChecked())
                    msg += " & cream";
                if(cbSugar.isChecked())
                    msg += " & sugar";
                Toast.makeText(getApplicationContext(), msg,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}