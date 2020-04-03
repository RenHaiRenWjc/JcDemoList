package com.wjc.jcdemolist.demo.KotlinDemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.wjc.jcdemolist.R
import com.wjc.jcdemolist.Utils.SQLiteOpenHelperUtils

class KotlinActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_kotlin)
    val  mBtCreateTable=findViewById(R.id.bt_create_table) as Button;
    val dbHelper = SQLiteOpenHelperUtils(this,"BookStore.db",null,1)
    mBtCreateTable.setOnClickListener(View.OnClickListener { dbHelper.writableDatabase })

  }


}
