package com.example.excaliburcreations.customerapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dell pc on 14-Apr-17.
 */

public class AdapterPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Step 1", "Step 2", "Step 3","Step 4" };
    private Context context;

    public AdapterPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FragmentUserInfo();
        } else if (position == 1) {
            return new FragmentStoreInfo();
        } else if (position == 2) {
            return new FragmentBankInfo();
        } else {
            return new FragmnetLocInfo();

        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
