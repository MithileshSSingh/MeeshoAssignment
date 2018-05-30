package com.example.mithilesh.meeshoassignment.data.remote;

import com.example.mithilesh.meeshoassignment.mvp.model.BeanPullRequest;
import com.example.mithilesh.meeshoassignment.mvp.model.User;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public class ApiClient {

    public interface APICalls {

        @GET(ServiceType.GET_ALL_PULL_REQUEST)
        Call<ArrayList<BeanPullRequest>> getAllPullRequests(
                @Path("owner_name") String ownerName,
                @Path("repo_name") String repoName);

    }

    public static class ServiceType {
        public static final String BASE_URL = "https://api.github.com";

        public static final String GET_ALL_PULL_REQUEST = BASE_URL + "/repos/{owner_name}/{repo_name}/pulls";
    }

    public static class HttpErrorCode {
        public static final Integer NO_CODE = 000;
    }

}
