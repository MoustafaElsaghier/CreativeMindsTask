package com.test.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.test.Adapters.BookingsAdapter;
import com.test.R;
import com.test.databinding.FragmentAccepptedBinding;

import java.util.ArrayList;

public class AcepptedFragment extends Fragment {

    FragmentAccepptedBinding binding;

    public AcepptedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_acceppted, container, false);

        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> listItems = new ArrayList<>();
        listItems.add("وظيفه مقبوله رقم 1");
        listItems.add("وظيفه مقبوله رقم 2");
        listItems.add("وظيفه مقبوله رقم 3");
        listItems.add("وظيفه مقبوله رقم 4");
        listItems.add("وظيفه مقبوله رقم 5");
        listItems.add("وظيفه مقبوله رقم 6");
        listItems.add("وظيفه مقبوله رقم 7");
        listItems.add("وظيفه مقبوله رقم 8");
        listItems.add("وظيفه مقبوله رقم 9");
        listItems.add("وظيفه مقبوله رقم 10");
        listItems.add("وظيفه مقبوله رقم 11");
        listItems.add("وظيفه مقبوله رقم 12");
        binding.recyclerView.setAdapter(new BookingsAdapter(getContext(), listItems, false));
    }
}