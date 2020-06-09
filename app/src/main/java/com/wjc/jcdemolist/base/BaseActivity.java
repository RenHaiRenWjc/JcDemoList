package com.wjc.jcdemolist.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.bean.ActivityTypeBean;

import java.util.List;


/**
 * ClassName:com.example.wjc.myapplication
 * Description:
 * JcChen on 2019/6/16 10:16
 */
public abstract class  BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayoutId());
        Adapter adapter = new Adapter(this);
        adapter.setDates(getList());
        RecyclerView recyclerView = findViewById(R.id.rl_base);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    public abstract int setContentLayoutId();

    public abstract List<ActivityTypeBean> getList();

    private class Adapter extends BaseAdapter<ActivityTypeBean> {
        public Adapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_base;
        }

        @Override
        public void onBindItemHolder(BaseViewHolder holder, final int position) {
            TextView testName = holder.getView(R.id.tv_test_name);
            testName.setText(mDates.get(position).getName());
            testName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, mDates.get(position).getIntentClass()));
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });
        }
    }
}
