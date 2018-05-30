package com.example.mithilesh.meeshoassignment.data;

import com.example.mithilesh.meeshoassignment.mvp.model.BeanPullRequest;

import java.util.ArrayList;

public class Repository implements DataSource {


    private static Repository INSTANCE = null;

    private DataSource mLocalDataSource = null;
    private DataSource mRemoteDataSource = null;

    private Repository() {

    }

    private Repository(DataSource localDataSource, DataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static Repository getInstance(DataSource localDataSource, DataSource remoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new Repository(localDataSource, remoteDataSource);
        }

        return INSTANCE;
    }

    @Override
    public void loadAllPullRequest(
            String ownerName,
            String repoName,
            final GetPullRequestDetail callBack) {

        mRemoteDataSource.loadAllPullRequest(
                ownerName,
                repoName,
                new GetPullRequestDetail() {
                    @Override
                    public void success(ArrayList<BeanPullRequest> list) {
                        callBack.success(list);
                    }

                    @Override
                    public void failed(int errorCode, String errorMsg) {
                        callBack.failed(errorCode, errorMsg);
                    }
                }
        );
    }
}