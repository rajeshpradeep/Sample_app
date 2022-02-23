package com.example.structure.di.module;

import com.example.structure.retrofit.AddElectricVehicleWebService;
import com.example.structure.retrofit.ChangePasswordWebService;
import com.example.structure.retrofit.ControlEVSEWebService;
import com.example.structure.retrofit.ControlWebService;
import com.example.structure.retrofit.CreateAccountWebservice;
import com.example.structure.retrofit.DashboardWebService;
import com.example.structure.retrofit.LeaderboardWebservice;
import com.example.structure.retrofit.LoginWebService;
import com.example.structure.retrofit.MonitorWebService;
import com.example.structure.retrofit.NotificationWebService;
import com.example.structure.retrofit.PeronalInfoWebService;
import com.example.structure.retrofit.SessionInfoWebService;
import com.example.structure.retrofit.VehicleWebService;
import com.example.structure.utils.Constant;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
@Module
public class RetrofitModule {

//    private boolean isInTestMode = true;

    /*private String getApiUrl() {
        if (isInTestMode) {
            Constant.API_POINTING_TO_TEST = true;
            return Constant.BASE_URL_DEV;
        } else {
            Constant.API_POINTING_TO_TEST = false;
            return Constant.BASE_URL_PRODUCTION;
        }
    }*/

    @Provides
    @Singleton
    Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient getOkHttpCleint(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
//                .addInterceptor(new BasicAuthInterceptor("dev", "vjetWcoh"))
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    //Login
    @Provides
    @Singleton
    LoginWebService getLoginWebService(Retrofit retroFit) {
        return retroFit.create(LoginWebService.class);
    }

    //Dashboard
    @Provides
    @Singleton
    DashboardWebService getDashboardWebService(Retrofit retroFit) {
        return retroFit.create(DashboardWebService.class);
    }

    // Leaderboard
    @Provides
    @Singleton
    LeaderboardWebservice getLeaderboardWebservice(Retrofit retroFit) {
        return retroFit.create(LeaderboardWebservice.class);
    }

    // Add Electric Vehicle
    @Provides
    @Singleton
    AddElectricVehicleWebService getAddElectricVehicleWebService(Retrofit retroFit) {
        return retroFit.create(AddElectricVehicleWebService.class);
    }

    // Create account
    @Provides
    @Singleton
    CreateAccountWebservice getCreateAccountWebservice(Retrofit retroFit) {
        return retroFit.create(CreateAccountWebservice.class);
    }

    // Control
    @Provides
    @Singleton
    ControlWebService getControlWebService(Retrofit retroFit) {
        return retroFit.create(ControlWebService.class);
    }

    // Add Vehicle
    @Provides
    @Singleton
    VehicleWebService getVehicleWebService(Retrofit retroFit) {
        return retroFit.create(VehicleWebService.class);
    }

    // Peronal Info
    @Provides
    @Singleton
    PeronalInfoWebService getPeronalInfoWebService(Retrofit retroFit) {
        return retroFit.create(PeronalInfoWebService.class);
    }

    // Notification
    @Provides
    @Singleton
    NotificationWebService getNotificationWebService(Retrofit retroFit) {
        return retroFit.create(NotificationWebService.class);
    }

    // Notification
    @Provides
    @Singleton
    MonitorWebService getMonitorWebService(Retrofit retroFit) {
        return retroFit.create(MonitorWebService.class);
    }

    // Change Password
    @Provides
    @Singleton
    ChangePasswordWebService getChangePasswordWebService(Retrofit retroFit) {
        return retroFit.create(ChangePasswordWebService.class);
    }

    // Session Info
    @Provides
    @Singleton
    SessionInfoWebService getSessionInfoWebService(Retrofit retroFit) {
        return retroFit.create(SessionInfoWebService.class);
    }

    // Control EVSE
    @Provides
    @Singleton
    ControlEVSEWebService getControlEVSEWebService(Retrofit retroFit) {
        return retroFit.create(ControlEVSEWebService.class);
    }
}
