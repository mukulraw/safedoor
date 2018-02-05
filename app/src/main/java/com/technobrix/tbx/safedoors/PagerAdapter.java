package com.technobrix.tbx.safedoors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by tvs on 11/17/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm , int tab) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {

            return new MyBooking();
        } else if (position == 1) {

            return new Book();
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
