package com.test.WebApi;

import com.test.Models.RepoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterFace {

    @GET("repos")
    Call<List<RepoItem>> getListOfRepos();

}
