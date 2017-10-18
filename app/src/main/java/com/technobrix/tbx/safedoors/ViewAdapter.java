package com.technobrix.tbx.safedoors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by tvs on 10/16/2017.
 */

public class ViewAdapter extends FragmentStatePagerAdapter {


    public ViewAdapter(FragmentManager fm , int tab) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        if (position == 0){

            return new Society();
        }

        else if (position == 1){

            return new Emergency();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
