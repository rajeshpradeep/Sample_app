package com.example.structure.ui.base;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import androidx.fragment.app.Fragment;

import com.example.structure.di.component.ApplicationComponent;
import com.example.structure.di.component.DaggerApplicationComponent;
import com.example.structure.di.module.ApplicationModule;
import com.example.structure.utils.Constant;
import com.example.structure.utils.SharedPreferencesUtil;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
public
class MyApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector, HasServiceInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;
    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;
    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceInjector;
    private FirebaseCrashlytics crashlytics;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationComponent appComponent = DaggerApplicationComponent.builder()
                .application(this)
                .applicationModule(new ApplicationModule(this))
                .build();
        appComponent.inject(this);
        try {
            FirebaseApp.initializeApp(this);
            crashlytics = FirebaseCrashlytics.getInstance();
//        crashlytics.log("");
            crashlytics.setUserId(String.valueOf(sharedPreferencesUtil.getUserID(Constant.USER_ID)));
//        Fabric.with(this, crashlytics);
//        throw new RuntimeException("Test Crash"); // Force a crash
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }
}
