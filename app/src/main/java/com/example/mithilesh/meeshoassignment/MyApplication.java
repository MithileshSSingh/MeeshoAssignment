package com.example.mithilesh.meeshoassignment;

import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MyApplication extends Application {
    private DisplayImageOptions mDefaultOptions;
    private ImageLoaderConfiguration mConfig;

    @Override
    public void onCreate() {
        super.onCreate();

        initServices();
    }

    private void initServices() {
        initUniversalImageLoader();
    }

    private void initUniversalImageLoader() {
        // UNIVERSAL IMAGE LOADER SETUP
        mDefaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        mConfig = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(mDefaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(mConfig);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}
