package com.wjc.jcdemolist.demo.customRv;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wjc.jcdemolist.R;

public class RefreshHeadView extends LinearLayout implements IRefreshHeader {
  private static final String TAG = "RefreshHeadView";
  private LinearLayout mContentView;
  private int mStatus;
  private int measuredHeight;
  private TextView mTv;


  public RefreshHeadView(Context context) {
    super(context);
    init();
  }

  public RefreshHeadView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public RefreshHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    layoutParams.setMargins(0, 0, 0, 0);
    this.setLayoutParams(layoutParams);
    this.setPadding(0, 0, 0, 0);


    mContentView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_type_refresh_head, null);
    addView(mContentView, new LayoutParams(LayoutParams.MATCH_PARENT, 0));

    mTv = findViewById(R.id.tv);

    measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    measuredHeight = getMeasuredHeight();
    Log.i(TAG, "init: measuredHeight=" + measuredHeight);
  }


  @Override
  public void onRefreshComplete() {
    onStatusChange(STATUS_DONE);
    new Handler().postDelayed(() -> reset(), 300);
  }

  @Override
  public void onMove(float offset) {
    if (getVisibleHeight() > 0 || offset > 0) {
      setVisibleHeight(offset + getVisibleHeight());
      if (mStatus == STATUS_NORNAL || mStatus == STATUS_REFRESH) {
        if (getVisibleHeight() > measuredHeight) {//下拉高度大于header高度，释放刷新
          onStatusChange(STATUS_REFRESH);
        } else {
          onStatusChange(STATUS_NORNAL); // 下拉中，普通状态
        }
      }
    }
  }

  // 1. 手指释放，header下拉高度大于header高度并且处于刷新状态，则表示刷新中
  // 2. 手指释放，header 下拉高度小于 header 高度，即不是处于刷新中的状态，则不刷新，隐藏header
  // 3. 手指释放，header 正处于刷新中，则显示header完整高度
  @Override
  public boolean onRelease() {
    boolean isOnRefresh = false;
    int height = getVisibleHeight();
    if (height == 0) isOnRefresh = false;
    if (height > measuredHeight && mStatus == STATUS_REFRESH) {
      onStatusChange(STATUS_REFRESHING);
      isOnRefresh = true;
    }
    if (mStatus != STATUS_REFRESHING) {
      smoothScrollTo(0);
    }
    if (mStatus == STATUS_REFRESHING) {
      smoothScrollTo(measuredHeight);
    }
    return isOnRefresh;
  }

  @Override
  public void onStatusChange(int status) {
    Log.i(TAG, "onStatusChange: status="+status);
    if (mStatus == status) return;// mStatus 是上次的状态，不是最新的
    switch (status) {
      case STATUS_NORNAL:
        mTv.setText("我是头部");
        break;
      case STATUS_REFRESH:
        mTv.setText("刷新。。。");
        break;
      case STATUS_REFRESHING:
        mTv.setText("刷新中。。。");
        break;
      case STATUS_DONE:
        mTv.setText("刷新完成。。");
        break;
    }
    mStatus = status;

  }


  public int getVisibleHeight() {
    return mContentView.getLayoutParams().height; // 获得刷新头部实时高度
  }

  public void setVisibleHeight(float height) {
    if (height < 0) height = 0;
    ViewGroup.LayoutParams params = mContentView.getLayoutParams();
    params.height = (int) height;
    mContentView.setLayoutParams(params);
  }

  private void smoothScrollTo(float height) {
    ValueAnimator animator = ValueAnimator.ofFloat(getVisibleHeight(), height);//属性动画
    animator.addUpdateListener(
      (ValueAnimator valueAnimator) -> setVisibleHeight((float) valueAnimator.getAnimatedValue())
    );
    animator.setDuration(300).start();
  }

  public void reset() {//重置，header隐藏
    smoothScrollTo(0);
    onStatusChange(STATUS_NORNAL);
  }

  public int getStatus() {
    return mStatus;
  }

}
