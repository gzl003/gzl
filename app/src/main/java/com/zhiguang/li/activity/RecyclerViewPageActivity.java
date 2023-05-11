package com.zhiguang.li.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhiguang.li.R;
import com.zhiguang.li.adapter.CardAdapter;
import com.zhiguang.li.utils.BlurBitmapUtils;
import com.zhiguang.li.utils.ViewSwitchUtils;
import com.zhiguang.li.widget.recyclerviewpage.AnimManager;
import com.zhiguang.li.widget.recyclerviewpage.GalleryRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿照viewpager的recyclerview
 */
public class RecyclerViewPageActivity extends AppCompatActivity implements GalleryRecyclerView.OnItemClickListener {

    private GalleryRecyclerView mRecyclerView;
    private CardAdapter adapter;
    private ImageView burview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerviewpage);
        mRecyclerView = findViewById(R.id.rv_list);
        burview = findViewById(R.id.burview);

        adapter = new CardAdapter(getDatas());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.initFlingSpeed(9000)                                   // 设置滑动速度（像素/s）
                .initPageParams(0, 50)     // 设置页边距和左右图片的可见宽度，单位dp
                .setAnimFactor(0.15f)                                   // 设置切换动画的参数因子
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)            // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
                .setOnItemClickListener(this);                          // 设置点击事件


        // 背景高斯模糊 & 淡入淡出
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    setBlurImage();
                }
            }
        });
        setBlurImage();
    }

    public void setBlurImage() {
        burview.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), adapter.getResId(mRecyclerView.getScrolledPosition()));
                ViewSwitchUtils.startSwitchBackgroundAnim(burview, BlurBitmapUtils.getBlurBitmap(burview.getContext(), bitmap, 15));
            }
        }, 300);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    /***
     * 测试数据
     * @return
     */
    public List<Integer> getDatas() {

        List<Integer> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(R.drawable.adapterview_1);
            mList.add(R.drawable.adapterview_2);
            mList.add(R.drawable.adapterview_4);
            mList.add(R.drawable.adapterview_5);
            mList.add(R.drawable.adapterview_6);
        }
        return mList;
    }
}
