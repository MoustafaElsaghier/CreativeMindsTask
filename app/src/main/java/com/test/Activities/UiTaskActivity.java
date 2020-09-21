package com.test.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.tabs.TabLayout;
import com.test.Adapters.PagerAdapter;
import com.test.R;
import com.test.databinding.ActivityUiTaskBinding;

public class UiTaskActivity extends AppCompatActivity {

    ActivityUiTaskBinding binding;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ui_task);
        initViews();
    }

    private void initViews() {
        pagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        binding.pagerBooking.setAdapter(pagerAdapter);

        binding.slidingTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                binding.pagerBooking.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.slidingTabs.setupWithViewPager(binding.pagerBooking);
        binding.pagerBooking.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.slidingTabs));

    }
}