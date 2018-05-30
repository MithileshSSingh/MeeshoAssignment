package com.example.mithilesh.meeshoassignment.data.local;

import android.content.Context;

import com.example.mithilesh.meeshoassignment.data.DataSource;
import com.example.mithilesh.meeshoassignment.mvp.model.User;

public class LocalDataSource implements DataSource {
    private static LocalDataSource ourInstance = new LocalDataSource();

    private LocalDataSource() {
    }

    public static LocalDataSource getInstance(Context context) {
        return ourInstance;
    }

    @Override
    public void loadAllPullRequest(String ownerName, String repoName, GetPullRequestDetail callBack) {

    }
}
