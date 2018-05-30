package com.example.mithilesh.meeshoassignment.mvp.screen_main;

import com.example.mithilesh.meeshoassignment.mvp.BasePresenter;
import com.example.mithilesh.meeshoassignment.mvp.BaseView;
import com.example.mithilesh.meeshoassignment.mvp.model.BeanPullRequest;

import java.util.ArrayList;

public class MainContract {

    interface View extends BaseView<Presenter> {

        interface GetPullRequestDetail {
            void success(ArrayList<BeanPullRequest> list);

            void failed(int errorCode, String errorMsg);
        }
    }

    interface Presenter extends BasePresenter {
        void loadAllPullRequest(
                String ownerName,
                String repoName,
                View.GetPullRequestDetail callBack
        );
    }
}
