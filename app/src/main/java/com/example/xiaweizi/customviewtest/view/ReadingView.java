package com.example.xiaweizi.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.xiaweizi.customviewtest.R;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.ReadingView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/22
 *     desc   : 具有放大镜效果的 View
 * </pre>
 */

public class ReadingView extends View {

    private static final int RADIUS = 260;
    private static final int FACTOR = 3;

    private Bitmap mBitmap;
    private ShapeDrawable mShapeDrawable;
    private final Matrix mMatrix = new Matrix();

    public ReadingView(Context context) {
        this(context, null);
    }

    public ReadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        // 设置放大镜的便宜量，
        mMatrix.setTranslate(RADIUS - x * FACTOR, RADIUS - y * FACTOR);
        mShapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);
        mShapeDrawable.setBounds(new Rect(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS));
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap == null) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.background);
            mBitmap = Bitmap.createScaledBitmap(bmp, getWidth(), getHeight(), false);
            BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth() * FACTOR, mBitmap.getHeight() * FACTOR, true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mShapeDrawable = new ShapeDrawable(new OvalShape());
            mShapeDrawable.getPaint().setShader(shader);
            mShapeDrawable.setBounds(0, 0, RADIUS * 2, RADIUS * 2);
        }
        canvas.drawBitmap(mBitmap, 0, 0, null);
        mShapeDrawable.draw(canvas);
    }
}
