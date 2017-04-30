package com.example.excaliburcreations.customerapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dell pc on 14-Apr-17.
 */

public class AdapterPagerAdapter extends FragmentPagerAdapter {
    SignupActivity signupActivity = new SignupActivity();
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Step 1", "Step 2", "Step 3"};
    private Context context;
    private Bundle args;

    public AdapterPagerAdapter(FragmentManager fm, Context context, Bundle args) {
        super(fm);
        this.context = context;
        this.args = args;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                return FragmentUserInfo.newInstance(0);

            case 1:
                return FragmentStoreInfo.newInstance(1);

            case 2 :
                //signupActivity.setButton();
                return FragmentBankInfo.newInstance(2);

            default:
                return null;
        }

//        if (position == 0) {
//            return new FragmentUserInfo();
//        } else if (position == 1) {
//            return new FragmentStoreInfo();
//        } else if (position == 2) {
//            return new FragmentBankInfo();
//        } else if (position == 3) {
//            FragmnetLocInfo fragmnetLocInfo =  new FragmnetLocInfo();
////            fragmnetLocInfo.setArguments(args);
//            return fragmnetLocInfo;
//
//
//        }

    }



    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
