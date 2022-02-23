package com.example.structure.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
@Module(includes = {RetrofitModule.class, RoomModule.class, SharedPreferenceModule.class})
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return application;
    }
}
