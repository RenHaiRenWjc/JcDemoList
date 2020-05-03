package com.wjc.jcdemolist.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * ClassName:com.example.wjc.myapplication
 * Description:
 * JcChen on 2019/6/16 11:18
 */
class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> viewSparseArray;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.viewSparseArray = new SparseArray<>();//稀疏数组,占用内存比 hashMap少
    }

    public <T extends View> T getView(int viewId) {
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }
}
