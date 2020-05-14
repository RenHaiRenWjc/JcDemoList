package com.wjc.jcdemolist.demo.JcRetrofit.bean;

import java.util.List;

/**
 * ClassName: com.wjc.jcdemolist.demo.JcRetrofit.bean
 * Description:
 * JcChen on 2020.05.14.21:55
 */
public class ArticleList {


  /**
   * data : {"curPage":1,"articleList":[{"apkLink":""}],"offset":0,"over":false,"pageCount":56,"size":20,"total":1102}
   * errorCode : 0
   * errorMsg :
   */

  private DataBean data;
  private int errorCode;
  private String errorMsg;

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

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

  public static class DataBean {
    /**
     * curPage : 1
     * articleList : [{"apkLink":""}]
     * offset : 0
     * over : false
     * pageCount : 56
     * size : 20
     * total : 1102
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<Article> articleList;

    public int getCurPage() {
      return curPage;
    }

    public void setCurPage(int curPage) {
      this.curPage = curPage;
    }

    public int getOffset() {
      return offset;
    }

    public void setOffset(int offset) {
      this.offset = offset;
    }

    public boolean isOver() {
      return over;
    }

    public void setOver(boolean over) {
      this.over = over;
    }

    public int getPageCount() {
      return pageCount;
    }

    public void setPageCount(int pageCount) {
      this.pageCount = pageCount;
    }

    public int getSize() {
      return size;
    }

    public void setSize(int size) {
      this.size = size;
    }

    public int getTotal() {
      return total;
    }

    public void setTotal(int total) {
      this.total = total;
    }

    public List<Article> getArticleList() {
      return articleList;
    }

    public void setArticleList(List<Article> articleList) {
      this.articleList = articleList;
    }


  }
}
