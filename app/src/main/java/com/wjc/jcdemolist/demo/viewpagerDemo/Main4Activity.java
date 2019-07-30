package com.wjc.jcdemolist.demo.viewpagerDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//参考 https://blog.csdn.net/qq_34773981/article/details/82022647

public class Main4Activity extends AppCompatActivity {
    private static final String TAG = "Main4Activity";
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @BindView(R.id.tl_nav)
    TabLayout mTlNav;
    @BindView(R.id.vp_container)
    ViewPager mVpContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);

        for (int i = 0; i < 10; i++) {
            titles.add("title:" + i);
            mFragments.add(TabLayoutFragment.newInstance("title:" + i));
        }
        mVpContainer.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                LogUtils.i(TAG, "getPageTitle: position=" + position);
                return titles.get(position);
            }
        });
        mTlNav.setupWithViewPager(mVpContainer);
    }
}
