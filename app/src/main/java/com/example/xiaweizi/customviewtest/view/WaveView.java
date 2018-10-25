package com.example.xiaweizi.customviewtest.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.WaveView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/18
 *     desc   :
 * </pre>
 */

public class WaveView extends View {

    private static final String TAG = "WaveView::";

    private Paint mPaint;
    private Path mPath = new Path();
    private int mWidth;
    private int mHeight;
    private int mItemWidth = 1400;
    private int offsetX;
    private int offsetY = 100;
    private int mAddValue = 1;
    private ValueAnimator mAnimator;
    private Rect mRect = new Rect();
    private PorterDuffXfermode mXfermode;

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#27d4fd"));
        mPaint.setStyle(Paint.Style.FILL);
    }

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
        setLayerType(LAYER_TYPE_HARDWARE, null);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    private void initAnimator() {
        mAnimator = ValueAnimator.ofInt(0, mItemWidth);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offsetX = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        offsetY += mAddValue;
        if (offsetY >= getHeight() - 100) {
            mAddValue = -1;
        } else if (offsetY < 100) {
            mAddValue = 1;
        }
        int startX = -mItemWidth + offsetX;
        int startY = mHeight - offsetY;
        int controlHeight = mHeight / 3;
        int halfWaveWidth = mItemWidth / 2;
        mPath.moveTo(startX, startY);
        for (int i = startX, j = 0; i < mWidth + mItemWidth; i += mItemWidth, j++) {
            mPath.rQuadTo(halfWaveWidth / 2, -controlHeight, halfWaveWidth, 0);
            mPath.rQuadTo(halfWaveWidth / 2, controlHeight, halfWaveWidth, 0);
        }
        mPath.lineTo(mWidth, mHeight);
        mPath.lineTo(0, mHeight);
        mPath.close();

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.parseColor("#ceb1e2"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2, mPaint);
        mPaint.setXfermode(mXfermode);
        mPaint.setColor(Color.parseColor("#27d4fd"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        initPaint();
        initAnimator();
    }

    public void start() {
        mAnimator.start();
    }

    public void stop() {
        mAnimator.cancel();
    }
}
