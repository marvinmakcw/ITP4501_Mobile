package android.exercise.com.lab07ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout;
    boolean alarmOn = false;
    Button alarm;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm = findViewById(R.id.button);
        layout = findViewById(R.id.linearLayout1);
        AlarmView alarmView = new AlarmView(MainActivity.this);
        layout.addView(alarmView);
    }


    class AlarmView extends View {
        boolean flash = true;

        public AlarmView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.BLACK);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(getWidth() / 2, 200, 65, paint);

            if (!alarmOn) {
                paint.setColor(Color.YELLOW);
                canvas.drawCircle(getWidth() / 2, 200, 60, paint);
            } else if (flash) {
                paint.setColor(Color.RED);
                canvas.drawCircle(getWidth() / 2, 200, 60, paint);
            } else {
                paint.setColor(Color.BLACK);
                canvas.drawCircle(getWidth() / 2, 200, 60, paint);

            }
            flash = !flash;
            this.postInvalidateDelayed(1000); //delay for 1 second
        } // end onDraw
    } // end class AlarmView

    public void alarmOnOff(View v) {
        alarmOn = !alarmOn;
        if (alarmOn) {
            alarm.setText("ALARM OFF");
        }else {
            alarm.setText("ALARM ON");
        }
    }
}