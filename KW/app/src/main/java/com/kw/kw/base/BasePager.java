package com.kw.kw.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kw.kw.R;
import com.kw.kw.activity.MainActivity;

public class BasePager {

    public final Context context;//MainActivity

    public View rootView;
    public TextView home_title;
    public ImageButton ib_menu;//侧滑
    public FrameLayout fl_content;


    public BasePager(Context context){
        this.context = context;
        rootView = initView();
    }

    private  View initView(){
        View view = View.inflate(context, R.layout.base_pager,null);
        home_title = view.findViewById(R.id.home_title);
        ib_menu = view.findViewById(R.id.ib_menu);
        fl_content = view.findViewById(R.id.fl_content);
        ib_menu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();
            }
        });
        return view;
    }

    public void initData(){

    }
}
