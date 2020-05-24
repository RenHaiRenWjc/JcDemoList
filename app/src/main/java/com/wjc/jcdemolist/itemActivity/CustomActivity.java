package com.wjc.jcdemolist.itemActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.JcButterknife;

import com.wjc.annotations.BindView;

public class CustomActivity extends AppCompatActivity {

  @BindView(R.id.tv_test_01)
  TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_custom);
    JcButterknife.bind(this);
    textView.setText("xxxxxxx");
  }
}
