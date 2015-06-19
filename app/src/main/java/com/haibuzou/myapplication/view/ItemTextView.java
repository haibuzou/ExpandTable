package com.haibuzou.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/6/17.
 */
public class ItemTextView extends TextView {

    private Paint paint;
    private int mwidth;
    private int mheight;

    public ItemTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(0, mheight - 1, mwidth - 1, mheight - 1, paint);
        canvas.drawLine(mwidth - 1, mheight - 1, mwidth - 1, 0, paint);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mwidth = w;
        mheight = h;
    }
}
