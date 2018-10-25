package com.kw.kw.base;

import android.content.Context;
import android.view.View;

public abstract class MenuDetailBasePager {
    public  final Context context;
    public View rootView;

    public MenuDetailBasePager(Context context){
        this.context = context;
        rootView = initView();
    }

    public MenuDetailBasePager() {

        context = null;
    }

    public abstract View initView();

    public void initData(){
        
    }

}
