package com.wjc.jcdemolist.itemActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

public class Custom2Activity extends AppCompatActivity {
    private static final String TAG = "Custom2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom2);
        View tvTest = findViewById(R.id.tv_test);
        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Custom2Activity.this, "点击了。。。", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                super.dispatchTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                super.dispatchTouchEvent(ev);
                break;
        }
        boolean result = super.dispatchTouchEvent(ev);
        LogUtils.i(TAG, "dispatchTouchEvent: result=" + result + ",ev=" + ev.getAction());
        return result;
    }
}
