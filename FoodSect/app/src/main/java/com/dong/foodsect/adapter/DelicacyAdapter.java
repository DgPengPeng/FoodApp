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
import com.dong.foodsect.bean.DelicacyBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/24.
 *
 * 这是 美食 适配器
 */
public class DelicacyAdapter extends BaseAdapter {

    private List<DelicacyBean.FeedsBean> data;
    private Context context;
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_COUNT = 3;


    public DelicacyAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<DelicacyBean.FeedsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void clearData(){
        data.clear();
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
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
    public int getItemViewType(int position) {
       if (data.get(position).getContent_type() == 1){
           return TYPE_ONE;
       }else {
           return TYPE_TWO;
       }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        OneDelicacyViewHolder oneDelicacyViewHolder = null;
        TwoDelicacyViewHolder twoDelicacyViewHolder = null;
        if (view == null){
            switch (getItemViewType(i)){
                case TYPE_ONE:
                    view = LayoutInflater.from(context).inflate(R.layout.item_delicacy_one_listview,viewGroup,false);
                    oneDelicacyViewHolder = new OneDelicacyViewHolder(view);
                    view.setTag(oneDelicacyViewHolder);
                    break;
                case TYPE_TWO:
                    view = LayoutInflater.from(context).inflate(R.layout.item_delicacy_two_listview,viewGroup,false);
                    twoDelicacyViewHolder = new TwoDelicacyViewHolder(view);
                    view.setTag(twoDelicacyViewHolder);
                    break;
        }
        }else {
            switch (getItemViewType(i)){
                case TYPE_ONE:
                    oneDelicacyViewHolder = (OneDelicacyViewHolder) view.getTag();
                    oneDelicacyViewHolder.tvTitleOne.setText(data.get(i).getTitle());
                    oneDelicacyViewHolder.tvSourceOne.setText(data.get(i).getSource());
                    oneDelicacyViewHolder.tvTailOne.setText(data.get(i).getTail());
                    Picasso.with(context).load(data.get(i).getImages().get(0)).into(oneDelicacyViewHolder.ivImagesOne);
                    break;
                case TYPE_TWO:
                    twoDelicacyViewHolder = (TwoDelicacyViewHolder) view.getTag();
                    twoDelicacyViewHolder.tvTitleTwo.setText(data.get(i).getTitle());
                    twoDelicacyViewHolder.tvSourceTwo.setText(data.get(i).getSource());
                    twoDelicacyViewHolder.tvTailTwo.setText(data.get(i).getTail());
                    Picasso.with(context).load(data.get(i).getImages().get(0)).into(twoDelicacyViewHolder.iv1ImagesTwo);
                    Picasso.with(context).load(data.get(i).getImages().get(1)).into(twoDelicacyViewHolder.iv2ImagesTwo);
                    Picasso.with(context).load(data.get(i).getImages().get(2)).into(twoDelicacyViewHolder.iv3ImagesTwo);
                    break;
            }
        }

        return view;
    }

    class OneDelicacyViewHolder {
        private TextView tvTitleOne, tvSourceOne, tvTailOne;
        private ImageView ivImagesOne;

        public OneDelicacyViewHolder(View view) {
            tvTitleOne = (TextView) view.findViewById(R.id.tv_delicacy_item_title_one);
            tvSourceOne = (TextView) view.findViewById(R.id.tv_delicacy_item_source_one);
            tvTailOne = (TextView) view.findViewById(R.id.tv_delicacy_item_tail_one);
            ivImagesOne = (ImageView) view.findViewById(R.id.iv_delicacy_item_images_one);
        }
    }

    class TwoDelicacyViewHolder {
        private TextView tvTitleTwo, tvSourceTwo, tvTailTwo;
        private ImageView iv1ImagesTwo,iv2ImagesTwo,iv3ImagesTwo;

        public TwoDelicacyViewHolder(View view) {
            tvTitleTwo = (TextView) view.findViewById(R.id.tv_delicacy_item_title_two);
            tvSourceTwo = (TextView) view.findViewById(R.id.tv_delicacy_item_source_two);
            tvTailTwo = (TextView) view.findViewById(R.id.tv_delicacy_item_tail_two);
            iv1ImagesTwo = (ImageView) view.findViewById(R.id.iv1_delicacy_item_images_two);
            iv2ImagesTwo = (ImageView) view.findViewById(R.id.iv2_delicacy_item_images_two);
            iv3ImagesTwo = (ImageView) view.findViewById(R.id.iv3_delicacy_item_images_two);

        }
    }
}
