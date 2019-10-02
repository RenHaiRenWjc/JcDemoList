package com.wjc.jcdemolist.demo.PaintAndCanvas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.demo.customView.DrawGestureView;

import java.util.ArrayList;
import java.util.List;

public class PaintAndCanvasActivity extends AppCompatActivity {
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_canvas);

        ViewPager mViewPager = findViewById(R.id.main_viewpager);

        RoundViewFragment roundViewFragment = new RoundViewFragment();
        CustomDrawableFragment customDrawableFragment = new CustomDrawableFragment();
        DrawGestureFragment drawGestureFragment = new DrawGestureFragment();
        fragments.add(drawGestureFragment);
        fragments.add(roundViewFragment);
        fragments.add(customDrawableFragment);

        FragmentStatePagerAdapter fragmentAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        mViewPager.setAdapter(fragmentAdapter);
    }
}
