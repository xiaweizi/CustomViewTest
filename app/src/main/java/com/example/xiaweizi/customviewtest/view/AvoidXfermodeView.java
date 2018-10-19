package com.example.xiaweizi.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.xiaweizi.customviewtest.R;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.AvoidXfermodeView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/19
 *     desc   : 类被废弃了，stop
 * </pre>
 */

public class AvoidXfermodeView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Rect mRect = new Rect();

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    public AvoidXfermodeView(Context context) {
        this(context, null);
    }

    public AvoidXfermodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvoidXfermodeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        initPaint();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.xfermode);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int height = width * mBitmap.getHeight() / mBitmap.getWidth();
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint);
        mRect.set(0, 0, width, height);
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);
        canvas.restoreToCount(layerId);
    }
}
