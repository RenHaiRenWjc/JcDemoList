package com.wjc.jcdemolist.base;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ClassName:com.example.wjc.myapplication
 * Description:
 * JcChen on 2019/6/16 11:03
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "BaseAdapter";
    public Context context;
    private LayoutInflater mInflater;
    public List<T> mDates = new ArrayList<>();

    public BaseAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(mInflater.inflate(getLayoutId(), parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        onBindItemHolder(baseViewHolder, position);
    }


    @Override
    public int getItemCount() {
        return mDates.size();
    }

    public List<T> getDates() {
        return mDates;
    }

    public void setDates(Collection<T> list) {
        mDates.clear();
        mDates.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<T> list) {
        int lastIndex = mDates.size();
        if (mDates.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void remove(int position) {
        if (position < 0 || position > mDates.size()) {
            return;
        }
        mDates.remove(position);
        notifyItemRemoved(position);
        if (position != mDates.size() - 1) {//如果是最后一个忽略
            notifyItemRangeChanged(position, mDates.size() - position);
        }
    }

    public void clear() {
        mDates.clear();
        notifyDataSetChanged();
    }


    public abstract int getLayoutId();

    public abstract void onBindItemHolder(BaseViewHolder holder, int position);
}
