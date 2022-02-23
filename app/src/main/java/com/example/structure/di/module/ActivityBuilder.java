package com.example.structure.di.module;

import com.example.structure.ui.bottom_menu.BottomMenuActivity;
import com.example.structure.ui.login.LoginActivity;
import com.example.structure.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract BottomMenuActivity bindBottomMenuActivity();
}
