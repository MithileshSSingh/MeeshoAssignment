package com.example.mithilesh.meeshoassignment.mvp.screen_main;

import com.example.mithilesh.meeshoassignment.data.DataSource;
import com.example.mithilesh.meeshoassignment.data.Repository;
import com.example.mithilesh.meeshoassignment.mvp.model.BeanPullRequest;
import com.example.mithilesh.meeshoassignment.mvp.model.User;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {

    private Repository mRepository = null;
    private MainContract.View mView = null;

    private MainPresenter() {
    }

    public MainPresenter(Repository repository, MainContract.View view) {

        mRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void loadAllPullRequest(
            String ownerName,
            String repoName,
            final MainContract.View.GetPullRequestDetail callBack) {

        mRepository.loadAllPullRequest(
                ownerName,
                repoName,
                new DataSource.GetPullRequestDetail() {
                    @Override
                    public void success(ArrayList<BeanPullRequest> list) {
                        callBack.success(list);
                    }

                    @Override
                    public void failed(int errorCode, String errorMsg) {
                        callBack.failed(errorCode,errorMsg);
                    }
                }
        );
    }
}
