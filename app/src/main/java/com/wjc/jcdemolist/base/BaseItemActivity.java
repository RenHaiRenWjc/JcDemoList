package com.wjc.jcdemolist.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import androidx.annotation.Nullable;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.demo.PaintAndCanvas.CustomDrawableFragment;
import com.wjc.jcdemolist.demo.PaintAndCanvas.DrawGestureFragment;
import com.wjc.jcdemolist.demo.PaintAndCanvas.RoundViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:com.wjc.jcdemolist.base
 * Description:
 * JcChen on 2019/10/20 10:08
 */
public abstract class BaseItemActivity extends AppCompatActivity {
    public List<Fragment> fragments = new ArrayList<>();

    public abstract void addFragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_base);
        ViewPager mViewPager = findViewById(R.id.vp_item_base);
        addFragment();
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
