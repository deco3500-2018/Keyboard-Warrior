package com.kw.kw.pager;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kw.kw.activity.MainActivity;
import com.kw.kw.base.BasePager;
import com.kw.kw.base.MenuDetailBasePager;
import com.kw.kw.domain.NewsCenter;

import com.kw.kw.fragment.LeftmenuFragment;
import com.kw.kw.utils.CacheUtils;
import com.kw.kw.utils.Constants;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class HomePager extends BasePager {
    //左侧菜单对应数据集合
    private List<NewsCenter.ArticlesBean> data;

//    private ListView listView;
//    private ArrayAdapter<String> adapter;

    private ArrayList<MenuDetailBasePager> detailBasePagers;


    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        ib_menu.setVisibility(View.VISIBLE);
        //设置标题
        home_title.setText("News");
        //联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //把子视图添加到basepager中
        fl_content.addView(textView);
        //绑定数据
        textView.setText("main content");
        //缓存缓存数据
//        String saveJson = CacheUtils.getString(context,Constants.NEWSCENTER_PAGER_URL);
//        if(!TextUtils.isEmpty(saveJson)){
//            processData(saveJson);
//        }

        getDataFromNet();

    }

    //use xUtils3 request data
    private void getDataFromNet() {
        RequestParams params = new RequestParams(Constants.NEWSCENTER_PAGER_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("Successful request from Network using xUtils3=="+result);
                //缓存数据
                CacheUtils.putString(context,Constants.NEWSCENTER_PAGER_URL,result);
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
        NewsCenter bean = parsedJson(json);
        String title = bean.getArticles().get(0).getTitle();
        LogUtil.e("Successful using Gson to parse json-title=="+title);

        data = bean.getArticles();

        MainActivity mainActivity = (MainActivity) context;
        LeftmenuFragment leftmenuFragment = mainActivity.getLeftmenuFragment();

//        detailBasePagers = new ArrayList<>();
//        detailBasePagers.add(new NewsMenuDetailPager(context,data.get(0)));
//        detailBasePagers.add(new TopicMenuDetailPager(context));
//        detailBasePagers.add(new PhotosDetailPager(context));

        leftmenuFragment.setData(data);
//        return null;
    }
//    public List<String> getDataSource(){
//        List<String> list = new ArrayList<String>()
//        for(int i = 0; i <30; i++){
//            list.add("jack"+i);
//        }
//    }


    //解析json数据，使用系统的API解析json数据，使用第三方框架解析json数据，eg.Gson,fastjson
    private NewsCenter parsedJson(String json) {
//        Gson gsn = new Gson();
//        NewsCenter bean = gsn.fromJson(json,NewsCenter.class);
        return new Gson().fromJson(json, NewsCenter.class);
    }

}
