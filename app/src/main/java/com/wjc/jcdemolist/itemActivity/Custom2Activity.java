package com.wjc.jcdemolist.itemActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

public class Custom2Activity extends AppCompatActivity {
    private static final String TAG = "Custom2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom2);
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
