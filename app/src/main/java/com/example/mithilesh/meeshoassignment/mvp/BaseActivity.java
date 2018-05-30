package com.example.mithilesh.meeshoassignment.mvp;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.mithilesh.meeshoassignment.R;
import com.example.mithilesh.meeshoassignment.mvp.screen_main.MainFragment;
import com.example.mithilesh.meeshoassignment.utils.ActivityUtils;
import com.example.mithilesh.meeshoassignment.utils.AppConstants;


public abstract class BaseActivity extends AppCompatActivity {

    public static String TAG = "TAG";
    public Resources mResources;
    public Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mActivity = this;
        mResources = this.getResources();
    }


    public void showFragment(int screenId, Bundle data) {

        String tag = null;
        BaseFragment fragment = null;
        boolean addToBackStack = false;

        switch (screenId) {
            case AppConstants.Screens.SCREEN_MAIN:

                fragment = MainFragment.newInstance();
                tag = MainFragment.TAG;

                break;
            default:
                break;
        }

        if (fragment != null && tag != null) {
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    fragment, R.id.content,
                    tag, addToBackStack);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected abstract void init();

    protected abstract void initView();

    protected abstract void initMembers();

    protected abstract void initListeners();

    protected abstract void initData();

}
