package com.dong.foodsect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.foodsect.R;
import com.dong.foodsect.bean.EvaluationBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/24.
 *
 * 这是 测评 适配器
 */
public class EvaluationAdapter extends BaseAdapter{

    private List<EvaluationBean.FeedsBean>data;
    private Context context;

    public EvaluationAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<EvaluationBean.FeedsBean> data) {
        this.data .addAll(data);
        notifyDataSetChanged();
    }
    // 刷新数据
    public void Clear(){
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
       EvaViewHolder evaViewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_evaluation_listview,viewGroup,false);
            evaViewHolder = new EvaViewHolder(view);
            view.setTag(evaViewHolder);
        }else {
            evaViewHolder = (EvaViewHolder) view.getTag();
        }
        evaViewHolder.tvSource.setText(data.get(i).getSource());
        evaViewHolder.tvTitle.setText(data.get(i).getTitle());
        evaViewHolder.tvTail.setText(data.get(i).getTail());
        Picasso.with(context).load(data.get(i).getBackground()).into(evaViewHolder.ivBackground);
        return view;
    }


    class EvaViewHolder{
        private ImageView ivBackground;
        private TextView tvSource,tvTitle,tvTail;
        public EvaViewHolder(View view) {

            ivBackground = (ImageView) view.findViewById(R.id.iv_item_evaluation_background);
            tvSource = (TextView) view.findViewById(R.id.tv_item_evaluation_source);
            tvTitle = (TextView) view.findViewById(R.id.tv_item_evaluation_title);
            tvTail = (TextView) view.findViewById(R.id.tv_item_evaluation_tail);
        }
    }
}
