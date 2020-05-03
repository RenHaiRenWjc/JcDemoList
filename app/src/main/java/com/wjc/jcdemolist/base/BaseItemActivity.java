package com.wjc.jcdemolist.base;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.Nullable;

import com.wjc.jcdemolist.R;

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
