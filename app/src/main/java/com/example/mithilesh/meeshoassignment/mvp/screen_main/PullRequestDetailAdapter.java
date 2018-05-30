package com.example.mithilesh.meeshoassignment.mvp.screen_main;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mithilesh.meeshoassignment.R;
import com.example.mithilesh.meeshoassignment.mvp.BaseViewHolder;
import com.example.mithilesh.meeshoassignment.mvp.listeners.OnItemClicked;
import com.example.mithilesh.meeshoassignment.mvp.model.BeanPullRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PullRequestDetailAdapter extends RecyclerView.Adapter<PullRequestDetailViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<BeanPullRequest> mListData = Collections.emptyList();

    private OnItemClicked mListener;

    public PullRequestDetailAdapter(Context context,
                                    ArrayList<BeanPullRequest> listPosts,
                                    OnItemClicked listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListData = listPosts;
        mListener = listener;

    }

    @Override
    public PullRequestDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_pull_requests, parent, false);
        return new PullRequestDetailViewHolder(mContext, convertView, mListener);
    }

    @Override
    public void onBindViewHolder(PullRequestDetailViewHolder holder, int position) {
        BeanPullRequest data = mListData.get(position);
        holder.apply(data, position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setListData(ArrayList<BeanPullRequest> data) {
        mListData = data;
    }
}


class PullRequestDetailViewHolder extends RecyclerView.ViewHolder implements BaseViewHolder<BeanPullRequest>, View.OnClickListener {

    private View mView;
    private int mPosition;
    private Context mContext;
    private BeanPullRequest mData;

    private OnItemClicked mListener;
    private TextView tvName;
    private TextView tvTitle;
    private ImageView ivProfile;
    private TextView tvNumber;
    private ProgressBar progress;

    public PullRequestDetailViewHolder(Context context, View itemView,
                                       OnItemClicked listener) {
        super(itemView);
        mView = itemView;
        mContext = context;
        mListener = listener;

        init();
    }
    public PullRequestDetailViewHolder(View itemView) {
        super(itemView);
    }

    private void init() {
        initView();
        initListener();
    }

    private void initView() {
        tvName = (TextView) mView.findViewById(R.id.tvName);
        tvTitle = (TextView) mView.findViewById(R.id.tvTitle);
        tvNumber = (TextView) mView.findViewById(R.id.tvNumber);
        progress = (ProgressBar) mView.findViewById(R.id.progress);
        ivProfile = (ImageView) mView.findViewById(R.id.ivUserImage);

    }

    private void initListener() {
    }

    @Override
    public void apply(BeanPullRequest data, int position) {
        mData = data;
        mPosition = position;

        if (mData.getUser() != null) {
            tvName.setText(String.valueOf(mData.getUser().getUserId()));
        }
        tvTitle.setText(String.valueOf(mData.getTitle()));
        tvNumber.setText(String.valueOf(String.valueOf(mData.getNumber())));
        if (mData.getUser() != null && !TextUtils.isEmpty(mData.getUser().getAvatarUrl())) {
            initProfileImage(mData.getUser().getAvatarUrl());
        }


    }

    private void initProfileImage(String url) {
        ImageLoader imageLoader = ImageLoader.getInstance();

        DisplayImageOptions options;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .resetViewBeforeLoading(true)
                    .build();
        } else {
            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .resetViewBeforeLoading(false)
                    .build();
        }

        imageLoader.displayImage(url, ivProfile, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progress.setVisibility(View.VISIBLE);
                ivProfile.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progress.setVisibility(View.GONE);
                ivProfile.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progress.setVisibility(View.GONE);
                ivProfile.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progress.setVisibility(View.GONE);
                ivProfile.setVisibility(View.GONE);
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {

            }
        });

    }

    @Override
    public void onClick(View view) {
    }
}
