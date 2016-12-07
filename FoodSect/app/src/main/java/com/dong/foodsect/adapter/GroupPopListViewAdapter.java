package com.dong.foodsect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dong.foodsect.R;
import com.dong.foodsect.bean.LibraryBean;

import java.util.List;

/**
 * Created by dllo on 16/12/2.
 */
public class GroupPopListViewAdapter extends BaseAdapter {

    //private List<LibraryBean.GroupBean.CategoriesBean.SubCategoriesBean> data;
    private List<String> data;
    private Context context;

    public GroupPopListViewAdapter(Context context) {
        this.context = context;
    }
//
//    public void setData(List<LibraryBean.GroupBean.CategoriesBean.SubCategoriesBean> data) {
//        this.data = data;
//        notifyDataSetChanged();
//    }



    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
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
        MyViewHolder myViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_pop_listview, viewGroup, false);
            myViewHolder = new MyViewHolder(view);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        myViewHolder.showTv.setText(data.get(i));
        return view;
    }

    class MyViewHolder {
        private TextView showTv;

        public MyViewHolder(View view) {
            showTv = (TextView) view.findViewById(R.id.tv_group_pop_listview);

        }
    }
}
