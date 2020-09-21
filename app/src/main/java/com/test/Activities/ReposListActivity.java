package com.test.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.Adapters.ReposAdapter;
import com.test.Models.RepoItem;
import com.test.R;
import com.test.Utilities.EndlessRecyclerViewScrollListener;
import com.test.WebApi.ApiClient;
import com.test.WebApi.ApiInterFace;
import com.test.databinding.ActivityReposListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposListActivity extends AppCompatActivity {

    private static final int PAGE_SIZE = 10;
    private static final String ACCESS_TOKEN = "a43e1d4f617c876d46d34377265c3e0454bc64a3";
    ActivityReposListBinding binding;
    ReposAdapter reposAdapter;
    ArrayList<RepoItem> listOfData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repos_list);
        initViews();
        loadData(1);
    }

    private void initViews() {
        listOfData = new ArrayList<>();
        reposAdapter = new ReposAdapter(this, listOfData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        binding.recyclerView.setAdapter(reposAdapter);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (page > 1)
                    loadData(page);
            }
        };
        binding.recyclerView.addOnScrollListener(scrollListener);

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            listOfData.clear();
            // to make listener reset it's state and back to page 1 again
            // init page = 1 if pages are 1 based index & 0 if it's 0 based index
            scrollListener.resetState();
            // to reset while swipe to refresh
            binding.filterEdit.setText("");
            loadData(1);

        });
        binding.filterEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                reposAdapter.getFilter().filter(s.toString());
            }
        });
    }

    private void loadData(int page) {
        ApiInterFace apiService =
                ApiClient.getClient();
        Call<List<RepoItem>> listOfRepos = apiService.getListOfRepos(page, PAGE_SIZE, ACCESS_TOKEN);
        listOfRepos.enqueue(new Callback<List<RepoItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<RepoItem>> call, @NonNull Response<List<RepoItem>> response) {
                if (response.isSuccessful()) {
                    List<RepoItem> body = response.body();
                    if (body != null) {
                        if (binding.swipeToRefresh.isRefreshing())
                            binding.swipeToRefresh.setRefreshing(false);
                        listOfData.addAll(body);
                        reposAdapter.notifyItemRangeInserted(reposAdapter.getItemCount(), body.size());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RepoItem>> call, @NonNull Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

}