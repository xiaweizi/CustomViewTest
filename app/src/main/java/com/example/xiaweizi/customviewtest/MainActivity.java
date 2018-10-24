package com.example.xiaweizi.customviewtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.xiaweizi.customviewtest.common.Util;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt5).setOnClickListener(this);
        findViewById(R.id.bt6).setOnClickListener(this);
        findViewById(R.id.bt7).setOnClickListener(this);
        findViewById(R.id.bt8).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                startActivity(new Intent(this, PathMeasureActivity.class));
                break;
            case R.id.bt2:
                startActivity(new Intent(this, VectorActivity.class));
                break;
            case R.id.bt3:
                startActivity(new Intent(this, PaintActivity.class));
                break;
            case R.id.bt4:
                startActivity(new Intent(this, BezierActivity.class));
                break;
            case R.id.bt5:
                startActivity(new Intent(this, TelescopeActivity.class));
                break;
            case R.id.bt6:
                startActivity(new Intent(this, XfermodeActivity.class));
                break;
            case R.id.bt7:
                startActivity(new Intent(this, EraseActivity.class));
                break;
            case R.id.bt8:
                saveBitmapToLocal();
                break;
        }
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

}
