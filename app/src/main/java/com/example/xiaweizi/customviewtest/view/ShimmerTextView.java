package com.example.xiaweizi.customviewtest.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.ShimmerTextView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/18
 *     desc   :
 * </pre>
 */

public class ShimmerTextView extends android.support.v7.widget.AppCompatTextView {

    private Paint mPaint;
    private int mDx;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;

    public ShimmerTextView(Context context) {
        this(context, null);
    }

    public ShimmerTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShimmerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        mPaint = getPaint();
        int length = (int) mPaint.measureText(getText().toString());
        mMatrix = new Matrix();
        createAnimator(length);
        createLinearGradient(length);
    }

    private void createAnimator(int length) {
        ValueAnimator animator = ValueAnimator.ofInt(0, 2 * length);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.start();
    }

    private void createLinearGradient(int length) {
        mLinearGradient = new LinearGradient(-length, 0, 0, 0, new int[]{
                getCurrentTextColor(), 0xff00ff00, getCurrentTextColor()},
                new float[]{
                0, 0.5f, 1
                }, Shader.TileMode.CLAMP
        );
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mMatrix.setTranslate(mDx, 0);
        mLinearGradient.setLocalMatrix(mMatrix);
        super.onDraw(canvas);
    }
}
