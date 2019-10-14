package com.wjc.jcdemolist.demo.changeSkin;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.wjc.jcdemolist.R;

import java.io.File;

public class ChangeSkinActivity extends AppCompatActivity {

    private SkinFactory mSkinFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        LayoutInflater.from(this).setFactory2(new LayoutInflater.Factory2() {
//            @Override
//            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//                if (TextUtils.equals(name, "TextView")) {
//                    Button button = new Button(ChangeSkinActivity.this);
//                    button.setText("button-----------------");
//                    return button;
//                }
//                return null;
//            }
//
//            @Override
//            public View onCreateView(String name, Context context, AttributeSet attrs) {
//                return null;
//            }
//        });
        mSkinFactory = new SkinFactory();
        mSkinFactory.setDelegate(getDelegate());
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        layoutInflater.setFactory2(mSkinFactory);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skiy);
        Button bt = findViewById(R.id.bt_test);
        bt.setBackgroundResource(R.mipmap.ic_launcher_round);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File skinFile = new File(Environment.getExternalStorageDirectory(), "app-debug.apk");
                SkinEngine.getInstance().load(skinFile.getAbsolutePath());
                mSkinFactory.changeSkin();
            }
        });

    }
}
