package com.test.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.test.Fragments.AcepptedFragment;
import com.test.Fragments.CancelledFragment;
import com.test.Fragments.WaitingFragment;
import com.test.R;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private WaitingFragment waitingFragment;
    private AcepptedFragment acepptedFragment;
    private CancelledFragment cancelledFragment;
    private Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        waitingFragment = new WaitingFragment();
        acepptedFragment = new AcepptedFragment();
        cancelledFragment = new CancelledFragment();
        mContext = context;
    }

    // This determines the Fragment for each tab
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return cancelledFragment;
        } else if (position == 1) {
            return acepptedFragment;
        } else {
            return waitingFragment;
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.cancel);
            case 1:
                return mContext.getString(R.string.accept);
            case 2:
                return mContext.getString(R.string.wait);
            default:
                return null;
        }
    }
}
