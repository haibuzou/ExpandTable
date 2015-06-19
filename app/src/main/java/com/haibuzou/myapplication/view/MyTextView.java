package com.haibuzou.myapplication.view;

import android.content.Context;
import android.widget.TextView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by AAA on 2015/6/16.
 */
public class MyTextView extends TextView {


    private Paint txtpaint;
    private Paint linepaint;
    private Paint tripaint;
    private Path path;
    private Boolean isDraw = false;
    private float textSize;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        txtpaint = new Paint();
        path = new Path();
        txtpaint.setColor(Color.BLACK);
        txtpaint.setTextSize(textSize);
        txtpaint.setTextAlign(Align.LEFT);

        linepaint = new Paint();
        linepaint.setColor(Color.LTGRAY);
        linepaint.setStyle(Paint.Style.STROKE);

        tripaint = new Paint();
        tripaint.setColor(Color.GRAY);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), linepaint);
        int len = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                7, getResources().getDisplayMetrics());
        if (isDraw) {
            path.moveTo(getWidth() / 2, getHeight() - len);
            path.lineTo(getWidth() / 2 - len, getHeight());
            path.lineTo(getWidth() / 2 + len, getHeight());
            canvas.drawPath(path, tripaint);
        }
        super.onDraw(canvas);
    }

    public void isDraw(boolean isDraw) {
        this.isDraw = isDraw;
        postInvalidate();
    }


}
