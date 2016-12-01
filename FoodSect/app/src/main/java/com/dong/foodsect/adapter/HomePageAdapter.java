package com.dong.foodsect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.foodsect.R;
import com.dong.foodsect.Tools.HomePageClick;
import com.dong.foodsect.bean.HomePageBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/11/23.
 * <p/>
 * <p/>
 * 这是  首页  适配器
 */
public class HomePageAdapter extends RecyclerView.Adapter {
    // 设置底部布局
    private static final int TYPE_FOOTER = 0;
    // 设置默认布局
    private static final int TYPE_DEFAULT = 1;
    // 判断是不是最后一个item  默认为true
    private boolean mShowFooter = true;
    private List<HomePageBean.FeedsBean> data;
    private Context context;
    private static final int CONTENT_TYPE_ONE = 6;
    private static final int CONTENT_TYPE_TWO = 5;
//    private static final int COUNT = 20;
    private HomePageClick homePageClick;


    public void setHomePageClick(HomePageClick homePageClick) {
        this.homePageClick = homePageClick;

    }

    public HomePageAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<HomePageBean.FeedsBean> data) {
//        this.data.addAll(data);
        this.data = data;
        notifyDataSetChanged();
    }

    public void setClear() {
        data.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getContent_type();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mholder = null;
        switch (viewType) {
            case CONTENT_TYPE_ONE:
                View viewOne = LayoutInflater.from(context).inflate(R.layout.item_homepage_one, parent, false);
                mholder = new OneViewHolder(viewOne);
                break;
            case CONTENT_TYPE_TWO:
                View viewTwo = LayoutInflater.from(context).inflate(R.layout.item_homepage_second, parent, false);
                mholder = new TwoViewHolder(viewTwo);
                break;

        }
        return mholder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder,final int position) {
        int type = getItemViewType(position);
        switch (type) {
            case CONTENT_TYPE_ONE:
                final OneViewHolder oneViewHolder = (OneViewHolder) holder;
                Picasso.with(context).load(data.get(position).getCard_image()).into(oneViewHolder.ivOne);

                break;
            case CONTENT_TYPE_TWO:
                TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
                twoViewHolder.tvTitle.setText(data.get(position).getTitle());
                twoViewHolder.tvDescription.setText(data.get(position).getDescription());
                twoViewHolder.tvPublisher.setText(data.get(position).getPublisher());
                twoViewHolder.tvLike.setText(data.get(position).getLike_ct() + "");
                Picasso.with(context).load(data.get(position).getCard_image()).into(twoViewHolder.ivCardTwo);
                Picasso.with(context).load(data.get(position).getPublisher_avatar()).into(twoViewHolder.ivAvaTwo);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homePageClick.HomePageListenter(position,data.get(position).getContent_type());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivOne;

        public OneViewHolder(View itemView) {
            super(itemView);
            ivOne = (ImageView) itemView.findViewById(R.id.iv_item_homepage_one);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCardTwo;
        private CircleImageView ivAvaTwo;
        private TextView tvTitle, tvDescription, tvPublisher, tvLike;

        public TwoViewHolder(View itemView) {
            super(itemView);
            ivCardTwo = (ImageView) itemView.findViewById(R.id.iv_homepage_card_image);
            ivAvaTwo = (CircleImageView) itemView.findViewById(R.id.iv_homepage_publisher_avatar);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_homepage_title);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_homepage_description);
            tvPublisher = (TextView) itemView.findViewById(R.id.tv_homepage_publisher);
            tvLike = (TextView) itemView.findViewById(R.id.tv_homepage_like_ct);
        }
    }


}
