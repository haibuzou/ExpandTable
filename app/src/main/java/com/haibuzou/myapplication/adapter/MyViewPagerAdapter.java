package com.haibuzou.myapplication.adapter;

import java.util.List;

import android.support.v4.view.ViewPager;
import android.view.View;

public class MyViewPagerAdapter extends android.support.v4.view.PagerAdapter {

    private List<View> mlistViews;


    public MyViewPagerAdapter(List<View> mlistViews) {
        this.mlistViews = mlistViews;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mlistViews.size();
    }


    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mlistViews.get(position));
        return mlistViews.get(position);
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(mlistViews.get(arg1));
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

}
