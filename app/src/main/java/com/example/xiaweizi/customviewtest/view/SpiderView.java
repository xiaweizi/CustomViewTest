package com.example.xiaweizi.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.example.xiaweizi.customviewtest.view.SpiderView
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/10/24
 *     desc   :
 * </pre>
 */

public class SpiderView extends View {

    private static final String TAG = "SpiderView::";
    /**
     * 网图的最大等级
     */
    private static final int LEVEL = 10;
    // 最小网格的半径
    private static final int MIN_RADIUS = 50;
    private static final int[] COLORS = {Color.RED, Color.BLUE, Color.CYAN, Color.YELLOW, Color.GREEN};

    private Paint mLinePaint, mContentPaint;
    private int centerX;
    private int centerY;
    private List<SpiderData> mData;
    private Path mPath = new Path();
    // 最大半径
    private int maxRadius;

    public SpiderView(Context context) {
        this(context, null);
    }

    public SpiderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpiderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mData = createData();
                        postInvalidate();
                    }
                });
            }
        }, 2, 1, TimeUnit.SECONDS);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.GRAY);
        mLinePaint.setStrokeWidth(3);

        mContentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mContentPaint.setStrokeWidth(3);
        mContentPaint.setStyle(Paint.Style.STROKE);
        mContentPaint.setColor(Color.CYAN);

    }

    private List<SpiderData> createData() {
        List<SpiderData> spiderDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SpiderData spiderData = new SpiderData();
            List<Double> data = new ArrayList<>(6);
            for (int j = 0; j < 6; j++) {
                data.add(getRandomData());
            }
            spiderData.data = data;
            spiderDatas.add(spiderData);
        }
        return spiderDatas;
    }

    private double getRandomData() {
        Random random = new Random();
        return random.nextDouble() * (LEVEL - 1) + 1;
    }

    public void setSpiderData(List<SpiderData> data) {
        this.mData = data;
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null || mData.size() <= 0) {
            return;
        }
        maxRadius = LEVEL * MIN_RADIUS;
        long lastTime = System.currentTimeMillis();
        // 绘制网格
        drawGridLine(canvas);
        // 绘制数据
        drawData(canvas);

        Log.i(TAG, "onDraw: totalTime:\t" + (System.currentTimeMillis() - lastTime));

    }

    private void drawGridLine(Canvas canvas) {
        int height = (int) (MIN_RADIUS * sin(60));
        for (int i = 1; i <= LEVEL; i++) {
            mLinePaint.setStrokeWidth(i % 2 == 0 ? 6 : 3);
            // 绘制雷达单个线，然后高度递增
            drawSingle(canvas, height, i == LEVEL);
            height += (int) (MIN_RADIUS * sin(60));
        }
    }

    private void drawData(Canvas canvas) {
        for (int i = 0; i < mData.size(); i++) {
            mPath.reset();
            mContentPaint.setColor(COLORS[i % COLORS.length]);
            for (int j = 0; j < mData.get(i).data.size(); j++) {
                double radius = mData.get(i).data.get(j) / LEVEL * maxRadius;
                float x = centerX + (float) (radius * cos(j * 60));
                float y = centerY + (float) (radius * sin(j * 60));
                mContentPaint.setStrokeWidth(12);
                canvas.drawCircle(x, y, 5, mContentPaint);
                if (j == 0) {
                    mPath.moveTo(x, y);
                } else {
                    mPath.lineTo(x, y);
                }
            }
            mPath.close();
            mContentPaint.setStrokeWidth(3);
            canvas.drawPath(mPath, mContentPaint);
        }
    }

    /**
     * 绘制横线，然后旋转画布
     */
    private void drawSingle(Canvas canvas, int height, boolean isDrawLine) {
        int halfWidth = (int) (height / sin(60)) / 2;
        canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        for (int i = 1; i <= 6; i++) {
            canvas.rotate(60, centerX, centerY);
            canvas.drawLine(centerX - halfWidth, centerY - height, centerX + halfWidth, centerY - height, mLinePaint);
            if (isDrawLine) {
                canvas.drawLine(centerX, centerY, centerX + halfWidth, centerY - height, mLinePaint);
                maxRadius = (int) (height / sin(60));
            }
        }
        canvas.restore();
    }

    private double sin(int degree) {
        return Math.sin(Math.toRadians(degree));
    }

    private double tan(int degree) {
        return Math.tan(Math.toRadians(degree));
    }

    private double cos(int degree) {
        return Math.cos(Math.toRadians(degree));
    }

    static class SpiderData {
        List<Double> data = new ArrayList<>();
    }
}
