package com.dong.foodsect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dong.foodsect.R;
import com.dong.foodsect.bean.LibraryBean;

import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */
public class LibraryTagsAdapter extends BaseAdapter {
    private List<LibraryBean.GroupBean.CategoriesBean>datas;
    private Context context;


    public LibraryTagsAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<LibraryBean.GroupBean.CategoriesBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return datas != null ? datas.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder = null;
        if (view == null){
                view = LayoutInflater.from(context).inflate(R.layout.item_libracy_girdview,viewGroup,false);
                groupViewHolder = new GroupViewHolder(view);
                view.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder) view.getTag();

        }
        groupViewHolder.groupTv.setText(datas.get(i).getName());
        Glide.with(context).load(datas.get(i).getImage_url()).into(groupViewHolder.groupIv);
        return view;
    }
    class GroupViewHolder{
        private ImageView groupIv;
        private TextView groupTv;
        public GroupViewHolder(View view){
            groupIv = (ImageView) view.findViewById(R.id.item_group_civ);
            groupTv = (TextView) view.findViewById(R.id.item_group_tv);

        }
    }

}
