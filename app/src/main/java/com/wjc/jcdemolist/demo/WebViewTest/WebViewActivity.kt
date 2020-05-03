package com.wjc.jcdemolist.demo.WebViewTest

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.wjc.jcdemolist.R


class WebViewActivity : AppCompatActivity() {
  var mWebview: WebView? = null
  var mWebSettings: WebSettings? = null
  var beginLoading: TextView? = null
  var endLoading: TextView? = null
  var loading: TextView? = null
  var mtitle: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_web_view)
    beginLoading = findViewById(R.id.text_beginLoading)
    endLoading = findViewById(R.id.text_endLoading)
    loading = findViewById(R.id.text_Loading)
    mtitle = findViewById(R.id.title)
    mWebview = findViewById(R.id.webView1)
    handleWebView()
  }

  fun handleWebView() {
    mWebview!!.loadUrl("http://www.baidu.com/")
    mWebview!!.setWebViewClient(object : WebViewClient() {
      override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
      }
    /*  override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
        println("开始加载了")
        beginLoading!!.setText("开始加载了")
      }

      //设置结束加载函数
      override fun onPageFinished(view: WebView, url: String) {
        endLoading!!.setText("结束加载了")
      }*/
    })


    //设置WebChromeClient类
    mWebview!!.setWebChromeClient(object : WebChromeClient() {
      //获取网站标题
      override fun onReceivedTitle(view: WebView, title: String) {
        println("标题在这里")
        mtitle!!.setText(title)
      }

      //获取加载进度
      override fun onProgressChanged(view: WebView, newProgress: Int) {
        if (newProgress < 100) {
          val progress = "$newProgress%"
          loading!!.setText(progress)
        } else if (newProgress == 100) {
          val progress = "$newProgress%"
          loading!!.setText(progress)
        }
      }
    })

  }

  override fun onDestroy() {
    if (mWebview != null) {
      mWebview!!.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
      mWebview!!.clearHistory()
      (mWebview!!.parent as ViewGroup).removeView(mWebview)
      mWebview!!.destroy()
      mWebview = null
    }
    super.onDestroy()
  }


}
