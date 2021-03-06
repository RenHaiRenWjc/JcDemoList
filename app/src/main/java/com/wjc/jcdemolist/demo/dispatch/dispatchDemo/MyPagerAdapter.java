package com.wjc.jcdemolist.demo.dispatch.dispatchDemo;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wjc.jcdemolist.R;

import java.util.List;
import java.util.Map;

/**
 * ClassName:com.wjc.jcdemolist.demo.dispatch.dispatchDemo
 * Description:
 * JcChen on 2019/9/9 23:10
 */
public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<Map<String, Integer>> mData;


    public MyPagerAdapter(Context context, List<Map<String, Integer>> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_list, null);
        ListView list = view.findViewById(R.id.list);
//        TextView textView = view.findViewById(R.id.tv_page);
//        textView.setText("page--" + position);
        list.setAdapter(new SimpleAdapter(container.getContext(), mData, R.layout.item_base02, new String[]{"key"}, new int[]{R.id.iv}));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
