package com.example.xiaweizi.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.xiaweizi.customviewtest.R;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.ColorMatrixsView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/24
 *     desc   :
 * </pre>
 */

public class ColorMatrixView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private ColorMatrixColorFilter filter;
    private ColorMatrix colorMatrix;
    private Rect rect;
    private int height;
    private float[] src = new float[] {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 50,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0,
    };

    // 颜色翻转
    private float[] src1 = new float[] {
            -1, 0, 0, 0, 255,
            0, -1, 0, 0, 255,
            0, 0, -1, 0, 255,
            0, 0, 0, 1, 0,
    };

    // 增加亮度
    private float[] src2 = new float[] {
            1.2f, 0, 0, 0, 0,
            0, 1.2f, 0, 0, 0,
            0, 0, 1.2f, 0, 0,
            0, 0, 0, 1, 0,
    };

    // 黑色图片
    private float[] src3 = new float[] {
            0.213f, 0.715f, 0.072f, 0, 0,
            0.213f, 0.715f, 0.072f, 0, 0,
            0.213f, 0.715f, 0.072f, 0, 0,
            0, 0, 0, 1, 0,
    };

    // 色彩反转
    private float[] src4 = new float[] {
            0, 1, 0, 0, 0,
            1, 0, 0, 0, 0,
            0, 0, 0, 0, 0,
            0, 0, 0, 1, 0,
    };

    // 照片变旧
    private float[] src5 = new float[] {
            1/2f, 1/2f, 1/2f, 0, 0,
            1/3f, 1/3f, 1/3f, 0, 0,
            1/4f, 1/4f, 1/4f, 0, 0,
            0, 0, 0, 1, 0,
    };

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public ColorMatrixView(Context context) {
        this(context, null);
    }

    public ColorMatrixView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorMatrixView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        //  R, 0, 0, 0, 0,
        //  0, G, 0, 0, 0,
        //  0, 0, B, 0, 0,
        //  0, 0, 0, A, 0,
        //  RGBA 修改后提取对应的值
        initPaint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.background);
        colorMatrix = new ColorMatrix(src5);
        filter = new ColorMatrixColorFilter(colorMatrix);
        height = 500 * mBitmap.getHeight() / mBitmap.getWidth();
        rect = new Rect(0, 0, 500, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColorFilter(null);
        canvas.drawBitmap(mBitmap, null, rect, mPaint);
        canvas.translate(0, height + 20);
        mPaint.setColorFilter(filter);
        canvas.drawBitmap(mBitmap, null, rect, mPaint);
    }
}
