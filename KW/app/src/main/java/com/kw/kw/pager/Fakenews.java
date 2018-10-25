package com.kw.kw.pager;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

import com.google.gson.Gson;

import com.kw.kw.base.BasePager;

import com.kw.kw.domain.FakeNewsCenter;
import com.kw.kw.utils.CacheUtils;
import com.kw.kw.utils.FakeNewsConstants;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class Fakenews extends BasePager {
    public Fakenews(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        //设置标题
        home_title.setText("Fake News");

        //联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //把子视图添加到basepager中
        fl_content.addView(textView);

        //缓存缓存数据
        String saveJson = CacheUtils.getString(context,FakeNewsConstants.FAKENEWS_PAGER_URL);
        if(!TextUtils.isEmpty(saveJson)){
            processData(saveJson);
        }

        getDataFromNet();

    }

    //use xUtils3 request data
    private void getDataFromNet() {
        RequestParams params = new RequestParams(FakeNewsConstants.FAKENEWS_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("Successful request from Network using xUtils3=="+result);
                //缓存数据
                CacheUtils.putString(context,FakeNewsConstants.FAKENEWS_PAGER_URL,result);
                processData(result);
                //设置适配器
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("Failed to request from Network using xUtils3=="+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("Used xUtils3-onCancelled=="+cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("Used xUtils3-onFinished==");
            }
        });
    }

    //解析和显示json数据
    private void processData(String json) {
        FakeNewsCenter bean = parsedJson(json);
        String title = bean.getArticles().get(0).getTitle();
        LogUtil.e("Successful using Gson to parse json-title=="+title);


    }

    //解析json数据，使用系统的API解析json数据，使用第三方框架解析json数据，eg.Gson,fastjson
    private FakeNewsCenter parsedJson(String json) {
//        Gson gsn = new Gson();
//        NewsCenter bean = gsn.fromJson(json,NewsCenter.class);
        return new Gson().fromJson(json, FakeNewsCenter.class);
    }


}