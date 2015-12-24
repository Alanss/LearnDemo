package com.alan.learndemo;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alan.learndemo.custom.MyListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "xjw";

    private MyListView mListView;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (MyListView) findViewById(R.id.aa);

        mData = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            mData.add("asdfasdf" + i);
        }
        //设置一个空的布局 没多大用处
        mListView.setEmptyView(findViewById(R.id.entryImage));


        mListView.setAdapter(new MyAdapter());

        // 遍历ListView中的所有Item
        //   for (int i = 0 ; i < mListView.getChildCount() ; i++){
        //     View view =  mListView.getChildAt(i);
        //       Log.e(TAG, "aaaaa");
        //   }

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Log.e(TAG, "滑动停止时");
                        Log.e(TAG, "获取可视视图中最后一个item的ID：" + mListView.getLastVisiblePosition());
                        Log.e(TAG , "获取可视视图中第一个item的ID：" + mListView.getFirstVisiblePosition());
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.e(TAG, "正在滚动时");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Log.e(TAG, "手指抛动时，即手指用力滑动，在离开后由于惯性继续滑动");
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // Log.e(TAG , "firstVisibleItem:" + firstVisibleItem);
                //  Log.e(TAG , "visibleItemCount:" + visibleItemCount);
                // Log.e(TAG , "totalItemCount:" + totalItemCount);
            }
        });


    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder;
            if (convertView == null) {
                mViewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, null);
                mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.textView);

                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            mViewHolder.mTextView.setText(mData.get(position));
            return convertView;
        }
    }

    class ViewHolder {
        private TextView mTextView;
    }
}
