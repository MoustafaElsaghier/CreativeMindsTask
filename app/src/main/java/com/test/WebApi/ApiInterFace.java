package com.test.WebApi;

import com.test.Models.RepoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterFace {

    @GET("repos")
    Call<List<RepoItem>> getListOfRepos(@Query("page") int page,
                                        @Query("per_page") int perPage,
                                        @Query("access_token") String accessToken);

}
