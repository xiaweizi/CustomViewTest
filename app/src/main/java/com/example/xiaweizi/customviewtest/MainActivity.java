package com.example.xiaweizi.customviewtest;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        findViewById(R.id.bt9).setOnClickListener(this);
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
                startActivity(new Intent(this, BitmapActivity.class));
                break;
            case R.id.bt9:
                startActivity(new Intent(this, MatrixsActivity.class));
                break;
        }
    }



}
