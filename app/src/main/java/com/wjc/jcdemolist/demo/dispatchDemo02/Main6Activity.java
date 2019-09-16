package com.wjc.jcdemolist.demo.dispatchDemo02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wjc.jcdemolist.R;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ImageView imageView = findViewById(R.id.iv_test01);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main6Activity.this, "点击了。。。。", Toast.LENGTH_LONG).show();
            }
        });
    }


}
