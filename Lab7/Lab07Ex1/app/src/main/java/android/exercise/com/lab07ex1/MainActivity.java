package android.exercise.com.lab07ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    String title = "Fund Portfolio";
    String items[] = {"Financials", "Properties", "Utilities", "Others"};
    float data[] = {52, 25, 11, 12};
    int rColor[] = {0xffff0000, 0xffffff00, 0xff32cd32, 0xff880055};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));
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

            Paint titleText = new Paint();
            titleText.setColor(Color.BLACK);
            titleText.setStyle(Paint.Style.FILL);
            titleText.setTextSize(100);
            titleText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));

            Paint legend = new Paint();

            Paint legendText = new Paint();
            legendText.setColor(Color.BLACK);
            legendText.setStyle(Paint.Style.FILL);
            legendText.setTextSize(50);
            legendText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));

            //RectF rec = new RectF(50, 200, getWidth() - 50, getWidth() - 50);
            float startAng = 0;
            float endAng = 0;
            int totalData = 0;
            //c.drawText(title, 50, 100, titleText);
            for (int i = 0; i < data.length; i++) {
                totalData += data[i];
            }
            //int x = 700, y = 1200;
            /*for (int i = 0; i < data.length; i++) {
                paint.setColor(rColor[i]);
                legend.setColor(rColor[i]);
                startAng = endAng;
                endAng = startAng + (360 * data[i] / 100);
                c.drawArc(rec, startAng, endAng - startAng, true, paint);
                c. drawRect(x - 50, y, x - 30, y - 20, legend);
                c.drawText(items[i], x, y, legendText);
                y += 100;
            }*/
            int x = 100, y = 100;
            paint.setColor(Color.GREEN);
            c.drawRect(x, y, x + 200, y + 200, paint);

            paint.setColor(Color.BLUE);
            c.drawLine(x, y + 200, x + 200, y, paint);
        }
    }
}