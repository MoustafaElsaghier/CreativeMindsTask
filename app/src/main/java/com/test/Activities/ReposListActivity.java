package com.test.Activities;

import android.os.Bundle;
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
        binding.recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (page > 1)
                    loadData(page);
            }
        });
    }

    private void loadData(int page) {
        ApiInterFace apiService =
                ApiClient.getClient();
        Call<List<RepoItem>> listOfRepos = apiService.getListOfRepos(page, PAGE_SIZE);
        listOfRepos.enqueue(new Callback<List<RepoItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<RepoItem>> call, @NonNull Response<List<RepoItem>> response) {
                if (response.isSuccessful()) {
                    List<RepoItem> body = response.body();
                    if (body != null) {
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