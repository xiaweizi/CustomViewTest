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
    private ImageView mIvBitmap;
    private TextView mTvBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        mIvBitmap = findViewById(R.id.iv_bitmap);
        mTvBitmap = findViewById(R.id.tv_bitmap);
        findViewById(R.id.bt_bitmap1).setOnClickListener(this);
        findViewById(R.id.bt_bitmap2).setOnClickListener(this);
    }

    private void saveBitmapToLocal() {
        long lastTime = System.currentTimeMillis();
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.RED);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(100, 100, 50, paint);
        Util.saveBitmapToLocalJPEG(this, Environment.getExternalStorageDirectory().getPath() + "/xiaweizi/", "testBitmap_" + System.currentTimeMillis(), bitmap, true);
        Log.i("saveBitmapTime::", "totalTime:\t" + (System.currentTimeMillis() - lastTime));
    }

    private void inJustDecodeBounds() {
        StringBuilder sb = new StringBuilder();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.background, options);
        sb.append("bitmap:\t").append(bitmap).append("width:\t").append(options.outWidth).append("height:	").append(options.outHeight);
        mTvBitmap.setText(sb.toString());
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        mIvBitmap.setImageDrawable(drawable);
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
        }
    }
}
