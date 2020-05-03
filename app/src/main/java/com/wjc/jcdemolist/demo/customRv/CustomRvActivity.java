package com.wjc.jcdemolist.demo.customRv;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wjc.jcdemolist.R;

import java.util.ArrayList;
import java.util.List;

// 参考：https://blog.csdn.net/qq402164452/article/details/69063149
public class CustomRvActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cutom_rv);
    CusRecyclerView recyclerView=findViewById(R.id.rv_test);
    DemoAdapter adapter=new DemoAdapter(getDatas());
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setOnRefreshListener(()->{
      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          recyclerView.refreshComplete();
        }
      }, 2000);
    });

  }

  private List<String> getDatas(){
    List list=new ArrayList();
    for (int i=0;i<14;i++){
      list.add("item:"+i);
    }
    return list;
  }
}
