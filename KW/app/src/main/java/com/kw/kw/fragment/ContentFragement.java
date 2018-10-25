package com.kw.kw.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.kw.kw.R;
import com.kw.kw.activity.MainActivity;
import com.kw.kw.adapter.ContentFragementAdapter;
import com.kw.kw.base.BaseFragment;
import com.kw.kw.base.BasePager;
import com.kw.kw.pager.Fakenews;
import com.kw.kw.pager.HomePager;
import com.kw.kw.pager.Profile;
import com.kw.kw.view.NoScrollViewPage;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class ContentFragement extends BaseFragment {
//  初始化控件
@ViewInject(R.id.viewpager)
    private NoScrollViewPage  viewpager;

    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    private ArrayList<BasePager> basePagers;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.content_fragment, null);
//        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
//        rg_main = (RadioGroup) view.findViewById(R.id.rg_main);
        //put view into the fragement in order to associate ContentFragement.this with view
        x.view().inject(ContentFragement.this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        //初始化页面，并放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context));
        basePagers.add(new Fakenews(context));
        basePagers.add(new Profile(context));

       //设置默认页面
        rg_main.check(R.id.rb_home);

        //设置ViewPager的适配器
        viewpager.setAdapter(new ContentFragementAdapter(basePagers));

        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        rg_main.check(R.id.rb_home);
        basePagers.get(0).initData();
        isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);

    }

    public HomePager getHomepager() {
        return (HomePager) basePagers.get(1);
    }



    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
//            BasePager basePager = basePagers.get(i);
            basePagers.get(i).initData();
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home:
                    viewpager.setCurrentItem(0,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_fakenews:
                    viewpager.setCurrentItem(1,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_profile:
                    viewpager.setCurrentItem(2,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }
        }


    }

    private void isEnableSlidingMenu(int touchmodeFullscreen) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmodeFullscreen);
    }
}
