package com.didikee.gifparser.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.didikee.gifparser.R;

/**
 * Created by didik on 2016/12/15.
 */

public class MyTestRvAdapter extends RecyclerView.Adapter<MyTestRvAdapter.ViewHolder> {

    public String[] data;

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_show.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_show;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_show= (TextView) itemView.findViewById(R.id.tv_show);
        }
    }
}
