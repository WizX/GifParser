package com.didikee.gifparser.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by didik 
 * Created time 2016/12/12
 * Description: 
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> frags = new ArrayList<>();
    private String tabTitles[] ;

    public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> frags, String[] tabTitles) {
        super(fm);
        this.frags = frags;
        this.tabTitles = tabTitles;
    }

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return frags.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
