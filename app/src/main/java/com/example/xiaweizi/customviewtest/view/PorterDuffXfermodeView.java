package com.example.xiaweizi.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.PorterDuffXfermodeView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/19
 *     desc   :
 * </pre>
 */

public class PorterDuffXfermodeView extends View {

    private int mWidth = 400;
    private int mHeight = 400;
    private Bitmap mDstBitmap;
    private Bitmap mSrcBitmap;
    private Paint mPaint;


    public PorterDuffXfermodeView(Context context) {
        this(context, null);
    }

    public PorterDuffXfermodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffXfermodeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mDstBitmap = makeDst(mWidth, mHeight);
        mSrcBitmap = makeSrc(mWidth, mHeight);
    }

    private Bitmap makeDst(int w, int h) {
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xffffcc44);
        canvas.drawOval(new RectF(0, 0, w, h), paint);
        return bitmap;
    }

    private Bitmap makeSrc(int w, int h) {
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xff66aaff);
        canvas.drawRect(0, 0, w, h, paint);
        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mDstBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        canvas.drawBitmap(mSrcBitmap, mWidth / 2, mHeight / 2, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
