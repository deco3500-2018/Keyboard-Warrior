package com.kw.kw.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.kw.kw.base.BasePager;

public class Profile extends BasePager {
    public Profile(Context context) {
        super(context);

        //设置标题
        home_title.setText("Profile");
        //联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //把子视图添加到basepager中
        fl_content.addView(textView);
        //绑定数据
        textView.setText("main content");

    }
}
