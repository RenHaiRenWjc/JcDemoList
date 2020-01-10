package com.wjc.jcdemolist.demo.customRv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wjc.jcdemolist.R;

import java.util.ArrayList;
import java.util.List;

public class CustomRvActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cutom_rv);
    RecyclerView recyclerView=findViewById(R.id.rv_test);
    CustomRvAdapter adapter=new CustomRvAdapter(this,getDatas());
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

  }

  private List<String> getDatas(){
    List list=new ArrayList();
    for (int i=0;i<14;i++){
      list.add("item:"+i);
    }
    return list;
  }
}
