package com.example.gzl.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gzl.R;
import com.example.gzl.utils.ScreenUtils;

/**
 * Created by 智光 on 2016/4/12.
 */
public class SelectItemWidget extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private View itemview;
    private OnClickedListener clickedListener;

    private TextView textContent;
    private ImageView down_ed;
    private ImageView no_down;
    private boolean isChecked;
    private boolean isDowned;
    private int width;

    public SelectItemWidget(Context context) {
        super(context);
        this.context = context;
        width = (int) ((ScreenUtils.getScreenWidth(context) - 50) / 7);
        initView();
        Log.e("SelectItemWidget", "SelectItemWidget");
    }

    public SelectItemWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public SelectItemWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        itemview = LayoutInflater.from(context).inflate(R.layout.select_item_view, null, false);
        textContent = (TextView) itemview.findViewById(R.id.text_context);
        down_ed = (ImageView) itemview.findViewById(R.id.down_ed);
        no_down = (ImageView) itemview.findViewById(R.id.no_down);
        itemview.setBackgroundResource(R.drawable.select_no);
        isChecked = false;
        itemview.setOnClickListener(this);
        itemview.setLayoutParams(new LayoutParams(width, width));//设置每个view的宽高
        addView(itemview);
    }

    public void setClickedListener(OnClickedListener clickedListener) {
        this.clickedListener = clickedListener;
    }

    /**
     * 设置textview
     *
     * @param text
     */
    public void setTextContext(String text) {
        textContent.setText(text);
    }

    /**
     * 设置下载状态
     *
     * @param downing
     */
    public void setDowned(boolean downing) {
        if (downing) {
            down_ed.setVisibility(View.VISIBLE);//下载过的
            itemview.setBackgroundResource(R.drawable.select_ed);
        } else {
            down_ed.setVisibility(View.GONE);//没下载的
            itemview.setBackgroundResource(R.drawable.select_no);
        }
        isDowned = downing;
    }

    /**
     * 设置选中状态
     *
     * @param isSelect
     */
    public void setSltState(boolean isSelect) {
        if (isSelect) {
            down_ed.setVisibility(View.VISIBLE);
            isChecked = true;
        } else {
            down_ed.setVisibility(View.GONE);
            isChecked = false;
        }
    }

    /**
     * 设置是否可以下载的状态
     *
     * @param isok
     */
    public void setCanDown(boolean isok) {
        if (isok) {
            no_down.setVisibility(View.GONE);
//            itemview.setEnabled(false);//不可点击
        } else {
            no_down.setVisibility(View.VISIBLE);
//            itemview.setEnabled(true);//可点击
        }
    }

    /**
     * 判断是否可以下载
     *
     * @return
     */
    public boolean getCanDown() {
        if (no_down.getVisibility() == VISIBLE) {//不可下载
            return false;
        } else if (isDowned()) {//下载过的
            return false;
        } else {//没有下载的且可以下载
            return true;
        }
    }

    /**
     * 判断按钮的选中状态
     *
     * @return
     */
    public boolean getDowning() {
        return isChecked;
    }

    /**
     * 判断下载的状态
     *
     * @return
     */
    public boolean isDowned() {
        return isDowned;
    }

    @Override
    public void onClick(View v) {
        if (getCanDown()) {
            if (getDowning()) {//得到选中状态
                setSltState(false);
            } else {
                setSltState(true);
            }
            clickedListener.refreshButton();
        } else {
            if (isDowned()) {
                Toast.makeText(context, "缓存过的影片不能重复缓存", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "由于版权问题该影片暂不提供缓存", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public interface OnClickedListener {
        void refreshButton();
    }
}
