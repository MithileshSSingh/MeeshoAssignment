package com.example.mithilesh.meeshoassignment.mvp.screen_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mithilesh.meeshoassignment.R;
import com.example.mithilesh.meeshoassignment.di.RepositoryInjector;
import com.example.mithilesh.meeshoassignment.mvp.BaseFragment;
import com.example.mithilesh.meeshoassignment.mvp.listeners.OnItemClicked;
import com.example.mithilesh.meeshoassignment.mvp.model.BeanPullRequest;
import com.example.mithilesh.meeshoassignment.mvp.model.User;
import com.example.mithilesh.meeshoassignment.utils.ActivityUtils;

import java.util.ArrayList;


public class MainFragment extends BaseFragment implements MainContract.View, OnItemClicked, View.OnClickListener {

    public static final String TAG = MainFragment.class.getSimpleName();

    private MainContract.Presenter mPresenter;
    private User data;


    private LinearLayoutManager mLayoutManagerRV;
    private PullRequestDetailAdapter mAdapterPullRequestDetail;
    private ArrayList<BeanPullRequest> mListData = new ArrayList<>();
    private EditText etOwnerName;
    private EditText etRepoName;
    private ProgressBar progressBar;
    private Button btnGo;
    private RecyclerView rvPullReqeustDetail;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    protected void init() {
        initView();
        initMembers();
        initListeners();
        initData();
    }

    @Override
    protected void initView() {
        etOwnerName = (EditText) mView.findViewById(R.id.etOwnerName);
        etRepoName = (EditText) mView.findViewById(R.id.etRepoName);

        btnGo = (Button) mView.findViewById(R.id.btnGo);
        progressBar = (ProgressBar) mView.findViewById(R.id.progress);

        rvPullReqeustDetail = (RecyclerView) mView.findViewById(R.id.rvPullRequests);

    }

    @Override
    protected void initMembers() {
        mPresenter = new MainPresenter(RepositoryInjector.provideRepository(mActivity), this);
        initRecyclerView();
    }

    @Override
    protected void initListeners() {
        btnGo.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    private void initRecyclerView() {
        mAdapterPullRequestDetail = new PullRequestDetailAdapter(mActivity, mListData, this);
        mLayoutManagerRV = new LinearLayoutManager(
                mActivity.getApplicationContext()
        );
        RecyclerView.ItemAnimator itemAnimatorVertical = new DefaultItemAnimator();

        rvPullReqeustDetail.setHasFixedSize(true);
        rvPullReqeustDetail.setLayoutManager(mLayoutManagerRV);
        rvPullReqeustDetail.setItemAnimator(itemAnimatorVertical);
        rvPullReqeustDetail.setAdapter(mAdapterPullRequestDetail);

        mAdapterPullRequestDetail.notifyDataSetChanged();

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading(String title, String message) {

    }

    @Override
    public void onItemClicked(View v, int position) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGo:
                loadData();
                break;
        }
    }

    private void loadData() {
        String ownerName = etOwnerName.getText().toString();
        String repoName = etRepoName.getText().toString();

        if (TextUtils.isEmpty(ownerName)) {
            Toast.makeText(mActivity.getApplicationContext(), "Please Enter Owner Name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(repoName)) {
            Toast.makeText(mActivity.getApplicationContext(), "Please Enter Repository Name", Toast.LENGTH_SHORT).show();
            return;
        }

        ActivityUtils.hideKeyBoard(btnGo, getActivity());

        progressBar.setVisibility(View.VISIBLE);
        rvPullReqeustDetail.setVisibility(View.GONE);
        mPresenter.loadAllPullRequest(
                ownerName,
                repoName,
                new GetPullRequestDetail() {
                    @Override
                    public void success(ArrayList<BeanPullRequest> list) {
                        mListData = list;
                        mAdapterPullRequestDetail.setListData(mListData);
                        mAdapterPullRequestDetail.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        rvPullReqeustDetail.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void failed(int errorCode, String errorMsg) {
                        Toast.makeText(mActivity.getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);
                        rvPullReqeustDetail.setVisibility(View.VISIBLE);
                    }
                }
        );
    }
}
