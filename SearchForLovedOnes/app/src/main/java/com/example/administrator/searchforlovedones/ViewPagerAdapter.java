package com.example.administrator.searchforlovedones;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;

    public ViewPagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = new ViewPagers();
                return fragment;

            case 1:
                fragment = new F3();
                return fragment;

            case 2:
                fragment = new F4();
                return fragment;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
