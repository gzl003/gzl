package com.zhiguang.li.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.zhiguang.li.R;
import com.zhiguang.li.modle.Museum;
import com.zhiguang.li.utils.ScreenUtils;
import com.zhiguang.li.widget.BannerView;

import java.util.List;

/**
 *  Created by 智光 on 2016/12/28 16:08
 *  
 */
public class MuseumRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_VIEW_TYPE_BANNER = 1;//广告图
    public static final int ITEM_VIEW_TYPE_BIG = 2;//big
    public static final int ITEM_VIEW_TYPE_SMALL = 3;//small
    public static final int ITEM_VIEW_TYPE_TITLE = 4;//标题类型

    private int itemWidthS;
    private int itemHeightS;
    private int itemWidthB;
    private int itemHeightB;
    private int pagemargen;

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Museum.MuseunInfo> museumList;

    public void setMuseumList(List<Museum.MuseunInfo> museumList) {
        this.museumList = museumList;
        notifyDataSetChanged();
    }

    public MuseumRecycleViewAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        pagemargen = ScreenUtils.dip2px(mContext, 7f);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM_VIEW_TYPE_BANNER:
//                itemWidthB = ;
                viewHolder = new BannerViewHodler(mInflater.inflate(R.layout.museum_bannerview_layout, parent, false));
                break;
            case ITEM_VIEW_TYPE_BIG:
                viewHolder = new BigImgViewHolder(mInflater.inflate(R.layout.museum_bigview_layout, parent, false));
                break;
            case ITEM_VIEW_TYPE_SMALL:
                viewHolder = new SmallImgViewHolder(mInflater.inflate(R.layout.museum_smallview_layout, parent, false));
                break;
            case ITEM_VIEW_TYPE_TITLE:
                viewHolder = new TitleViewHolder(mInflater.inflate(R.layout.museum_titleview_layout, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_VIEW_TYPE_BANNER:
                if (holder instanceof BannerViewHodler) {
                    BannerViewHodler viewHodler = (BannerViewHodler) holder;
                    viewHodler.banner.setPages(
                            new CBViewHolderCreator<BannerView>() {
                                @Override
                                public BannerView createHolder() {
                                    return new BannerView();
                                }
                            }, museumList.get(0).banner)
                            //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                            .setPageIndicator(new int[]{R.drawable.focus_un, R.drawable.focus})
                            //设置指示器的方向
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
                }
                break;
            case ITEM_VIEW_TYPE_BIG:
                Museum.MuseunInfo info = museumList.get(position);
                if (holder instanceof BigImgViewHolder) {
                    BigImgViewHolder bigImgViewHolder = (BigImgViewHolder) holder;
                    bigImgViewHolder.text_score.setText(info.rating);
                    bigImgViewHolder.text_des.setText(info.appslogan);
                    bigImgViewHolder.text_title.setText(info.title);
                    Glide.with(mContext)
                            .load(info.stills)
                            .into(bigImgViewHolder.imageView);
                }
                break;
            case ITEM_VIEW_TYPE_SMALL:
                Museum.MuseunInfo smallinfo = museumList.get(position);
                if (holder instanceof SmallImgViewHolder) {
                    SmallImgViewHolder smallImgViewHolder = (SmallImgViewHolder) holder;
                    smallImgViewHolder.text_des.setText(smallinfo.appslogan);
                    smallImgViewHolder.text_title.setText(smallinfo.title);
                    Glide.with(mContext)
                            .load(smallinfo.stills)
                            .into(smallImgViewHolder.imageView);

                }
                break;
            case ITEM_VIEW_TYPE_TITLE:
                Museum.MuseunInfo titleinfo = museumList.get(position);
                if (holder instanceof TitleViewHolder) {
                    TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
                    titleViewHolder.text_name.setText(titleinfo.title);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (museumList == null) {
            return 0;
        } else {
            return museumList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return museumList.get(position).viewtype;
    }

    /**
     * 轮播图
     */
    public class BannerViewHodler extends RecyclerView.ViewHolder {

        ConvenientBanner banner;

        public BannerViewHodler(View itemView) {
            super(itemView);
            banner = (ConvenientBanner) itemView.findViewById(R.id.banner_view);
            itemView.getLayoutParams().width = (int) ScreenUtils.getScreenWidth(mContext);
            itemView.getLayoutParams().height = (int) (ScreenUtils.getScreenWidth(mContext) * 4f / 7.5);

        }
    }

    /**
     * 大图的view
     */
    public class BigImgViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text_title;
        TextView text_des;
        TextView text_score;

        public BigImgViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.big_img);
            text_title = (TextView) itemView.findViewById(R.id.big_title);
            text_des = (TextView) itemView.findViewById(R.id.big_des);
            text_score = (TextView) itemView.findViewById(R.id.big_score);
            itemView.getLayoutParams().height = (int) ScreenUtils.getScreenWidth(mContext) * 2 / 3;
            itemView.getLayoutParams().width = (int) ScreenUtils.getScreenWidth(mContext);
        }
    }

    /**
     * 小图的View
     */
    public class SmallImgViewHolder extends RecyclerView.ViewHolder {
        View itemview;
        ImageView imageView;
        TextView text_title;
        TextView text_des;

        public SmallImgViewHolder(View itemView) {
            super(itemView);
            this.itemview = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.small_img);
            text_title = (TextView) itemView.findViewById(R.id.small_title);
            text_des = (TextView) itemView.findViewById(R.id.small_des);
            imageView.getLayoutParams().width = (int) ((ScreenUtils.getScreenWidth(mContext) - pagemargen * 2) / 2);
            imageView.getLayoutParams().height = (int) ((ScreenUtils.getScreenWidth(mContext) - pagemargen * 2) / 2) * 4 / 7;
        }
    }

    /**
     * 文字标题
     */
    public class TitleViewHolder extends RecyclerView.ViewHolder {
        View itemview;
        TextView text_name;

        public TitleViewHolder(View itemView) {
            super(itemView);
            this.itemview = itemView;
            text_name = (TextView) itemView.findViewById(R.id.title_name);
        }
    }

    /**
     * 既销毁view之后调用。
     *
     * @param holder
     */
    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

    }

    /**
     * 既创建view之前调用
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

    }
}
