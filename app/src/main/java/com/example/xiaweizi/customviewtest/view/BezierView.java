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
 *     class  : com.example.xiaweizi.customviewtest.view.BezierView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/16
 *     desc   :
 * </pre>
 */

public class BezierView extends View {

    private static final String TAG = "BezierView::";

    private Paint mPaint;
    private Path mPath;
    private int controlX = 250;
    private int controlY = 250;

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
    }

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        initPaint();
        mPath = new Path();
        setClickable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int startX = 100, startY = 500;
        int endX = 500, endY = 500;

        // 绘制起点位置
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        canvas.drawPoint(startX, startY, mPaint);
        canvas.drawPoint(endX, endY, mPaint);
        canvas.drawPoint(controlX, controlY, mPaint);

        mPath.reset();
        mPath.moveTo(startX, startY);
        // 绘制二阶贝塞尔取消
        mPath.quadTo(controlX, controlY, endX, endY);
        mPaint.setStrokeWidth(5);
        canvas.drawPath(mPath, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawLine(startX, startY, controlX, controlY, mPaint);
        canvas.drawLine(controlX, controlY, endX, endY, mPaint);
    }

    public void reset() {
        controlX = 250;
        controlY = 250;
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        controlX = (int) event.getX();
        controlY = (int) event.getY();
        invalidate();
        return super.dispatchTouchEvent(event);
    }
}
