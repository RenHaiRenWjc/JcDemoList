package com.wjc.jcdemolist.demo.changeSkin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.Reflect.InjectUtils;
import com.wjc.jcdemolist.Utils.Reflect.InjectView;
import com.wjc.jcdemolist.Utils.Reflect.onClick;
import com.wjc.jcdemolist.Utils.Reflect.onClick02;

public class ChangeSkinActivity extends AppCompatActivity {
  private static final String TAG = "ChangeSkinActivity";
  private SkinFactory mSkinFactory;


  @InjectView(R.id.bt_test)
  Button btTest;

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
    InjectUtils.injectEvent02(this);
    InjectUtils.injectView(this);
    btTest.setText("跳转到第二个页面--");

  }

  @onClick02(id = {R.id.bt_test, R.id.bt_test02})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.bt_test02:
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("index", "valuexx");
        startActivity(intent);
        break;

      case R.id.bt_test:
        mSkinFactory.changeSkin();
        break;
    }
  }
}
