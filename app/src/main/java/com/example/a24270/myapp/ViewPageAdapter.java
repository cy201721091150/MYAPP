package com.example.a24270.myapp;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.logging.Handler;

public class ViewPageAdapter extends PagerAdapter{

    private List<App> homelist;
    private List<ImageView>imageViewList;
    private Context context;

    public ViewPageAdapter(List<App>homelist,List<ImageView>imageViewList,Context context){
        this.imageViewList = imageViewList;
        this.context = context;
        this.homelist = homelist;
    }


        @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final int reposition = position % homelist.size();
        ImageView imageView = imageViewList.get(reposition);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        App banner = homelist.get(reposition);
            Picasso.with(context)
                    .load(banner.getImagePath())
                    .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App banner = homelist.get(reposition);
                Intent intent = new Intent(context, web.class);
                intent.putExtra("data", banner.getUrl());
                context.startActivity(intent);
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public CharSequence getPageTitle(int positon){
        return homelist.get(positon % homelist.size()).getTitle();
    }
}
