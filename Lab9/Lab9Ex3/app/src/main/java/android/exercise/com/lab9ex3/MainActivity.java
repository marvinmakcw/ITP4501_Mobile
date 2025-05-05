package android.exercise.com.lab9ex3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btAlert;
    Button btToast;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        btAlert = findViewById(R.id.btAlert);
        btAlert.setOnClickListener(this);
        btToast = findViewById(R.id.btToast);
        btToast.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == btAlert) {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.about)
                    .setTitle("AlertDialog Title")
                    .setMessage("This is a AlertDialog message")
                    .setPositiveButton("Negative", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dlg, int position) {
                            //do nothing, just close its own
                        }
                    })                    // Complete for Neutral button and Negative button
                    .setNeutralButton("Positive", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dlg, int position) {
                            //do nothing, just close its own
                        }
                    })
                    .setNegativeButton("Neutral", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dlg, int position) {
                            //do nothing, just close its own
                        }
                    })
                    // .......
                    .show();
        } else {
            Toast.makeText(getApplicationContext(),"Hi! This is a Toast",
                    Toast.LENGTH_LONG).show();
            /*** Make a LONG Toast ***/
            /*** COMPLETE THIS PART ***/
        }
    }
}