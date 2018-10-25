package com.kw.kw.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.kw.kw.R;
import com.kw.kw.fragment.ContentFragement;
import com.kw.kw.fragment.FakenewsFragment;
import com.kw.kw.fragment.LeftmenuFragment;
import com.kw.kw.utils.DensityUtil;

public class MainActivity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSlidingMenu();
        //初始化Fragment
        initFragment();

    }

    private void initSlidingMenu() {
        //设置主页面
        setContentView(R.layout.activity_main);

        //设置左侧菜单
        setBehindContentView(R.layout.activity_leftmenu);

        //设置显示模式
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //设置主页占据宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this,200));
    }

    private void initFragment() {
        //得到fragment manger
        FragmentManager fm = getSupportFragmentManager();
        //开始
        FragmentTransaction ft = fm.beginTransaction();
        //替换
        ft.replace(R.id.fl_main_content, new ContentFragement(), MAIN_CONTENT_TAG);//main page
        ft.replace(R.id.fl_leftmenu, new LeftmenuFragment(), LEFTMENU_TAG);//left menu page
        //提交
        ft.commit();
    }

    public LeftmenuFragment getLeftmenuFragment() {
//        FragmentManager fm = getSupportFragmentManager();
        return (LeftmenuFragment) getSupportFragmentManager().findFragmentByTag(LEFTMENU_TAG);
    }

    public FakenewsFragment getFakenewsFragment() {
//        FragmentManageFakenewsFragmentfm = getSupportFragmentManager();
        return (FakenewsFragment) getSupportFragmentManager().findFragmentByTag(MAIN_CONTENT_TAG);
    }

    public ContentFragement getContentFragment() {
        return (ContentFragement) getSupportFragmentManager().findFragmentByTag(MAIN_CONTENT_TAG);
    }
}
