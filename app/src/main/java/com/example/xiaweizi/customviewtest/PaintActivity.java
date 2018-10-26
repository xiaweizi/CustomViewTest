package com.example.xiaweizi.customviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.xiaweizi.customviewtest.view.SpiderView;

public class PaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        findViewById(R.id.bt_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SpiderView) findViewById(R.id.spider_view)).change();
            }
        });
    }
}
