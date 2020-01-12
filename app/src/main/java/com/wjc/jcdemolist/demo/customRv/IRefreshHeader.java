package com.wjc.jcdemolist.demo.customRv;

import android.view.View;

public interface IRefreshHeader {
  public static final int STATUS_NORNAL = 0; //正常状态
  public static final int STATUS_REFRESH = 1; // 下拉中
  public static final int STATUS_REFRESHING = 2; // 刷新中
  public static final int STATUS_DONE = 3; // 下拉刷新完成


  void onMove(float offset); //下拉中

  void onRefreshComplete(); // 刷新完成


  boolean onRelease();//下拉松开


  void onStatusChange(int status);


}
