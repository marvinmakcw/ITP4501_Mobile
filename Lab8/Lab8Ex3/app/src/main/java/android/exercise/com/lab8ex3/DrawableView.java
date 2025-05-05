package android.exercise.com.lab8ex3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawableView extends View {
    Paint paint = new Paint();
    int gCenterX, gCenterY, gRadius, i;

    public DrawableView(Context context) {
        super(context);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /** COMPLETE THIS PART **/
        // Draw a circle with center (gCenterX, gCenterY) and radius ‘gRadius’.
        // .........
        canvas.drawCircle(gCenterX, gCenterY, gRadius, paint);

        i = gRadius - 50;
        while (i > 0) {
           canvas.drawCircle(gCenterX, gCenterY, i, paint);
           i -= 50;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerCount = event.getPointerCount();  // no. of touch points
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (pointerCount == 2)  // Multi-touch
                {
                    /** COMPLETE THIS PART **/
                    // Get the co-ordinates of the points of touch
                    // Find the center (mid-point of the points of touch)
                    // Find the radius of the circle (Hint: use the Pythagoras' theorem,
                    // and use Math.sqrt to find the square root)
                    int x1 = (int) event.getX(0);
                    int y1 = (int) event.getY(0);
                    int x2 = (int) event.getX(1);
                    int y2 = (int) event.getY(1);
                    gCenterX = (x1 + x2) / 2;
                    gCenterY = (y1 + y2) / 2;
                    gRadius = (int) (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) / 2);
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}