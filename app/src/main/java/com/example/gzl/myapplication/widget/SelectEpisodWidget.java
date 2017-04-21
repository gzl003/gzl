package com.example.gzl.myapplication.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gzl.myapplication.R;
import com.example.gzl.myapplication.adapter.PopAdapter;
import com.example.gzl.myapplication.adapter.SelectAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 智光 on 2016/4/13.
 */
public class SelectEpisodWidget extends RelativeLayout implements View.OnClickListener, PopAdapter.HDChackedListener {

    private Context context;
    private GridView myGridView;
    private TextView text_back;
    private TextView text_hd;
    private Button but_select;
    private Button but_start;
    private List<String> mlist;
    private List<String> poplist;
    private List<String> checkMovie;
    private SelectAdapter mAdapter;
    private SelectItemWidget.OnClickedListener clickedListener;
    private CloseListener closeListener;
    public PopupWindow mPopupWindow;
    private PopAdapter popAdapter;

    public SelectEpisodWidget(Context context) {
        super(context);
        this.context = context;
        initVew();
    }

    public SelectEpisodWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        initVew();
    }

    public SelectEpisodWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVew();
    }

    public void setPoplist(List<String> poplist) {
        this.poplist = poplist;
    }

    public void setMlist(List<String> mlist) {
        this.mlist = mlist;
    }

    private void initVew() {
        View itemview = LayoutInflater.from(context).inflate(R.layout.select_movie_layout, null, false);
        text_back = (TextView) itemview.findViewById(R.id.text_back);
        text_hd = (TextView) itemview.findViewById(R.id.text_hd);
        but_select = (Button) itemview.findViewById(R.id.but_select);
        but_start = (Button) itemview.findViewById(R.id.but_start);
        myGridView = (GridView) itemview.findViewById(R.id.movie_juji);
        text_back.setOnClickListener(this);
        text_hd.setOnClickListener(this);

        but_select.setOnClickListener(this);
        but_start.setOnClickListener(this);
        checkMovie = new ArrayList<String>();
        setData();
        initPopuptWindow();
        addView(itemview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_select:
                toSelect();
                break;
            case R.id.text_back:
                if (closeListener != null) {
                    closeListener.close();
                }
                break;
            case R.id.text_hd:
                showPopWindow();
                break;
        }
    }

    private void setData() {
        mlist = new ArrayList<String>();
        for (int i = 0; i < 8; i++) {
            mlist.add(i + 1 + "");
        }
        clickedListener = new SelectItemWidget.OnClickedListener() {
            @Override
            public void refreshButton() {
                refresh();

            }
        };
        mAdapter = new SelectAdapter(context, mlist, clickedListener);
        myGridView.setAdapter(mAdapter);
//        mAdapter.setmList(mlist);
    }

    /**
     * 创建PopupWindow
     */
    private void initPopuptWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View popupWindow = layoutInflater.inflate(R.layout.popup_window, null);
        ListView my_listview = (ListView) popupWindow.findViewById(R.id.my_listview);
        poplist = new ArrayList<String>();
        poplist.add("标清");
        poplist.add("流畅");
        poplist.add("高清");
        popAdapter = new PopAdapter(context, poplist);
        popAdapter.setHdChackedListener(this);
        my_listview.setAdapter(popAdapter);
        mPopupWindow = new PopupWindow(popupWindow, 150, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    /**
     * 获取PopupWindow实例
     */
    private void showPopWindow() {
        if (null != mPopupWindow) {
            if (mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            } else {
                mPopupWindow.showAsDropDown(text_hd, -30, -5);
            }
        }
    }

    /**
     * 选择按钮
     */
    public void toSelect() {
        if (isSelectAll()) {
            for (int i = 0; i < mAdapter.getViewlist().size(); i++) {
                if (mAdapter.getViewlist().get(i).getCanDown()) {
                    mAdapter.getViewlist().get(i).setSltState(false);
                }
            }
        } else {
            for (int i = 0; i < mAdapter.getViewlist().size(); i++) {
                if (mAdapter.getViewlist().get(i).getCanDown()) {
                    mAdapter.getViewlist().get(i).setSltState(true);
                }
            }
        }
        refresh();
    }

    /**
     * 判断是不是全选
     *
     * @return
     */
    public boolean isSelectAll() {
        int canselect = 0;
        boolean isAll;
        for (int i = 0; i < mAdapter.getViewlist().size(); i++) {
            if (mAdapter.getViewlist().get(i).getCanDown()) {//判断是不是可选
                canselect++;//可选的个数
            }
        }
        if (getSelectCount() == canselect) {
            isAll = true;
        } else {
            isAll = false;
        }
        return isAll;
    }

    /**
     * 得到选中的个数
     *
     * @return
     */
    public int getSelectCount() {
        int count = 0;
        if (mAdapter != null) {
            for (int i = 0; i < mAdapter.getViewlist().size(); i++) {
                if (mAdapter.getViewlist().get(i).getDowning()) {
                    count++;
                    mlist.get(i);
                }
            }
        }
        return count;
    }

    /**
     * 得到选中的集合
     *
     * @return
     */
    public List<String> getCheckMovie() {
        if (checkMovie == null) {
            checkMovie = new ArrayList<String>();
        } else {
            checkMovie.clear();
        }
        for (int i = 0; i < mAdapter.getViewlist().size(); i++) {
            if (mAdapter.getViewlist().get(i).getDowning()) {
                checkMovie.add(mlist.get(i));
            }
        }
        return checkMovie;
    }

    /**
     * 刷新按钮
     */
    public void refresh() {
        if (isSelectAll()) {
            but_select.setText("取消全选");
        } else {
            but_select.setText("全选");
        }
        if (getSelectCount() > 0) {
            but_select.setBackgroundColor(Color.RED);
            but_start.setBackgroundColor(Color.RED);
        } else {
            but_select.setBackgroundColor(Color.GRAY);
            but_start.setBackgroundColor(Color.GRAY);
        }
        Toast.makeText(context, "已经选择了" + getCheckMovie().size() + "个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChchecked(String string) {
        mPopupWindow.dismiss();
        text_hd.setText(string);
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

    public interface CloseListener {
        void close();
    }
}
