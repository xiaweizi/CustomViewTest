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
    private static final int TEXT_SIZE = 45;
    private Paint mPaint;
    private Paint mTextPaint;
    private Path mPath;
    private int controlX, controlY;
    private int startX, startY, endX, endY;

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(TEXT_SIZE);
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        startX = 100;
        startY = h -100;
        endX = w - 100;
        endY = h - 100;
        controlX = w / 2;
        controlY = h / 2;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制起点位置
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawPoint(startX, startY, mPaint);
        canvas.drawText("("+startX+","+startY+")", startX, startY + TEXT_SIZE, mTextPaint);
        canvas.drawPoint(endX, endY, mPaint);
        canvas.drawText("("+endX+","+endY+")", endX, endY + TEXT_SIZE, mTextPaint);
        canvas.drawPoint(controlX, controlY, mPaint);
        canvas.drawText("("+controlX+","+controlY+")", controlX, controlY - 50 , mTextPaint);

        mPath.reset();
        mPath.moveTo(startX, startY);
        // 绘制二阶贝塞尔
        mPath.quadTo(controlX, controlY, endX, endY);
        mPaint.setStrokeWidth(5);
        canvas.drawPath(mPath, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawLine(startX, startY, controlX, controlY, mPaint);
        canvas.drawLine(controlX, controlY, endX, endY, mPaint);

        String desc = "二阶贝塞尔曲线";
        float width = mTextPaint.measureText(desc);
        canvas.drawText(desc, getWidth() - width - 20, 120, mTextPaint);
    }

    public void reset() {
        controlX = getWidth() / 2;
        controlY = getHeight() / 2;
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
