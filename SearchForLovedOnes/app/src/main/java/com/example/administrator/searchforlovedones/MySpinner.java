package com.example.administrator.searchforlovedones;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Spinner;

@SuppressLint("AppCompatCustomView")
public class MySpinner extends Spinner {
    private int lastX = 0;
    private int lastY = 0;

    private int pos = 0;
    public MySpinner(Context context) {
        super(context);
    }
    public MySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.e("MyButtonLocation", "x:" + x
                + " y:" + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                break;
        }
        return super.onTouchEvent(event);
    }
}
