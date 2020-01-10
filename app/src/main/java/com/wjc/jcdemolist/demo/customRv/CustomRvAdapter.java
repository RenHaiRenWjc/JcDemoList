package com.wjc.jcdemolist.demo.customRv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjc.jcdemolist.R;

import java.util.List;

public class CustomRvAdapter extends RecyclerView.Adapter<CustomRvAdapter.CustomRvViewHolder> {
  private static final int type_refresh_head = 0;
  private static final int type_normal = 1;
  private final LayoutInflater mLayoutInflater;
  private List<String> mList;

  public CustomRvAdapter(Context context, List<String> list) {
    mList = list;
    mLayoutInflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public CustomRvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
    if (getItemViewType(position) == type_refresh_head) {
      return new CustomRvViewHolder(mLayoutInflater.inflate(R.layout.layout_type_refresh_head, viewGroup, false));
    } else {
      return new CustomRvViewHolder(mLayoutInflater.inflate(R.layout.layout_type_normal, viewGroup, false));
    }
  }

  @Override
  public void onBindViewHolder(@NonNull CustomRvViewHolder viewHolder, int position) {
    if (getItemViewType(position) == type_normal) {
      viewHolder.textView.setText(mList.get(position));
    }

  }

  @Override
  public int getItemCount() {
    return mList.size();
  }

  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return type_refresh_head;
    } else {
      return type_normal;
    }
  }

  class CustomRvViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public CustomRvViewHolder(@NonNull View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.tv);
    }
  }
}
