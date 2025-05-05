package android.exercise.com.lab8ex1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawableView extends View {
    Paint paint = new Paint();
    RectF rect = new RectF();
    float x1, y1, x2, y2;

    public DrawableView(Context context) {
        super(context);
        paint.setAntiAlias(true);
        x1 = x2 = y1 = y2 = 0;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect.set(x1, y1, x2, y2);
        canvas.drawRect(rect, paint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            paint.setColor(Color.YELLOW);
            x1 = event.getX();
            y1 = event.getY();
            x2 = event.getX();
            y2 = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x2 = event.getX();
            y2 = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            paint.setColor(Color.BLUE);
            x2 = event.getX();
            y2 = event.getY();
        }

        Log.d("Event", "point: " + x1 + " " + y1 + " " + x2 + " " + y2);
        invalidate();
        return true;
    }
}