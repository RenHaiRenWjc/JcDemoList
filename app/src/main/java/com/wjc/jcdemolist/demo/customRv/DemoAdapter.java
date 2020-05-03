package com.wjc.jcdemolist.demo.customRv;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjc.jcdemolist.R;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private List<String> mList;

  public DemoAdapter( List<String> list) {
    mList = list;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
    return new DemoRvViewHolder(LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.layout_type_normal,viewGroup,false));
  }


  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
    ((DemoRvViewHolder)viewHolder).textView.setText(mList.get(position));
  }



  @Override
  public int getItemCount() {
    return mList.size();
  }



  class DemoRvViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public DemoRvViewHolder(@NonNull View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.tv);
    }
  }
}
