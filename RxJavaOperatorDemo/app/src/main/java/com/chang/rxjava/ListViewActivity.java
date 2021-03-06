package com.chang.rxjava;

import android.content.Context;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chang.rxjava.databinding.ActivityListViewBinding;

import java.util.ArrayList;
import java.util.List;


public class ListViewActivity extends AppCompatActivity {

    private ActivityListViewBinding mBinding;
    private List<String> datas;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityListViewBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        datas = new ArrayList<>();
        adapter = new MyAdapter(this, datas, 0);
        mBinding.lvListView.setAdapter(adapter);

        asyncGetData();
    }

    private void asyncGetData() {
        for (int i = 0; i < 20; i++) {
            datas.add("一个sb" + i);
        }
        adapter.notifyDataSetChanged();
//        lvListView.setSelection(adapter.getCount());
        mBinding.lvListView.setSelection(mBinding.lvListView.getBottom());
    }

    static class MyAdapter<T> extends BaseAdapter {

        private Context context;
        private List<T> datas;
        private int layoutResId;

        public MyAdapter(Context context, List<T> datas, int layoutResId) {
            this.context = context;
            this.datas = datas;
            this.layoutResId = layoutResId;
        }

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public T getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = View.inflate(context, layoutResId, parent);
            TextView view = new TextView(context);
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(context, 50));
            view.setLayoutParams(layoutParams);
            view.setText((CharSequence) datas.get(position));
            return view;
        }

//        private int dip2px(Context context, float dip) {
//            float scale = context.getResources().getDisplayMetrics().density;
//            return (int) (dip * scale + 0.5);
//        }

        private int dip2px(Context context, float dip) {
            Resources resources = context.getResources();
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, resources.getDisplayMetrics());
        }
    }
}
