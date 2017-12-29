package com.zhiguang.li.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.zhiguang.li.R;
import com.zhiguang.li.adapter.GralleryPagerAdapter;
import com.zhiguang.li.utils.BlurBitmapUtils;
import com.zhiguang.li.utils.GrallyPageTransfrom;
import com.zhiguang.li.utils.ViewSwitchUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 画廊效果
 * clipToPadding属性是我们在一个页面中显示多个ViewPager  item
 */
public class GalleryTwoActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private String[] imageViews;
    private ImageView img_bar;
    public Runnable mBlurRunnable;
    public List<Integer> mList = new ArrayList<>();
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_two);
        mViewPager = (ViewPager) findViewById(R.id.gallery_pager);
        img_bar = (ImageView) findViewById(R.id.img_bar);
        initData();

        // 1.设置幕后item的缓存数目
        mViewPager.setOffscreenPageLimit(3);
        // 2.设置页与页之间的间距
        mViewPager.setPageMargin((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                48, getResources().getDisplayMetrics()));

        mViewPager.setPageTransformer(true, new GrallyPageTransfrom());
        mViewPager.setAdapter(new GralleryPagerAdapter(GalleryTwoActivity.this, imageViews, img_bar));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                Log.e("setPrimaryItem", imageViews[position]);
                img_bar.removeCallbacks(mBlurRunnable);
                mBlurRunnable = new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mList.get(position % 5));
                        ViewSwitchUtils.startSwitchBackgroundAnim(img_bar, BlurBitmapUtils.getBlurBitmap(img_bar.getContext(), bitmap, 15));
                    }
                };
                img_bar.postDelayed(mBlurRunnable, 500);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {


        mList.add(R.drawable.adapterview_5);
        mList.add(R.drawable.adapterview_6);
        mList.add(R.drawable.pic4);
        mList.add(R.drawable.pic5);
        mList.add(R.drawable.pic6);


        imageViews = new String[]{
                "https://pic8.huanxi.com/8a9eb00f5ec67bb1015ec7067fee00b9.jpg"
                , "https://pic1.huanxi.com/8a9eb00f5ec67bb1015ec706803900ba.jpg"
                , "https://pic0.huanxi.com/8a9eb00f5ec67bb1015ec706809c00bb.jpg"
                , "https://pic6.huanxi.com/8a9eb00f5ec67bb1015ec706810400bc.jpg"
                , "https://pic0.huanxi.com/8a9eb00f5ec67bb1015ec706817400bd.jpg"
                , "https://pic6.huanxi.com/8a9eb00f5ec67bb1015ec70681c900bf.jpg"
                , "https://pic8.huanxi.com/8a9eb00f5ec67bb1015ec706823f00c3.jpg"
                , "https://pic1.huanxi.com/8a9eb00f5ec67bb1015ec706829200c5.jpg"
                , "https://pic6.huanxi.com/8a9eb00f5ec67bb1015ec70682f300c7.jpg",
                "https://pic9.huanxi.com/8a9eb00f5ec67bb1015ec706835b00c9.jpg",
                "https://pic5.huanxi.com/8a9eb00f5ec67bb1015ec70683ca00cb.jpg",
                "https://pic7.huanxi.com/8a9eb00f5ec67bb1015ec706843200cd.jpg",
                "https://pic2.huanxi.com/8a9eb00f5ec67bb1015ec706849b00ce.jpg",
                "https://pic2.huanxi.com/8a9eb00f5ec67bb1015ec706850000d0.jpg",
                "https://pic3.huanxi.com/8a9eb00f5ec67bb1015ec706856600d2.jpg"
                , "https://pic3.huanxi.com/8a9eb00f5ec67bb1015ec70685eb00d6.jpg"
                , "https://pic2.huanxi.com/8a9eb00f5ec67bb1015ec706862d00d7.jpg"
                , "https://pic7.huanxi.com/8a9eb00f5ec67bb1015ec706869500d9.jpg"
                , "https://pic2.huanxi.com/8a9eb00f5ec67bb1015ec70686f700db.jpg"
                , "https://pic8.huanxi.com/8a9eb00f5ec67bb1015ec706875c00dd.jpg"
                , "https://pic0.huanxi.com/8a9eb00f5ec67bb1015ec706c1b700f7.jpg"
                , "https://pic0.huanxi.com/8a9eb00f5ec67bb1015ec706c22300f8.jpg"
                , "https://pic6.huanxi.com/8a9eb00f5ec67bb1015ec706c28200f9.jpg"
                , "https://pic8.huanxi.com/8a9eb00f5ec67bb1015ec706c2f300fb.jpg"
                , "https://pic4.huanxi.com/8a9eb00f5ec67bb1015ec706c34b00fd.jpg"
                , "https://pic1.huanxi.com/8a9eb00f5ec67bb1015ec706c3b800ff.jpg"
                , "https://pic3.huanxi.com/8a9eb00f5ec67bb1015ec706c4190101.jpg"
                , "https://pic1.huanxi.com/8a9eb00f5ec67bb1015ec706c4920103.jpg"
                , "https://pic5.huanxi.com/8a9eb00f5ec67bb1015ec706c4e30105.jpg"
                , "https://pic0.huanxi.com/8a9eb00f5ec67bb1015ec706c54b0107.jpg"
                , "https://pic4.huanxi.com/8a9eb00f5ec67bb1015ec706c5ad0109.jpg"
                , "https://pic1.huanxi.com/8a9eb00f5ec67bb1015ec706c620010b.jpg"
                , "https://pic7.huanxi.com/8a9eb00f5ec67bb1015ec706c679010d.jpg"
                , "https://pic2.huanxi.com/8a9eb00f5ec67bb1015ec706c6d7010f.jpg"
                , "https://pic8.huanxi.com/8a9eb00f5ec67bb1015ec706c7440111.jpg"
                , "https://pic2.huanxi.com/8a9eb00f5ec67bb1015ec706c7cc0114.jpg"
                , "https://pic2.huanxi.com/8a9eb00f5ec67bb1015ec706c8160115.jpg"
                , "https://pic1.huanxi.com/8a9eb00f5ec67bb1015ec706c8bf0118.jpg"
                , "https://pic0.huanxi.com/8a9eb00f5ec67bb1015ec706c8e70119.jpg"
                , "https://pic8.huanxi.com/8a9eb00f5ec67bb1015ec706c974011b.jpg"
                , "https://pic6.huanxi.com/8a9eb00f5ec67bb1015ec706f22d011f.jpg"
                , "https://pic2.huanxi.com/8a9eb00f5ec67bb1015ec706f28b0120.jpg"
                , "https://pic1.huanxi.com/8a9eb00f5ec67bb1015ec706f30c0121.jpg"
                , "https://pic2.huanxi.com/8a9eb00f5ec67bb1015ec706f34f0123.jpg"
                , "https://pic5.huanxi.com/8a9eb00f5ec67bb1015ec706f3b70125.jpg"
                , "https://pic4.huanxi.com/8a9eb00f5ec67bb1015ec706f41a0128.jpg"};


    }
}
