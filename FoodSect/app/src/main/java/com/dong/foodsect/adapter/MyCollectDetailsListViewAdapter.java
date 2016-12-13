package com.dong.foodsect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dong.foodsect.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/8.
 */
public class MyCollectDetailsListViewAdapter extends BaseAdapter {
    private List<String> data;
    private Context context;

    public MyCollectDetailsListViewAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setClear(){
        data.clear();
    }
    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data != null ? data.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MySeaDeaLvViewHolder mySeaDeaLvViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_my_collect_details_lv,viewGroup,false);
            mySeaDeaLvViewHolder = new MySeaDeaLvViewHolder(view);
            view.setTag(mySeaDeaLvViewHolder);
        }else {
            mySeaDeaLvViewHolder = (MySeaDeaLvViewHolder) view.getTag();
        }
        mySeaDeaLvViewHolder.showTv.setText(data.get(i));
        return view;
    }

    class MySeaDeaLvViewHolder {
        private TextView showTv;
        public MySeaDeaLvViewHolder(View view) {
            showTv = (TextView) view.findViewById(R.id.tv_item_my_collect_details_lv);
        }
    }
}
