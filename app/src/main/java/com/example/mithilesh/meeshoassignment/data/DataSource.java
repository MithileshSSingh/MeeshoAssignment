package com.example.mithilesh.meeshoassignment.data;

import com.example.mithilesh.meeshoassignment.mvp.model.BeanPullRequest;

import java.util.ArrayList;

public interface DataSource {


    void loadAllPullRequest(
            String ownerName,
            String repoName,
            GetPullRequestDetail callBack
    );

    interface GetPullRequestDetail {
        void success(ArrayList<BeanPullRequest> list);

        void failed(int errorCode, String errorMsg);
    }
}
