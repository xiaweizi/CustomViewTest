package com.example.xiaweizi.customviewtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaweizi.customviewtest.common.Util;

public class BitmapActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "BitmapActivity::";
    private ImageView mIvBitmap, mIvBitmap1;
    private TextView mTvBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        mIvBitmap = findViewById(R.id.iv_bitmap);
        mIvBitmap1 = findViewById(R.id.iv_bitmap1);
        mTvBitmap = findViewById(R.id.tv_bitmap);
        findViewById(R.id.bt_bitmap1).setOnClickListener(this);
        findViewById(R.id.bt_bitmap2).setOnClickListener(this);
        findViewById(R.id.bt_bitmap3).setOnClickListener(this);
        findViewById(R.id.bt_bitmap4).setOnClickListener(this);
    }

    private void saveBitmapToLocal() {
        StringBuilder sb = new StringBuilder();
        long lastTime = System.currentTimeMillis();
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.RED);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(100, 100, 50, paint);
        Util.saveBitmapToLocalJPEG(this, Environment.getExternalStorageDirectory().getPath() + "/xiaweizi/", "testBitmap_" + System.currentTimeMillis(), bitmap, true);
        Log.i("saveBitmapTime::", "totalTime:\t" + (System.currentTimeMillis() - lastTime));
        sb.append("count:\t").append(bitmap.getByteCount());
        mTvBitmap.setText(sb.toString());
    }

    private void inJustDecodeBounds() {
        StringBuilder sb = new StringBuilder();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.background, options);
        sb.append("bitmap:\t").append(bitmap).append("\nwidth:\t").append(options.outWidth).append("\nheight:	").append(options.outHeight).append("\ncount:\t").append(bitmap.getByteCount());
        mTvBitmap.setText(sb.toString());
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        mIvBitmap.setImageDrawable(drawable);
    }

    private void extractAlpha() {
        Bitmap srcBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_nice);
        Bitmap bitmap = Bitmap.createBitmap(srcBmp.getWidth(), srcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.CYAN);
        canvas.drawBitmap(srcBmp.extractAlpha(), 0, 0, paint);
        mIvBitmap.setImageBitmap(srcBmp);
        mIvBitmap1.setImageBitmap(bitmap);
    }

    private void createWater() {
        StringBuilder sb = new StringBuilder();
        long lastTime = System.currentTimeMillis();
        Bitmap maskBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_nice);
        Bitmap srcBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.background);
        Bitmap dstBmp = Util.createWaterBitmap(srcBmp, maskBmp);
        mIvBitmap.setImageBitmap(dstBmp);
        sb.append("totalTime:\t").append(System.currentTimeMillis()-lastTime).append("\n");
        sb.append("totalCount:\t").append((maskBmp.getAllocationByteCount() + srcBmp.getAllocationByteCount() + dstBmp.getAllocationByteCount()) * 1f / 1024 / 1024).append("MB");
        mTvBitmap.setText(sb.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_bitmap1:
                saveBitmapToLocal();
                break;
            case R.id.bt_bitmap2:
                inJustDecodeBounds();
                break;
            case R.id.bt_bitmap3:
                extractAlpha();
                break;
            case R.id.bt_bitmap4:
                createWater();
                break;
        }
    }

}
