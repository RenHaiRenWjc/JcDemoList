package com.wjc.jcdemolist.demo.customRv;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CusRecyclerView extends RecyclerView {
  private RefreshHeadView mRefreshHeadView;
  private float LastY;
  private CustomRvAdapter mCustomAdapter;
  private static final float DRAG_RATE = 3;//拖动阻力系数
  private onRefreshListener onRefreshListener;

  public CusRecyclerView(@NonNull Context context) {
    super(context);
  }

  public CusRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public CusRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public void setAdapter(@Nullable Adapter adapter) {
    mRefreshHeadView = new RefreshHeadView(getContext());
    mCustomAdapter = new CustomRvAdapter(adapter, mRefreshHeadView); //使用内部adapter包装用户adapter
    super.setAdapter(mCustomAdapter);

  }

  @Override
  public boolean onTouchEvent(MotionEvent e) {
    switch (e.getAction()) {
      case MotionEvent.ACTION_DOWN:
        LastY = e.getRawY();
        break;
      case MotionEvent.ACTION_MOVE:
        float deltaY = (e.getRawY() - LastY) / 2;
        LastY = e.getRawY();
        if (isOnTop()) { //头部的view父类不为空
          mRefreshHeadView.onMove(deltaY );
          if (mRefreshHeadView.getVisibleHeight() > 0 && mRefreshHeadView.getStatus() <= IRefreshHeader.STATUS_REFRESH) {
            return true;
          }

        }
        break;
      default:
        LastY = -1;
        if (isOnTop()){ //手指离开释放
          if (mRefreshHeadView.onRelease() && onRefreshListener!=null){
             onRefreshListener.onRefresh();
          }
        }
        break;
    }
    return super.onTouchEvent(e);
  }

  public boolean isOnTop() {//判断头部释放可见
    return mRefreshHeadView.getParent() != null;
  }

  public void refreshComplete(){
    mRefreshHeadView.onRefreshComplete();
  }

  public void setOnRefreshListener(onRefreshListener listener){
    this.onRefreshListener = listener;
  }

}
