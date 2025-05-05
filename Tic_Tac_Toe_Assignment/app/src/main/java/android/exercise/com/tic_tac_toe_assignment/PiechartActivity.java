package android.exercise.com.tic_tac_toe_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class PiechartActivity extends AppCompatActivity {
    String title = "Your Winning Status";
    String items[] = {"Win", "Lose", "Draw"};
    int[] records = new int[3];
    int rColor[] = {0xffff0000, 0xffffff00, 0xff32cd32, 0xff880055};
    float countRecordf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            //String j = (String) bundle.get("name");
            records[0] = (int) bundle.get("Win");
            records[1] = (int) bundle.get("Lose");
            records[2] = (int) bundle.get("Draw");
            countRecordf = (int) bundle.get("Record");
        }


    }

    class Panel extends View {
        public Panel(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas c) {
            super.onDraw(c);

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            c.drawPaint(paint);

            RectF rec = new RectF(50, 250, getWidth() - 50, getWidth() + 150);
            float startAng = 0;
            float endAng = 0;
            int totalData = 0;
            for (int i = 0; i < records.length; i++) {
                totalData += records[i];
            }

            for (int i = 0; i < records.length; i++) {
                paint.setColor(rColor[i]);
                startAng = endAng;
                endAng = startAng + (360 * records[i] / countRecordf);
                c.drawArc(rec, startAng, endAng - startAng, true, paint);
            }

            Paint legendText = new Paint();
            legendText.setColor(Color.BLACK);
            legendText.setStyle(Paint.Style.FILL);
            legendText.setTextSize(50);
            legendText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));

            Paint titleText = new Paint();

            titleText.setColor(Color.BLACK);
            titleText.setStyle(Paint.Style.FILL);
            titleText.setTextSize(100);
            titleText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));

            Paint legend = new Paint();
            int x = 700, y = 1400;
            c.drawText(title, 50, 100, titleText);
            for (int i = 0; i < 3; i++) {
                legend.setColor(rColor[i]);
                c. drawRect(x - 50, y, x - 30, y - 20, legend);
                c.drawText(items[i], x, y, legendText);
                y += 100;
            }
        }
    }
}