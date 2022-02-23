package com.example.structure.di.module;

import android.app.Application;

import com.example.structure.data.daos.CreateAccountDao;
import com.example.structure.data.database.LocalDatabase;
import com.example.structure.data.repository.AddElectricVehicleRepository;
import com.example.structure.data.repository.ChangePasswordRepository;
import com.example.structure.data.repository.ControlEVSERepository;
import com.example.structure.data.repository.ControlRepository;
import com.example.structure.data.repository.CreateAccountRepository;
import com.example.structure.data.repository.DashboardRepository;
import com.example.structure.data.repository.LeaderboardRepository;
import com.example.structure.data.repository.MonitorRepository;
import com.example.structure.data.repository.NotificationRepository;
import com.example.structure.data.repository.PeronalInfoRepository;
import com.example.structure.data.repository.SessionInfoRepository;
import com.example.structure.data.repository.VehicleRepository;
import com.example.structure.retrofit.AddElectricVehicleWebService;
import com.example.structure.retrofit.ChangePasswordWebService;
import com.example.structure.retrofit.ControlEVSEWebService;
import com.example.structure.retrofit.ControlWebService;
import com.example.structure.retrofit.CreateAccountWebservice;
import com.example.structure.retrofit.DashboardWebService;
import com.example.structure.retrofit.LeaderboardWebservice;
import com.example.structure.retrofit.MonitorWebService;
import com.example.structure.retrofit.NotificationWebService;
import com.example.structure.retrofit.PeronalInfoWebService;
import com.example.structure.retrofit.SessionInfoWebService;
import com.example.structure.retrofit.VehicleWebService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajesh Pradeep G on 14-10-2019
 */
@Module
public class RoomModule {

    private LocalDatabase localDatabase;

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    LocalDatabase provideLocalDatabase(Application application) {
        return this.localDatabase = Room.databaseBuilder(application,
                LocalDatabase.class, "structure-db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    // Dashboard
    @Provides
    @Singleton
    DashboardRepository provideDashboardRepository(DashboardWebService dashboardWebService) {
        return new DashboardRepository(dashboardWebService);
    }

    // Leaderboard
    @Provides
    @Singleton
    LeaderboardRepository provideLeaderboardRepository(LeaderboardWebservice leaderboardWebservice) {
        return new LeaderboardRepository(leaderboardWebservice);
    }

    // Add Electric Vehicle
    @Provides
    @Singleton
    AddElectricVehicleRepository provideAddElectricVehicleRepository(AddElectricVehicleWebService addElectricVehicleWebService) {
        return new AddElectricVehicleRepository(addElectricVehicleWebService);
    }

    // Create account
    @Provides
    @Singleton
    CreateAccountRepository provideCreateAccountRepository(CreateAccountWebservice createAccountWebservice) {
        return new CreateAccountRepository(createAccountWebservice);
    }

    /**
     * We need the CreateAccountDao Module\
     */
    @Provides
    @Singleton
    CreateAccountDao provideCreateAccountDao(LocalDatabase localDatabase) {
        return localDatabase.getCreateAccountDao();
    }

    // Control
    @Provides
    @Singleton
    ControlRepository provideControlRepository(ControlWebService controlWebService) {
        return new ControlRepository(controlWebService);
    }

    // Control
    @Provides
    @Singleton
    VehicleRepository provideVehicleRepository(VehicleWebService vehicleWebService) {
        return new VehicleRepository(vehicleWebService);
    }

    // Personal Info
    @Provides
    @Singleton
    PeronalInfoRepository providePeronalInfoRepository(PeronalInfoWebService peronalInfoWebService) {
        return new PeronalInfoRepository(peronalInfoWebService);
    }

    // Notification
    @Provides
    @Singleton
    NotificationRepository provideNotificationRepository(NotificationWebService notificationWebService) {
        return new NotificationRepository(notificationWebService);
    }

    // Monitor
    @Provides
    @Singleton
    MonitorRepository provideMonitorRepository(MonitorWebService monitorWebService) {
        return new MonitorRepository(monitorWebService);
    }

    // Change Password
    @Provides
    @Singleton
    ChangePasswordRepository provideChangePasswordRepository(ChangePasswordWebService changePasswordWebService) {
        return new ChangePasswordRepository(changePasswordWebService);
    }

    // Session Info
    @Provides
    @Singleton
    SessionInfoRepository provideSessionInfoRepository(SessionInfoWebService sessionInfoWebService) {
        return new SessionInfoRepository(sessionInfoWebService);
    }

    // Control EVSE
    @Provides
    @Singleton
    ControlEVSERepository provideControlEVSERepository(ControlEVSEWebService controlEVSEWebService) {
        return new ControlEVSERepository(controlEVSEWebService);
    }
}
