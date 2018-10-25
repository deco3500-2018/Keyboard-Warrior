package com.kw.kw.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.kw.kw.base.BasePager;

import java.util.ArrayList;

public class ContentFragementAdapter extends PagerAdapter {
    private final ArrayList<BasePager> basePagers;

    public ContentFragementAdapter(ArrayList<BasePager>basePagers){
        this.basePagers = basePagers;
    }

    @Override
    public int getCount() {
        return basePagers.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        BasePager basePager = basePagers.get(position);
        View rootView = basePager.rootView;
        //调用各页面的initData()
//            basePager.initData();//初始化数据
        container.addView(rootView);
        return rootView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}