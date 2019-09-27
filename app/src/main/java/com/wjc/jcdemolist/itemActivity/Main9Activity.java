package com.wjc.jcdemolist.itemActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.demo.customView.CustomClearDrawable;

public class Main9Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        ImageView imageView = findViewById(R.id.iv_test);
        CustomClearDrawable customClearDrawable = new CustomClearDrawable(this, 400, 400);
        imageView.setImageDrawable(customClearDrawable);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customClearDrawable.start();
            }
        });
    }
}
