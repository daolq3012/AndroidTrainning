package com.fstyle.androidtrainning.widget.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by DaoLQ on 20/04/2017.
 */

public class TouchEventView extends View {

    private Paint mPaint;
    private Path mPath;

    public TouchEventView(Context context) {
        this(context, null);
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttribute();
    }

    private void setAttribute() {
        mPaint = new Paint();
        mPath = new Path();

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.BLACK);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:

                mPath.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                return false;
        }

        // Schedules a repaint.
        invalidate();
        return true;
    }

    public void erase() {
        mPath.reset();
        invalidate();
    }
}
