package com.wjc.jcdemolist.demo.customRv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjc.jcdemolist.R;

import java.util.List;

public class CustomRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private static final String TAG = "CustomRvAdapter";
  private static final int type_refresh_head = 1000;
  private RecyclerView.Adapter adapter;
  private RefreshHeadView refreshHeadView;

  public CustomRvAdapter(RecyclerView.Adapter adapter, RefreshHeadView refreshHeadView) {
    this.adapter = adapter;
    this.refreshHeadView = refreshHeadView;
  }


  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    if (viewType == type_refresh_head) {
      return new CustomRvViewHolder(refreshHeadView);
    } else {
      return adapter.onCreateViewHolder(viewGroup, viewType );
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    if (isRefreshHeader(position)) {
      return;
    }
    int adjPos = position - 1;
    if (adapter != null) {
      if (adjPos < adapter.getItemCount()) {
        adapter.onBindViewHolder(viewHolder, adjPos);
      }
    }
  }


  @Override
  public int getItemCount() {
    if (adapter != null) {
      return adapter.getItemCount() + 1;
    }
    return 0;
  }

  @Override
  public int getItemViewType(int position) {
    int adjPos = getAdjPos(position);
    if (isRefreshHeader(position)) {
      return type_refresh_head;
    } else if (adapter != null && adjPos < getItemCount()) {
      return adapter.getItemViewType(position);
    }
    return 0;
  }

  @Override
  public long getItemId(int position) {
    if (adapter != null && position >= 1) {
      int adjPos = getAdjPos(position);
      if (adjPos < adapter.getItemCount()) {
        adapter.getItemId(adjPos);
      }
    }
    return -1;
  }

  private boolean isRefreshHeader(int position) {
    return position == 0;
  }

  private int getAdjPos(int position) {
    return position - 1;
  }

  class CustomRvViewHolder extends RecyclerView.ViewHolder {

    public CustomRvViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }

//  @Override
////  public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
////    super.onAttachedToRecyclerView(recyclerView);
////    RecyclerView.LayoutManager manager = getLayoutManager();
////    if (manager instanceof GridLayoutManager) {
////      final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
////      gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
////        @Override
////        public int getSpanSize(int position) {
////          return isRefreshHeader(position) ? gridLayoutManager.getSpanCount() : 1;
////        }
////      });
////    }
////    if (adapter != null) {
////      adapter.onAttachedToRecyclerView(recyclerView);
////    }
////  }

  @Override
  public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
    super.onViewAttachedToWindow(holder);
    ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
    if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams &&
      isRefreshHeader(holder.getLayoutPosition())) {
      StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
      params.setFullSpan(true);
    }
    if (adapter != null) {
      adapter.onViewAttachedToWindow(holder);
    }
  }


}
