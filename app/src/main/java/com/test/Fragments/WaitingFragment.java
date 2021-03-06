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
import com.test.databinding.FragmentWaitingBinding;

import java.util.ArrayList;

public class WaitingFragment extends Fragment {
    FragmentWaitingBinding binding;

    public WaitingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_waiting, container, false);

        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> listItems = new ArrayList<>();
        listItems.add("وظيفه رقم 1");
        listItems.add("وظيفه رقم 2");
        listItems.add("وظيفه رقم 3");
        listItems.add("وظيفه رقم 4");
        listItems.add("وظيفه رقم 5");
        listItems.add("وظيفه رقم 6");
        binding.recyclerView.setAdapter(new BookingsAdapter(getContext(), listItems, false));
    }
}