package com.dong.foodsect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dong.foodsect.R;
import com.dong.foodsect.Tools.GroupPopClick;
import com.dong.foodsect.bean.GroupPopBean;

import java.util.List;

/**
 * Created by dllo on 16/12/2.
 */
public class TagsPopAdapter extends RecyclerView.Adapter<TagsPopAdapter.MyViewHolder> {

    private List<GroupPopBean.TypesBean> data;
    private Context context;
    private GroupPopClick groupPopClick;

    public void setGroupPopClick(GroupPopClick groupPopClick) {
        this.groupPopClick = groupPopClick;

    }

    public TagsPopAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<GroupPopBean.TypesBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pop, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.showTv.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupPopClick.PopClick(data.get(position).getIndex());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView showTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            showTv = (TextView) itemView.findViewById(R.id.tv_item_pop);
        }
    }
}
