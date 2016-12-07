package com.dong.foodsect.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.foodsect.R;
import com.dong.foodsect.bean.LibraryDetailsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/12/1.
 */
public class LibraryDetailsAdapter extends BaseAdapter {

    private List<LibraryDetailsBean.FoodsBean> data;
    private Context context;

    public LibraryDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<LibraryDetailsBean.FoodsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void getClear(){
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
       LibDetViewHolder libDetViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_library_details,viewGroup,false);
            libDetViewHolder = new LibDetViewHolder(view);
            view.setTag(libDetViewHolder);
        }else {
            libDetViewHolder = (LibDetViewHolder) view.getTag();
        }

        libDetViewHolder.nameTv.setText(data.get(i).getName());
        libDetViewHolder.weightTv.setText(data.get(i).getWeight());
        libDetViewHolder.caloryTv.setText(data.get(i).getCalory());
        Picasso.with(context).load(data.get(i).getThumb_image_url()).into(libDetViewHolder.thumbIv);
        int num = data.get(i).getHealth_light();
        Log.d("LibraryDetailsAdapter", "num:" + num);

        switch (num){
            case 1:
                libDetViewHolder.pointIv.setImageResource(R.mipmap.ic_food_light_green);
                break;
            case 2:
                libDetViewHolder.pointIv.setImageResource(R.mipmap.ic_food_light_yellow);
                break;
            case 3:
                libDetViewHolder.pointIv.setImageResource(R.mipmap.ic_food_light_red);
                break;
        }
        return view;
    }

    class LibDetViewHolder{
        private TextView nameTv,weightTv,caloryTv;
        private ImageView thumbIv,pointIv;
        public LibDetViewHolder(View view) {

            nameTv = (TextView) view.findViewById(R.id.tv_name_egg);
            weightTv = (TextView) view.findViewById(R.id.tv_weight);
            caloryTv = (TextView) view.findViewById(R.id.tv_calory);
            pointIv = (ImageView) view.findViewById(R.id.iv_point);
            thumbIv = (ImageView) view.findViewById(R.id.iv_thumb_image_url);
        }
    }
}
