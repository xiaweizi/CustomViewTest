package com.example.xiaweizi.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.NormalGestureTrackView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/16
 *     desc   :
 * </pre>
 */

public class NormalGestureTrackView extends View {

    private Paint mPaint;
    private Path mPath = new Path();

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
    }


    public NormalGestureTrackView(Context context) {
        this(context, null);
    }

    public NormalGestureTrackView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NormalGestureTrackView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        initPaint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                postInvalidate();
                return true;
        }
        return true;
    }

    public void reset() {
        mPath.reset();
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
