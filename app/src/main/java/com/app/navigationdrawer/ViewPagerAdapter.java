package com.app.navigationdrawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.navigationdrawer.Fragment.Fragment1;
import com.app.navigationdrawer.Fragment.Fragment2;
import com.app.navigationdrawer.Fragment.Fragment3;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int numTabs;
    public ViewPagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs=numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Fragment1 fragment1 = new Fragment1();
                return fragment1;
            case 1:
                Fragment2 fragment2 = new Fragment2();
                return fragment2;
            case 2:
                Fragment3 fragment3 = new Fragment3();
                return fragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}

