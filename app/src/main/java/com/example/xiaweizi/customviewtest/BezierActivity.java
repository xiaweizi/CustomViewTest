package com.example.xiaweizi.customviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.xiaweizi.customviewtest.view.BezierGestureTrackView;
import com.example.xiaweizi.customviewtest.view.BezierView;
import com.example.xiaweizi.customviewtest.view.NormalGestureTrackView;

public class BezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NormalGestureTrackView) findViewById(R.id.normal_gesture_track)).reset();
                ((BezierGestureTrackView) findViewById(R.id.bezier_gesture_track)).reset();
                ((BezierView) findViewById(R.id.bezier_view)).reset();
            }
        });
    }
}
