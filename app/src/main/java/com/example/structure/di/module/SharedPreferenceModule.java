package com.example.structure.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.structure.utils.Constant;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
@Module
public class SharedPreferenceModule {
    //Provide SharedPreference for activity or fragment
    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences(Constant.SESSION_NAME,
                Context.MODE_PRIVATE);
    }
}
