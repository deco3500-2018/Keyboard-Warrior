package com.kw.kw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kw.kw.R;
import com.kw.kw.SplashActivity;
import com.kw.kw.utils.CacheUtils;
import com.kw.kw.utils.DensityUtil;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private static final String TAG = GuideActivity.class.getSimpleName();
    private ViewPager viewpager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;
    private ArrayList<ImageView> imageViews;

   //两点间距
    private int leftmax;

    private int widthdpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);

        //准备数据
        int[] ids = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };

        widthdpi = DensityUtil.dip2px(this,10);
        Log.e(TAG,widthdpi+"-------");

        imageViews = new ArrayList<>();
        for (int i = 0; ids.length > i; i++){
            ImageView imageView = new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[1]);
            //添加到集合中
            imageViews.add(imageView);

            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi,widthdpi);
            if(i!=0){
                params.leftMargin = widthdpi;
            }
            point.setLayoutParams(params);
            //添加线性布局里
            ll_point_group.addView(point);

        }

        viewpager.setAdapter(new MyPagerAdapter());

        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        //得到滑动百分比
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        btn_start_main.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //save state(user had saw the guide_page)
                CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);

                //enter main page
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                //close guide_page
                finish();
            }
        });

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //recall method when page come back
        @Override
        public void onPageScrolled(int i, float v, int i1) {
//            int leftmargin = (int) (v * leftmax);
            Log.e(TAG, "position==" +i+",positionOffset"+v+",PositionOffsetPixels=="+i1);

            int leftmargin = (int) (i*leftmax + (v* leftmax));

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftmargin;
            iv_red_point.setLayoutParams(params);
        }

        //recall method when the page is selected
        @Override
        public void onPageSelected(int i) {
            if (i == imageViews.size()-1){
                btn_start_main.setVisibility(View.VISIBLE);
            }else{
                btn_start_main.setVisibility(View.GONE);
            }

        }

        //recall method when status of page was changed
        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onGlobalLayout() {
            //执行不止一次
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(MyOnGlobalLayoutListener.this);

            leftmax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
        }
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
//            return position;
            return imageView;
//           return super.instantiateItem(container, position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
//           return view == imageViews.get(Integer.parseInt((String) object);
            return view ==object;
        }
//
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }
    }
}
