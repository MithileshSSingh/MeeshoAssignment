package com.example.mithilesh.meeshoassignment.di;

import android.content.Context;

import com.example.mithilesh.meeshoassignment.data.Repository;
import com.example.mithilesh.meeshoassignment.data.local.LocalDataSource;
import com.example.mithilesh.meeshoassignment.data.remote.RemoteDataSource;

public class RepositoryInjector {

    public static Repository provideRepository(Context context) {
        return Repository.getInstance(
                LocalDataSource.getInstance(context),
                RemoteDataSource.getInstance(context)
        );
    }
}
