package com.wwren.all.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 任伟伟
 * Datetime: 2017/2/8-19:40
 * Email: renweiwei@ufashion.com
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private List titles;
    private List<Fragment> fragments;

    public FragmentViewPagerAdapter(FragmentManager fm, List titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments=fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles!=null?titles.get(position).toString():"";
    }

    @Override
    public int getCount() {
        return fragments!=null?fragments.size():0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
