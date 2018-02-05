package com.technobrix.tbx.safedoors.NewGatekeeper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.technobrix.tbx.safedoors.Emergency;
import com.technobrix.tbx.safedoors.Society;

/**
 * Created by tvs on 10/26/2017.
 */

public class NotiPager extends FragmentStatePagerAdapter {


    public NotiPager(FragmentManager fm , int tab) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){

            return new Regular();
        }

        else if (position == 1){

            return new NotiNew();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
