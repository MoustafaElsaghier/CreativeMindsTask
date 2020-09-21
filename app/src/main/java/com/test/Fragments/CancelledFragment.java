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
import com.test.databinding.FragmentCancelledBinding;

import java.util.ArrayList;

public class CancelledFragment extends Fragment {

    FragmentCancelledBinding binding;

    public CancelledFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cancelled, container, false);

        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> listItems = new ArrayList<>();
        listItems.add("وظيفه ملغيه رقم 1");
        listItems.add("وظيفه ملغيه رقم 2");
        listItems.add("وظيفه ملغيه رقم 3");
        listItems.add("وظيفه ملغيه رقم 4");
        listItems.add("وظيفه ملغيه رقم 5");
        listItems.add("وظيفه ملغيه رقم 6");
        listItems.add("وظيفه ملغيه رقم 7");
        binding.recyclerView.setAdapter(new BookingsAdapter(getContext(), listItems, true));
    }
}