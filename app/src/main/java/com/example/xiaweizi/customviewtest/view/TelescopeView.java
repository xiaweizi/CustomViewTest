package com.example.xiaweizi.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.xiaweizi.customviewtest.R;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.TelescopeView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/18
 *     desc   :
 * </pre>
 */

public class TelescopeView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private BitmapShader mShader;

    private int mTouchX;
    private int mTouchY;

    public TelescopeView(Context context) {
        this(context, null);
    }

    public TelescopeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TelescopeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.background);
        mShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint.setShader(mShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTouchX != -1 && mTouchY != -1) {
            mTouchX = mTouchX <= 200 ? 200 : mTouchX;
            mTouchX = mTouchX >= getWidth() - 200 ? getWidth() - 200 : mTouchX;
            mTouchY = mTouchY <= 200 ? 200 : mTouchY;
            mTouchY = mTouchY >= getHeight() - 200 ? getHeight() - 200 : mTouchY;
            canvas.drawCircle(mTouchX, mTouchY, 200, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = (int) event.getX();
                mTouchY = (int) event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                mTouchX = (int) event.getX();
                mTouchY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mTouchX = -1;
                mTouchY = -1;
                break;

        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

}
