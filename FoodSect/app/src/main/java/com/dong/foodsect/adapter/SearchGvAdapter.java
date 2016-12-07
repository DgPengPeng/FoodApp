package com.dong.foodsect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dong.foodsect.R;

import java.util.List;

/**
 * Created by dllo on 16/12/7.
 */
public class SearchGvAdapter extends BaseAdapter {
    private List<String>data;
    private Context context;

    public SearchGvAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data !=null ?data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data != null ?data.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       MyGvViewHolder myGvViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_search_rv,viewGroup,false);
            myGvViewHolder = new MyGvViewHolder(view);
            view.setTag(myGvViewHolder);
        }else {
            myGvViewHolder = (MyGvViewHolder) view.getTag();
        }
        myGvViewHolder.showTv.setText(data.get(i));
        return view;
    }

    class MyGvViewHolder{
        private TextView showTv;
        public MyGvViewHolder(View view) {
            showTv = (TextView) view.findViewById(R.id.tv_item_search);
        }
    }
}
