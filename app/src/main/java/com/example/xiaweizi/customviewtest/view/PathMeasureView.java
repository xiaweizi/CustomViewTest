package com.example.xiaweizi.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.PathMeasureView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/08
 *     desc   :
 * </pre>
 */

public class PathMeasureView extends View {
    private static final String TAG = "PathMeasureView::";
    private Paint mPaint;
    private Path mPath;
    private Path mDstPath;
    private PathMeasure mPathMeasure;
    private float mCurValue;
    private MyHandler mHandler;

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.RED);
    }


    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mHandler = new MyHandler(this);
        initView(context, attrs, defStyle);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        initPaint();
        mPath = new Path();
        mDstPath = new Path();

        mPath.addCircle(0, 0, 50, Path.Direction.CW);
        mPath.moveTo(100, 100);
        mPath.lineTo(150, 50);
        mPath.lineTo(150, 150);
        mPath.lineTo(50, 150);
        mPath.lineTo(50, 50);
        mPathMeasure = new PathMeasure(mPath, false);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessage(1);
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);
        Log.i(TAG, "onDraw1: " + mCurValue);
        mCurValue = ((int) (mCurValue * 100)) / 100f;
        if (mCurValue < 1) {
            mPathMeasure.getSegment(0, mPathMeasure.getLength() * mCurValue, mDstPath, true);
        } else if (mCurValue == 1.0 || mCurValue == 2.0) {
            if (mCurValue == 2.0) {
                mCurValue = 0;
                mDstPath.reset();
                mPathMeasure = new PathMeasure(mPath, false);
            } else {
                mPathMeasure.getSegment(0, mPathMeasure.getLength(), mDstPath, true);
                mPathMeasure.nextContour();
            }

        } else {
            mPathMeasure.getSegment(0, mPathMeasure.getLength() * (mCurValue - 1), mDstPath, true);
        }
        canvas.drawPath(mDstPath, mPaint);
    }

    static class MyHandler extends Handler {
        WeakReference<PathMeasureView> mView;
    
        MyHandler(PathMeasureView view) {
            mView = new WeakReference<>(view);
        }
    
        @Override
        public void handleMessage(Message msg) {
            PathMeasureView theView = mView.get();
            if (theView == null || theView.getContext() == null) {
                return;
            }
            // 消息处理
            theView.mCurValue += 0.1d;
            theView.invalidate();
            theView.mHandler.sendEmptyMessageDelayed(1, 50);
        }
    }
}
