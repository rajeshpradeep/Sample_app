package com.example.structure.di.module;

import com.example.structure.support.MyFirebaseMessagingService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Rajesh Pradeep G on 23-01-2020
 */
@Module
public abstract class ServiceBuilderModule {

    // for my case, the service class which needs injection is MyFirebaseMessagingService
    @ContributesAndroidInjector
    abstract MyFirebaseMessagingService contributeMyFirebaseMessagingService();
}
