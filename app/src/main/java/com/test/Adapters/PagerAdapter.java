package com.test.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.fabricit.bookit.R;
import com.fabricit.bookit.fragment.PastBookingFragment;
import com.fabricit.bookit.fragment.UpComingFragment;

/**
 * Created by jelly on 11/10/2019.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private UpComingFragment upComingFragment;
    private PastBookingFragment pastBookingFragment;
    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        upComingFragment = new UpComingFragment();
        pastBookingFragment = new PastBookingFragment();
        mContext = context;
    }

    // This determines the Fragment for each tab
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return upComingFragment;
        } else {
            return pastBookingFragment;
        }
    }

    public void refreshFragment(int position) {
        switch (position) {
            case 0:
                upComingFragment.refreshApi();
                break;
            case 1:
                pastBookingFragment.refreshApi();
                break;
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.up_coming);
            case 1:
                return mContext.getString(R.string.past);
            default:
                return null;
        }
    }
}
