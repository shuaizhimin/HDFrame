package com.handsome.frame.android;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.handsome.frame.android.test.ImageUrls;
import com.handsome.frame.android.utils.adapter.BaseListAdapter;
import com.handsome.frame.android.utils.encrypt.HDDesUtil;
import com.handsome.frame.android.utils.imageloader.UILImageLoader;
import com.handsome.frame.android.utils.log.HDLog;


import java.util.Arrays;
import java.util.List;

public class HandsomeActivity extends AppCompatActivity {
    private GridView mGridView;
    private GridViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handsome);
        initView();
    }
    private void initView(){
        mGridView=(GridView)findViewById(R.id.gridView);
        mAdapter=new GridViewAdapter(this);
        mGridView.setAdapter(mAdapter);
        List<String> list= Arrays.asList(ImageUrls.imgurl);
        mAdapter.setData(list);

    }


    public class GridViewAdapter extends BaseListAdapter<String>{

        public GridViewAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView=inflater.inflate(R.layout.img_test_item,null);
                holder=new ViewHolder();
                holder.img=(ImageView)convertView.findViewById(R.id.img);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            String url=getItem(position);
            UILImageLoader.displayImage(url,holder.img,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
            return convertView;
        }

        class ViewHolder{
            ImageView img;

        }
    }
}
