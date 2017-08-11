package com.zhiguang.li.view;

import android.graphics.Matrix;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiguang.li.R;

/**
 *  * Created by 智光 on 2017/2/8 15:58
 *  Matrix 实现滚动视觉差
 */
public class ImgViewRecycleItem extends RecyclerView.ViewHolder {
    public ImageView imgview;
    public TextView textView;

    public Matrix matrix;

    public ImgViewRecycleItem(View itemView) {
        super(itemView);
        imgview = (ImageView) itemView.findViewById(R.id.recycle_img);
        textView = (TextView) itemView.findViewById(R.id.item_name);
        matrix = new Matrix();
    }

    public void setAnmin() {
        matrix.set(imgview.getImageMatrix());
        matrix.postTranslate(0, -15);
        imgview.setImageMatrix(matrix);
    }

    private ScrollListener scrollListener;

    public void setScrollListener(ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public interface ScrollListener{
        void imgScroll();
    }
}
