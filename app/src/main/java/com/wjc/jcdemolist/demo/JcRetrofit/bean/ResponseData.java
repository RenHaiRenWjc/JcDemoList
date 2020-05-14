package com.wjc.jcdemolist.demo.JcRetrofit.bean;

import java.util.List;

/**
 * ClassName: com.wjc.jcdemolist.demo.JcRetrofit.bean
 * Description:
 * JcChen on 2020.05.14.20:56
 */
public class ResponseData<T> {

  /**
   * data : [{"children":[],"courseId":13,"id":408,"name":"鸿洋","order":190000,"parentChapterId":407,"userControlSetTop":false,"visible":1},
   * {"children":[],"courseId":13,"id":427,"name":"susion随心","order":190011,"parentChapterId":407,"userControlSetTop":false,"visible":1}]
   * errorCode : 0
   * errorMsg :
   */

  private int errorCode;
  private String errorMsg;
  private List<T> dataList;

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public List<T> getDataList() {
    return dataList;
  }

}
