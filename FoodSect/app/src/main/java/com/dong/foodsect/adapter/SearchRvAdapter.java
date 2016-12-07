package com.dong.foodsect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dong.foodsect.R;

import java.util.List;

/**
 * Created by dllo on 16/12/7.
 */
public class SearchRvAdapter extends RecyclerView.Adapter<SearchRvAdapter.MyViewHolder> {
    //private SearchRvBean searchRvBean;
    private List<String>data;
    private Context context;


    public SearchRvAdapter(Context context) {
        this.context = context;
    }

//    public void setSearchRvBean(SearchRvBean searchRvBean) {
//        this.searchRvBean = searchRvBean;
//        notifyDataSetChanged();
//    }


    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_rv,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.showTv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView showTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            showTv = (TextView) itemView.findViewById(R.id.tv_item_search);
        }
    }
}
