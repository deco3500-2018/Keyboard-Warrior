package com.kw.kw.fragment;


import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kw.kw.R;
import com.kw.kw.activity.MainActivity;
import com.kw.kw.base.BaseFragment;
import com.kw.kw.domain.NewsCenter;
import com.kw.kw.pager.HomePager;


import org.xutils.common.util.LogUtil;

import java.util.List;

//Left menu fragment
public class LeftmenuFragment extends BaseFragment {

  private ListView listView;
    private List<NewsCenter.ArticlesBean> data;
    private LeftmenuFragmentAdapter adapter;

    private int prePosition;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View initView() {
        listView = new ListView(context);
        listView.setPadding(0, 40,0,0);
        listView.setDividerHeight(0);
        listView.setCacheColorHint(Color.TRANSPARENT);

        listView.setSelector(android.R.color.transparent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prePosition = position;
                adapter.notifyDataSetChanged();

                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();

//                swichPager(prePosition);

            }
        });
        return listView;
    }

//    private void swichPager(int position) {
//        MainActivity mainActivity = (MainActivity) context;
//        ContentFragement contentFragement = mainActivity.getContentFragment();
//        HomePager homePager = contentFragement.getHomepager();
//        homePager.swichPager(position);
//    }

    @Override
    public void initData() {
        super.initData();
    }

    class LeftmenuFragmentAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(context, R.layout.item_leftmenu,null);
            textView.setText((CharSequence) data.get(position).getTitle());
            textView.setEnabled(position==prePosition);
            return textView;

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    public void setData(List<NewsCenter.ArticlesBean> data) {
        this.data = data;
        for (int i=0;i<data.size();i++){
            LogUtil.e("Title=="+data.get(i).getTitle());
        }

        adapter = new LeftmenuFragmentAdapter();
        listView.setAdapter(adapter);
    }


}

