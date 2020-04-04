package com.wjc.jcdemolist.demo.KotlinDemo

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.wjc.jcdemolist.R
import com.wjc.jcdemolist.Utils.SQLiteOpenHelperUtils

class KotlinActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_kotlin)

    val mBtCreateTable = findViewById(R.id.bt_create_table) as Button;
    val mBtAddData = findViewById(R.id.bt_add_data) as Button;
    val mBtUpateData = findViewById(R.id.bt_update_data) as Button;
    val mBtRemoveData = findViewById(R.id.bt_remove_data) as Button;
    val mBtQueryData = findViewById(R.id.bt_query_data) as Button;
    val dbHelper = SQLiteOpenHelperUtils(this, "BookStore.db", null, 2)
    mBtCreateTable.setOnClickListener(View.OnClickListener {
      val db = dbHelper.writableDatabase
    })


    mBtAddData.setOnClickListener {
      val db = dbHelper.writableDatabase
      var values: ContentValues = ContentValues()
      values.put("name", "The WJC")
      values.put("author", "jc")
      values.put("pages", 100);
      values.put("price", 46.0);
      db.insert("Book", null, values)
      values.clear()

      values.put("name", "gz fc")
      values.put("author", "xy")
      values.put("pages", 1990);
      values.put("price", 46123.022);
      db.insert("Book", null, values)
      Log.d("TAG","add")
    }
    mBtUpateData.setOnClickListener {
      var values: ContentValues = ContentValues()
      values.put("price", 9999);
      var db: SQLiteDatabase = dbHelper.writableDatabase
      db.update("Book", values, "name=? AND author=?", arrayOf("The WJC", "jc"))
      Log.d("TAG","update")
    }

    mBtRemoveData.setOnClickListener {
      var db: SQLiteDatabase = dbHelper.writableDatabase
      db.delete("Book", "pages>?", arrayOf("1000"))
      Log.d("TAG","delete")
    }

    mBtQueryData.setOnClickListener {
      var db: SQLiteDatabase = dbHelper.writableDatabase
      var mCursor: Cursor = db.query("Book", null, null, null, null, null, null)
      if (mCursor.moveToFirst()) {
        do {
          val name = mCursor.getString(mCursor.getColumnIndex("name"))
          val price = mCursor.getString(mCursor.getColumnIndex("price"))
          Log.d("TAG","name="+name+",price="+price)
        } while (mCursor.moveToNext())
      }
      mCursor.close()
    }

  }


}
