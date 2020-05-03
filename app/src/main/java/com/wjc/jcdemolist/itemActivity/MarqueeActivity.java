package com.wjc.jcdemolist.itemActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.demo.customView.CustomMarqueeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarqueeActivity extends AppCompatActivity {

    @BindView(R.id.cu_test)
    CustomMarqueeView cuTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        ButterKnife.bind(this);
        cuTest.setScrollText("aaaa,你说什么✿✿ヽ(°▽°)ノ✿");
    }

    @OnClick({R.id.bt_pause, R.id.bt_play, R.id.bt_update_text, R.id.bt_update_text2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_pause:
                cuTest.stopScroll();
                break;
            case R.id.bt_play:
                cuTest.startScroll();
                break;
            case R.id.bt_update_text:
                cuTest.setScrollText("深藏功与名!");
                break;
            case R.id.bt_update_text2:
                cuTest.setScrollText("xxx,事了佛衣去，深藏功与名！");
                break;
        }
    }
}
