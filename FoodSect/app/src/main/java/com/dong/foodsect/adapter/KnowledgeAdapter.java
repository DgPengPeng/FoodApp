package com.dong.foodsect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.foodsect.R;
import com.dong.foodsect.bean.KnowleageBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/24.
 *
 * 这是 知识 适配器
 */
public class KnowledgeAdapter extends BaseAdapter {


    private List<KnowleageBean.FeedsBean>data;
    private Context context;

    public KnowledgeAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<KnowleageBean.FeedsBean> data) {
        this.data .addAll(data);
        notifyDataSetChanged();
    }

    public void setClear(){
        data.clear();
    }
    @Override
    public int getCount() {
        return data != null ?data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data!= null ?data.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        KnowledgeViewHolder knowledgeViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_knowledge_one_listview,viewGroup,false);
            knowledgeViewHolder  = new KnowledgeViewHolder(view);
            view.setTag(knowledgeViewHolder);
        }else{
            knowledgeViewHolder = (KnowledgeViewHolder) view.getTag();
        }
        knowledgeViewHolder.tvTitle.setText(data.get(i).getTitle());
        knowledgeViewHolder.tvSource.setText(data.get(i).getSource());
        knowledgeViewHolder.tvDail.setText(data.get(i).getTail());
        Picasso.with(context).load(data.get(i).getImages().get(0)).into(knowledgeViewHolder.ivImages);
        return view;
    }

    class KnowledgeViewHolder{

        private TextView tvTitle,tvSource,tvDail;
        private ImageView ivImages;
        public KnowledgeViewHolder(View view) {

            tvTitle = (TextView) view.findViewById(R.id.tv_knowledge_item_title_one);
            tvSource = (TextView) view.findViewById(R.id.tv_knowledge_item_source_one);
            tvDail = (TextView) view.findViewById(R.id.tv_knowledge_item_tail_one);
            ivImages = (ImageView) view.findViewById(R.id.iv_knowledge_item_images_one);
        }
    }
}
