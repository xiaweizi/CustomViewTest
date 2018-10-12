package com.example.xiaweizi.customviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.PaintView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/12
 *     desc   :
 * </pre>
 */

public class PaintView extends View {

    private static final String TAG = "PaintView:";

    private Paint mPaint;

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(70);
    }


    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int baseLineX = 20;
        int baseLineY = 100;
        int right = 0;
        mPaint.setColor(Color.RED);
        // 基线
        String str = "abcdefg 饕餮 ";
        canvas.drawText(str, baseLineX, baseLineY, mPaint);
        Rect rect = new Rect();
        mPaint.getTextBounds(str, 0, str.length(), rect);
        Log.i(TAG, "onDraw: rect:\t" + rect.toShortString());
        right = rect.right;

        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        canvas.drawLine(baseLineX, baseLineY, baseLineX + right, baseLineY, mPaint);
        int top = baseLineY + fontMetricsInt.top;
        canvas.drawLine(baseLineX, top, baseLineX + right, top, mPaint);
        int bottom = baseLineY + fontMetricsInt.bottom;
        canvas.drawLine(baseLineX, bottom, baseLineX + right, bottom, mPaint);
        int height = bottom - top;
        Log.i(TAG, "onDraw: height:\t" + height);


        mPaint.setColor(Color.BLUE);
        int minTop = rect.top + baseLineY;
        canvas.drawLine(baseLineX, minTop, baseLineX + right, minTop, mPaint);
        int minBottom = rect.bottom + baseLineY;
        canvas.drawLine(baseLineX, minBottom, baseLineX + right, minBottom, mPaint);
    }
}
